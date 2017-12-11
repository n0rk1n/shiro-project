package cn.oriki.shiro.realm;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.Collection;

public class MyRealm extends AuthorizingRealm {

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        Collection<?> realms = principals.fromRealm(super.getName());

        // 如果是zhangsan，授予用户boss角色
        if (realms.contains("zhangsan")) {
            authorizationInfo.addRole("boss");
        }
        // 如果登录用户是lisi，授予staff角色
        if (realms.contains("lisi")) {
            authorizationInfo.addRole("staff");
        }

        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        // 放行所有用户，返回他们的登录账号和密码
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
        String username = usernamePasswordToken.getUsername();
        String password = new String(usernamePasswordToken.getPassword());
        return new SimpleAuthenticationInfo(username, password, super.getName());
    }

}
