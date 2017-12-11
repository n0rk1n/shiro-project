package cn.oriki.shiro.test;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.subject.Subject;
import org.junit.Assert;
import org.junit.Test;

import cn.oriki.shiro.realm.MyRealm;

public class ShiroTest {

    @Test
    public void test() {
        Subject subject = getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken("zhangsan", "123456");

        subject.login(token);

        Assert.assertTrue(subject.isAuthenticated());

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

        // 设置自定义域
        securityManager.setRealm(getRealm());

        return securityManager;
    }

    /**
     * 获取自定义域
     *  
     *
     * @return
     */
    private Realm getRealm() {
        MyRealm realm = new MyRealm();

        // 设置密码校验的credentialsMatcher
        realm.setCredentialsMatcher(getCredentialsMatcher());

        return realm;
    }

    /**
     * 获取密码校验的credentialsMatcher
     *  
     *
     * @return
     */
    private CredentialsMatcher getCredentialsMatcher() {
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();

        // 设置散列算法为md5
        credentialsMatcher.setHashAlgorithmName("MD5");

        // 设置迭代次数为1
        credentialsMatcher.setHashIterations(1);

        return credentialsMatcher;
    }
}
