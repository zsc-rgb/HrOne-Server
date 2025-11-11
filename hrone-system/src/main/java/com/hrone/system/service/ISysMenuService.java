package com.hrone.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hrone.system.domain.SysMenu;

import java.util.List;

/**
 * 菜单权限 Service接口
 * 
 * @author hrone
 */
public interface ISysMenuService extends IService<SysMenu> {

    /**
     * 查询菜单列表
     * 
     * @param menu 菜单信息
     * @return 菜单列表
     */
    List<SysMenu> selectMenuList(SysMenu menu);

    /**
     * 查询菜单树结构
     * 
     * @param menu 菜单信息
     * @return 树结构列表
     */
    List<SysMenu> buildMenuTree(List<SysMenu> menus);

    /**
     * 根据ID查询菜单
     * 
     * @param menuId 菜单ID
     * @return 菜单信息
     */
    SysMenu selectMenuById(Long menuId);

    /**
     * 根据用户ID查询菜单（简化版本）
     * 
     * @param userId 用户ID
     * @return 菜单列表
     */
    List<SysMenu> selectMenusByUserId(Long userId);

    /**
     * 是否存在子菜单
     * 
     * @param menuId 菜单ID
     * @return 结果
     */
    boolean hasChildByMenuId(Long menuId);

    /**
     * 校验菜单名称是否唯一
     * 
     * @param menu 菜单信息
     * @return 结果
     */
    boolean checkMenuNameUnique(SysMenu menu);

    /**
     * 新增菜单
     * 
     * @param menu 菜单信息
     * @return 结果
     */
    int insertMenu(SysMenu menu);

    /**
     * 修改菜单
     * 
     * @param menu 菜单信息
     * @return 结果
     */
    int updateMenu(SysMenu menu);

    /**
     * 删除菜单
     * 
     * @param menuId 菜单ID
     * @return 结果
     */
    int deleteMenuById(Long menuId);
}

