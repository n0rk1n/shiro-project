package cn.oriki.shiro.test;

import com.alibaba.druid.pool.DruidDataSource;
import junit.framework.Assert;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

import javax.sql.DataSource;

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
        DefaultSecurityManager securityManager = new DefaultSecurityManager();
        securityManager.setRealm(getJdbcRealm());

        SecurityUtils.setSecurityManager(securityManager);

        return SecurityUtils.getSubject();
    }

    /**
     * 获取JdbcRealm
     *  
     *
     * @return
     */
    private JdbcRealm getJdbcRealm() {
        JdbcRealm jdbcRealm = new JdbcRealm();
        jdbcRealm.setDataSource(getDataSource());
        return jdbcRealm;
    }

    /**
     * 获取数据源
     *  
     *
     * @return
     */
    private DataSource getDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/db_shiro");
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUsername("root");
        dataSource.setPassword("4869");
        return dataSource;
    }
}
