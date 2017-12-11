package cn.oriki.shiro.dao;

import cn.oriki.shiro.entity.Role;

/**
  * role的持久层接口
  * 
  * @author oriki
  *
  */
public interface RoleDao {

    public Role createRole(Role role);

    public void deleteRole(Long roleId);

    /**
     * 绑定权限到角色
     * 
     * @param roleId
     * @param permissionIds
     */
    public void correlationPermissions(Long roleId, Long... permissionIds);

    /**
     * 解绑权限到角色
     * 
     * @param roleId
     * @param permissionIds
     */
    public void uncorrelationPermissions(Long roleId, Long... permissionIds);

}
