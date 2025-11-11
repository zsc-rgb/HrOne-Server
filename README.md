# HROne 后端学习项目

从零开始实现一个企业级Spring Boot后端系统

## 📋 项目概述

这是一个循序渐进的学习项目，通过实际编码来理解企业级后端系统的架构和实现。

### 技术栈

**核心框架：**
- Spring Boot 2.5.14
- Spring Security + JWT
- MyBatis-Plus 3.4.2
- Redis

**数据库：**
- MySQL 8.0

**工具库：**
- Hutool 5.8.25
- Lombok
- Swagger 3.0.0
- Druid 1.2.16

## 🎯 学习路线图

### 第1阶段：项目基础搭建 ✅
**目标：** 创建一个能运行的Spring Boot项目

- [x] 创建多模块Maven项目
- [x] 配置父POM依赖管理
- [x] 创建启动类
- [x] 配置application.yml
- [x] 测试项目启动

**学习重点：**
- Maven多模块项目结构
- Spring Boot自动配置原理
- 配置文件的加载顺序

---

### 第2阶段：通用工具模块 (hrone-common)
**目标：** 实现项目中常用的工具类和通用组件

**2.1 统一响应结果 (1小时)** ✅
- [x] AjaxResult - 统一返回结果
- [x] HttpStatus - HTTP状态码常量

**2.2 基础工具类 (2小时)** ✅
- [x] StringUtils - 字符串工具
- [x] DateUtils - 日期工具
- [x] ServletUtils - Servlet工具
- [x] UtilsTestController - 测试接口

**2.3 通用实体类 (1小时)** ✅
- [x] BaseEntity - 基础实体类（创建时间、更新时间等）
- [x] PageDomain - 分页参数
- [x] TableDataInfo - 分页响应
- [x] BaseController - Controller基类
- [x] PageTestController - 分页测试接口

**2.4 异常处理 (2小时)** ✅
- [x] BaseException - 基础异常类
- [x] ServiceException - 业务异常
- [x] GlobalExceptionHandler - 全局异常处理器
- [x] ExceptionTestController - 异常测试接口

**2.5 常量定义 (30分钟)** ✅
- [x] Constants - 通用常量
- [x] UserConstants - 用户常量
- [x] CacheConstants - 缓存常量
- [x] ConstantsTestController - 常量测试接口

**预计完成时间：** 1-2天

---

### 第3阶段：数据访问层 (hrone-framework)
**目标：** 集成MyBatis-Plus，实现数据库访问

**3.1 数据源配置 (2小时)** ✅
- [x] Druid数据源配置
- [x] MyBatis-Plus基础配置
- [x] 分页插件配置
- [x] SQL脚本准备

**3.2 实体类和Mapper (1小时)** ✅
- [x] SysUser实体类
- [x] SysUserMapper接口
- [x] ISysUserService接口
- [x] SysUserServiceImpl实现类

**3.3 数据库操作 (2小时)** ✅
- [x] 基础CRUD操作
- [x] Lambda条件构造器
- [x] MyBatis-Plus分页查询
- [x] SysUserController接口（9个接口）

**预计完成时间：** 1天

---

### 第4阶段：Redis缓存 (hrone-framework) ✅
**目标：** 集成Redis，实现缓存功能

**4.1 Redis配置 (1小时)** ✅
- [x] Redis连接配置
- [x] RedisTemplate配置
- [x] Fastjson2序列化配置

**4.2 缓存工具类 (2小时)** ✅
- [x] RedisCache - 缓存操作工具（540行）
- [x] String、List、Hash、Set操作（40+方法）
- [x] 设置过期时间、模糊查询、批量操作
- [x] RedisCacheTestController - 测试接口（10个）

**预计完成时间：** 半天  
**实际完成时间：** 3小时

---

### 第5阶段：系统基础模块 (hrone-system) ✅
**目标：** 实现系统管理的基础功能

**5.1 数据库表设计 (1小时)** ✅
- [x] 部门表（树形结构）
- [x] 角色表
- [x] 菜单表（树形结构）
- [x] 关联表（用户角色、角色菜单、角色部门）

**5.2 实体类和Mapper (2小时)** ✅
- [x] SysDept、SysRole、SysMenu实体类
- [x] 对应的Mapper接口
- [x] TreeUtils树形结构工具类

