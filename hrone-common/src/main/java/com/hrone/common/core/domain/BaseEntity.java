package com.hrone.common.core.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 基础实体类
 * 
 * 功能说明：
 * 1. 所有数据库实体类的父类
 * 2. 包含通用字段：创建时间、更新时间、备注等
 * 3. 提供搜索参数、额外参数的存储
 * 
 * 技术要点：
 * - 实现 Serializable 接口，支持序列化
 * - 使用 @JsonFormat 格式化日期
 * - 提供请求参数的临时存储（不映射到数据库）
 * 
 * 使用场景：
 * - 所有实体类继承此类，自动拥有创建时间、更新时间等字段
 * - 分页查询时传递额外参数
 * 
 * @author hrone
 */
public class BaseEntity implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    /**
     * 搜索值
     * 用于模糊查询，不映射到数据库
     */
    private String searchValue;
    
    /**
     * 创建者
     */
    private String createBy;
    
    /**
     * 创建时间
     * 使用 @JsonFormat 注解格式化日期，统一返回格式
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    
    /**
     * 更新者
     */
    private String updateBy;
    
    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
    
    /**
     * 备注
     */
    private String remark;
    
    /**
     * 请求参数
     * 用于存储额外的请求参数，不映射到数据库
     * 
     * 使用场景：
     * - 分页参数（pageNum、pageSize）
     * - 时间范围查询（beginTime、endTime）
     * - 其他动态查询条件
     */
    private Map<String, Object> params;
    
    /**
     * 获取搜索值
     */
    public String getSearchValue() {
        return searchValue;
    }
    
    /**
     * 设置搜索值
     */
    public void setSearchValue(String searchValue) {
        this.searchValue = searchValue;
    }
    
    /**
     * 获取创建者
     */
    public String getCreateBy() {
        return createBy;
    }
    
    /**
     * 设置创建者
     */
    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }
    
    /**
     * 获取创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }
    
    /**
     * 设置创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    
    /**
     * 获取更新者
     */
    public String getUpdateBy() {
        return updateBy;
    }
    
    /**
     * 设置更新者
     */
    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }
    
    /**
     * 获取更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }
    
    /**
     * 设置更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
    
    /**
     * 获取备注
     */
    public String getRemark() {
        return remark;
    }
    
    /**
     * 设置备注
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }
    
    /**
     * 获取请求参数
     * 
     * @return 参数Map
     */
    public Map<String, Object> getParams() {
        if (params == null) {
            params = new HashMap<>();
        }
        return params;
    }
    
    /**
     * 设置请求参数
     * 
     * @param params 参数Map
     */
    public void setParams(Map<String, Object> params) {
        this.params = params;
    }
}

