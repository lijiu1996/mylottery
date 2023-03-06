package com.lijiawei.lottery.provider.service;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lijiawei.lottery.api.ActivityApi;
import com.lijiawei.lottery.api.dto.ActivityDTO;
import com.lijiawei.lottery.api.request.ActivityPageRequest;
import com.lijiawei.lottery.common.util.DeepBeanUtil;
import com.lijiawei.lottery.data.entity.ActivityEntity;
import com.lijiawei.lottery.data.service.ActivityService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ActivityProvide implements ActivityApi {

    @Resource
    private ActivityService activityService;

    @Override
    public ActivityDTO getById(Long id) {
        ActivityEntity activityEntity = activityService.getByActivityId(id);
        ActivityDTO activityDTO = new ActivityDTO();
        BeanUtils.copyProperties(activityEntity,activityDTO);
        return activityDTO;
    }

    @Override
    public List<ActivityDTO> list() {
        List<ActivityEntity> list = activityService.list();
        List<ActivityDTO> activityDTOS = DeepBeanUtil.copyListProperties(list, ActivityDTO::new);
        return activityDTOS;
    }

    @Override
    public Boolean add(ActivityDTO activityDTO) {
        ActivityEntity activityEntity = new ActivityEntity();
        BeanUtils.copyProperties(activityDTO,activityEntity);
        return activityService.save(activityEntity);
    }

    @Override
    public Map<String, Object> pageSearch(ActivityPageRequest pageRequest) {
        Map<String, Object> res = new HashMap<>();
        QueryWrapper<ActivityEntity> qw = new QueryWrapper<>();
        if(!StrUtil.isBlank(pageRequest.getQueryColumn()) && !StrUtil.isBlank(pageRequest.getValue())) {
            qw.like(pageRequest.getQueryColumn(),pageRequest.getValue());
        }
        Page<ActivityEntity> pageHolder = new Page<>(pageRequest.getCurrentPage(), pageRequest.getPageSize());
        activityService.page(pageHolder, qw );
        res.put(resData,pageHolder.getRecords());
        res.put(recordCount,pageHolder.getTotal());
        res.put(pageCount,pageHolder.getPages());
        return res;
    }


}
