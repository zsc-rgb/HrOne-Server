package com.hrone.framework.aspectj;

import java.lang.annotation.*;

/**
 * 数据权限注解（第7阶段）
 *
 * 说明：
 * - 当前实现演示“本部门数据”过滤（data_scope=3），与“全部数据”（data_scope=1）
 * - 后续可扩展“本部门及以下”等更复杂规则
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataScope {
	/**
	 * 目标表中部门字段的别名（如：用户查询用 u.dept_id）
	 * 演示场景下使用实体字段拼装条件，不强依赖别名
	 */
	String deptAlias() default "";
}


