package com.future.csamsserver.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.future.csamsserver.domain.Activity;
import com.future.csamsserver.service.ActivityService;
import com.future.csamsserver.mapper.ActivityMapper;
import org.springframework.stereotype.Service;

/**
* @author Jenming
* @description 针对表【activity(活动表)】的数据库操作Service实现
* @createDate 2025-03-10 22:00:51
*/
@Service
public class ActivityServiceImpl extends ServiceImpl<ActivityMapper, Activity>
    implements ActivityService{

}




