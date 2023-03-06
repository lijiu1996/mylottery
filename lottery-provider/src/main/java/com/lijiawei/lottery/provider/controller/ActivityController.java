package com.lijiawei.lottery.provider.controller;

import com.lijiawei.lottery.api.dto.ActivityDTO;
import com.lijiawei.lottery.api.request.ActivityPageRequest;
import com.lijiawei.lottery.provider.service.ActivityProvide;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequestMapping("/activity")
@RestController
public class ActivityController {

    @Autowired
    private ActivityProvide activityProvide;

    @GetMapping("/{id}")
    public ActivityDTO getByid(@PathVariable("id") Long id) {
        return activityProvide.getById(id);
    }

    @GetMapping("/list")
    public List<ActivityDTO> list() {
        return activityProvide.list();
    }

    @PostMapping
    public Boolean add(@RequestBody ActivityDTO activityDTO) {
        return activityProvide.add(activityDTO);
    }

    @PostMapping("/page")
    public Map<String,Object> page(@RequestBody ActivityPageRequest pageRequest) {
        return activityProvide.pageSearch(pageRequest);
    }

}
