package com.hrone.framework.aspectj;

/**
 * 数据权限上下文（ThreadLocal）
 */
public final class DataScopeContext {
	private static final ThreadLocal<Long> DEPT_ID_HOLDER = new ThreadLocal<>();

	private DataScopeContext() {}

	public static void setDeptId(Long deptId) {
		DEPT_ID_HOLDER.set(deptId);
	}

	public static Long getDeptId() {
		return DEPT_ID_HOLDER.get();
	}

	public static void clear() {
		DEPT_ID_HOLDER.remove();
	}
}


