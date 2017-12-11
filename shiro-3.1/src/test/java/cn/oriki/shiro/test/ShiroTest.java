package cn.oriki.shiro.test;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

import cn.oriki.shiro.realm.MyRealm;

public class ShiroTest {

    @Test
    public void test() {
        Subject subject = this.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken("zhangsan", "123456");

        // 登录
        subject.login(token);

        subject.checkRole("boss");// 检查登录用户是否有boss权限

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
        DefaultSecurityManager securityManager = new DefaultSecurityManager();

        securityManager.setRealm(new MyRealm());

        SecurityUtils.setSecurityManager(securityManager);

        return SecurityUtils.getSubject();
    }
}
