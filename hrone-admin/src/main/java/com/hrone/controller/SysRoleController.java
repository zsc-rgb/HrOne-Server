package com.hrone.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hrone.common.core.controller.BaseController;
import com.hrone.common.core.domain.AjaxResult;
import com.hrone.common.core.page.TableDataInfo;
import com.hrone.system.domain.SysRole;
import com.hrone.system.service.ISysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 角色管理 Controller
 * 
 * @author hrone
 */
@RestController
@RequestMapping("/system/role")
public class SysRoleController extends BaseController {

    @Autowired
    private ISysRoleService roleService;

    /**
     * 查询角色列表
     * 
     * GET /system/role/list
     */
    @GetMapping("/list")
    public AjaxResult list(SysRole role) {
        List<SysRole> list = roleService.selectRoleList(role);
        return AjaxResult.success(list);
    }

    /**
     * 分页查询角色列表
     * 
     * GET /system/role/page?pageNum=1&pageSize=10
     */
    @GetMapping("/page")
    public TableDataInfo page(SysRole role,
                               @RequestParam(defaultValue = "1") Integer pageNum,
                               @RequestParam(defaultValue = "10") Integer pageSize) {
        Page<SysRole> page = new Page<>(pageNum, pageSize);
        
        IPage<SysRole> result = roleService.page(page);
        
        return getDataTable(result);
    }

    /**
     * 根据角色ID查询详细信息
     * 
     * GET /system/role/{roleId}
     */
    @GetMapping("/{roleId}")
    public AjaxResult getInfo(@PathVariable Long roleId) {
        SysRole role = roleService.selectRoleById(roleId);
        return AjaxResult.success(role);
    }

    /**
     * 新增角色
     * 
     * POST /system/role
     */
    @PostMapping
    public AjaxResult add(@RequestBody SysRole role) {
        return toAjax(roleService.insertRole(role));
    }

    /**
     * 修改角色
     * 
     * PUT /system/role
     */
    @PutMapping
    public AjaxResult edit(@RequestBody SysRole role) {
        return toAjax(roleService.updateRole(role));
    }

    /**
     * 删除角色
     * 
     * DELETE /system/role/{roleId}
     */
    @DeleteMapping("/{roleId}")
    public AjaxResult remove(@PathVariable Long roleId) {
        return toAjax(roleService.deleteRoleById(roleId));
    }

    /**
     * 批量删除角色
     * 
     * DELETE /system/role/batch
     */
    @DeleteMapping("/batch")
    public AjaxResult removeBatch(@RequestBody Long[] roleIds) {
        return toAjax(roleService.deleteRoleByIds(roleIds));
    }
}

