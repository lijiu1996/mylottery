package com.lijiawei.lottery.provider.controller;

import com.lijiawei.lottery.api.dto.ActivityDTO;
import com.lijiawei.lottery.provider.service.ActivityProvide;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("activity")
@RestController
public class ActivityController {

    @Autowired
    private ActivityProvide activityProvide;

    @GetMapping("/{id}")
    public ActivityDTO getByid(@PathVariable("id") Long id) {
        return activityProvide.getById(id);
    }

}
