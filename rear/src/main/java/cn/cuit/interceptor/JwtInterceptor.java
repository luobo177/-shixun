package cn.cuit.interceptor;

import cn.cuit.utils.JwtUtil;
import cn.cuit.utils.ThreadLocalUtil;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JwtInterceptor implements HandlerInterceptor {
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        // 放行OPTIONS请求
        if ("OPTIONS".equals(request.getMethod())) {
            return true;
        }
        
        // 获取token
        String token = request.getHeader("Authorization");
        if (!StringUtils.hasText(token)) {
            throw new RuntimeException("未登录");
        }
        
        try {
            // 验证token并获取adminId
            Long adminId = JwtUtil.getAdminId(token);
            // 存入ThreadLocal
            ThreadLocalUtil.setCurrentUser(adminId);
            return true;
        } catch (Exception e) {
            throw new RuntimeException("token无效");
        }
    }
    
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        // 清理ThreadLocal
        ThreadLocalUtil.remove();
    }
} 