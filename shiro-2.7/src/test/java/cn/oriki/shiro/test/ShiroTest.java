package cn.oriki.shiro.test;

import cn.oriki.shiro.realm.MyRealm;
import cn.oriki.shiro.realm.MyRealm2;
import junit.framework.Assert;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.Authenticator;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.pam.AllSuccessfulStrategy;
import org.apache.shiro.authc.pam.AuthenticationStrategy;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

import java.util.Arrays;

public class ShiroTest {

    @Test
    public void test() {
        Subject subject = getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken("zhangsan", "123456");

        subject.login(token);

        Assert.assertTrue(subject.isAuthenticated());

        PrincipalCollection principals = subject.getPrincipals();

        System.out.println(principals);// zhangsan,zc

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
     * 获取SecurityManager
     *  
     *
     * @return
     */
    private org.apache.shiro.mgt.SecurityManager getSecurityManager() {
        DefaultSecurityManager securityManager = new DefaultSecurityManager();
        // 设置自定义认证器
        securityManager.setAuthenticator(getAuthenticator());
        securityManager.setRealms(Arrays.asList(new MyRealm(), new MyRealm2()));
        return securityManager;
    }

    /**
     * 获取认证器
     *  
     *
     * @return
     */
    private Authenticator getAuthenticator() {
        ModularRealmAuthenticator authenticator = new ModularRealmAuthenticator();
        authenticator.setAuthenticationStrategy(getAuthenticationStrategy());
        return authenticator;
    }

    /**
     * 获取AllSuccessfulStrategy认证策略
     *  
     *
     * @return
     */
    private AuthenticationStrategy getAuthenticationStrategy() {
        return new AllSuccessfulStrategy();
    }
}
