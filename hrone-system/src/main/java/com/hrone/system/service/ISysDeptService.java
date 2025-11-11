package com.hrone.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hrone.system.domain.SysDept;

import java.util.List;

/**
 * 部门管理 Service接口
 * 
 * @author hrone
 */
public interface ISysDeptService extends IService<SysDept> {

    /**
     * 查询部门列表
     * 
     * @param dept 部门信息
     * @return 部门列表
     */
    List<SysDept> selectDeptList(SysDept dept);

    /**
     * 查询部门树结构
     * 
     * @param dept 部门信息
     * @return 树结构列表
     */
    List<SysDept> buildDeptTree(List<SysDept> depts);

    /**
     * 根据ID查询部门
     * 
     * @param deptId 部门ID
     * @return 部门信息
     */
    SysDept selectDeptById(Long deptId);

    /**
     * 根据ID查询所有子部门
     * 
     * @param deptId 部门ID
     * @return 子部门ID列表
     */
    List<Long> selectChildDeptIds(Long deptId);

    /**
     * 是否存在子节点
     * 
     * @param deptId 部门ID
     * @return 结果
     */
    boolean hasChildByDeptId(Long deptId);

    /**
     * 查询部门是否存在用户
     * 
     * @param deptId 部门ID
     * @return 结果 true=存在 false=不存在
     */
    boolean checkDeptExistUser(Long deptId);

    /**
     * 校验部门名称是否唯一
     * 
     * @param dept 部门信息
     * @return 结果
     */
    boolean checkDeptNameUnique(SysDept dept);

    /**
     * 新增部门
     * 
     * @param dept 部门信息
     * @return 结果
     */
    int insertDept(SysDept dept);

    /**
     * 修改部门
     * 
     * @param dept 部门信息
     * @return 结果
     */
    int updateDept(SysDept dept);

    /**
     * 删除部门
     * 
     * @param deptId 部门ID
     * @return 结果
     */
    int deleteDeptById(Long deptId);
}

