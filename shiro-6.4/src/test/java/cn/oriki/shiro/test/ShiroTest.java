package cn.oriki.shiro.test;

import cn.oriki.shiro.realm.MyRealm;
import cn.oriki.shiro.realm.MyRealm2;
import cn.oriki.shiro.realm.MyRealm3;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

import java.util.Arrays;
import java.util.Set;

public class ShiroTest {

    @Test
    public void test() {
        Subject subject = getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken("zhangsan", "123456");

        subject.login(token);

        // 获取所有凭证
        PrincipalCollection principals = subject.getPrincipals();
        for (Object object : principals) {
            System.out.println(object);
            // zhangsan
            // 哆啦A梦
        }

        // 打印主要凭证，随机返回一个
        Object primaryPrincipal = principals.getPrimaryPrincipal();
        System.out.println(primaryPrincipal);

        // 返回认证成功的所有realm的名字
        Set<String> realmNames = principals.getRealmNames();
        System.out.println(realmNames);// [myRealm1, myRealm2, myRealm3]

        subject.logout();
    }

    private org.apache.shiro.mgt.SecurityManager getSecurityManager() {
        DefaultSecurityManager securityManager = new DefaultSecurityManager();

        securityManager.setRealms(Arrays.asList(new MyRealm(), new MyRealm2(), new MyRealm3()));

        return securityManager;
    }

    private Subject getSubject() {
        SecurityUtils.setSecurityManager(getSecurityManager());

        return SecurityUtils.getSubject();
    }
}
