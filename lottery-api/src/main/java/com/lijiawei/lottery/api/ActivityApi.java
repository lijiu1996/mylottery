package com.lijiawei.lottery.api;

import com.lijiawei.lottery.api.dto.ActivityDTO;
import com.lijiawei.lottery.api.request.ActivityPageRequest;

import java.util.List;
import java.util.Map;

public interface ActivityApi {

    ActivityDTO getById(Long id);

    List<ActivityDTO> list();

    Boolean add(ActivityDTO activityDTO);

    String resData = "listRes";

    String recordCount = "allRecords";

    String pageCount = "allPages";

    Map<String, Object> pageSearch(ActivityPageRequest pageRequest);

}
