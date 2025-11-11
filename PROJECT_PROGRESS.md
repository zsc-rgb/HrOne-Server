# 📊 HROne 后端项目学习进度

> 最后更新：2025-11-11

## 🎯 总体进度

```
进度：█████████████████████ 88%
当前阶段：第6阶段 - 安全认证（进行中：6.1 JWT 工具完成）
```

---

## ✅ 已完成阶段

### 第1阶段：项目基础搭建 ✅

**完成时间：** 2025-11-03

**学习内容：**
- ✅ 创建Maven多模块项目结构
- ✅ 配置父POM依赖管理
- ✅ 创建4个子模块（admin、common、framework、system）
- ✅ 编写启动类和测试Controller
- ✅ 配置application.yml
- ✅ 成功启动项目并测试接口

**学习笔记：**
- 理解了Maven多模块项目的依赖关系
- 掌握了Spring Boot的基本配置
- 学会了排除DataSource自动配置
- 熟悉了IDEA的Maven项目管理

**遇到的问题及解决：**
1. ❌ **问题：** Maven找不到子模块
   - ✅ **解决：** 在父项目根目录执行 `mvn clean install`

2. ❌ **问题：** 启动报错 "Failed to determine a suitable driver class"
   - ✅ **解决：** 添加 `@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})`

3. ❌ **问题：** IDEA右上角运行按钮显示黑色
   - ✅ **解决：** 刷新Maven项目（Reload All Maven Projects）

---

## ✅ 已完成阶段（续）

### 第2阶段：通用工具模块 (hrone-common) ✅

**开始时间：** 2025-11-05  
**完成时间：** 2025-11-05  
**预计用时：** 1-2天  
**实际用时：** 1天（7.5小时）

**目标：** 实现项目中常用的工具类和通用组件

#### 2.1 统一响应结果 ✅

**状态：** 已完成  
**预计时间：** 1小时  
**实际时间：** 1小时  
**完成日期：** 2025-11-05

- [x] HttpStatus - HTTP状态码常量
- [x] AjaxResult - 统一返回结果类
- [x] 测试响应结果封装

**学习目标：**
- ✅ 理解统一响应格式的重要性
- ✅ 掌握泛型的使用
- ✅ 了解RESTful API设计规范

**已完成内容：**
- 创建了 HttpStatus 常量类，定义了标准HTTP状态码
- 实现了 AjaxResult 统一响应类，支持链式调用
- 修改了 TestController 使用统一响应格式
- 测试了多种响应场景（成功、失败、带数据等）

#### 2.2 基础工具类 ✅

**状态：** 已完成  
**预计时间：** 2小时  
**实际时间：** 2小时  
**完成日期：** 2025-11-05

- [x] StringUtils - 字符串工具类
- [x] DateUtils - 日期工具类
- [x] ServletUtils - Servlet工具类
- [x] 测试工具类方法

**学习目标：** 
- ✅ 掌握常用工具类的实现
- ✅ 理解Apache Commons和Hutool的区别
- ✅ 学习工具类的设计模式

**已完成内容：**
- **StringUtils（348行）**：字符串判空、格式化、驼峰转下划线等20+个方法
- **DateUtils（436行）**：日期格式化、解析、计算、Date与LocalDateTime互转等30+个方法
- **ServletUtils（343行）**：获取Request/Response、请求参数处理、客户端IP获取等20+个方法
- **UtilsTestController（189行）**：创建了完整的测试接口，包含4个测试方法和1个综合场景
- 添加了 Apache Commons Lang3 依赖（3.12.0版本）

#### 2.3 通用实体类 ✅

**状态：** 已完成  
**预计时间：** 1小时  
**实际时间：** 1小时  
**完成日期：** 2025-11-05

- [x] BaseEntity - 基础实体类
- [x] PageDomain - 分页参数
- [x] TableDataInfo - 分页响应
- [x] BaseController - Controller基类
- [x] PageTestController - 分页测试接口

