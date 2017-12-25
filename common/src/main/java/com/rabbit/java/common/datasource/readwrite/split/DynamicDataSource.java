package com.rabbit.java.common.datasource.readwrite.split;

import com.rabbit.java.common.enums.MasterSlave;
import com.rabbit.java.common.log.MyLogger;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.util.ReflectionUtils;

import javax.sql.DataSource;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Mr.Rabbit
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

    /**
     * 轮询计算器阈值
     */
    private static final int COUNTER_THRESHOLD = 9999;
    /**
     * 轮询计算初始值
     */
    private static final int COUNTER_DEFAULT = -1;

    /**
     * 从库数量
     */
    private Integer slaveCount;


    /**
     * 轮询计数,初始为-1,AtomicInteger是线程安全的
     */
    private AtomicInteger counter = new AtomicInteger(-1);

    /**
     * 记录读库的key
     */
    private List<Object> slaveDataSources = new ArrayList<Object>(0);

    @Override
    protected Object determineCurrentLookupKey() {
        // 使用DynamicDataSourceHolder保证线程安全，并且得到当前线程中的数据源key
        if (DynamicDataSourceHolder.isMaster()) {
            Object key = DynamicDataSourceHolder.getDataSourceKey();
            MyLogger.inf(DynamicDataSource.class, "当前DataSource为 : {}", key.toString());
            return key;
        }
        Object key = getSlaveKey();
        MyLogger.inf(DynamicDataSource.class, "当前DataSource为 : {}", key.toString());
        return key;
//        return DynamicDataSourceHolder.getDataSourceKey();
    }

    @Override
    public void afterPropertiesSet() {
        super.afterPropertiesSet();
        // 由于父类的resolvedDataSources属性是私有的子类获取不到，需要使用反射获取
        Field field = ReflectionUtils.findField(AbstractRoutingDataSource.class, "resolvedDataSources");
        // 设置可访问
        field.setAccessible(true);

        try {
            Map<Object, DataSource> resolvedDataSources = (Map<Object, DataSource>) field.get(this);
            // 读库的数据量等于数据源总数减去写库的数量
            this.slaveCount = resolvedDataSources.size() - 1;
            for (Map.Entry<Object, DataSource> entry : resolvedDataSources.entrySet()) {
                if (MasterSlave.MASTER.equals(entry.getKey())) {
                    continue;
                }
                slaveDataSources.add(entry.getKey());
            }
        } catch (Exception e) {
            MyLogger.err("method : {},err : {})", "afterPropertiesSet", e);
        }
    }

    /**
     * 轮询算法,实现写库的负责均衡
     *
     * @return
     */
    public Object getSlaveKey() {
        // 得到的下标为：0、1、2、3……
        Integer index = counter.incrementAndGet() % slaveCount;
        // 以免超出Integer范围
        if (counter.get() > COUNTER_THRESHOLD) {
            // 还原
            counter.set(COUNTER_DEFAULT);
        }
        return slaveDataSources.get(index);
    }
}

