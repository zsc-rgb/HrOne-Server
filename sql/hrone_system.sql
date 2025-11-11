-- ==========================================
-- HROne 系统基础模块数据库脚本
-- 第5阶段：用户、角色、菜单、部门管理
-- ==========================================

-- 使用数据库
USE hrone_db;

-- ==========================================
-- 1. 部门表（树形结构）
-- ==========================================
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept` (
  `dept_id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '部门ID',
  `parent_id` BIGINT(20) DEFAULT 0 COMMENT '父部门ID（0表示根节点）',
  `ancestors` VARCHAR(500) DEFAULT '' COMMENT '祖级列表',
  `dept_name` VARCHAR(30) NOT NULL COMMENT '部门名称',
  `order_num` INT(4) DEFAULT 0 COMMENT '显示顺序',
  `leader` VARCHAR(20) DEFAULT NULL COMMENT '负责人',
  `phone` VARCHAR(11) DEFAULT NULL COMMENT '联系电话',
  `email` VARCHAR(50) DEFAULT NULL COMMENT '邮箱',
  `status` CHAR(1) DEFAULT '0' COMMENT '状态（0正常 1停用）',
  `del_flag` CHAR(1) DEFAULT '0' COMMENT '删除标志（0存在 2删除）',
  `create_by` VARCHAR(64) DEFAULT '' COMMENT '创建者',
  `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` VARCHAR(64) DEFAULT '' COMMENT '更新者',
  `update_time` DATETIME DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `remark` VARCHAR(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`dept_id`)
) ENGINE=InnoDB AUTO_INCREMENT=200 DEFAULT CHARSET=utf8mb4 COMMENT='部门表';

-- ==========================================
-- 2. 角色表
-- ==========================================
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `role_id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `role_name` VARCHAR(30) NOT NULL COMMENT '角色名称',
  `role_key` VARCHAR(100) NOT NULL COMMENT '角色权限字符串',
  `role_sort` INT(4) NOT NULL COMMENT '显示顺序',
  `data_scope` CHAR(1) DEFAULT '1' COMMENT '数据范围（1全部 2自定义 3本部门 4本部门及以下 5仅本人）',
  `status` CHAR(1) DEFAULT '0' COMMENT '状态（0正常 1停用）',
  `del_flag` CHAR(1) DEFAULT '0' COMMENT '删除标志（0存在 2删除）',
  `create_by` VARCHAR(64) DEFAULT '' COMMENT '创建者',
  `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` VARCHAR(64) DEFAULT '' COMMENT '更新者',
  `update_time` DATETIME DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `remark` VARCHAR(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`role_id`),
  UNIQUE KEY `uk_role_key` (`role_key`)
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8mb4 COMMENT='角色信息表';

-- ==========================================
-- 3. 菜单表（树形结构）
-- ==========================================
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `menu_id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '菜单ID',
  `menu_name` VARCHAR(50) NOT NULL COMMENT '菜单名称',
  `parent_id` BIGINT(20) DEFAULT 0 COMMENT '父菜单ID（0表示根节点）',
  `order_num` INT(4) DEFAULT 0 COMMENT '显示顺序',
  `path` VARCHAR(200) DEFAULT '' COMMENT '路由地址',
  `component` VARCHAR(255) DEFAULT NULL COMMENT '组件路径',
  `is_frame` INT(1) DEFAULT 1 COMMENT '是否为外链（0是 1否）',
  `is_cache` INT(1) DEFAULT 0 COMMENT '是否缓存（0缓存 1不缓存）',
  `menu_type` CHAR(1) DEFAULT '' COMMENT '菜单类型（M目录 C菜单 F按钮）',
  `visible` CHAR(1) DEFAULT '0' COMMENT '显示状态（0显示 1隐藏）',
  `status` CHAR(1) DEFAULT '0' COMMENT '状态（0正常 1停用）',
  `perms` VARCHAR(100) DEFAULT NULL COMMENT '权限标识',
  `icon` VARCHAR(100) DEFAULT '#' COMMENT '菜单图标',
  `create_by` VARCHAR(64) DEFAULT '' COMMENT '创建者',
  `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` VARCHAR(64) DEFAULT '' COMMENT '更新者',
  `update_time` DATETIME DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `remark` VARCHAR(500) DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2000 DEFAULT CHARSET=utf8mb4 COMMENT='菜单权限表';

-- ==========================================
-- 4. 用户和角色关联表
-- ==========================================
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `user_id` BIGINT(20) NOT NULL COMMENT '用户ID',
  `role_id` BIGINT(20) NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`user_id`, `role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户和角色关联表';

-- ==========================================
-- 5. 角色和菜单关联表
-- ==========================================
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `role_id` BIGINT(20) NOT NULL COMMENT '角色ID',
  `menu_id` BIGINT(20) NOT NULL COMMENT '菜单ID',
  PRIMARY KEY (`role_id`, `menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色和菜单关联表';

