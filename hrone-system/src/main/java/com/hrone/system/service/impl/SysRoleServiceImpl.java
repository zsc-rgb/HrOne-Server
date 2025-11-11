package com.hrone.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hrone.common.exception.ServiceException;
import com.hrone.common.utils.StringUtils;
import com.hrone.system.domain.SysRole;
import com.hrone.system.mapper.SysRoleMapper;
import com.hrone.system.service.ISysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * 角色管理 Service实现
 * 
 * @author hrone
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements ISysRoleService {

    @Autowired
    private SysRoleMapper roleMapper;

    /**
     * 查询角色列表
     */
    @Override
    public List<SysRole> selectRoleList(SysRole role) {
        LambdaQueryWrapper<SysRole> wrapper = new LambdaQueryWrapper<>();
        
        if (StringUtils.isNotEmpty(role.getRoleName())) {
            wrapper.like(SysRole::getRoleName, role.getRoleName());
        }
        if (StringUtils.isNotEmpty(role.getRoleKey())) {
            wrapper.like(SysRole::getRoleKey, role.getRoleKey());
        }
        if (StringUtils.isNotEmpty(role.getStatus())) {
            wrapper.eq(SysRole::getStatus, role.getStatus());
        }
        
        wrapper.orderByAsc(SysRole::getRoleSort);
        
        return roleMapper.selectList(wrapper);
    }

    /**
     * 根据ID查询角色
     */
    @Override
    public SysRole selectRoleById(Long roleId) {
        return roleMapper.selectById(roleId);
    }

    /**
     * 根据用户ID查询角色（简化版本，实际需要关联查询）
     */
    @Override
    public List<SysRole> selectRolesByUserId(Long userId) {
        // TODO: 实际需要通过 sys_user_role 关联表查询
        // 这里暂时返回所有角色
        return roleMapper.selectList(null);
    }

    /**
     * 校验角色名称是否唯一
     */
    @Override
    public boolean checkRoleNameUnique(SysRole role) {
        Long roleId = role.getRoleId() == null ? -1L : role.getRoleId();
        
        LambdaQueryWrapper<SysRole> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysRole::getRoleName, role.getRoleName());
        
        SysRole existRole = roleMapper.selectOne(wrapper);
        
        return existRole == null || existRole.getRoleId().equals(roleId);
    }

    /**
     * 校验角色权限是否唯一
     */
    @Override
    public boolean checkRoleKeyUnique(SysRole role) {
        Long roleId = role.getRoleId() == null ? -1L : role.getRoleId();
        
        LambdaQueryWrapper<SysRole> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysRole::getRoleKey, role.getRoleKey());
        
        SysRole existRole = roleMapper.selectOne(wrapper);
        
        return existRole == null || existRole.getRoleId().equals(roleId);
    }

    /**
     * 新增角色
     */
    @Override
    public int insertRole(SysRole role) {
        // 校验角色名称
        if (!checkRoleNameUnique(role)) {
            throw new ServiceException("角色名称已存在");
        }
        
        // 校验角色权限
        if (!checkRoleKeyUnique(role)) {
            throw new ServiceException("角色权限已存在");
        }
        
        return roleMapper.insert(role);
    }

    /**
     * 修改角色
     */
    @Override
    public int updateRole(SysRole role) {
        // 不能修改超级管理员
        if (SysRole.isAdmin(role.getRoleId())) {
            throw new ServiceException("不允许修改超级管理员角色");
        }
        
        // 校验角色名称
        if (!checkRoleNameUnique(role)) {
            throw new ServiceException("角色名称已存在");
        }
        
        // 校验角色权限
        if (!checkRoleKeyUnique(role)) {
            throw new ServiceException("角色权限已存在");
        }
        
        return roleMapper.updateById(role);
    }

    /**
     * 删除角色
     */
    @Override
    public int deleteRoleById(Long roleId) {
        // 不能删除超级管理员
        if (SysRole.isAdmin(roleId)) {
            throw new ServiceException("不允许删除超级管理员角色");
        }
        
        return roleMapper.deleteById(roleId);
    }

    /**
     * 批量删除角色
     */
    @Override
    public int deleteRoleByIds(Long[] roleIds) {
        for (Long roleId : roleIds) {
            // 不能删除超级管理员
            if (SysRole.isAdmin(roleId)) {
                throw new ServiceException("不允许删除超级管理员角色");
            }
        }
        
        return roleMapper.deleteBatchIds(Arrays.asList(roleIds));
    }
}

