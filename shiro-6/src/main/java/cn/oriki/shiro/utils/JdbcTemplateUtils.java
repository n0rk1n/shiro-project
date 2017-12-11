package cn.oriki.shiro.utils;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

/**
  * 单例获取JdbcTemplate对象
  * 
  * @author oriki
  *
  */
public class JdbcTemplateUtils {

    private static JdbcTemplate jdbcTemplate;

    private JdbcTemplateUtils() {
    }

    public static JdbcTemplate jdbcTemplate() {
        if (jdbcTemplate == null) {
            // 创建JdbcTemplate
            jdbcTemplate = createJdbcTemplate();
        }
        return jdbcTemplate;
    }

    private static JdbcTemplate createJdbcTemplate() {
        DruidDataSource ds = new DruidDataSource();
        ds.setDriverClassName("com.mysql.jdbc.Driver");
        ds.setUrl("jdbc:mysql://localhost:3306/db_shiro");
        ds.setUsername("root");
        ds.setPassword("4869");
        return new JdbcTemplate(ds);
    }

}
