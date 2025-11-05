package com.hrone.common.core.domain;

import com.hrone.common.utils.StringUtils;

/**
 * 分页参数
 * 
 * 功能说明：
 * 1. 封装分页查询的参数（页码、每页大小、排序）
 * 2. 统一分页参数的接收和处理
 * 3. 支持前端传递的分页排序参数
 * 
 * 技术要点：
 * - pageNum：当前页码（从1开始）
 * - pageSize：每页显示条数
 * - orderByColumn：排序字段
 * - isAsc：排序方式（asc升序、desc降序）
 * 
 * 前端传参示例：
 * ?pageNum=1&pageSize=10&orderByColumn=createTime&isAsc=desc
 * 
 * @author hrone
 */
public class PageDomain {
    
    /**
     * 当前页码
     * 默认第1页
     */
    private Integer pageNum;
    
    /**
     * 每页显示条数
     * 默认10条
     */
    private Integer pageSize;
    
    /**
     * 排序字段
     * 例如：create_time、user_name
     */
    private String orderByColumn;
    
    /**
     * 排序方式
     * asc：升序
     * desc：降序
     */
    private String isAsc = "asc";
    
    /**
     * 是否开启合理化查询
     * 
     * 合理化说明：
     * - pageNum < 1 时，自动查询第一页
     * - pageNum > 总页数时，自动查询最后一页
     */
    private Boolean reasonable = true;
    
    /**
     * 获取当前页码
     */
    public Integer getPageNum() {
        return pageNum;
    }
    
    /**
     * 设置当前页码
     */
    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }
    
    /**
     * 获取每页显示条数
     */
    public Integer getPageSize() {
        return pageSize;
    }
    
    /**
     * 设置每页显示条数
     */
    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
    
    /**
     * 获取排序字段
     */
    public String getOrderByColumn() {
        return orderByColumn;
    }
    
    /**
     * 设置排序字段
     * 
     * @param orderByColumn 排序字段（驼峰格式）
     */
    public void setOrderByColumn(String orderByColumn) {
        this.orderByColumn = orderByColumn;
    }
    
    /**
     * 获取排序方式
     */
    public String getIsAsc() {
        return isAsc;
    }
    
    /**
     * 设置排序方式
     * 
     * @param isAsc asc或desc
     */
    public void setIsAsc(String isAsc) {
        // 统一转换为小写
        if (StringUtils.isNotEmpty(isAsc)) {
            this.isAsc = isAsc.toLowerCase();
        }
    }
    
    /**
     * 是否开启合理化查询
     */
    public Boolean getReasonable() {
        if (reasonable == null) {
            return Boolean.TRUE;
        }
        return reasonable;
    }
    
    /**
     * 设置是否开启合理化查询
     */
    public void setReasonable(Boolean reasonable) {
        this.reasonable = reasonable;
    }
}

