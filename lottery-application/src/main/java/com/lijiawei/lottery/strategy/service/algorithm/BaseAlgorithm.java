package com.lijiawei.lottery.strategy.service.algorithm;

import com.lijiawei.lottery.strategy.model.AwardRateInfo;
import org.junit.jupiter.api.Assertions;

import java.math.BigDecimal;
import java.security.SecureRandom;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Li JiaWei
 * @ClassName: BaseAlgorithm
 * @Description:
 * @Date: 2023/3/8 13:48
 * @Version: 1.0
 */
public abstract class BaseAlgorithm implements IDrawAlgorithm{

    protected Map<Integer,List<AwardRateInfo>> originalConfigMap = new ConcurrentHashMap<>();

    protected Map<Integer,String[]> rateMap = new ConcurrentHashMap<>();

    public void init(Integer strategyId, List<AwardRateInfo> rateInfoList) {
        originalConfigMap.put(strategyId,rateInfoList);
        String[] rateTuple = rateMap.computeIfAbsent(strategyId,k -> new String[RATE_TUPLE_LENGTH]);

        int currentWindow = 0;
        for (AwardRateInfo awardRateInfo : rateInfoList) {
            // 需要放入多少个 如果概率是0.2 则需要放入20个
            int rateCount = awardRateInfo.getRate().multiply(new BigDecimal(100)).intValue();
            String awardName = awardRateInfo.getAwardName();
            for (int i = 0 ; i < rateCount; i++) {
                rateTuple[hashIdx(currentWindow + i)] = awardName;
            }
            currentWindow += rateCount;
        }
    }

    public boolean isExist(Integer strategyId) {
        return rateMap.containsKey(strategyId);
    }

    /** 斐波那契散列增量，逻辑：黄金分割点：(√5 - 1) / 2 = 0.6180339887，Math.pow(2, 32) * 0.6180339887 = 0x61c88647 */
    private final int HASH_INCREMENT = 0x61c88647;

    /** 数组初始化长度 128，保证数据填充时不发生碰撞的最小初始化值 */
    private final int RATE_TUPLE_LENGTH = 128;

    /** 斐波那契算法计算奖品对应的哈希值 */
    protected int hashIdx(int val) {
        int hashCode = val * HASH_INCREMENT + HASH_INCREMENT;
        return hashCode & (RATE_TUPLE_LENGTH - 1);
    }

    // 获取抽奖概率
    protected int generateSecureRandomIntCode(int bound){
        return new SecureRandom().nextInt(bound) + 1;
    }
}
