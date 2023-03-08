package com.lijiawei.lottery.strategy.service.algorithm.impl;

import com.lijiawei.lottery.strategy.service.algorithm.BaseAlgorithm;
import org.junit.jupiter.api.Assertions;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Li JiaWei
 * @ClassName: DefaultSingleDrawAlgorithm
 * @Description:    now have two kinds of drawAwardAlgorithm
 *                      all    - 如果奖品被抽完 - 提升其他奖品的抽奖概率 以保证剩余奖品也会被抽到
 *                      single - 即使其中一项奖品被抽完 也不会提升其他奖品的抽奖概率
 * @Date: 2023/3/8 13:52
 * @Version: 1.0
 */
@Service("singleDrawAlgorithm")
public class DefaultSingleDrawAlgorithm extends BaseAlgorithm {

    // null 表示未中奖
    public String getAward(Integer strategyId, List<String> excludeAwardsList) {
        String[] awardTuple = rateMap.get(strategyId);
        Assertions.assertNotNull(awardTuple,"未初始化的返回策略");

        int randomValue = generateSecureRandomIntCode(100);
        String awardTitle = awardTuple[hashIdx(randomValue)];
        if (excludeAwardsList.contains(awardTitle)) {
            return null;
        }
        return awardTitle;
    }
}
