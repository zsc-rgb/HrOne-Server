package com.hrone.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hrone.system.domain.SysRole;

import java.util.List;

/**
 * 角色管理 Service接口
 * 
 * @author hrone
 */
public interface ISysRoleService extends IService<SysRole> {

    /**
     * 查询角色列表
     * 
     * @param role 角色信息
     * @return 角色列表
     */
    List<SysRole> selectRoleList(SysRole role);

    /**
     * 根据ID查询角色
     * 
     * @param roleId 角色ID
     * @return 角色信息
     */
    SysRole selectRoleById(Long roleId);

    /**
     * 根据用户ID查询角色
     * 
     * @param userId 用户ID
     * @return 角色列表
     */
    List<SysRole> selectRolesByUserId(Long userId);

    /**
     * 校验角色名称是否唯一
     * 
     * @param role 角色信息
     * @return 结果
     */
    boolean checkRoleNameUnique(SysRole role);

    /**
     * 校验角色权限是否唯一
     * 
     * @param role 角色信息
     * @return 结果
     */
    boolean checkRoleKeyUnique(SysRole role);

    /**
     * 新增角色
     * 
     * @param role 角色信息
     * @return 结果
     */
    int insertRole(SysRole role);

    /**
     * 修改角色
     * 
     * @param role 角色信息
     * @return 结果
     */
    int updateRole(SysRole role);

    /**
     * 删除角色
     * 
     * @param roleId 角色ID
     * @return 结果
     */
    int deleteRoleById(Long roleId);

    /**
     * 批量删除角色
     * 
     * @param roleIds 角色ID数组
     * @return 结果
     */
    int deleteRoleByIds(Long[] roleIds);
}

