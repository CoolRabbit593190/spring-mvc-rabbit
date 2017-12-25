package com.rabbit.java.common.datasource.readwrite.split;


import com.rabbit.java.common.enums.MasterSlave;

/**
 * @author Mr.Rabbit
 */
public class DynamicDataSourceHolder {


    /**
     * 使用ThreadLocal记录当前线程的数据源key,线程安全考虑
     */
    private static final ThreadLocal<MasterSlave> holder = new ThreadLocal<MasterSlave>();

    /**
     * 设置数据源key
     *
     * @param key
     */
    public static void putDataSourceKey(MasterSlave key) {
        holder.set(key);
    }

    /**
     * 获取数据源key
     *
     * @return
     */
    public static MasterSlave getDataSourceKey() {
        return holder.get();
    }

    /**
     * 标记写库
     */
    public static void markMaster() {
        putDataSourceKey(MasterSlave.MASTER);
    }

    /**
     * 标记读库
     */
    public static void markSlave() {
        putDataSourceKey(MasterSlave.SLAVE);
    }


    /**
     * 判断当前数据库状态
     */
    public static Boolean isMaster() {
        if (MasterSlave.MASTER.equals(getDataSourceKey())) {
            return true;
        }
        return false;
    }
}
