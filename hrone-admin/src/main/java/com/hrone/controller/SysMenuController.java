package com.hrone.controller;

import com.hrone.common.core.controller.BaseController;
import com.hrone.common.core.domain.AjaxResult;
import com.hrone.system.domain.SysMenu;
import com.hrone.system.service.ISysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 菜单权限 Controller
 * 
 * @author hrone
 */
@RestController
@RequestMapping("/system/menu")
public class SysMenuController extends BaseController {

    @Autowired
    private ISysMenuService menuService;

    /**
     * 查询菜单列表
     * 
     * GET /system/menu/list
     */
    @GetMapping("/list")
    public AjaxResult list(SysMenu menu) {
        List<SysMenu> menus = menuService.selectMenuList(menu);
        return AjaxResult.success(menus);
    }

    /**
     * 查询菜单树结构
     * 
     * GET /system/menu/tree
     */
    @GetMapping("/tree")
    public AjaxResult tree(SysMenu menu) {
        List<SysMenu> menus = menuService.selectMenuList(menu);
        List<SysMenu> tree = menuService.buildMenuTree(menus);
        return AjaxResult.success(tree);
    }

    /**
     * 根据菜单ID查询详细信息
     * 
     * GET /system/menu/{menuId}
     */
    @GetMapping("/{menuId}")
    public AjaxResult getInfo(@PathVariable Long menuId) {
        SysMenu menu = menuService.selectMenuById(menuId);
        return AjaxResult.success(menu);
    }

    /**
     * 新增菜单
     * 
     * POST /system/menu
     */
    @PostMapping
    public AjaxResult add(@RequestBody SysMenu menu) {
        return toAjax(menuService.insertMenu(menu));
    }

    /**
     * 修改菜单
     * 
     * PUT /system/menu
     */
    @PutMapping
    public AjaxResult edit(@RequestBody SysMenu menu) {
        return toAjax(menuService.updateMenu(menu));
    }

    /**
     * 删除菜单
     * 
     * DELETE /system/menu/{menuId}
     */
    @DeleteMapping("/{menuId}")
    public AjaxResult remove(@PathVariable Long menuId) {
        return toAjax(menuService.deleteMenuById(menuId));
    }

    /**
     * 根据用户ID查询菜单（用于前端路由）
     * 
     * GET /system/menu/user/{userId}
     */
    @GetMapping("/user/{userId}")
    public AjaxResult getMenuByUser(@PathVariable Long userId) {
        List<SysMenu> menus = menuService.selectMenusByUserId(userId);
        List<SysMenu> tree = menuService.buildMenuTree(menus);
        return AjaxResult.success(tree);
    }
}

