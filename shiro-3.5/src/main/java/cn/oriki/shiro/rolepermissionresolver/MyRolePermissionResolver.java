package cn.oriki.shiro.rolepermissionresolver;

import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.permission.RolePermissionResolver;
import org.apache.shiro.authz.permission.WildcardPermission;

import java.util.Arrays;
import java.util.Collection;

public class MyRolePermissionResolver implements RolePermissionResolver {

    @Override
    public Collection<Permission> resolvePermissionsInRole(String roleString) {
        // 如果是boss角色，赋予user模块下的所有权限
        if ("boss".equals(roleString)) {
            return Arrays.asList(new WildcardPermission("user:*"));
        }
        return null;
    }

}
