package com.lijiawei.lottery.common.util;

import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DeepBeanUtilTest {

    @Test
    void copyListProperties() {
        User li = new User(1, "li", 1);
        User l2 = new User(2,"l2",2);
        List<User> of = List.of(li, l2);
        List<UserDO> userDOS = DeepBeanUtil.copyListProperties(of, UserDO::new);
        System.out.println(userDOS);
    }

    @Test
    void testCopyListProperties() {
        User li = new User(1, "li", 1);
        User l2 = new User(2,"l2",2);
        List<User> of = List.of(li, l2);
        List<UserDO> userDOS = DeepBeanUtil.copyListProperties(of, UserDO::new, (s,t) -> {
            t.setSex(SexEnum.getById(s.getSexId()).getName());
        });
        System.out.println(userDOS);
    }
}