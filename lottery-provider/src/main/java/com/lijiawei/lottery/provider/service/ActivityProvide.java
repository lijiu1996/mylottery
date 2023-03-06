package com.lijiawei.lottery.provider.service;

import com.lijiawei.lottery.api.ActivityApi;
import com.lijiawei.lottery.api.dto.ActivityDTO;
import com.lijiawei.lottery.data.entity.ActivityEntity;
import com.lijiawei.lottery.data.service.ActivityService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ActivityProvide implements ActivityApi {

    @Resource
    private ActivityService activityService;

    @Override
    public ActivityDTO getById(Long id) {
        ActivityEntity activityEntity = activityService.getById(id);
        ActivityDTO activityDTO = new ActivityDTO();
        BeanUtils.copyProperties(activityEntity,activityDTO);
        return activityDTO;
    }


}
