package com.kk.common.service;

/**
 * @ClassName Function
 * @Author Administrator
 * @Param
 * @Return
 * @Date 2018/5/6 15:24
 */
public interface Function<T , E> {

    public T callback(E e);
}
