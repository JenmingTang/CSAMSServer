package com.future.csamsserver.mapper;

import com.future.csamsserver.domain.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.future.csamsserver.utils.U;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDateTime;
import java.util.List;

/**
* @author Jenming
* @description 针对表【user(用户表)】的数据库操作Mapper
* @createDate 2025-03-04 22:19:52
* @Entity com.future.csamsserver.domain.User
*/
public interface UserMapper extends BaseMapper<User> {

    List<User> selectUserListByParams(Long current, Long size, LocalDateTime createTimeStart, LocalDateTime createTimeEnd, String username, Integer enabled, String realName, String college, String major, String userClass, String phone, String email);


    List<User> selectUserListWithSuperAdminRole();

    @Select("SELECT u.id, u.username, u.password, u.real_name, u.college, u.major, " +
            "u.user_class, u.email, u.phone, u.avatar_url, u.is_enabled AS enabled, " +
            "u.create_time, u.update_time " +
            "FROM user AS u " +
            "WHERE u.email = #{email}")
    User selectUserByEmail(@Param("email") String email);


    Long selectUserIdByActivityLocationRole();
}




