package com.jiebai.framework.common.cglib;

import com.github.pagehelper.PageInfo;
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
public class PageInfoCopier<F, T> extends BaseBeanCopier<F, T> {

    private static final net.sf.cglib.beans.BeanCopier pageInfoCopier =
        net.sf.cglib.beans.BeanCopier.create(PageInfo.class, PageInfo.class, false);

    public static <F, T> PageInfo<T> transform(PageInfo<F> sourcePageInfo, Function<? super F, ? extends T> function) {
        List<T> targetList = Objects.nonNull(sourcePageInfo.getList()) ?
            Lists.transform(sourcePageInfo.getList(), function) :
            Lists.newArrayList();
        PageInfo<T> targetPageInfo = new PageInfo<>();
        pageInfoCopier.copy(sourcePageInfo, targetPageInfo, null);
        targetPageInfo.setList(targetList);
        return targetPageInfo;
    }


}
