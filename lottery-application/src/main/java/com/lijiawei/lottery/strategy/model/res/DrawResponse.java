package com.lijiawei.lottery.strategy.model.res;

import com.lijiawei.lottery.strategy.model.pojo.AwardInfo;
import lombok.Data;

/**
 * @author Li JiaWei
 * @ClassName: DrawResponse
 * @Description:
 * @Date: 2023/3/8 16:18
 * @Version: 1.0
 */
@Data
public class DrawResponse {

    private Long userId;

    private Long strategyId;

    private Integer response;

    private AwardInfo awardInfo;
}
