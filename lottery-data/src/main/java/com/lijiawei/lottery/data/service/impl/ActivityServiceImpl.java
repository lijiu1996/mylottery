package com.lijiawei.lottery.data.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lijiawei.lottery.data.entity.ActivityEntity;
import com.lijiawei.lottery.data.service.ActivityService;
import com.lijiawei.lottery.data.mapper.ActivityMapper;
import org.springframework.stereotype.Service;

/**
* @author lijiawei
* @description 针对表【activity(活动配置)】的数据库操作Service实现
* @createDate 2023-03-06 00:43:49
*/
@Service
public class ActivityServiceImpl extends ServiceImpl<ActivityMapper, ActivityEntity>
    implements ActivityService{

    @Override
    public ActivityEntity getByActivityId(Long id) {
        LambdaQueryWrapper<ActivityEntity> lqw = new LambdaQueryWrapper<>();
        lqw.eq(ActivityEntity::getActivityId,id);
        return getOne(lqw,false);
    }
}




