package cn.oriki.shiro.test;

import cn.oriki.shiro.realm.MyRealm;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.session.mgt.DefaultSessionManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

import java.util.Arrays;

public class ShiroTest {

    @Test
    public void test(){
        SecurityUtils.setSecurityManager(getSecurityManager());

        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken("zhangsan", "123456");

        subject.login(token);

        subject.isAuthenticated();

        subject.logout();
    }

    private SecurityManager getSecurityManager() {
        DefaultSecurityManager securityManager = new DefaultSecurityManager();
        securityManager.setRealms(Arrays.asList(getRealm()));

        securityManager.setSessionManager(getSessionManager());

        return securityManager;
    }

    private SessionManager getSessionManager(){
        DefaultSessionManager sessionManager = new DefaultSessionManager();
        sessionManager.setSessionDAO(getSessionDao());
        //添加会话管理器的缓存
        sessionManager.setCacheManager(getCacheManager());
        return sessionManager;
    }

    private CacheManager getCacheManager() {
        EhCacheManager cacheManager = new EhCacheManager();
        cacheManager.setCacheManagerConfigFile("classpath:ehcache.xml");
        return cacheManager;
    }

    private SessionDAO getSessionDao() {
        return new EnterpriseCacheSessionDAO();
    }

    private Realm getRealm() {
        return new MyRealm();
    }
}
