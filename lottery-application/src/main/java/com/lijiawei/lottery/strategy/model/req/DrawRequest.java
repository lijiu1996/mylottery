package com.lijiawei.lottery.strategy.model.req;

import lombok.Data;

/**
 * @author Li JiaWei
 * @ClassName: DrawRequest
 * @Description:
 * @Date: 2023/3/8 16:18
 * @Version: 1.0
 */
@Data
public class DrawRequest {

    private Long userId;

    private Integer strategyId;

}
