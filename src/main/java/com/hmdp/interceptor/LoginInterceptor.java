package com.hmdp.interceptor;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.hmdp.dto.UserDTO;
import com.hmdp.entity.User;
import com.hmdp.utils.RedisConstants;
import com.hmdp.utils.UserHolder;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        // 1.获取session
//        HttpSession session = request.getSession();
//        // 2.获取session中的用户
//        Object user = session.getAttribute("user");
//        // 3.判断用户是否存在
//        if(user == null){
//            // 4.不存在 拦截  返回401状态码
//            response.setStatus(401);
//            return false;
//        }
//        // 5.存在，保存用户信息到threadLocal
//        UserHolder.saveUser((UserDTO) user);
//        // 6.放行
//        return true;



//        // 1.获取redis中的token
//        String token = request.getHeader("authorization");
//        if(StrUtil.isBlank(token)){
//            // 不存在，拦截，返回401状态码
//            response.setStatus(401);
//            return false;
//        }
//        // 2.基于token获取redis中的用户
//        String key = RedisConstants.LOGIN_USER_KEY + token;
//        Map<Object, Object> userMap = stringRedisTemplate.opsForHash().entries(key);
//        // 3.判断用户是否存在
//        if(userMap.isEmpty()){
//            // 4.不存在 拦截  返回401状态码
//            response.setStatus(401);
//            return false;
//        }
//        // 5.将查询到的hash数据转为userDTO对象
//        UserDTO userDTO = BeanUtil.fillBeanWithMap(userMap, new UserDTO(), false);
//        // 6.存在，保存用户信息到threadLocal
//        UserHolder.saveUser(userDTO);
//        // 6.放行
//        return true;

        // 判断是否需要去拦截
        if(UserHolder.getUser() == null){
            //没有 需要拦截，设置状态码
            response.setStatus(401);
            //拦截
            return false;
        }
        // 有用户，则放行
        return true;
    }
}
