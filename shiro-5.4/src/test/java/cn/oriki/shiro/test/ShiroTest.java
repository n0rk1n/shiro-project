package cn.oriki.shiro.test;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.DefaultPasswordService;
import org.apache.shiro.authc.credential.PasswordMatcher;
import org.apache.shiro.authc.credential.PasswordService;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

import cn.oriki.shiro.realm.MyRealm;
import junit.framework.Assert;

public class ShiroTest {

    @Test
    public void test() {
        // 获取Subject
        Subject subject = getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken("zhangsan", "123456");

        // 登入
        subject.login(token);

        // 校验是否是登录
        Assert.assertTrue(subject.isAuthenticated());

        // 登出
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

    private org.apache.shiro.mgt.SecurityManager getSecurityManager() {
        DefaultSecurityManager securityManager = new DefaultSecurityManager();
        // 设置域Realm
        securityManager.setRealm(getRealm());

        return securityManager;
    }

    /**
     * 获取Realm域
     *  
     *
     * @return
     */
    private Realm getRealm() {
        MyRealm realm = new MyRealm();
        // 设置我们传入的PasswordService和Matcher中使用的PasswordService一致
        realm.setPasswordService(getPasswordService());

        realm.setCredentialsMatcher(getCredentialsMathcer());
        return realm;
    }

    /**
     * 获取校验用Matcher，保证Matcher使用的加密方式和自定义Realm中使用的加密方式一致
     *  
     *
     * @return
     */
    private CredentialsMatcher getCredentialsMathcer() {
        PasswordMatcher passwordMatcher = new PasswordMatcher();
        // 设置Matcher中的加密方式
        passwordMatcher.setPasswordService(getPasswordService());
        return passwordMatcher;
    }

    /**
     * 获取加密功能的PasswordService，默认使用DefaultPasswordService
     *  
     *
     * @return
     */
    private PasswordService getPasswordService() {
        return new DefaultPasswordService();
    }
}
