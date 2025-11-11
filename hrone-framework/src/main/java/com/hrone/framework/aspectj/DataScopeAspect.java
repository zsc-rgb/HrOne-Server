package com.hrone.framework.aspectj;

import com.hrone.common.constant.Constants;
import com.hrone.common.exception.ServiceException;
import com.hrone.system.domain.SysRole;
import com.hrone.system.domain.SysUser;
import com.hrone.system.service.ISysRoleService;
import com.hrone.system.service.ISysUserService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.hrone.framework.aspectj.DataScopeContext.clear;
import static com.hrone.framework.aspectj.DataScopeContext.setDeptId;

/**
 * 数据权限切面（演示版）
 *
 * 规则：
 * - 若用户任一角色 data_scope = '1'（全部数据），则不做过滤
 * - 若存在 data_scope = '3'（本部门），则按用户 deptId 过滤
 * - 其他情况暂不限制（可后续扩展）
 */
@Aspect
@Component
public class DataScopeAspect {

	@Autowired
	private ISysUserService userService;

	@Autowired
	private ISysRoleService roleService;

	@Autowired
	private javax.servlet.http.HttpServletRequest request;

	@Before("@annotation(com.hrone.framework.aspectj.DataScope)")
	public void before(JoinPoint joinPoint) {
		Object uid = request.getAttribute(Constants.LOGIN_USER_KEY);
		if (uid == null) {
			// 未登录则不做数据过滤（交给鉴权层面）
			return;
		}
		Long userId = Long.valueOf(String.valueOf(uid));
		SysUser user = userService.selectUserById(userId);
		if (user == null) {
			throw new ServiceException("用户不存在，无法进行数据权限过滤", 401);
		}
		List<SysRole> roles = roleService.selectRolesByUserId(userId);
		boolean all = roles.stream().anyMatch(r -> "1".equals(r.getDataScope()));
		if (all) {
			return; // 全部数据，不做限制
		}
		boolean deptOnly = roles.stream().anyMatch(r -> "3".equals(r.getDataScope()));
		if (deptOnly && user.getDeptId() != null) {
			setDeptId(user.getDeptId());
		}
	}

	@After("@annotation(com.hrone.framework.aspectj.DataScope)")
	public void after(JoinPoint joinPoint) {
		clear();
	}
}


