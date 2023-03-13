package com.lijiawei.lottery.strategy.model;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author Li JiaWei
 * @ClassName: AwardRateInfo
 * @Description:
 * @Date: 2023/3/8 14:16
 * @Version: 1.0
 */
@Data
public class AwardRateInfo {

    // 奖品名称
    private String awardName;

    // 奖品被抽中概率
    private BigDecimal rate;
}
