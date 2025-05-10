package com.future.csamsserver.controller;

import cn.dev33.satoken.util.SaResult;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.future.csamsserver.domain.Statistics;
import com.future.csamsserver.enumeration.StatisticsEnum;
import com.future.csamsserver.mapper.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * @description
 * @dateTime 2025:05:10 22:17
 * @user Jenming
 */
@Slf4j
@RestController
@RequestMapping("home")
public class HomeController {

    private final ActivityMapper activityMapper;
    private final ClubMapper clubMapper;
    private final StatisticsMapper statisticsMapper;
    private final ActivityMemberMapper activityMemberMapper;
    private final ClubMemberMapper clubMemberMapper;
    private final UserMapper userMapper;


    public HomeController(ActivityMapper activityMapper, ClubMapper clubMapper, StatisticsMapper statisticsMapper, ActivityMemberMapper activityMemberMapper, ClubMemberMapper clubMemberMapper, UserMapper userMapper) {
        this.activityMapper = activityMapper;
        this.clubMapper = clubMapper;
        this.statisticsMapper = statisticsMapper;
        this.activityMemberMapper = activityMemberMapper;
        this.clubMemberMapper = clubMemberMapper;
        this.userMapper = userMapper;
    }

    /**
     * 这是 Java 中的 双括号初始化（Double Brace Initialization） 语法，用于在声明时快速初始化集合类对象（如 HashMap、ArrayList 等）。
     * <p>
     * 其他初始化方式
     * 1. 使用 Map.of()（Java 9+）
     * 适用于不可变的 Map：
     * <p>
     * 2. 构造函数
     * <p>
     * 3. 类初始化块 或 实例初始化块
     */
    private final HashMap<String, Long> statisticsDataMap = new HashMap<>() {
        {
            put(StatisticsEnum.VIEW_COUNT.getType(), StatisticsEnum.VIEW_COUNT.getValue());
        }
    };

    /**
     * 实例初始化块
     */ {
        statisticsDataMap.put(StatisticsEnum.PARTICIPATION_COUNT.getType(), StatisticsEnum.PARTICIPATION_COUNT.getValue());
        statisticsDataMap.put(StatisticsEnum.ACTIVITY_COUNT.getType(), StatisticsEnum.ACTIVITY_COUNT.getValue());
        statisticsDataMap.put(StatisticsEnum.USER_COUNT.getType(), StatisticsEnum.USER_COUNT.getValue());
    }

    @GetMapping
    public SaResult index() {
        final Long viewCount = statisticsMapper.selectOne(
                new LambdaQueryWrapper<Statistics>().eq(true, Statistics::getType, StatisticsEnum.VIEW_COUNT.getType())
        ).getValue();
        /**
         * 将所有社团、活动加入人员
         * 无论是否已审批
         */
        final long participationCount = clubMemberMapper.selectCount(null) + activityMemberMapper.selectCount(null);
        statisticsDataMap.put(StatisticsEnum.VIEW_COUNT.getType(), viewCount);
        statisticsDataMap.put(StatisticsEnum.PARTICIPATION_COUNT.getType(), participationCount);
        statisticsDataMap.put(StatisticsEnum.ACTIVITY_COUNT.getType(), activityMapper.selectCount(null));
        statisticsDataMap.put(StatisticsEnum.USER_COUNT.getType(), userMapper.selectCount(null));
        return SaResult.data(statisticsDataMap);
        /**
         * {
         *   "code": 200,
         *   "msg": "ok",
         *   "data": {
         *     "user_count": 10,
         *     "activity_count": 1,
         *     "participation_count": 4,
         *     "view_count": 21
         *   }
         * }
         */
    }

}
