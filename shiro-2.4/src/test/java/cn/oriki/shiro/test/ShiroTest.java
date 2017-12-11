package cn.oriki.shiro.test;

import cn.oriki.shiro.realm.MyRealm;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.subject.Subject;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class ShiroTest {

    @Test
    public void test() {
        // 获取Subject
        Subject subject = getSubject();

        // 初始化token
        UsernamePasswordToken token = new UsernamePasswordToken("zhangsan", "123456");

        // 登录
        subject.login(token);

        // 断言用户是否已经登录
        Assert.assertTrue(subject.isAuthenticated());

        // 登出
        subject.logout();

    }

    private Subject getSubject() {
        DefaultSecurityManager securityManager = new DefaultSecurityManager();
        securityManager.setRealms(Arrays.asList(new MyRealm()));
        SecurityUtils.setSecurityManager(securityManager);
        return SecurityUtils.getSubject();
    }

}
