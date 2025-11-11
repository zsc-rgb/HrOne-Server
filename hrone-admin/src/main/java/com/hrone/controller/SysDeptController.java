package com.hrone.controller;

import com.hrone.common.core.controller.BaseController;
import com.hrone.common.core.domain.AjaxResult;
import com.hrone.system.domain.SysDept;
import com.hrone.system.service.ISysDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 部门管理 Controller
 * 
 * @author hrone
 */
@RestController
@RequestMapping("/system/dept")
public class SysDeptController extends BaseController {

    @Autowired
    private ISysDeptService deptService;

    /**
     * 查询部门列表
     * 
     * GET /system/dept/list
     */
    @GetMapping("/list")
    public AjaxResult list(SysDept dept) {
        List<SysDept> depts = deptService.selectDeptList(dept);
        return AjaxResult.success(depts);
    }

    /**
     * 查询部门树结构
     * 
     * GET /system/dept/tree
     */
    @GetMapping("/tree")
    public AjaxResult tree(SysDept dept) {
        List<SysDept> depts = deptService.selectDeptList(dept);
        List<SysDept> tree = deptService.buildDeptTree(depts);
        return AjaxResult.success(tree);
    }

    /**
     * 根据部门ID查询信息
     * 
     * GET /system/dept/{deptId}
     */
    @GetMapping("/{deptId}")
    public AjaxResult getInfo(@PathVariable Long deptId) {
        SysDept dept = deptService.selectDeptById(deptId);
        return AjaxResult.success(dept);
    }

    /**
     * 新增部门
     * 
     * POST /system/dept
     */
    @PostMapping
    public AjaxResult add(@RequestBody SysDept dept) {
        return toAjax(deptService.insertDept(dept));
    }

    /**
     * 修改部门
     * 
     * PUT /system/dept
     */
    @PutMapping
    public AjaxResult edit(@RequestBody SysDept dept) {
        return toAjax(deptService.updateDept(dept));
    }

    /**
     * 删除部门
     * 
     * DELETE /system/dept/{deptId}
     */
    @DeleteMapping("/{deptId}")
    public AjaxResult remove(@PathVariable Long deptId) {
        return toAjax(deptService.deleteDeptById(deptId));
    }
}

