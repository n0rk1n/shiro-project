package cn.oriki.shiro.test;

import cn.oriki.shiro.realm.MyRealm;
import cn.oriki.shiro.rolepermissionresolver.MyRolePermissionResolver;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.Authorizer;
import org.apache.shiro.authz.ModularRealmAuthorizer;
import org.apache.shiro.authz.permission.RolePermissionResolver;
import org.apache.shiro.authz.permission.WildcardPermissionResolver;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

public class ShiroTest {

    @Test
    public void test() {
        Subject subject = getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken("zhangsan", "123456");

        subject.login(token);

        // 检查zhangsan是否拥有user下的权限
        subject.checkPermission("user:create");
        subject.checkPermission("user:delete");

        subject.logout();
    }

    /**
     * 获取Subject
     *  
     *
     * @return
     */
    private Subject getSubject() {
        SecurityUtils.setSecurityManager(getSecurityManager());

        return SecurityUtils.getSubject();
    }

    /**
     * 获取安全管理器
     *  
     *
     * @return
     */
    private org.apache.shiro.mgt.SecurityManager getSecurityManager() {
        DefaultSecurityManager securityManager = new DefaultSecurityManager();

        securityManager.setAuthorizer(getAuthorizer());
        securityManager.setRealm(new MyRealm());
        return securityManager;
    }

    /**
     * 获取授权器
     *  
     *
     * @return
     */
    private Authorizer getAuthorizer() {
        ModularRealmAuthorizer authorizer = new ModularRealmAuthorizer();

        // 设置PermissionResovler
        authorizer.setPermissionResolver(new WildcardPermissionResolver());

        // 设置RolePermissionResolver
        authorizer.setRolePermissionResolver(getRolePermissionResolver());

        return authorizer;
    }

    /**
     * 获取自定义的RolePermissionResolver
     *  
     *
     * @return
     */
    private RolePermissionResolver getRolePermissionResolver() {
        return new MyRolePermissionResolver();
    }
}
