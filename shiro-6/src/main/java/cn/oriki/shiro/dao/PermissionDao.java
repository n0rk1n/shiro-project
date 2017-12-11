package cn.oriki.shiro.dao;

import cn.oriki.shiro.entity.Permission;

/**
 *  * 权限持久层接口
 *  * 
 *  * @author oriki
 *  *
 *  
 */
public interface PermissionDao {

    /**
     * 创建权限
     *  
     *
     * @param permission
     * @return
     */
    Permission createPermission(Permission permission);

    /**
     * 删除权限
     *  
     *
     * @param permissionId
     */
    void deletePermission(Long permissionId);

}
