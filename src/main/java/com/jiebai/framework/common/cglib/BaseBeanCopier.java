package com.jiebai.framework.common.cglib;

import com.google.common.base.Function;

import java.util.Objects;

/**
 * bean拷贝基类
 *
 * @author lizhihui
 * @version 1.0.0
 */
public class BaseBeanCopier<F, T> implements Function<F, T> {

    private net.sf.cglib.beans.BeanCopier beanCopier;

    protected net.sf.cglib.beans.BeanCopier getBeanCopier() {
        return beanCopier;
    }

    protected void init() {
        this.beanCopier = net.sf.cglib.beans.BeanCopier.create(sourceClass, targetClass, false);
    }

    private Class<T> targetClass;

    private Class<F> sourceClass;

    private BaseBeanCopier<T, F> reverse;

    public BaseBeanCopier<T, F> reverse() {
        return reverse;
    }

    public Function<T, F> getReverse() {
        if (this.reverse != null) {
            return this.reverse;
        }
        BaseBeanCopier<T, F> reverse = this.reverse;
        synchronized (this) {
            if (reverse == null) {
                reverse = new BaseBeanCopier<T, F>();
                reverse.setSourceClass(this.getTargetClass());
                reverse.setTargetClass(this.getSourceClass());
                reverse.init();
            }
        }
        return reverse;
    }

    public void setReverse(BaseBeanCopier<T, F> reverse) {
        this.reverse = reverse;
    }

    protected Class<T> getTargetClass() {
        return targetClass;
    }

    protected Class<F> getSourceClass() {
        return sourceClass;
    }

    public void setTargetClass(Class<T> targetClass) {
        this.targetClass = targetClass;
    }

    public void setSourceClass(Class<F> sourceClass) {
        this.sourceClass = sourceClass;
    }

    public T afterCopy(F source, T target) {
        return target;
    }

    public T copy(F input) {
        try {
            if (Objects.isNull(input)) {
                return null;
            }
            T o = targetClass.newInstance();
            beanCopier.copy(input, o, null);
            return afterCopy(input, o);
        } catch (Exception e) {
            throw new RuntimeException("create object fail, class:" + targetClass.getName() + " ", e);
        }
    }

    @Override
    public T apply(F input) {
        return copy(input);
    }
}
