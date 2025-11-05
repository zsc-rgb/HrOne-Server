@echo off
chcp 65001 >nul
echo ========================================
echo    启动 HROne 后端系统
echo ========================================
echo.

cd /d "%~dp0"

echo [1/2] 正在编译项目...
call mvn clean package -DskipTests
if errorlevel 1 (
    echo.
    echo ❌ 编译失败，请检查错误信息
    pause
    exit /b 1
)

echo.
echo [2/2] 正在启动应用...
echo.
echo ========================================
echo   访问地址：http://localhost:8080
echo   测试接口：http://localhost:8080/test/hello
echo ========================================
echo.

cd hrone-admin
call mvn spring-boot:run

pause

