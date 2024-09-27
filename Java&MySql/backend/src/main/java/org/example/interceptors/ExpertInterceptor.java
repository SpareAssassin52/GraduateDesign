package org.example.interceptors;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.utls.JwtUtil;
import org.example.utls.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Map;

@Component
public class ExpertInterceptor implements HandlerInterceptor {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //using "HttpServletRequest request" to get token
        String token = request.getHeader("Authorization");

        //parse token
        try {
            //get the same token from redis
            ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
            String redisToken = operations.get(token);
            if (redisToken == null) {
                //token invalid
                throw new RuntimeException();
            }

            Map<String, Object> claims = JwtUtil.parseToken(token);
            if (!claims.get("role").equals("expert")&&!claims.get("role").equals("admin")) {
                //token invalid
                throw new RuntimeException();
            }

            //store token into ThreadLocal(global argument)
            ThreadLocalUtil.set(claims);

            return true;    //interceptor using boolean type to decide it will let you pass or not. returning true means pass.
        } catch (Exception e) {
            response.setStatus(401);    //according to the readme.md, if token failed, server should respond an error code 401.
            return false;
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        //clear threadlocal to prevent memory leak.
        ThreadLocalUtil.remove();
    }
}
