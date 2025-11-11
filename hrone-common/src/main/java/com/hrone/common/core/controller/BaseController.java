package com.hrone.common.core.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hrone.common.constant.HttpStatus;
import com.hrone.common.core.domain.AjaxResult;
import com.hrone.common.core.domain.PageDomain;
import com.hrone.common.core.page.TableDataInfo;
import com.hrone.common.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * web层通用数据处理
 * 
 * 功能说明：
 * 1. Controller的基类，提供通用方法
 * 2. 封装分页响应的构建
 * 3. 提供通用的响应方法
 * 
 * 技术要点：
 * - 所有Controller继承此类
 * - 提供getDataTable()方法快速构建分页响应
 * - 统一响应格式
 * 
 * 使用示例：
 * public class UserController extends BaseController {
 *     @GetMapping("/list")
 *     public TableDataInfo list(User user) {
 *         List<User> list = userService.selectUserList(user);
 *         return getDataTable(list);
 *     }
 * }
 * 
 * @author hrone
 */
public class BaseController {
    
    /**
     * 日志对象
     */
    protected final Logger log = LoggerFactory.getLogger(this.getClass());
    
    /**
     * 设置请求分页数据
     * 
     * 使用场景：
     * - 在分页查询前调用
     * - 从请求参数中获取分页参数
     * - 设置到ThreadLocal中供PageHelper使用
     */
    protected void startPage() {
        PageDomain pageDomain = getPageDomain();
        Integer pageNum = pageDomain.getPageNum();
        Integer pageSize = pageDomain.getPageSize();
        
        // 这里暂时不实现PageHelper的集成
        // 在后续阶段集成PageHelper后再完善
        // PageHelper.startPage(pageNum, pageSize, orderBy);
    }
    
    /**
     * 获取分页参数
     * 
     * @return 分页参数对象
     */
    protected PageDomain getPageDomain() {
        PageDomain pageDomain = new PageDomain();
        pageDomain.setPageNum(1);  // 默认第1页
        pageDomain.setPageSize(10); // 默认10条
        return pageDomain;
    }
    
    /**
     * 响应请求分页数据
     * 
     * 使用场景：
     * - 分页查询后调用
     * - 自动从PageHelper中获取分页信息
     * - 构建TableDataInfo响应
     * 
     * @param list 数据列表
     * @return 分页响应对象
     */
    protected TableDataInfo getDataTable(List<?> list) {
        TableDataInfo rspData = new TableDataInfo();
        rspData.setCode(HttpStatus.SUCCESS);
        rspData.setMsg("查询成功");
        rspData.setRows(list);
        
        // 暂时使用列表大小作为总数
        // 在后续阶段集成PageHelper后，使用 PageInfo 获取真实总数
        rspData.setTotal(list.size());
        return rspData;
    }
    
    /**
     * 响应请求分页数据（MyBatis-Plus IPage版本）
     * 
     * 使用场景：
     * - MyBatis-Plus分页查询后调用
     * - 从IPage中提取数据列表和总记录数
     * - 构建TableDataInfo响应
     * 
     * @param page MyBatis-Plus分页对象
     * @return 分页响应对象
     */
    protected TableDataInfo getDataTable(IPage<?> page) {
        TableDataInfo rspData = new TableDataInfo();
        rspData.setCode(HttpStatus.SUCCESS);
        rspData.setMsg("查询成功");
        rspData.setRows(page.getRecords());
        rspData.setTotal(page.getTotal());
        return rspData;
    }
    
    /**
     * 响应返回结果
     * 
     * @param rows 受影响行数
     * @return 操作结果
     */
    protected AjaxResult toAjax(int rows) {
        return rows > 0 ? AjaxResult.success() : AjaxResult.error();
    }
    
    /**
     * 响应返回结果
     * 
     * @param result 操作结果
     * @return 操作结果
     */
    protected AjaxResult toAjax(boolean result) {
        return result ? AjaxResult.success() : AjaxResult.error();
    }
}