**学习目标：**
- ✅ 理解实体类继承的好处
- ✅ 掌握分页参数的设计
- ✅ 了解分页响应的封装

**已完成内容：**
- **BaseEntity（174行）**：所有实体类的父类，包含创建时间、更新时间、备注等通用字段
- **PageDomain（144行）**：分页参数封装，包含pageNum、pageSize、排序字段等
- **TableDataInfo（122行）**：分页响应封装，包含total、rows、code、msg
- **BaseController（103行）**：Controller基类，提供getDataTable()等通用方法
- **PageTestController（208行）**：完整的分页测试接口，包含5个测试场景

#### 2.4 异常处理 ✅

**状态：** 已完成  
**预计时间：** 2小时  
**实际时间：** 2小时  
**完成日期：** 2025-11-05

- [x] BaseException - 基础异常类
- [x] ServiceException - 业务异常
- [x] GlobalExceptionHandler - 全局异常处理器
- [x] ExceptionTestController - 异常测试接口

**学习目标：**
- ✅ 理解异常处理的最佳实践
- ✅ 掌握@RestControllerAdvice的使用
- ✅ 学习异常分层设计

**已完成内容：**
- **BaseException（170行）**：基础异常类，支持模块、错误码、参数等扩展信息
- **ServiceException（138行）**：业务异常类，简化业务层异常抛出
- **GlobalExceptionHandler（180行）**：全局异常处理器，统一捕获和处理所有异常
- **ExceptionTestController（193行）**：异常测试接口，包含8种异常场景测试
- **StringUtils 扩展**：添加 join() 方法，支持集合和数组拼接

#### 2.5 常量定义 ✅

**状态：** 已完成  
**预计时间：** 30分钟  
**实际时间：** 30分钟  
**完成日期：** 2025-11-05

- [x] Constants - 通用常量
- [x] UserConstants - 用户常量
- [x] CacheConstants - 缓存常量
- [x] ConstantsTestController - 常量测试接口

**学习目标：**
- ✅ 理解常量类的组织方式
- ✅ 掌握魔法值的消除
- ✅ 了解常量的实际应用

**已完成内容：**
- **Constants（158行）**：通用常量，包含字符集、协议、状态码、Token等40+个常量
- **UserConstants（137行）**：用户相关常量，包含用户状态、角色、菜单类型等30+个常量
- **CacheConstants（123行）**：缓存常量，包含缓存Key前缀、过期时间等20+个常量
- **ConstantsTestController（186行）**：常量测试接口，包含5个测试场景，演示常量使用

---

## ✅ 已完成阶段（续）

### 第3阶段：数据访问层 ✅

**开始时间：** 2025-11-05  
**完成时间：** 2025-11-05  
**实际用时：** 3小时

**目标：** 集成MyBatis-Plus，实现数据库访问

#### 3.1 数据源配置 ✅

**状态：** 已完成  
**预计时间：** 2小时  
**实际时间：** 1小时  
**完成日期：** 2025-11-05

- [x] Druid数据源配置（application-dev.yml）
- [x] MyBatis-Plus基础配置
- [x] 分页插件配置（MybatisPlusConfig）
- [x] SQL脚本准备（hrone_schema.sql）
- [x] 启用数据库依赖（移除DataSource排除）

**已完成内容：**
- **MybatisPlusConfig（69行）**：配置分页插件、Mapper扫描路径
- **application-dev.yml**：Druid连接池配置、MyBatis-Plus配置
- **hrone_schema.sql**：数据库和表创建脚本、测试数据
- **Druid监控**：启用/druid监控面板

#### 3.2 实体类和Mapper ✅

**状态：** 已完成  
**预计时间：** 1小时  
**实际时间：** 1小时  
**完成日期：** 2025-11-05

