package com.future.csamsserver.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.future.csamsserver.controller.view.ViewController;
import com.future.csamsserver.domain.ActivityLocation;
import com.future.csamsserver.domain.ActivityLocationWithActivityEndTime;
import com.future.csamsserver.domain.NewsItem;
import com.future.csamsserver.domain.vo.ActivityLocationVO;
import com.future.csamsserver.domain.vo.ActivityLocationWithActivityEndTimeVO;

import java.time.LocalDateTime;
import java.util.List;

/**
* @author Jenming
* @description 针对表【activity_location(活动场地表)】的数据库操作Mapper
* @createDate 2025-03-16 02:37:19
* @Entity com.future.csamsserver.domain.ActivityLocation
*/
public interface ViewMapper {
    List<NewsItem> root();

}




