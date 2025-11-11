package com.hrone.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hrone.system.domain.SysRole;
import org.apache.ibatis.annotations.Mapper;

/**
 * 角色管理 Mapper接口
 * 
 * @author hrone
 */
@Mapper
public interface SysRoleMapper extends BaseMapper<SysRole> {
    
}

