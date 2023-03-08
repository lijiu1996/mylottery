package com.lijiawei.lottery.strategy.service.algorithm;

import com.lijiawei.lottery.strategy.model.AwardRateInfo;

import java.util.List;

/**
 * @author Li JiaWei
 * @ClassName: DrawAlgorithm
 * @Description:    总结业务场景
 *      抽奖 -- 有可能存在奖品被抽完的情况
 *      预热 -- 单机
 * @Date: 2023/3/8 13:48
 * @Version: 1.0
 */
public interface IDrawAlgorithm {

    /**
     *  对
     */
    void init(Integer strategyId, List<AwardRateInfo> rateInfoList);

    /**
     *
     * @param strategyId
     * @return
     */
    boolean isExist(Integer strategyId);

    String getAward(Integer strategyId, List<String> excludeAward);

}
