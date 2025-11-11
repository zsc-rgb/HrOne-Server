package com.hrone.common.utils;

import  java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;

/**
 * 树形结构工具类
 * 
 * 用于将扁平化的列表数据转换为树形结构
 * 
 * @author hrone
 */
public class TreeUtils {

    /**
     * 构建树形结构（泛型版本）
     * 
     * @param list 扁平化的数据列表
     * @param getId 获取节点ID的函数
     * @param getParentId 获取父节点ID的函数
     * @param getChildren 获取子节点列表的函数
     * @param setChildren 设置子节点列表的函数
     * @param rootId 根节点ID（通常为0或null）
     * @param <T> 数据类型
     * @return 树形结构列表
     */
    public static <T> List<T> buildTree(List<T> list,
                                         Function<T, Object> getId,
                                         Function<T, Object> getParentId,
                                         Function<T, List<T>> getChildren,
                                         java.util.function.BiConsumer<T, List<T>> setChildren,
                                         Object rootId) {
        List<T> result = new ArrayList<>();
        
        for (T item : list) {
            Object parentId = getParentId.apply(item);
            
            // 如果是根节点
            if (isRoot(parentId, rootId)) {
                result.add(item);
                // 递归查找子节点
                setChildren.accept(item, getChildList(list, item, getId, getParentId, getChildren, setChildren));
            }
        }
        
        return result;
    }

    /**
     * 递归查找子节点
     */
    private static <T> List<T> getChildList(List<T> list,
                                             T parent,
                                             Function<T, Object> getId,
                                             Function<T, Object> getParentId,
                                             Function<T, List<T>> getChildren,
                                             java.util.function.BiConsumer<T, List<T>> setChildren) {
        List<T> childList = new ArrayList<>();
        
        Object parentId = getId.apply(parent);
        
        for (T item : list) {
            Object itemParentId = getParentId.apply(item);
            
            // 如果是当前节点的子节点
            if (parentId != null && parentId.equals(itemParentId)) {
                childList.add(item);
                // 递归查找子节点的子节点
                setChildren.accept(item, getChildList(list, item, getId, getParentId, getChildren, setChildren));
            }
        }
        
        return childList;
    }

    /**
     * 判断是否为根节点
     */
    private static boolean isRoot(Object parentId, Object rootId) {
        if (rootId == null) {
            return parentId == null;
        }
        
        if (rootId instanceof Long) {
            return rootId.equals(parentId) || (parentId == null) || "0".equals(String.valueOf(parentId)) || Long.valueOf(0).equals(parentId);
        }
        
        return rootId.equals(parentId);
    }

    /**
     * 查找所有子节点ID（包含自己）
     * 
     * @param list 所有节点列表
     * @param parent 父节点
     * @param getId 获取ID的函数
     * @param getParentId 获取父ID的函数
     * @param <T> 数据类型
     * @return 所有子节点ID列表（包含父节点ID）
     */
    public static <T> List<Object> getChildIds(List<T> list,
                                                 T parent,
                                                 Function<T, Object> getId,
                                                 Function<T, Object> getParentId) {
        List<Object> ids = new ArrayList<>();
        
        // 添加父节点ID
        ids.add(getId.apply(parent));
        
        // 递归添加子节点ID
        Object parentId = getId.apply(parent);
        for (T item : list) {
            if (parentId.equals(getParentId.apply(item))) {
                ids.addAll(getChildIds(list, item, getId, getParentId));
            }
        }
        
        return ids;
    }
}

