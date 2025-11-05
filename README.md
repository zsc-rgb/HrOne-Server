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

**2.4 异常处理 (2小时)**
- [ ] 自定义异常类体系
- [ ] 全局异常处理器
- [ ] 业务异常、服务异常

**2.5 常量定义 (30分钟)**
- [ ] Constants - 通用常量
- [ ] CacheConstants - 缓存常量
- [ ] UserConstants - 用户常量

**预计完成时间：** 1-2天

---

### 第3阶段：数据访问层 (hrone-framework)
**目标：** 集成MyBatis-Plus，实现数据库访问

**3.1 数据源配置 (2小时)**
- [ ] Druid数据源配置
- [ ] MyBatis-Plus基础配置
- [ ] 分页插件配置

**3.2 代码生成器 (3小时)**
- [ ] 配置代码生成器
- [ ] 自动生成Entity、Mapper、Service
- [ ] 理解MVC分层架构

**3.3 数据库操作 (2小时)**
- [ ] 基础CRUD操作
- [ ] 条件构造器
- [ ] 分页查询
- [ ] 多表关联查询

**预计完成时间：** 1天

---

### 第4阶段：Redis缓存 (hrone-framework)
**目标：** 集成Redis，实现缓存功能

**4.1 Redis配置 (1小时)**
- [ ] Redis连接配置
- [ ] RedisTemplate配置
- [ ] 序列化配置

**4.2 缓存工具类 (2小时)**
- [ ] RedisCache - 缓存操作工具
- [ ] 字符串、列表、哈希、集合操作
- [ ] 设置过期时间

**4.3 缓存应用 (1小时)**
- [ ] 字典数据缓存
- [ ] 配置缓存
- [ ] 用户Token缓存

**预计完成时间：** 半天

---

### 第5阶段：系统基础模块 (hrone-system)
**目标：** 实现系统管理的基础功能

**5.1 用户管理 (4小时)**
- [ ] 用户表设计
- [ ] 用户CRUD接口
- [ ] 用户信息查询
- [ ] 密码加密存储

**5.2 角色管理 (3小时)**
- [ ] 角色表设计
- [ ] 角色CRUD接口
- [ ] 用户角色关联

**5.3 菜单管理 (3小时)**
- [ ] 菜单表设计（树形结构）
- [ ] 菜单CRUD接口
- [ ] 树形结构构建

**5.4 字典管理 (2小时)**
- [ ] 字典表设计
- [ ] 字典CRUD接口
- [ ] 字典缓存

**预计完成时间：** 2天

---

### 第6阶段：安全认证 (hrone-framework)
**目标：** 实现JWT登录认证和授权

**6.1 JWT工具类 (2小时)**
- [ ] JWT生成
- [ ] JWT解析
- [ ] JWT验证

**6.2 登录功能 (4小时)**
- [ ] 登录接口
- [ ] 验证码生成
- [ ] 密码验证
- [ ] Token生成和返回

**6.3 JWT过滤器 (3小时)**
- [ ] JwtAuthenticationTokenFilter
- [ ] Token解析
- [ ] 用户信息加载

**6.4 权限注解 (2小时)**
- [ ] @RequiresPermissions
- [ ] @RequiresRoles
- [ ] 权限验证切面

**预计完成时间：** 2天

---

### 第7阶段：权限系统 (hrone-framework)
**目标：** 实现RBAC权限控制

**7.1 权限设计 (1小时)**
- [ ] 理解RBAC模型
- [ ] 用户-角色-菜单-权限关系

**7.2 权限查询 (3小时)**
- [ ] 根据用户查询角色
- [ ] 根据角色查询菜单
- [ ] 根据菜单查询权限

**7.3 权限验证 (2小时)**
- [ ] 接口权限验证
- [ ] 数据权限过滤
- [ ] 按钮权限控制

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
- ✅ 第2.1阶段：统一响应结果
- ✅ 第2.2阶段：基础工具类
- ✅ 第2.3阶段：通用实体类
- 🔄 第2.4阶段：异常处理（待开始）

### 3. 下一步
阅读 `docs/phase-02-通用工具.md` 继续第二阶段的学习（通用实体类、异常处理、常量定义）

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
| 第2.4阶段 | - | - | - |

---

💪 加油！一步一步来，每天进步一点点！

