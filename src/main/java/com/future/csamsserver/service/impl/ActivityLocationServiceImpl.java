package com.future.csamsserver.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.future.csamsserver.domain.ActivityLocation;
import com.future.csamsserver.service.ActivityLocationService;
import com.future.csamsserver.mapper.ActivityLocationMapper;
import org.springframework.stereotype.Service;

/**
* @author Jenming
* @description 针对表【activity_location(活动场地表)】的数据库操作Service实现
* @createDate 2025-03-16 02:37:19
*/
@Service
public class ActivityLocationServiceImpl extends ServiceImpl<ActivityLocationMapper, ActivityLocation>
    implements ActivityLocationService{

}




