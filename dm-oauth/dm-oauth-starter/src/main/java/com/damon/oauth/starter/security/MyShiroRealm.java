package com.damon.oauth.starter.security;

import com.damon.oauth.domain.Permission;
import com.damon.oauth.domain.Role;
import com.damon.oauth.domain.User;
import com.damon.oauth.queryfilter.UserQueryFilter;
import com.damon.oauth.service.PermissionService;
import com.damon.oauth.service.RoleService;
import com.damon.oauth.service.UserService;
import com.damon.oauth.shared.enums.UserState;
import lombok.extern.slf4j.Slf4j;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.DefaultPasswordService;
import org.apache.shiro.authc.credential.PasswordService;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.util.ObjectUtils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author Damon S.
 */
@Slf4j
public class MyShiroRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    private PasswordService passwordService =
            new DefaultPasswordService();

    private final static String LOCKED = "LOCKED";
    /**用户登录是否被锁定 一小时 redisKey 前缀*/
    private final static String SHIRO_USER_LOCK = "SHIRO_USER_LOCK:";
    /**用户登录次数计数  redisKey 前缀*/
    private final static String SHIRO_LOGIN_COUNT = "SHIRO_LOGIN_COUNT:";

    private final static Integer MAX_LOGIN_COUNT = 5;

    private final static Integer PURE_NUMBER_1 = 1;


    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authToken)
            throws AuthenticationException {
        log.info("身份认证：" + this.getClass().getSimpleName() + ".doGetAuthenticationInfo()");

        // 获取用户输入的用户名和密码
        UsernamePasswordToken upToken = (UsernamePasswordToken)authToken;
        String username = upToken.getUsername();
        String password = String.valueOf(upToken.getPassword());

        // 登录一次，计数一次
        ValueOperations<String, String> loginCounterCache = stringRedisTemplate.opsForValue();
        loginCounterCache.increment(SHIRO_LOGIN_COUNT.concat(username), PURE_NUMBER_1);

        // 计数大于5时，设置用户被锁定一小时
        if(Integer.parseInt(loginCounterCache.get(SHIRO_LOGIN_COUNT.concat(username))) >= MAX_LOGIN_COUNT) {
            loginCounterCache.set(SHIRO_USER_LOCK.concat(username), LOCKED);
            stringRedisTemplate.expire(SHIRO_USER_LOCK.concat(username), PURE_NUMBER_1, TimeUnit.HOURS);
        }
        if (LOCKED.equals(loginCounterCache.get(SHIRO_USER_LOCK.concat(username)))){
            throw new ExcessiveAttemptsException("密码输入错误次数大于5次，帐号已禁止登录，请1小时后再试！");
        }

        // 登录验证
        UserQueryFilter filter = new UserQueryFilter();
        filter.setName(username);
        // filter.setPassword(DesUtils.encryptBasedDES(password + username));
        passwordService.encryptPassword(password);

        // 从数据库获取对应密码的用户
        List<User> users = userService.find(filter);
        User loginUser = users.get(0);

        if (ObjectUtils.isEmpty(users)) {
            throw new AccountException("用户名或密码不正确！");
        } else if(UserState.FROZEN.equals(loginUser.getState())) {
            throw new DisabledAccountException("账号已被锁定，请联系管理员！");
        } else {
            //登录成功
            loginUser.setLastLogin(LocalDateTime.from(Instant.now()));
            loginUser.update();
            // 重置登录次数计数
            loginCounterCache.set(SHIRO_LOGIN_COUNT.concat(username), "0");
        }
        log.info("身份认证成功，登录用户：" + username);
        return new SimpleAuthenticationInfo(loginUser, password, getName());
    }


    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        Logger.getLogger(getClass()).info("身份认证：" + this.getClass().getSimpleName() + ".doGetAuthorizationInfo()");

        User user = (User) SecurityUtils.getSubject().getPrincipal();
        SimpleAuthorizationInfo authorizationInfo =  new SimpleAuthorizationInfo();

        final Set<String> userRoles = new HashSet<>();
        final Set<String> userPerms = new HashSet<>();

        // 根据用户Id查询角色，放入到Authorization里。
        List<Role> roles = roleService.find(user.getUserId());
        if (ObjectUtils.isEmpty(roles)) {
            roles.forEach(role -> {
                userRoles.add(role.getName());
            });
        }
        authorizationInfo.setRoles(userRoles);

        // 根据用户ID查询权限，放入到Authorization里。
        List<Permission> permissions = permissionService.find(null);
        if (ObjectUtils.isEmpty(permissions)) {
            permissions.forEach(perm -> {
                userPerms.add(String.valueOf(perm.getPermissionId()));
            });
        }
        authorizationInfo.setStringPermissions(userPerms);

        return authorizationInfo;
    }
}
