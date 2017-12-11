package cn.oriki.shiro.test;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ThreadContext;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import cn.oriki.shiro.entity.Permission;
import cn.oriki.shiro.entity.Role;
import cn.oriki.shiro.entity.User;
import cn.oriki.shiro.matcher.RetryLimitHashedCredentialsMatcher;
import cn.oriki.shiro.realm.UserRealm;
import cn.oriki.shiro.service.PermissionService;
import cn.oriki.shiro.service.RoleService;
import cn.oriki.shiro.service.UserService;
import cn.oriki.shiro.service.impl.PermissionServiceImpl;
import cn.oriki.shiro.service.impl.RoleServiceImpl;
import cn.oriki.shiro.service.impl.UserServiceImpl;
import cn.oriki.shiro.utils.JdbcTemplateUtils;

public class ShiroTest {

    protected PermissionService permissionService = new PermissionServiceImpl();
    protected RoleService roleService = new RoleServiceImpl();
    protected UserService userService = new UserServiceImpl();

    protected String password = "123456";

    protected Permission p1;
    protected Permission p2;
    protected Permission p3;
    protected Role r1;
    protected Role r2;
    protected User u1;

    @Before
    public void setUp() {
        // 删除表内所有数据
        JdbcTemplateUtils.jdbcTemplate().update("delete from sys_users");
        JdbcTemplateUtils.jdbcTemplate().update("delete from sys_roles");
        JdbcTemplateUtils.jdbcTemplate().update("delete from sys_permissions");
        JdbcTemplateUtils.jdbcTemplate().update("delete from sys_users_roles");
        JdbcTemplateUtils.jdbcTemplate().update("delete from sys_roles_permissions");

        // 新增权限
        p1 = new Permission("user:create", "用户模块新增", Boolean.TRUE);
        p2 = new Permission("user:update", "用户模块修改", Boolean.TRUE);
        p3 = new Permission("menu:create", "菜单模块新增", Boolean.TRUE);
        permissionService.createPermission(p1);
        permissionService.createPermission(p2);
        permissionService.createPermission(p3);

        // 新增角色
        r1 = new Role("admin", "管理员", Boolean.TRUE);
        r2 = new Role("user", "用户管理员", Boolean.TRUE);
        roleService.createRole(r1);
        roleService.createRole(r2);

        // 关联角色-权限
        roleService.correlationPermissions(r1.getId(), p1.getId());
        roleService.correlationPermissions(r1.getId(), p2.getId());
        roleService.correlationPermissions(r1.getId(), p3.getId());

        roleService.correlationPermissions(r2.getId(), p1.getId());
        roleService.correlationPermissions(r2.getId(), p2.getId());

        // 新增用户
        u1 = new User("zhangsan", password);
        userService.createUser(u1);

        // 5、关联用户-角色
        userService.correlationRoles(u1.getId(), r1.getId());

    }

    @Test
    public void test() {
        DefaultSecurityManager securityManager = getSecurityManager();

        SecurityUtils.setSecurityManager(securityManager);

        // 3、得到Subject及创建用户名/密码身份验证Token（即用户身份/凭证）
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("zhangsan", "123456");

        subject.login(token);

        // 校验用户是否具有如下权限
        subject.checkPermissions("user:create", "user:update", "menu:create");

        subject.logout();
    }

    private DefaultSecurityManager getSecurityManager() {
        UserRealm realm = getRealm();

        DefaultSecurityManager securityManager = new DefaultSecurityManager();

        securityManager.setRealm(realm);
        return securityManager;
    }

    private UserRealm getRealm() {
        RetryLimitHashedCredentialsMatcher matcher = getCredentialsMatcher();
        UserRealm realm = new UserRealm();
        realm.setCredentialsMatcher(matcher);
        return realm;
    }

    private RetryLimitHashedCredentialsMatcher getCredentialsMatcher() {
        RetryLimitHashedCredentialsMatcher matcher = new RetryLimitHashedCredentialsMatcher();
        matcher.setHashAlgorithmName("md5");
        matcher.setHashIterations(2);
        return matcher;
    }

    public Subject subject() {
        return SecurityUtils.getSubject();
    }

    @After
    public void tearDown() throws Exception {
        ThreadContext.unbindSubject();// 退出时请解除绑定Subject到线程 否则对下次测试造成影响
    }
}
