package cn.oriki.shiro.service;

import cn.oriki.shiro.entity.Permission;

/**
  * 权限业务接口
  * 
  * @author oriki
  *
  */
public interface PermissionService {
    /**
     * 创建权限
     * 
     * @param permission
     * @return
     */
    public Permission createPermission(Permission permission);

    /**
     * 删除权限
     * 
     * @param permissionId
     */
    public void deletePermission(Long permissionId);
}
