package com.hrone.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hrone.system.domain.SysMenu;
import org.apache.ibatis.annotations.Mapper;

/**
 * 菜单权限 Mapper接口
 * 
 * @author hrone
 */
@Mapper
public interface SysMenuMapper extends BaseMapper<SysMenu> {
    
}