**5.3 Service和Controller (3小时)** ✅
- [x] 部门管理Service和Controller（6个接口）
- [x] 角色管理Service和Controller（7个接口）
- [x] 菜单管理Service和Controller（7个接口）
- [x] 树形结构构建、业务规则验证

**预计完成时间：** 2天  
**实际完成时间：** 6小时

---

### 第6阶段：安全认证 (hrone-framework)
**目标：** 实现JWT登录认证和授权

**6.1 JWT工具类 (2小时)** ✅
- [x] JWT生成（`JwtUtils.generateToken`）
- [x] JWT解析（`JwtUtils.parseClaims`/`getSubject`）
- [x] JWT验证（`JwtUtils.validateToken`）
- [x] 新增示例配置：`application-dev.yml` 下 `jwt.secret`、`jwt.expire-minutes`

**6.2 登录功能 (4小时)**
- [x] 登录接口 `/auth/login`（简化版）
- [x] 验证码生成 `/auth/captcha`（Redis缓存，默认2分钟）
- [x] 密码验证（兼容明文与BCrypt）
- [x] Token生成和返回（JwtUtils）

**6.3 JWT过滤器 (3小时)**
- [x] JwtAuthenticationTokenFilter（OncePerRequestFilter）
- [x] Token解析与用户ID注入（request attribute：login_user_key）
- [x] 白名单放行：/auth/**、/test/**、/druid/**、/actuator/**

**6.4 权限注解 (2小时)**
- [x] @RequiresPermissions 注解
- [x] PermissionAspect 切面（从请求头 X-Perms 演示校验）
- [ ] @RequiresRoles（预留）

**预计完成时间：** 2天

---

### 第7阶段：权限系统 (hrone-framework)
**目标：** 实现RBAC权限控制

**7.1 权限设计 (1小时)**
- [x] 理解RBAC模型
- [x] 用户-角色-菜单-权限关系（演示版以菜单perms为权限来源）

**7.2 权限查询 (3小时)**
- [x] 根据用户查询菜单（用户→角色→角色菜单→菜单）
- [x] 从菜单perms抽取权限集合

**7.3 权限验证 (2小时)**
- [x] 接口权限验证（@RequiresPermissions + PermissionAspect）
- [x] 数据权限过滤（@DataScope + DataScopeAspect，支持“全部/本部门”演示）
- [ ] 按钮权限控制（预告）

**预计完成时间：** 1天

---

### 第8阶段：API接口开发 (hrone-admin)
**目标：** 实现具体的业务接口

**8.1 系统管理接口 (4小时)**
- [ ] 用户管理接口
- [ ] 角色管理接口
- [ ] 菜单管理接口
- [ ] 字典管理接口

**8.2 文件上传 (2小时)**
- [ ] 文件上传接口
- [ ] 文件类型验证
- [ ] 文件大小限制

**8.3 日志管理 (3小时)**
- [ ] 操作日志记录
- [ ] 登录日志记录
- [ ] 日志查询接口

**预计完成时间：** 1-2天

---

### 第9阶段：高级特性
**目标：** 实现企业级功能

**9.1 定时任务 (hrone-quartz)**
- [ ] Quartz配置
- [ ] 动态任务管理

**9.2 代码生成器 (hrone-generator)**
- [ ] 表结构解析
- [ ] 代码模板生成

**9.3 接口文档 (Swagger)**
- [ ] Swagger配置
- [ ] 接口注解

**预计完成时间：** 2-3天

---

## 📁 项目结构

```
hrone-Server/
├── hrone-admin/                # 启动模块（Web入口）
│   ├── src/main/java/
│   │   └── com/hrone/
│   │       ├── HROneApplication.java    # 启动类
│   │       └── controller/              # 控制器
│   └── src/main/resources/
│       ├── application.yml              # 主配置文件
│       └── application-dev.yml          # 开发环境配置
│
├── hrone-framework/            # 框架核心模块
│   └── src/main/java/
│       └── com/hrone/framework/
│           ├── config/                  # 配置类
│           ├── security/                # 安全认证
│           ├── aspectj/                 # AOP切面
│           └── interceptor/             # 拦截器
│
├── hrone-system/               # 系统管理模块
│   └── src/main/java/
│       └── com/hrone/system/
│           ├── domain/                  # 实体类
│           ├── mapper/                  # MyBatis Mapper
│           ├── service/                 # 业务逻辑
│           └── controller/              # 控制器
│
├── hrone-common/               # 通用工具模块
│   └── src/main/java/
│       └── com/hrone/common/
│           ├── annotation/              # 自定义注解
│           ├── constant/                # 常量定义
│           ├── core/                    # 核心组件
│           ├── enums/                   # 枚举类
│           ├── exception/               # 异常类
│           └── utils/                   # 工具类
│
├── docs/                       # 学习文档
│   ├── phase-01-基础搭建.md
│   ├── phase-02-通用工具.md
│   ├── phase-03-数据访问.md
│   └── ...
│
├── sql/                        # 数据库脚本
│   ├── hrone_base.sql                   # 数据库结构
│   └── hrone_base_data.sql             # 初始数据
│
└── pom.xml                     # 父POM
```

## 🚀 快速开始

### 1. 环境准备
- JDK 1.8+
- Maven 3.6+
- MySQL 8.0+
- Redis 5.0+
- IDEA（推荐）

### 2. 当前进度
- ✅ 第1阶段：基础项目搭建
- ✅ 第2阶段：通用工具模块（100%完成）
  - ✅ 2.1 统一响应结果
  - ✅ 2.2 基础工具类
  - ✅ 2.3 通用实体类
  - ✅ 2.4 异常处理
  - ✅ 2.5 常量定义
- ✅ 第3阶段：数据访问层（100%完成，需MySQL测试）
  - ✅ 3.1 数据源配置
  - ✅ 3.2 实体类和Mapper
  - ✅ 3.3 CRUD操作
- ✅ 第4阶段：Redis缓存（100%完成，需Redis测试）
  - ✅ 4.1 Redis配置
  - ✅ 4.2 RedisCache工具类
  - ✅ 4.3 测试接口
- 🎉 第5阶段：系统基础模块（100%完成，需MySQL测试）
  - ✅ 5.1 数据库表设计
  - ✅ 5.2 实体类和Mapper
  - ✅ 5.3 Service和Controller

### 3. 下一步
✅ 第5阶段代码已完成！请先按照 `第5阶段-准备工作.md` 准备数据库，然后测试接口

## 📚 学习建议

1. **按顺序学习**：每个阶段都有依赖关系，建议按顺序完成
2. **动手实践**：不要只看代码，一定要自己写一遍
3. **理解原理**：每个功能都要理解为什么这样实现
4. **做笔记**：记录遇到的问题和解决方案
5. **提问讨论**：遇到问题及时提问

## 📖 参考资料

- [Spring Boot官方文档](https://spring.io/projects/spring-boot)
- [MyBatis-Plus官方文档](https://baomidou.com/)
- [Spring Security官方文档](https://spring.io/projects/spring-security)
- [JWT官方网站](https://jwt.io/)

## 📝 学习记录

| 阶段 | 开始时间 | 完成时间 | 学习笔记 |
|------|---------|---------|----------|
| 第1阶段 | 2025-11-03 | 2025-11-03 | Maven多模块项目、Spring Boot基础配置 |
| 第2.1阶段 | 2025-11-05 | 2025-11-05 | 统一响应结果、AjaxResult、HttpStatus |
| 第2.2阶段 | 2025-11-05 | 2025-11-05 | StringUtils、DateUtils、ServletUtils |
| 第2.3阶段 | 2025-11-05 | 2025-11-05 | BaseEntity、PageDomain、TableDataInfo、BaseController |
| 第2.4阶段 | 2025-11-05 | 2025-11-05 | BaseException、ServiceException、GlobalExceptionHandler |
| 第2.5阶段 | 2025-11-05 | 2025-11-05 | Constants、UserConstants、CacheConstants |
| **第2阶段** | **2025-11-05** | **2025-11-05** | **✅ 100%完成！** |
| 第3阶段 | 2025-11-05 | 2025-11-05 | MyBatis-Plus、Druid、CRUD（代码已完成，需MySQL）|
| 第4阶段 | 2025-11-06 | 2025-11-06 | Redis、RedisCache、缓存操作（代码已完成，需Redis）|
| 第5阶段 | 2025-11-06 | 2025-11-06 | 部门、角色、菜单、树形结构（代码已完成，需MySQL）|

---

💪 加油！一步一步来，每天进步一点点！

