package com.hrone.framework.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * 跨域配置
 * 
 * 功能说明：
 * 1. 解决前后端分离项目的跨域问题
 * 2. 允许前端（localhost:3000）访问后端（localhost:8080）
 * 
 * 技术要点：
 * - 使用 CorsFilter 处理跨域请求
 * - 配置允许的源、方法、请求头
 * - 允许携带认证信息（Cookie、Token）
 * 
 * @author hrone
 */
@Configuration
public class CorsConfig {
    
    /**
     * 跨域过滤器配置
     * 
     * @return CorsFilter
     */
    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();
        
        // 允许的源（前端地址）
        // 开发环境：localhost:3000
        // 生产环境：根据实际情况配置
        config.addAllowedOriginPattern("*");
        
        // 允许携带认证信息（Cookie、Authorization Header）
        config.setAllowCredentials(true);
        
        // 允许的请求头
        config.addAllowedHeader("*");
        
        // 允许的请求方法
        config.addAllowedMethod("*");
        
        // 预检请求的缓存时间（秒）
        config.setMaxAge(3600L);
        
        // 配置路径映射
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        
        return new CorsFilter(source);
    }
}

