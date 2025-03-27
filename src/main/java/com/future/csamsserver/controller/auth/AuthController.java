package com.future.csamsserver.controller.auth;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import cn.hutool.crypto.SecureUtil;
import com.future.csamsserver.domain.User;
import com.future.csamsserver.domain.dto.LoginDTO;
import com.future.csamsserver.domain.sa.UserInfo;
import com.future.csamsserver.mapper.PermissionMapper;
import com.future.csamsserver.mapper.RoleMapper;
import com.future.csamsserver.mapper.UserMapper;
import com.future.csamsserver.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/**
 * @description
 * @dateTime 2025:03:04 21:55
 * @user Jenming
 */
@RequestMapping("auth")
@RestController
@Slf4j
public class AuthController {

    private final UserService userService;
    private final PermissionMapper permissionMapper;
    private final RoleMapper roleMapper;
    private final UserMapper userMapper;

    public AuthController(UserService userService, PermissionMapper permissionMapper, RoleMapper roleMapper, UserMapper userMapper) {
        this.userService = userService;
        this.permissionMapper = permissionMapper;
        this.roleMapper = roleMapper;
        this.userMapper = userMapper;
    }

    @PostMapping("login")
    public SaResult login(@RequestBody LoginDTO loginDto) {
        log.info("login: {}", loginDto);
            /*
             * 通过用户名查询是否存在此用户
             * */
            final User user = userService.lambdaQuery()
                    /*
                     * 不能使用getEntity
                     * */
//                .eq(User::getUsername, loginDto.getUsername()).getEntity();
                    .eq(User::getUsername, loginDto.getUsername()).one();

            if (user == null)
                return SaResult.ok("查无此用户！");
            if (user.getEnabled() == 0)
                return SaResult.ok("用户已被禁用！");
            if (!Objects.equals(user.getPassword(), SecureUtil.md5(loginDto.getPassword())))
//        if (!user.getPassword().equals(SecureUtil.md5(password)))
                return SaResult.ok("密码不对！");
            StpUtil.login(user.getId(), loginDto.isRememberMe());
            final SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
            final HashMap<String, String> token = new HashMap<>();
            final String tokenValue = tokenInfo.getTokenValue();
            token.put("token", tokenValue);
            token.put("refreshToken", tokenValue);
            log.info("tokenInfo: {}", tokenInfo);
            return SaResult.ok("登录成功！").setData(token);

    }

    @GetMapping("getUserInfo")
    public SaResult getUserInfo() {
            long userId = StpUtil.getLoginIdAsLong();
            String username = userMapper.selectById(userId).getUsername();
            List<String> roleList = roleMapper.selectRoleNameByUserId(userId);
            List<String> permissionList = permissionMapper.selectPermissionCodeByUserId(userId);
            UserInfo userInfo = new UserInfo();
            userInfo.setUserId(String.valueOf(userId));
            userInfo.setUserName(username);
            userInfo.setRoles(roleList);
            userInfo.setButtons(permissionList);
            return SaResult.data(userInfo);

    }

    @GetMapping("logout")
    public SaResult logout() {
        if (!StpUtil.isLogin()) {
            return SaResult.error("Not logged in");
        }
        StpUtil.logout();
        return SaResult.ok();
    }

}
