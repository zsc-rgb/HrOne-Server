package com.hrone.controller;

import com.hrone.common.core.controller.BaseController;
import com.hrone.common.core.domain.BaseEntity;
import com.hrone.common.core.page.TableDataInfo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 分页测试Controller
 * 
 * 用途：测试第2.3阶段实现的分页功能
 * 
 * @author hrone
 */
@RestController
@RequestMapping("/test/page")
public class PageTestController extends BaseController {
    
    /**
     * 测试用户实体类
     * 
     * 继承 BaseEntity，自动拥有创建时间、更新时间等字段
     */
    static class TestUser extends BaseEntity {
        private Long userId;
        private String userName;
        private String email;
        private String phone;
        private Integer age;
        
        public TestUser(Long userId, String userName, String email, String phone, Integer age) {
            this.userId = userId;
            this.userName = userName;
            this.email = email;
            this.phone = phone;
            this.age = age;
            
            // 设置基础字段
            this.setCreateBy("admin");
            this.setCreateTime(new Date());
            this.setRemark("测试用户数据");
        }
        
        // Getters and Setters
        public Long getUserId() { return userId; }
        public void setUserId(Long userId) { this.userId = userId; }
        
        public String getUserName() { return userName; }
        public void setUserName(String userName) { this.userName = userName; }
        
        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }
        
        public String getPhone() { return phone; }
        public void setPhone(String phone) { this.phone = phone; }
        
        public Integer getAge() { return age; }
        public void setAge(Integer age) { this.age = age; }
    }
    
    /**
     * 生成测试数据
     * 
     * @param count 数据条数
     * @return 测试用户列表
     */
    private List<TestUser> generateTestData(int count) {
        List<TestUser> list = new ArrayList<>();
        for (int i = 1; i <= count; i++) {
            TestUser user = new TestUser(
                (long) i,
                "用户" + i,
                "user" + i + "@example.com",
                "1380000" + String.format("%04d", i),
                20 + (i % 50)
            );
            list.add(user);
        }
        return list;
    }
    
    /**
     * 测试分页查询 - 简单示例
     * 
     * 访问地址：GET http://localhost:8080/test/page/list
     */
    @GetMapping("/list")
    public TableDataInfo testPageList() {
        // 模拟查询10条数据
        List<TestUser> list = generateTestData(10);
        
        // 使用 BaseController 的 getDataTable 方法构建分页响应
        return getDataTable(list);
    }
    
    /**
     * 测试分页查询 - 大数据量
     * 
     * 访问地址：GET http://localhost:8080/test/page/bigList
     */
    @GetMapping("/bigList")
    public TableDataInfo testBigList() {
        // 模拟查询100条数据
        List<TestUser> list = generateTestData(100);
        
        return getDataTable(list);
    }
    
    /**
     * 测试 BaseEntity 字段
     * 
     * 访问地址：GET http://localhost:8080/test/page/baseFields
     * 
     * 说明：
     * 查看返回的数据中是否包含：
     * - createBy（创建者）
     * - createTime（创建时间）
     * - remark（备注）
     */
    @GetMapping("/baseFields")
    public TableDataInfo testBaseFields() {
        List<TestUser> list = new ArrayList<>();
        
        TestUser user1 = new TestUser(1L, "张三", "zhangsan@example.com", "13800001111", 25);
        user1.setCreateBy("admin");
        user1.setCreateTime(new Date());
        user1.setUpdateBy("admin");
        user1.setUpdateTime(new Date());
        user1.setRemark("这是第一个测试用户");
        
        TestUser user2 = new TestUser(2L, "李四", "lisi@example.com", "13800002222", 30);
        user2.setCreateBy("system");
        user2.setCreateTime(new Date());
        user2.setRemark("这是第二个测试用户");
        
        list.add(user1);
        list.add(user2);
        
        return getDataTable(list);
    }
    
    /**
     * 测试空数据
     * 
     * 访问地址：GET http://localhost:8080/test/page/empty
     */
    @GetMapping("/empty")
    public TableDataInfo testEmpty() {
        List<TestUser> list = new ArrayList<>();
        return getDataTable(list);
    }
    
    /**
     * 测试首页（显示所有测试接口）
     * 
     * 访问地址：GET http://localhost:8080/test/page
     */
    @GetMapping
    public TableDataInfo index() {
        List<Object> apis = new ArrayList<>();
        
        // 使用匿名内部类创建API说明对象
        apis.add(new Object() {
            public String name = "简单分页测试";
            public String url = "GET /test/page/list";
            public String description = "返回10条测试数据";
        });
        
        apis.add(new Object() {
            public String name = "大数据量测试";
            public String url = "GET /test/page/bigList";
            public String description = "返回100条测试数据";
        });
        
        apis.add(new Object() {
            public String name = "BaseEntity字段测试";
            public String url = "GET /test/page/baseFields";
            public String description = "查看创建时间、更新时间等字段";
        });
        
        apis.add(new Object() {
            public String name = "空数据测试";
            public String url = "GET /test/page/empty";
            public String description = "返回空列表";
        });
        
        TableDataInfo result = new TableDataInfo();
        result.setRows(apis);
        result.setTotal(apis.size());
        result.setCode(200);
        result.setMsg("分页测试接口列表");
        
        return result;
    }
}

