package cn.oriki.shiro.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import cn.oriki.shiro.utils.MyPasswordService;

public class MyRealm extends AuthorizingRealm {

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;

        String username = usernamePasswordToken.getUsername();

        String password = new String(usernamePasswordToken.getPassword());

        // 定义盐，盐是存储在数据库中的盐值
        String salt = "salt";

        // 对密码进行加密，加密的盐为salt
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(username,
                MyPasswordService.getNewPassword(password, salt), super.getName());

        // 设置传入的盐为salt，以后这个数据是注册时随着加密密码一块存储的盐
        authenticationInfo.setCredentialsSalt(ByteSource.Util.bytes(salt));

        return authenticationInfo;
    }

}
