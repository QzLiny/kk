package com.kk.manage.datasource;

/**
 * @ClassName DynamicDataSourceHolder 使用ThreadLocal技术来记录当前线程中的数据源的key
 * @Author Administrator
 * @Param
 * @Return
 * @Date 2018/5/13 15:39
 */
public class DynamicDataSourceHolder {

    /**
     *     写库对应的数据源key
     */
    private static final String MASTER = "master";

    /**
     * 读库对应的数据源key
     */
    private static final String SLAVE = "slave";

    /**
     *  使用ThreadLocal记录当前线程的数据源key
     */
    private static final ThreadLocal<String> holder = new ThreadLocal<String>();

    /**
     * 设置数据源key
     * @param key
     */
    public static void putDataSourceKey(String key) {
        holder.set(key);
    }

    /**
     * 获取数据源key
     * @return
     */
    public static String getDataSourceKey() {
        return holder.get();
    }
    
    /**
     * 标记写库
     */
    public static void markMaster(){
        putDataSourceKey(MASTER);
    }
    
    /**
     * 标记读库
     */
    public static void markSlave(){
        putDataSourceKey(SLAVE);
    }

}