-- ==========================================
-- 6. 角色和部门关联表（数据权限）
-- ==========================================
DROP TABLE IF EXISTS `sys_role_dept`;
CREATE TABLE `sys_role_dept` (
  `role_id` BIGINT(20) NOT NULL COMMENT '角色ID',
  `dept_id` BIGINT(20) NOT NULL COMMENT '部门ID',
  PRIMARY KEY (`role_id`, `dept_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色和部门关联表';

-- ==========================================
-- 7. 更新用户表（添加部门字段）
-- ==========================================
-- 检查列是否存在，如果不存在则添加
SET @dbname = DATABASE();
SET @tablename = 'sys_user';
SET @columnname = 'dept_id';
SET @preparedStatement = (SELECT IF(
  (
    SELECT COUNT(*) FROM INFORMATION_SCHEMA.COLUMNS
    WHERE
      (TABLE_SCHEMA = @dbname)
      AND (TABLE_NAME = @tablename)
      AND (COLUMN_NAME = @columnname)
  ) > 0,
  'SELECT 1', -- 列已存在，不执行任何操作
  CONCAT('ALTER TABLE `', @tablename, '` ADD COLUMN `', @columnname, '` BIGINT(20) DEFAULT NULL COMMENT ''部门ID'' AFTER `user_id`;')
));
PREPARE alterIfNotExists FROM @preparedStatement;
EXECUTE alterIfNotExists;
DEALLOCATE PREPARE alterIfNotExists;

-- ==========================================
-- 初始化数据
-- ==========================================

-- 初始化部门数据
INSERT INTO `sys_dept` VALUES 
(100, 0, '0', 'HROne科技', 0, '管理员', '15888888888', 'admin@hrone.com', '0', '0', 'admin', NOW(), '', NULL, '总公司'),
(101, 100, '0,100', '深圳总公司', 1, '张三', '15888888888', 'sz@hrone.com', '0', '0', 'admin', NOW(), '', NULL, NULL),
(102, 100, '0,100', '北京分公司', 2, '李四', '15888888888', 'bj@hrone.com', '0', '0', 'admin', NOW(), '', NULL, NULL),
(103, 101, '0,100,101', '研发部门', 1, '王五', '15888888888', 'rd@hrone.com', '0', '0', 'admin', NOW(), '', NULL, NULL),
(104, 101, '0,100,101', '市场部门', 2, '赵六', '15888888888', 'market@hrone.com', '0', '0', 'admin', NOW(), '', NULL, NULL),
(105, 101, '0,100,101', '财务部门', 3, '孙七', '15888888888', 'finance@hrone.com', '0', '0', 'admin', NOW(), '', NULL, NULL);

-- 初始化角色数据
INSERT INTO `sys_role` VALUES 
(1, '超级管理员', 'admin', 1, '1', '0', '0', 'admin', NOW(), '', NULL, '超级管理员'),
(2, '普通角色', 'common', 2, '2', '0', '0', 'admin', NOW(), '', NULL, '普通角色');

-- 初始化菜单数据
INSERT INTO `sys_menu` VALUES 
-- 一级菜单
(1, '系统管理', 0, 1, 'system', NULL, 1, 0, 'M', '0', '0', '', 'system', 'admin', NOW(), '', NULL, '系统管理目录'),
(2, '系统监控', 0, 2, 'monitor', NULL, 1, 0, 'M', '0', '0', '', 'monitor', 'admin', NOW(), '', NULL, '系统监控目录'),

-- 系统管理子菜单
(100, '用户管理', 1, 1, 'user', 'system/user/index', 1, 0, 'C', '0', '0', 'system:user:list', 'user', 'admin', NOW(), '', NULL, '用户管理菜单'),
(101, '角色管理', 1, 2, 'role', 'system/role/index', 1, 0, 'C', '0', '0', 'system:role:list', 'peoples', 'admin', NOW(), '', NULL, '角色管理菜单'),
(102, '菜单管理', 1, 3, 'menu', 'system/menu/index', 1, 0, 'C', '0', '0', 'system:menu:list', 'tree-table', 'admin', NOW(), '', NULL, '菜单管理菜单'),
(103, '部门管理', 1, 4, 'dept', 'system/dept/index', 1, 0, 'C', '0', '0', 'system:dept:list', 'tree', 'admin', NOW(), '', NULL, '部门管理菜单'),

-- 用户管理按钮
(1000, '用户查询', 100, 1, '', '', 1, 0, 'F', '0', '0', 'system:user:query', '#', 'admin', NOW(), '', NULL, ''),
(1001, '用户新增', 100, 2, '', '', 1, 0, 'F', '0', '0', 'system:user:add', '#', 'admin', NOW(), '', NULL, ''),
(1002, '用户修改', 100, 3, '', '', 1, 0, 'F', '0', '0', 'system:user:edit', '#', 'admin', NOW(), '', NULL, ''),
(1003, '用户删除', 100, 4, '', '', 1, 0, 'F', '0', '0', 'system:user:remove', '#', 'admin', NOW(), '', NULL, ''),

-- 角色管理按钮
(1010, '角色查询', 101, 1, '', '', 1, 0, 'F', '0', '0', 'system:role:query', '#', 'admin', NOW(), '', NULL, ''),
(1011, '角色新增', 101, 2, '', '', 1, 0, 'F', '0', '0', 'system:role:add', '#', 'admin', NOW(), '', NULL, ''),
(1012, '角色修改', 101, 3, '', '', 1, 0, 'F', '0', '0', 'system:role:edit', '#', 'admin', NOW(), '', NULL, ''),
(1013, '角色删除', 101, 4, '', '', 1, 0, 'F', '0', '0', 'system:role:remove', '#', 'admin', NOW(), '', NULL, '');

-- 初始化用户角色关联（admin用户为超级管理员）
INSERT INTO `sys_user_role` VALUES (1, 1);

-- 初始化角色菜单关联（超级管理员拥有所有菜单权限）
INSERT INTO `sys_role_menu` VALUES 
(1, 1), (1, 2),
(1, 100), (1, 101), (1, 102), (1, 103),
(1, 1000), (1, 1001), (1, 1002), (1, 1003),
(1, 1010), (1, 1011), (1, 1012), (1, 1013);

-- ==========================================
-- 完成
-- ==========================================