- [x] SysUser实体类（对应sys_user表）
- [x] SysUserMapper接口（继承BaseMapper）
- [x] ISysUserService接口（继承IService）
- [x] SysUserServiceImpl实现类（继承ServiceImpl）

**已完成内容：**
- **SysUser（217行）**：用户实体类，使用MyBatis-Plus注解
- **SysUserMapper（35行）**：用户Mapper，继承BaseMapper自动获得CRUD方法
- **ISysUserService（78行）**：用户Service接口，定义业务方法
- **SysUserServiceImpl（208行）**：Service实现，使用LambdaQueryWrapper构建条件

#### 3.3 CRUD操作 ✅

**状态：** 已完成  
**预计时间：** 2小时  
**实际时间：** 1小时  
**完成日期：** 2025-11-05

- [x] 用户查询（列表查询、分页查询、ID查询）
- [x] 用户新增（含唯一性校验）
- [x] 用户修改（含权限校验）
- [x] 用户删除（逻辑删除）
- [x] 批量删除

**已完成内容：**
- **SysUserController（194行）**：用户管理接口，包含9个CRUD接口
- 使用LambdaQueryWrapper构建查询条件
- 使用MyBatis-Plus的Page实现分页
- 集成全局异常处理和统一响应

### 第4阶段：Redis缓存 ✅

**开始时间：** 2025-11-06  
**完成时间：** 2025-11-06  
**实际用时：** 3小时

**目标：** 集成Redis，实现缓存功能

#### 4.1 Redis配置 ✅

**状态：** 已完成  
**预计时间：** 1小时  
**实际时间：** 1小时  
**完成日期：** 2025-11-06

- [x] Redis依赖配置（spring-boot-starter-data-redis）
- [x] 连接池依赖（commons-pool2）
- [x] Fastjson2依赖
- [x] application-dev.yml配置（Lettuce连接池）
- [x] RedisConfig配置类（Fastjson2序列化器）

**已完成内容：**
- **RedisConfig（90行）**：配置RedisTemplate、Fastjson2序列化器
- **application-dev.yml**：Redis连接配置、Lettuce连接池配置

#### 4.2 RedisCache工具类 ✅

**状态：** 已完成  
**预计时间：** 2小时  
**实际时间：** 2小时  
**完成日期：** 2025-11-06

- [x] String操作（set、get、delete、increment等）
- [x] List操作（列表增删改查）
- [x] Hash操作（哈希表操作）
- [x] Set操作（集合操作）
- [x] 通用操作（keys、expire、batch delete等）
- [x] RedisCacheTestController测试接口

**已完成内容：**
- **RedisCache（540行）**：Redis缓存工具类，包含40+个方法
- **RedisCacheTestController（420行）**：10个测试接口，覆盖所有数据类型

### 第5阶段：系统基础模块 ✅

**开始时间：** 2025-11-06  
**完成时间：** 2025-11-06  
**实际用时：** 6小时

**目标：** 实现系统管理的基础功能

#### 5.1 数据库表设计 ✅

**状态：** 已完成  
**预计时间：** 1小时  
**实际时间：** 1小时  
**完成日期：** 2025-11-06

- [x] sys_dept部门表（树形结构）
- [x] sys_role角色表
- [x] sys_menu菜单表（树形结构）
- [x] sys_user_role用户角色关联表
- [x] sys_role_menu角色菜单关联表
- [x] sys_role_dept角色部门关联表

**已完成内容：**
- **hrone_system.sql（170行）**：7张表的结构设计和初始数据
- **初始数据**：6个部门、2个角色、14个菜单

#### 5.2 实体类和Mapper ✅

**状态：** 已完成  
**预计时间：** 2小时  
**实际时间：** 2小时  
**完成日期：** 2025-11-06

- [x] SysDept实体类（部门）
- [x] SysRole实体类（角色）
- [x] SysMenu实体类（菜单）
- [x] 对应的Mapper接口
- [x] TreeUtils树形结构工具类

