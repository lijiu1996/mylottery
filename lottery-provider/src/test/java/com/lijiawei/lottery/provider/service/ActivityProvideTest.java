package com.lijiawei.lottery.provider.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lijiawei.lottery.data.entity.ActivityEntity;
import com.lijiawei.lottery.data.service.ActivityService;
import com.lijiawei.lottery.provider.ProviderApp;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;

import javax.annotation.Resource;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
class ActivityProvideTest {

    @Resource
    private ActivityService activityService;

    @Test
    public void test_insert() {
        ActivityEntity activity = new ActivityEntity();
        activity.setActivityId(100001L);
        activity.setActivityName("测试活动");
        activity.setActivityDesc("仅用于插入数据测试");
        activity.setBeginDateTime(new Date());
        activity.setEndDateTime(new Date());
        activity.setStockCount(100);
        activity.setTakeCount(10);
        activity.setState(0);
        activity.setCreator("xiaofuge");
        activityService.save(activity);
    }

    @Test
    public void test_select() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        ActivityEntity activity = activityService.getByActivityId(100001L);
        log.info("测试结果：{}", objectMapper.writeValueAsString(activity));
    }


}