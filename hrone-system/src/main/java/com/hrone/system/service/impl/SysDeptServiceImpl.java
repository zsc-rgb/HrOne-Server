package com.hrone.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hrone.common.constant.UserConstants;
import com.hrone.common.exception.ServiceException;
import com.hrone.common.utils.StringUtils;
import com.hrone.common.utils.TreeUtils;
import com.hrone.system.domain.SysDept;
import com.hrone.system.domain.SysUser;
import com.hrone.system.mapper.SysDeptMapper;
import com.hrone.system.mapper.SysUserMapper;
import com.hrone.system.service.ISysDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 部门管理 Service实现
 * 
 * @author hrone
 */
@Service
public class SysDeptServiceImpl extends ServiceImpl<SysDeptMapper, SysDept> implements ISysDeptService {

    @Autowired
    private SysDeptMapper deptMapper;

    @Autowired
    private SysUserMapper userMapper;

    /**
     * 查询部门列表
     */
    @Override
    public List<SysDept> selectDeptList(SysDept dept) {
        LambdaQueryWrapper<SysDept> wrapper = new LambdaQueryWrapper<>();
        
        // 条件查询
        if (dept.getParentId() != null) {
            wrapper.eq(SysDept::getParentId, dept.getParentId());
        }
        if (StringUtils.isNotEmpty(dept.getDeptName())) {
            wrapper.like(SysDept::getDeptName, dept.getDeptName());
        }
        if (StringUtils.isNotEmpty(dept.getStatus())) {
            wrapper.eq(SysDept::getStatus, dept.getStatus());
        }
        
        // 排序
        wrapper.orderByAsc(SysDept::getParentId, SysDept::getOrderNum);
        
        return deptMapper.selectList(wrapper);
    }

    /**
     * 构建部门树结构
     */
    @Override
    public List<SysDept> buildDeptTree(List<SysDept> depts) {
        return TreeUtils.buildTree(
            depts,
            SysDept::getDeptId,
            SysDept::getParentId,
            SysDept::getChildren,
            SysDept::setChildren,
            0L
        );
    }

    /**
     * 根据ID查询部门
     */
    @Override
    public SysDept selectDeptById(Long deptId) {
        return deptMapper.selectById(deptId);
    }

    /**
     * 查询所有子部门ID
     */
    @Override
    public List<Long> selectChildDeptIds(Long deptId) {
        SysDept dept = deptMapper.selectById(deptId);
        if (dept == null) {
            return new ArrayList<>();
        }
        
        List<SysDept> allDepts = deptMapper.selectList(null);
        List<Object> ids = TreeUtils.getChildIds(allDepts, dept, SysDept::getDeptId, SysDept::getParentId);
        
        return ids.stream().map(id -> (Long) id).collect(Collectors.toList());
    }

    /**
     * 是否存在子节点
     */
    @Override
    public boolean hasChildByDeptId(Long deptId) {
        LambdaQueryWrapper<SysDept> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysDept::getParentId, deptId);
        return deptMapper.selectCount(wrapper) > 0;
    }

    /**
     * 查询部门是否存在用户
     */
    @Override
    public boolean checkDeptExistUser(Long deptId) {
        LambdaQueryWrapper<SysUser> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysUser::getDeptId, deptId);
        return userMapper.selectCount(wrapper) > 0;
    }

    /**
     * 校验部门名称是否唯一
     */
    @Override
    public boolean checkDeptNameUnique(SysDept dept) {
        Long deptId = dept.getDeptId() == null ? -1L : dept.getDeptId();
        
        LambdaQueryWrapper<SysDept> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysDept::getDeptName, dept.getDeptName());
        wrapper.eq(SysDept::getParentId, dept.getParentId());
        
        SysDept existDept = deptMapper.selectOne(wrapper);
        
        return existDept == null || existDept.getDeptId().equals(deptId);
    }

    /**
     * 新增部门
     */
    @Override
    public int insertDept(SysDept dept) {
        // 校验部门名称
        if (!checkDeptNameUnique(dept)) {
            throw new ServiceException("部门名称已存在");
        }
        
        // 生成祖级列表
        SysDept parentDept = deptMapper.selectById(dept.getParentId());
        if (parentDept != null) {
            dept.setAncestors(parentDept.getAncestors() + "," + dept.getParentId());
        } else {
            dept.setAncestors("0");
        }
        
        return deptMapper.insert(dept);
    }

    /**
     * 修改部门
     */
    @Override
    public int updateDept(SysDept dept) {
        // 校验部门名称
        if (!checkDeptNameUnique(dept)) {
            throw new ServiceException("部门名称已存在");
        }
        
        // 如果修改了父部门，需要更新祖级列表
        SysDept oldDept = deptMapper.selectById(dept.getDeptId());
        SysDept newParentDept = deptMapper.selectById(dept.getParentId());
        
        if (oldDept != null && newParentDept != null) {
            String newAncestors = newParentDept.getAncestors() + "," + newParentDept.getDeptId();
            String oldAncestors = oldDept.getAncestors();
            dept.setAncestors(newAncestors);
            updateDeptChildren(dept.getDeptId(), newAncestors, oldAncestors);
        }
        
        return deptMapper.updateById(dept);
    }

    /**
     * 修改子元素关系
     */
    private void updateDeptChildren(Long deptId, String newAncestors, String oldAncestors) {
        LambdaQueryWrapper<SysDept> wrapper = new LambdaQueryWrapper<>();
        wrapper.apply("FIND_IN_SET({0}, ancestors)", deptId);
        
        List<SysDept> children = deptMapper.selectList(wrapper);
        for (SysDept child : children) {
            child.setAncestors(child.getAncestors().replaceFirst(oldAncestors, newAncestors));
        }
        
        if (children.size() > 0) {
            updateBatchById(children);
        }
    }

    /**
     * 删除部门
     */
    @Override
    public int deleteDeptById(Long deptId) {
        // 检查是否有子部门
        if (hasChildByDeptId(deptId)) {
            throw new ServiceException("存在下级部门，不允许删除");
        }
        
        // 检查是否有用户
        if (checkDeptExistUser(deptId)) {
            throw new ServiceException("部门存在用户，不允许删除");
        }
        
        return deptMapper.deleteById(deptId);
    }
}

