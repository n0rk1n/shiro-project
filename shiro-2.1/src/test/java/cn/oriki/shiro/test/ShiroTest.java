package cn.oriki.shiro.test;

import cn.oriki.shiro.realm.MyRealm;
import junit.framework.Assert;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.subject.Subject;

import java.util.Arrays;
import java.util.List;

public class ShiroTest {

    /**
     * 使用DefaultSecurityManager完成身份验证操作
     */
    @org.junit.Test
    public void test() {
        // 封装Realm集合
        List<Realm> realmList = Arrays.asList(new MyRealm());

        // 获取Subject对象
        Subject subject = getSubject(realmList);

        // 登录
        subject.login(new UsernamePasswordToken("zhangsan", "123456"));

        // 断言用户已经登录
        Assert.assertTrue(subject.isAuthenticated());

        // 登出
        subject.logout();

    }

    /**
     * 根据给定的Realm集合生成Subject（用户）对象
     *  
     *
     * @param list
     * @return
     */
    private Subject getSubject(List<Realm> list) {
        // 初始化SecurityManager
        DefaultSecurityManager securityManager = new DefaultSecurityManager();

        // 设置Realm
        securityManager.setRealms(list);

        // 在SecurityUtil中注入securityManager
        SecurityUtils.setSecurityManager(securityManager);

        return SecurityUtils.getSubject();
    }
}
