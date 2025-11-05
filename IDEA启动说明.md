# 💡 在IDEA中启动项目（最简单！）

## 为什么推荐用IDEA？

- ✅ 无需记忆复杂的Maven命令
- ✅ 启动日志清晰，方便调试
- ✅ 可以设置断点调试
- ✅ 热部署自动生效

## 📝 详细步骤

### 第1步：确保项目已导入IDEA

1. 打开IDEA
2. `File` → `Open`
3. 选择 `D:\HroneWorking\hrone-Server` 文件夹
4. 点击 `OK`
5. 等待Maven依赖加载完成（右下角可以看到进度）

### 第2步：找到启动类

在左侧项目树中：

```
hrone-Server
  └── hrone-admin
      └── src
          └── main
              └── java
                  └── com.hrone
                      └── HROneApplication.java  ← 这个文件！
```

### 第3步：运行启动类

**方式A：使用行号旁的运行按钮**
1. 双击打开 `HROneApplication.java`
2. 在第14行 `public static void main` 左侧
3. 点击绿色三角形 ▶️
4. 选择 `Run 'HROneApplication.main()'`

**方式B：右键菜单**
1. 在项目树中右键点击 `HROneApplication.java`
2. 选择 `Run 'HROneApplication.main()'`

**方式C：使用快捷键**
1. 打开 `HROneApplication.java` 文件
2. 按 `Shift + F10` 运行

### 第4步：查看启动日志

在IDEA底部会出现 `Run` 窗口，显示启动日志：

```
  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::               (v2.5.14)

...

  _   _ ____   ___             
 | | | |  _ \ / _ \ _ __   ___ 
 | |_| | |_) | | | | '_ \ / _ \
 |  _  |  _ <| |_| | | | |  __/
 |_| |_|_| \_\___/|_| |_|\___|

========================================
   HROne 后端系统启动成功！
   访问地址：http://localhost:8080
   测试接口：http://localhost:8080/test/hello
========================================
```

看到这个输出就说明启动成功了！

### 第5步：测试接口

打开浏览器，访问：
- http://localhost:8080/test/hello
- http://localhost:8080/test/health

应该看到JSON响应。

## 🔧 常见问题

### Q1: 找不到绿色运行按钮？

**解决方案：**
- 确保IDEA已识别为Maven项目
- 右侧有 `Maven` 工具栏，点击刷新
- 或直接右键文件选择 `Run`

### Q2: 启动报错：DataSource错误

**解决方案：**

修改 `HROneApplication.java`，第11行改为：

```java
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class HROneApplication {
```

需要添加import：
```java
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
```

### Q3: 端口8080被占用

**解决方案：**

修改 `hrone-admin/src/main/resources/application.yml`：

```yaml
server:
  port: 8081  # 改成其他端口
```

### Q4: Maven依赖下载失败

**解决方案：**
1. 点击右侧 `Maven` 工具栏
2. 点击刷新按钮（圆形箭头图标）
3. 或者执行：`mvn clean install -U`

## 💡 推荐设置

### 1. 启用热部署

已配置 `spring-boot-devtools`，修改代码后：
- 按 `Ctrl + F9` 编译
- 应用会自动重启

### 2. 设置自动编译

1. `File` → `Settings`
2. `Build, Execution, Deployment` → `Compiler`
3. 勾选 `Build project automatically`

### 3. 安装Lombok插件

1. `File` → `Settings` → `Plugins`
2. 搜索 `Lombok`
3. 点击 `Install`
4. 重启IDEA

## 🎯 启动成功的标志

✅ 控制台显示ASCII艺术字
✅ 日志最后显示"启动成功"信息
✅ 浏览器能访问测试接口
✅ 没有ERROR级别的日志

---

**提示：** 这是学习项目最推荐的启动方式！

