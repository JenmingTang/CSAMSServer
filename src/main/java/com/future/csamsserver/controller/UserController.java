package com.future.csamsserver.controller;

import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import com.future.csamsserver.domain.User;
import com.future.csamsserver.mapper.UserMapper;
import com.future.csamsserver.utils.U;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @description
 * @dateTime 2025:03:04 21:54
 * @user Jenming
 */
@RequestMapping("user")
@RestController
@Slf4j
public class UserController {

    private final UserMapper userMapper;

    public UserController(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    //import { deleteSysUserById, deleteSysUserListByIdList, selectSysUserListByParams } from '@/service/api/csams/sys';
//    @GetMapping("selectUserListByParams")
//    public SaResult selectUserListByParams(UserQueryDTO userQueryDTO) {
//        final Long current = userQueryDTO.getCurrent();
//        if (current != null && current == 1) {
////            因为前端传过来的current是1，而mybatis-plus的分页是从0开始的，所以这里要减1
//            userQueryDTO.setCurrent(0L);
//        }
//        final Long size = userQueryDTO.getSize();
//        return SaResult.data(U.wrapAsSAResult(current + 1, size,
//                userMapper.selectSysUserListByParams(
//                        current, size, userQueryDTO.getCreateTimeStart(), userQueryDTO.getCreateTimeEnd(),
//                        userQueryDTO.getUsername(), userQueryDTO.getIsEnabled())));
//    }

    @GetMapping("selectUserListByParams")
    public SaResult selectUserListByParams(
            @RequestParam(defaultValue = "1") Long current,
            @RequestParam(defaultValue = "10") Long size,
            @RequestParam(required = false) LocalDateTime createTimeStart,
            @RequestParam(required = false) LocalDateTime createTimeEnd,
            @RequestParam(required = false) String username,
            @RequestParam(required = false) Integer enabled,
            @RequestParam(required = false) String realName,
            @RequestParam(required = false) String college,
            @RequestParam(required = false) String major,
            @RequestParam(required = false) String userClass,
            @RequestParam(required = false) String phone,
            @RequestParam(required = false) String email
    ) {
        log.info("selectUserListByParams: current={}, size={}, createTimeStart={}, createTimeEnd={}, username={}, enabled={}", current, size, createTimeStart, createTimeEnd, username, enabled);
        if (current != null && current == 1) {
//            因为前端传过来的current是1，而mybatis-plus的分页是从0开始的，所以这里要减1
            current = 0L;
        }
        final List<User> userList = userMapper.selectUserListByParams(
                current, size, createTimeStart, createTimeEnd, username, enabled,
                realName,college,major,userClass,phone,email);
        List<User> updatedUsers = userList.stream()
                .peek(user -> user.setPassword(""))
//                .collect(Collectors.toList());
                .toList();
//        List<User> updatedUsers = userList.stream()
//                .map(user -> {
//                    user.setPassword("");
//                    return user;
//                })
//                .collect(Collectors.toList()); // 必须添加终端操作（如 collect）

        return SaResult.data(U.wrapAsSAResult(current + 1,size,userMapper.selectCount(null),
                updatedUsers));
    }
/*
注意
类型转换支持​
基础类型（String/Integer等）自动转换
LocalDateTime需要配置转换器（实现Converter<String, LocalDateTime>接口并注册）
注意
@JsonProperty("create_time_start")
private LocalDateTime createTimeStart;

public SaResult selectSysUserListByParams(@ModelAttribute SysUserQueryDTO query) {
或
public SaResult selectSysUserListByParams(SysUserQueryDTO query) {
* */

    @DeleteMapping("deleteUserById/{id}")
    public SaResult deleteUserById(
            @PathVariable("id") Long id) {
        log.info("deleteUserById: {}", id);
        return SaResult.data(userMapper.deleteById(id));
    }

    @DeleteMapping("deleteUserListByIdList")
    public SaResult deleteUserListByIdList(
            @RequestBody List<Long> idList) {
        log.info("deleteUserListByIdList: {}", idList);
        return SaResult.data(userMapper.deleteByIds(idList));
    }

    @PostMapping("insertOrUpdateUser")
    public SaResult insertOrUpdateUser(
            @RequestBody User user) {
        log.info("insertOrUpdateUser: {}", user);
        if (user.getId() == null) {
            return SaResult.error("请联系管理员添加用户！");
        }
        /*
        * 超管禁用用户
        * */
        if (user.getEnabled()==0){
            StpUtil.logout();
        }
        return SaResult.data(userMapper.insertOrUpdate(user));
    }

    @GetMapping("selectUserList")
    public SaResult selectUserList() {
////        根据实体类的某个字段名查询某个字段
//        userMapper.selectList(new LambdaQueryWrapper<User>()
//                .eq(User::getUsername, "admin"));
//        只返回这个字段，其他字段为null，减少MySQL压力
//        根据实体类的某个字段名查询某个字段，只返回这个字段的列表，不是返回实体的列表
//        final List<User> users = userMapper.selectList(new LambdaQueryWrapper<User>()
//                .select(User::getUsername));
//        提取实体列表的某个字段的列表
//        final List<String> usernames = users.stream().map(User::getUsername).toList();
        log.info("selectUserList");
        final List<User> userList = userMapper.selectList(null);
        List<User> updatedUsers = userList.stream()
                .peek(user -> user.setPassword(""))
//                .collect(Collectors.toList());
                .toList();
        return SaResult.data(updatedUsers);
    }

    @GetMapping("selectUserListWithSuperAdminRole")
    public SaResult selectUserListWithSuperAdminRole() {
        log.info("selectUserListWithSuperAdminRole");
        return SaResult.data(userMapper.selectUserListWithSuperAdminRole());
    }
}
