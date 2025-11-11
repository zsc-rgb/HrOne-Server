package com.hrone.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hrone.system.domain.SysMenu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 菜单权限 Mapper接口
 * 
 * @author hrone
 */
@Mapper
public interface SysMenuMapper extends BaseMapper<SysMenu> {
    
    /**
     * 根据用户ID查询其可见菜单（基于用户-角色-菜单关系）
     */
    @Select("SELECT DISTINCT m.* " +
            "FROM sys_menu m " +
            "JOIN sys_role_menu rm ON rm.menu_id = m.menu_id " +
            "JOIN sys_user_role ur ON ur.role_id = rm.role_id " +
            "WHERE ur.user_id = #{userId} " +
            "  AND m.status = '0' " +
            "  AND m.menu_type IN ('M','C','F') " +
            "ORDER BY m.parent_id ASC, m.order_num ASC")
    List<SysMenu> selectMenusByUserId(@Param("userId") Long userId);
}