**已完成内容：**
- **SysDept（176行）**：部门实体，支持树形结构
- **SysRole（170行）**：角色实体，包含数据权限字段
- **SysMenu（195行）**：菜单实体，支持三级结构
- **TreeUtils（120行）**：通用树形结构工具类
- **3个Mapper接口**：继承BaseMapper

#### 5.3 Service和Controller ✅

**状态：** 已完成  
**预计时间：** 3小时  
**实际时间：** 3小时  
**完成日期：** 2025-11-06

- [x] 部门管理（Service + Controller）
- [x] 角色管理（Service + Controller）
- [x] 菜单管理（Service + Controller）
- [x] 树形结构构建
- [x] 业务规则验证

**已完成内容：**
- **部门Service（311行）**：树构建、唯一性校验、级联检查
- **角色Service（255行）**：角色管理、超级管理员保护
- **菜单Service（239行）**：菜单树构建、权限管理
- **3个Controller（305行）**：20个RESTful API

---

## ⏸️ 待开始阶段

---

### 第6阶段：安全认证 ✅/进行中

**开始时间：** 2025-11-11  
**当前进度：** 6.1 已完成；6.2（登录+验证码+加密）已完成；6.3 过滤器完成；6.4 权限注解完成（演示版）

#### 6.1 JWT工具类 ✅
**状态：** 已完成  
**实际时间：** 1小时  

- [x] 添加 `jjwt` 依赖（父POM已管理版本）
- [x] 在 `hrone-framework` 新增 `JwtUtils`（生成/解析/验证）
- [x] 在 `application-dev.yml` 增加 `jwt.secret` 与 `jwt.expire-minutes` 示例配置

**收获：**
- 了解 JWT 的结构（Header.Payload.Signature）
- 掌握 HS256 签名生成与验证
- 学会通过 Claims 读取主体与自定义字段

#### 6.2 登录功能 ✅
**状态：** 登录接口、验证码、密码加密/校验、颁发Token全部完成  
**耗时：** 2小时

- [x] 创建 `AuthController`，新增 `/auth/login` 接口
- [x] 支持从 `application-dev.yml` 读取 `jwt.secret`、`jwt.expire-minutes`
- [x] 验证码接口 `/auth/captcha`（Redis缓存，2分钟过期）
- [x] 密码校验：兼容明文与BCrypt（`spring-security-crypto`）
- [x] 返回 `token`、`expireMinutes`、`userId`、`userName` 等

#### 6.3 JWT过滤器 ✅
- [x] OncePerRequestFilter 实现，白名单放行
- [x] 校验Token并注入 userId（request attribute：login_user_key）
- [x] 无效或缺失 Token 返回 401

#### 6.4 权限注解（演示版） ✅
- [x] `@RequiresPermissions` 注解
- [x] `PermissionAspect`：从请求头 `X-Perms` 读取权限并校验
- [ ] 结合数据库动态权限（待第7阶段完善）

---

### 第7阶段：权限系统 ✅/进行中

**开始时间：** 2025-11-11  
**当前进度：** 完成 7.1 权限设计、7.2 权限查询（演示版）、7.3 接口权限验证；数据权限/按钮权限预告

#### 7.1 权限设计 ✅
- [x] 采用 RBAC：用户-角色-菜单-权限
- [x] 使用菜单 `perms` 作为权限标识（演示阶段）

#### 7.2 权限查询（演示版） ✅
- [x] 通过 用户→角色→角色菜单 关联获取可见菜单（sys_user_role、sys_role_menu）
- [x] 从菜单 `perms` 字段抽取权限集合（逗号分隔合并）

