package com.jiebai.framework.common.cglib;

import com.google.common.base.Function;
import com.google.common.collect.Lists;

import java.util.List;
import java.util.Objects;

/**
 * PageInfo拷贝工具类
 *
 * @author lizhihui
 * @version 1.0.0
 */
public class ListCopier<F, T> extends BaseBeanCopier<F, T> {

    public static <F, T> List<T> transform(List<F> sourceList,
        Function<? super F, ? extends T> function) {
        List<T> targetList = Objects.nonNull(sourceList) ?
            Lists.transform(sourceList, function) :
            Lists.newArrayList();
        return Lists.newArrayList(targetList);
    }

}
