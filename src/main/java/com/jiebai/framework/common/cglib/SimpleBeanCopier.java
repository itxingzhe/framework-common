package com.jiebai.framework.common.cglib;

/**
 * 普通bean拷贝工具类
 *
 * @author lizhihui
 * @version 1.0.0
 */
public class SimpleBeanCopier<F, T> extends BaseBeanCopier<F, T> {

    public SimpleBeanCopier(Class<F> sourceClass, Class<T> targetClass) {
        setSourceClass(sourceClass);
        setTargetClass(targetClass);
        init();
    }

}