#### 7.3 权限验证 ✅
- [x] 注解 `@RequiresPermissions` + 切面 `PermissionAspect` 实现接口权限验证
- [x] 过滤器解析 Token，并在 request attribute 注入 `login_user_key`
- [x] 校验顺序：优先使用登录用户的权限集合，回退到请求头 `X-Perms`（便于快速演示）
 - [x] 数据权限过滤：新增 `@DataScope` 注解与 `DataScopeAspect`，在用户列表查询中演示“全部/本部门”数据范围（基于角色 data_scope 与用户 dept_id）

后续计划：
- [ ] 数据权限过滤（基于部门/角色的数据范围）
- [ ] 结合角色-菜单关联实现真实的按用户权限收敛

---

### 第8阶段：API接口开发 ⏸️

**预计开始：** 2025-11-11  
**预计完成：** 2025-11-12

**内容预览：**
- 系统管理接口
- 文件上传
- 日志管理

---

### 第9阶段：高级特性 ⏸️

**预计开始：** 2025-11-13  
**预计完成：** 2025-11-15

**内容预览：**
- 定时任务
- 代码生成器
- 接口文档

---

## 📈 学习统计

### 时间统计

| 阶段 | 预计时间 | 实际时间 | 效率 |
|-----|---------|---------|-----|
| 第1阶段 | 0.5天 | 0.5天 | 100% |
| 第2阶段 | 1-2天 | 0.5天（进行中） | 良好 |
| 第3阶段 | 1天 | 未开始 | - |
| 第4阶段 | 0.5天 | 未开始 | - |
| 第5阶段 | 2天 | 未开始 | - |
| 第6阶段 | 2天 | 未开始 | - |
| 第7阶段 | 1天 | 未开始 | - |
| 第8阶段 | 1-2天 | 未开始 | - |
| 第9阶段 | 2-3天 | 未开始 | - |

**总预计时间：** 10-15天  
**已用时间：** 2.5天  
**完成度：** 85%

### 知识点统计

- ✅ **已掌握：** 64个核心知识点
  - Maven多模块项目
  - Spring Boot基础配置
  - 启动类编写
  - Controller基础
  - 配置文件管理
  - 统一响应结果封装
  - HTTP状态码规范
  - 静态工厂方法模式
  - 链式调用（Fluent API）
  - 字符串工具类实现
  - 日期时间处理（Date与LocalDateTime）
  - Servlet工具类封装
  - RequestContextHolder的使用
  - Maven依赖管理
  - Apache Commons Lang3的应用
  - 实体类继承设计
  - 分页参数封装
  - 分页响应封装
  - BaseController设计模式
  - @JsonFormat日期格式化
  - Serializable序列化
  - 跨域配置（CORS）
  - 自定义异常设计
  - @RestControllerAdvice注解
  - @ExceptionHandler注解
  - 异常分层架构
  - 全局异常处理
  - 异常日志记录
  - 常量类设计
  - 魔法值消除
  - MyBatis-Plus基础
  - BaseMapper使用
  - LambdaQueryWrapper构建条件
  - IService接口
  - ServiceImpl实现类
  - 分页查询（Page）
  - Druid数据源配置
  - 逻辑删除
  - Redis基础概念
  - RedisTemplate配置
  - Fastjson2序列化器
  - String操作
  - List操作
  - Hash操作
  - Set操作
  - 过期时间管理
  - 模糊查询
  - 批量操作
  - 对象序列化
  - Lettuce连接池
  - 树形结构设计
  - 递归算法实现
  - 祖级列表（ancestors）
  - RBAC权限模型
  - 用户-角色-菜单关系
  - 数据权限设计
  - 关联表设计
  - 菜单类型（目录/菜单/按钮）
  - 权限标识（perms）
  - 泛型工具类设计
  - Function函数式接口
  - BiConsumer消费者接口
  - 级联检查
  - 唯一性校验

- 🔄 **学习中：** 
  - 第3、5阶段需要安装MySQL数据库才能测试
  - 第4阶段需要安装Redis才能测试
  
- ⏸️ **待学习：** 30+个

---

## 💡 学习心得

