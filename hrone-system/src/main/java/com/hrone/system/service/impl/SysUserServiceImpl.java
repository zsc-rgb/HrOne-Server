package com.hrone.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hrone.common.constant.UserConstants;
import com.hrone.common.exception.ServiceException;
import com.hrone.common.utils.StringUtils;
import com.hrone.system.domain.SysUser;
import com.hrone.system.mapper.SysUserMapper;
import com.hrone.system.service.ISysUserService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 用户Service实现类
 * 
 * 功能说明：
 * 1. 继承ServiceImpl实现IService的方法
 * 2. 实现自定义的业务方法
 * 3. 处理业务逻辑和异常
 * 
 * 技术要点：
 * - ServiceImpl<SysUserMapper, SysUser>：指定Mapper和Entity
 * - 使用LambdaQueryWrapper构建查询条件
 * - 抛出ServiceException处理业务异常
 * 
 * @author hrone
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {
    
    /**
     * 查询用户列表
     * 
     * @param user 用户信息
     * @return 用户列表
     */
    @Override
    @com.hrone.framework.aspectj.DataScope
    public List<SysUser> selectUserList(SysUser user) {
        LambdaQueryWrapper<SysUser> wrapper = new LambdaQueryWrapper<>();
        
        // 构建查询条件
        if (StringUtils.isNotEmpty(user.getUserName())) {
            wrapper.like(SysUser::getUserName, user.getUserName());
        }
        if (StringUtils.isNotEmpty(user.getPhone())) {
            wrapper.eq(SysUser::getPhone, user.getPhone());
        }
        if (StringUtils.isNotEmpty(user.getStatus())) {
            wrapper.eq(SysUser::getStatus, user.getStatus());
        }
        // 数据权限：本部门数据（演示）
        Long scopeDeptId = com.hrone.framework.aspectj.DataScopeContext.getDeptId();
        if (scopeDeptId != null) {
            wrapper.eq(SysUser::getDeptId, scopeDeptId);
        }
        
        // 排除已删除的数据
        wrapper.eq(SysUser::getDelFlag, "0");
        
        // 按创建时间倒序
        wrapper.orderByDesc(SysUser::getCreateTime);
        
        return this.list(wrapper);
    }
    
    /**
     * 根据用户ID查询用户
     * 
     * @param userId 用户ID
     * @return 用户对象
     */
    @Override
    public SysUser selectUserById(Long userId) {
        if (userId == null || userId <= 0) {
            throw new ServiceException("用户ID不能为空", 400);
        }
        
        SysUser user = this.getById(userId);
        if (user == null) {
            throw new ServiceException("用户不存在", 404);
        }
        
        return user;
    }
    
    /**
     * 根据用户名查询用户
     * 
     * @param userName 用户名
     * @return 用户对象
     */
    @Override
    public SysUser selectUserByUserName(String userName) {
        if (StringUtils.isEmpty(userName)) {
            throw new ServiceException("用户名不能为空", 400);
        }
        
        LambdaQueryWrapper<SysUser> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysUser::getUserName, userName);
        wrapper.eq(SysUser::getDelFlag, "0");
        
        return this.getOne(wrapper);
    }
    
    /**
     * 校验用户名是否唯一
     * 
     * @param userName 用户名
     * @return true=唯一，false=不唯一
     */
    @Override
    public boolean checkUserNameUnique(String userName) {
        LambdaQueryWrapper<SysUser> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysUser::getUserName, userName);
        wrapper.eq(SysUser::getDelFlag, "0");
        
        long count = this.count(wrapper);
        return count == 0;
    }
    
    /**
     * 新增用户
     * 
     * @param user 用户信息
     * @return 结果
     */
    @Override
    public int insertUser(SysUser user) {
        // 校验用户名唯一性
        if (!checkUserNameUnique(user.getUserName())) {
            throw new ServiceException("用户名已存在");
        }
        
        // 设置默认值
        if (StringUtils.isEmpty(user.getStatus())) {
            user.setStatus(UserConstants.USER_NORMAL);
        }
        if (StringUtils.isEmpty(user.getDelFlag())) {
            user.setDelFlag("0");
        }
        
        // 设置创建时间
        user.setCreateTime(new Date());
        
        // 保存用户
        boolean result = this.save(user);
        return result ? 1 : 0;
    }
    
    /**
     * 修改用户
     * 
     * @param user 用户信息
     * @return 结果
     */
    @Override
    public int updateUser(SysUser user) {
        if (user.getUserId() == null) {
            throw new ServiceException("用户ID不能为空", 400);
        }
        
        // 检查用户是否存在
        SysUser existUser = this.getById(user.getUserId());
        if (existUser == null) {
            throw new ServiceException("用户不存在", 404);
        }
        
        // 不能修改超级管理员
        if (UserConstants.ADMIN_ID.equals(user.getUserId())) {
            throw new ServiceException("不允许修改超级管理员", 403);
        }
        
        // 设置更新时间
        user.setUpdateTime(new Date());
        
        // 更新用户
        boolean result = this.updateById(user);
        return result ? 1 : 0;
    }
    
    /**
     * 删除用户
     * 
     * @param userId 用户ID
     * @return 结果
     */
    @Override
    public int deleteUserById(Long userId) {
        if (userId == null || userId <= 0) {
            throw new ServiceException("用户ID不能为空", 400);
        }
        
        // 不能删除超级管理员
        if (UserConstants.ADMIN_ID.equals(userId)) {
            throw new ServiceException("不允许删除超级管理员", 403);
        }
        
        // 检查用户是否存在
        SysUser user = this.getById(userId);
        if (user == null) {
            throw new ServiceException("用户不存在", 404);
        }
        
        // 逻辑删除（设置删除标志）
        user.setDelFlag("2");
        user.setUpdateTime(new Date());
        boolean result = this.updateById(user);
        
        return result ? 1 : 0;
    }
}

