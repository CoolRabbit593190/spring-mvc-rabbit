package com.rabbit.java.common.serialization.filter;//package com.rabbit.ssm.common.serialization;
//
//import com.alibaba.fastjson.serializer.PropertyFilter;
//import org.hibernate.collection.spi.PersistentCollection;
//import org.hibernate.proxy.HibernateProxy;
//import org.hibernate.proxy.LazyInitializer;
//import org.springframework.stereotype.Component;
//
///**
// * 自定义fastjson序列化过滤器
// * <p>
// * 用于过滤未初始化代理对象
// */
//@Component("serializeFilter")
//public class SimplePropertyFilter implements PropertyFilter {
//
//    public boolean apply(Object object, String name, Object value) {
//        if (value instanceof HibernateProxy) {//hibernate代理对象
//            LazyInitializer initializer = ((HibernateProxy) value).getHibernateLazyInitializer();
//            if (initializer.isUninitialized()) {
//                return false;
//            }
//        } else if (value instanceof PersistentCollection) {//实体关联集合一对多等
//            PersistentCollection collection = (PersistentCollection) value;
//            if (!collection.wasInitialized()) {
//                return false;
//            }
//            Object val = collection.getValue();
//            if (null == val) {//集合为null
//                return false;
//            }
//        }
//        return true;
//    }
//}
//
