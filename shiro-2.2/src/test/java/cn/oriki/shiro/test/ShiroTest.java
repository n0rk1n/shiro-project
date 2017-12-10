package cn.oriki.shiro.test;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

import junit.framework.Assert;

public class ShiroTest {

    @Test
    public void test() {
        // 获取Subject
        Subject subject = this.getSubject();

        // 初始化token
        UsernamePasswordToken token = new UsernamePasswordToken("zhangsan", "123456");

        // 登录
        subject.login(token);

        // 断言是否登录
        Assert.assertTrue(subject.isAuthenticated());

        // 登出
        subject.logout();
    }

    private Subject getSubject() {
        // 使用IniSecurityManager获取SecurityManager对象
        IniSecurityManagerFactory securityManagerFactory = new IniSecurityManagerFactory("classpath:shiro-securitymanager.ini");
        SecurityManager securityManager = securityManagerFactory.getInstance();

        SecurityUtils.setSecurityManager(securityManager);

        return SecurityUtils.getSubject();
    }
}
