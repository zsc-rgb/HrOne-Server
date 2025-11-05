package com.hrone.common.core.page;

import java.io.Serializable;
import java.util.List;

/**
 * 表格分页数据对象
 * 
 * 功能说明：
 * 1. 封装分页查询的响应数据
 * 2. 包含数据列表、总记录数、状态码、提示信息
 * 3. 前端表格组件可以直接使用
 * 
 * 技术要点：
 * - total：总记录数（用于计算总页数）
 * - rows：当前页的数据列表
 * - code：状态码（200成功）
 * - msg：提示信息
 * 
 * 响应示例：
 * {
 *   "total": 100,
 *   "rows": [...],
 *   "code": 200,
 *   "msg": "查询成功"
 * }
 * 
 * @author hrone
 */
public class TableDataInfo implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    /**
     * 总记录数
     */
    private long total;
    
    /**
     * 列表数据
     */
    private List<?> rows;
    
    /**
     * 消息状态码
     */
    private int code;
    
    /**
     * 消息内容
     */
    private String msg;
    
    /**
     * 无参构造方法
     */
    public TableDataInfo() {
    }
    
    /**
     * 分页构造方法
     * 
     * @param list  列表数据
     * @param total 总记录数
     */
    public TableDataInfo(List<?> list, int total) {
        this.rows = list;
        this.total = total;
    }
    
    /**
     * 获取总记录数
     */
    public long getTotal() {
        return total;
    }
    
    /**
     * 设置总记录数
     */
    public void setTotal(long total) {
        this.total = total;
    }
    
    /**
     * 获取列表数据
     */
    public List<?> getRows() {
        return rows;
    }
    
    /**
     * 设置列表数据
     */
    public void setRows(List<?> rows) {
        this.rows = rows;
    }
    
    /**
     * 获取消息状态码
     */
    public int getCode() {
        return code;
    }
    
    /**
     * 设置消息状态码
     */
    public void setCode(int code) {
        this.code = code;
    }
    
    /**
     * 获取消息内容
     */
    public String getMsg() {
        return msg;
    }
    
    /**
     * 设置消息内容
     */
    public void setMsg(String msg) {
        this.msg = msg;
    }
}