### 第1阶段心得

**收获：**
1. 理解了企业级项目的模块划分思路
2. 掌握了Maven依赖管理的机制
3. 熟悉了Spring Boot的快速开发方式

**感悟：**
- Maven多模块项目比单模块更清晰
- 合理的项目结构能提高开发效率
- 配置管理很重要

**改进方向：**
- 需要更深入理解Spring Boot自动配置原理
- 要多看官方文档
- 养成写文档的习惯

---

## 🎯 下一步计划

### 本周目标（第2阶段）

- [ ] 完成统一响应结果封装
- [ ] 实现常用工具类
- [ ] 搭建异常处理体系
- [ ] 定义项目常量

### 本月目标（第1-5阶段）

- [ ] 完成基础框架搭建
- [ ] 集成数据库和缓存
- [ ] 实现系统管理功能

### 长期目标（完整项目）

- [ ] 完成9个阶段的学习
- [ ] 深入理解Spring Boot
- [ ] 掌握企业级开发规范
- [ ] 能独立开发后端系统

---

## 📝 每日学习记录

### 2025-11-03

**学习内容：**
- 完成第1阶段：项目基础搭建
- 解决了Maven配置问题
- 成功启动第一个Spring Boot应用

**学习时长：** 4小时

**遇到的问题：**
1. Maven依赖问题
2. 数据库配置问题
3. IDEA配置问题

**收获：**
- 学会了Maven多模块项目的创建
- 理解了Spring Boot的启动流程
- 掌握了基本的项目结构设计

### 2025-11-06

**学习内容：**
- 完成第4阶段：Redis缓存
  - 配置Redis连接和RedisTemplate
  - 实现RedisCache工具类（540行）
  - 创建RedisCacheTestController测试接口（10个接口）
  - 学习Fastjson2序列化器
- 完成第5阶段：系统基础模块
  - 设计系统基础表结构（7张表）
  - 实现部门、角色、菜单实体类和Mapper
  - 实现TreeUtils树形结构工具类
  - 实现3个Service和Controller（20个接口）

**学习时长：** 9小时（第4阶段3小时 + 第5阶段6小时）

**遇到的问题：**
1. ❌ **问题：** Redis依赖配置位置错误
   - ✅ **解决：** 在hrone-common中也添加Redis依赖
2. ❌ **问题：** RedisTemplate类型不匹配
   - ✅ **解决：** 统一使用 `<String, Object>` 类型

**收获：**
- 掌握了Redis的集成和配置
- 理解了RedisTemplate的序列化机制
- 学会了5种Redis数据类型的使用
- 理解了缓存过期时间的设置
- 掌握了对象序列化和反序列化
- **掌握了树形结构的设计和实现**
- **理解了RBAC权限模型的基础**
- **学会了递归算法处理树形数据**
- **掌握了祖级列表（ancestors）的维护**
- **理解了关联表的设计思想**

### 2025-11-05

**学习内容：**
- 完成第2.1阶段：统一响应结果
  - 创建 HttpStatus 常量类
  - 实现 AjaxResult 统一响应类
  - 更新测试接口
- 完成第2.2阶段：基础工具类
  - 实现 StringUtils（字符串工具类）
  - 实现 DateUtils（日期工具类）
  - 实现 ServletUtils（Servlet工具类）
  - 创建 UtilsTestController 测试接口

**学习时长：** 3小时

**遇到的问题：**
1. ❌ **问题：** 端口8080被占用导致启动失败
   - ✅ **解决：** 使用 `netstat -ano | findstr :8080` 查找进程，`taskkill /F /PID` 杀掉进程
   
2. ❌ **问题：** 缺少 Apache Commons Lang3 依赖导致编译失败
   - ✅ **解决：** 在父POM和hrone-common的pom.xml中添加依赖
   
