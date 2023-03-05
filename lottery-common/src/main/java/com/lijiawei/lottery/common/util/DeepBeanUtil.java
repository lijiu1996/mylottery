package com.lijiawei.lottery.common.util;

import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

/**
 * 扩展BeanUtils实现集合拷贝
 */
public class DeepBeanUtil extends BeanUtils {

    public static <S,T> List<T> copyListProperties(List<S> source, Supplier<T> target) {
        return copyListProperties(source,target,null);
    }

    public static <S,T> List<T> copyListProperties(List<S> source, Supplier<T> target, CopyCallBack<S,T> callBack) {
        List<T> list = new ArrayList<>(source.size());
        for (S s : source) {
            T t = target.get();
            copyProperties(s,t);
            if (callBack != null) {
               callBack.callBack(s,t);
            }
            list.add(t);
        }
        return list;
    }
}
