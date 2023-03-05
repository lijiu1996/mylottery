package com.lijiawei.lottery.common.util;

@FunctionalInterface
public interface CopyCallBack<S,T>  {

    void callBack(S s, T t);
}