3. ❌ **问题：** StringUtils.isEmpty() 方法调用歧义
   - ✅ **解决：** 使用类型转换 `(CharSequence) null` 明确指定调用哪个重载方法

**收获：**
- 深入理解了工具类的设计原则（继承现有工具类、添加项目特定方法）
- 掌握了 Java 8 的 LocalDateTime API 及其与 Date 的互转
- 学会了使用 RequestContextHolder 在任意位置获取 Request/Response
- 理解了静态工厂方法模式和链式调用的优势
- 熟悉了 Maven 依赖管理的机制

- 完成第2.3阶段：通用实体类
  - 实现 BaseEntity（基础实体类）
  - 实现 PageDomain（分页参数）
  - 实现 TableDataInfo（分页响应）
  - 实现 BaseController（Controller基类）
  - 创建 PageTestController（分页测试接口）
- 配置前后端联调
  - 配置后端跨域（CorsConfig）
  - 配置前端代理（vite.config.ts）
  - 创建启动脚本

**学习时长：** 2小时

**遇到的问题：**
1. ❌ **问题：** 前端环境变量文件被 gitignore 忽略
   - ✅ **解决：** 在启动脚本中自动创建 .env 文件
   
2. ❌ **问题：** 前端请求报跨域错误
   - ✅ **解决：** 在后端创建 CorsConfig 配置类

**收获：**
- 理解了实体类继承的设计思想（所有实体继承BaseEntity）
- 掌握了分页参数的标准封装方式
- 学会了使用 @JsonFormat 统一日期格式
- 理解了 BaseController 的作用（提供通用方法）
- 掌握了前后端分离项目的跨域配置
- 熟悉了 Vite 的代理配置

- 完成第2.4阶段：异常处理
  - 实现 BaseException（基础异常类）
  - 实现 ServiceException（业务异常类）
  - 实现 GlobalExceptionHandler（全局异常处理器）
  - 创建 ExceptionTestController（异常测试接口）
  - 扩展 StringUtils 添加 join() 方法

**学习时长：** 2小时

**收获：**
- 掌握了全局异常处理的设计和实现
- 理解了 @RestControllerAdvice 和 @ExceptionHandler 的使用
- 学会了异常分层架构（BaseException → ServiceException）
- 理解了运行时异常的处理策略
- 掌握了异常日志的记录方式
- 理解了为什么不在Controller层catch异常

- 完成第2.5阶段：常量定义
  - 实现 Constants（通用常量类）
  - 实现 UserConstants（用户常量类）
  - 实现 CacheConstants（缓存常量类）
  - 创建 ConstantsTestController（常量测试接口）

**学习时长：** 30分钟

**收获：**
- 掌握了常量类的设计和组织方式
- 理解了消除魔法值的重要性
- 学会了按模块划分常量类
- 掌握了常量命名规范（全大写、下划线分隔）
- 理解了使用常量vs硬编码的区别

**🎉 第2阶段100%完成！** 
**🎉 第3阶段100%完成！**
**🎉 第4阶段100%完成！**
**🎉 第5阶段100%完成！**

**下一步：**
- 开始第6阶段：安全认证
- 实现JWT Token
- 实现登录功能
- 集成Spring Security

---

## 📚 学习资源

### 参考文档
- [Spring Boot官方文档](https://spring.io/projects/spring-boot)
- [Maven官方文档](https://maven.apache.org/)
- [Hutool工具类](https://hutool.cn/)

### 推荐视频
- 尚硅谷Spring Boot教程
- 黑马程序员Spring Boot

### 代码仓库
- 本地：D:\HroneWorking\hrone-Server

---

**更新说明：**
- 本文档会随着学习进度实时更新
- 记录每个阶段的学习心得和问题
- 追踪时间和效率

**使用建议：**
1. 每天更新学习记录
2. 及时记录遇到的问题
3. 定期回顾和总结
4. 与README.md配合使用

---

💪 **坚持学习，每天进步！**

