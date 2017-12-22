package cn.oriki.shiro.test;

import cn.oriki.shiro.realm.MyRealm;
import cn.oriki.shiro.sessiondao.MySessionDAO;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.session.mgt.DefaultSessionManager;
import org.apache.shiro.session.mgt.SessionManager;
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

        subject.logout();
    }

    private SecurityManager getSecurityManager() {
        DefaultSecurityManager securityManager = new DefaultSecurityManager();

        securityManager.setSessionManager(getSessionManager());

        securityManager.setRealms(Arrays.asList(getRealm()));

        return securityManager;
    }

    private SessionManager getSessionManager() {
        DefaultSessionManager sessionManager = new DefaultSessionManager();

        sessionManager.setSessionDAO(getSessionDAO());

        return sessionManager;
    }

    private SessionDAO getSessionDAO() {
        return new MySessionDAO();
    }

    private Realm getRealm() {
        return new MyRealm();
    }
}
