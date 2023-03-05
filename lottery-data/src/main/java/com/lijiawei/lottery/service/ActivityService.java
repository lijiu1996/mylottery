package com.lijiawei.lottery.service;

import com.lijiawei.lottery.entity.ActivityEntity;

import java.util.List;
import java.util.Map;

/**
 * 数据访问接口定义
 * 实体 - 活动类
 */
public interface ActivityService {

    /**
     *  根据id获取实体对象完整信息, 如果不存在返回null
     */
    ActivityEntity get(long id);

    /**
     *  增加实体对象
     */
    boolean add(ActivityEntity activityEntity);

    /**
     *  全量数据返回 如果没有，list为空
     */
    List<ActivityEntity> list();

    /**
     *  统计信息
     * @param currentPage   当前页号
     * @param size          当前页数据量
     * @param column        模糊查询数据列
     * @param keyword       模糊查询关键字
     * @return
     * 1、 key = allData、value = list集合
     * 2、 key = allRecorders、value = 总记录数
     * 3、 key = allPages、value = 页数
     */
    Map<String,Object> page(int currentPage, int size, String column, String keyword);

}
