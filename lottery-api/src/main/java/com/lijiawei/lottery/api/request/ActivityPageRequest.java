package com.lijiawei.lottery.api.request;

import lombok.Data;

/**
 * @author Li JiaWei
 * @ClassName: ActivityPageRequest
 * @Description:
 * @Date: 2023/3/6 14:58
 * @Version: 1.0
 */
@Data
public class ActivityPageRequest {

    private Integer currentPage;

    private Integer pageSize;

    private String queryColumn;

    private String value;
}
