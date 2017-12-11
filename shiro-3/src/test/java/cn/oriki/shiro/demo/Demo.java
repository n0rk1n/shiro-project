package cn.oriki.shiro.demo;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

public class Demo {

    @Test
    public void test() {
        Subject subject = SecurityUtils.getSubject();
        if (subject.hasRole("admin")) {
            //有权限
        } else {
            //无权限
        }
    }

    @RequiresRoles("admin")
    public void hello() {
        //有权限
    }
}
