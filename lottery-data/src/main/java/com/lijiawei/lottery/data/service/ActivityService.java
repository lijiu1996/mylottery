package com.lijiawei.lottery.data.service;

import com.lijiawei.lottery.data.entity.ActivityEntity;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author lijiawei
* @description 针对表【activity(活动配置)】的数据库操作Service
* @createDate 2023-03-06 00:43:49
*/
public interface ActivityService extends IService<ActivityEntity> {

    ActivityEntity getByActivityId(Long id);

}
