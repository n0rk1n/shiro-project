package cn.oriki.shiro.service.impl;

import cn.oriki.shiro.dao.PermissionDao;
import cn.oriki.shiro.dao.impl.PermissionDaoImpl;
import cn.oriki.shiro.entity.Permission;
import cn.oriki.shiro.service.PermissionService;

public class PermissionServiceImpl implements PermissionService {

    private PermissionDao permissionDao = new PermissionDaoImpl();

    public Permission createPermission(Permission permission) {
        return permissionDao.createPermission(permission);
    }

    public void deletePermission(Long permissionId) {
        permissionDao.deletePermission(permissionId);
    }
}
