package cn.oriki.shiro.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.realm.Realm;

public class MyRealm implements Realm {

    /*
     * 获取该Realm的名称
     *
     * @see org.apache.shiro.realm.Realm#getName()
     */
    @Override
    public String getName() {
        return this.getClass().getName();
    }

    /*
     * 判断token是否支持该Realm
     *
     * @see org.apache.shiro.realm.Realm#supports(org.apache.shiro.authc.
     * AuthenticationToken)
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof UsernamePasswordToken;
    }

    /*
     * 用户登录校验
     *
     * @see
     * org.apache.shiro.realm.Realm#getAuthenticationInfo(org.apache.shiro.authc.
     * AuthenticationToken)
     */
    @Override
    public AuthenticationInfo getAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
        String username = (String) usernamePasswordToken.getPrincipal();
        String password = new String(usernamePasswordToken.getPassword());

        if (!"zhangsan".equals(username)) {
            throw new UnknownAccountException();
        }
        if (!"123456".equals(password)) {
            throw new IncorrectCredentialsException();
        }

        return new SimpleAuthenticationInfo(username, password, this.getName());
    }

}
