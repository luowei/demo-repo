package test.controller;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @className:MyInteceptor.java
 * @classDescription:
 * @author:wei.luo
 * @createTime:2012-2-16上午01:30:20
 */
public class MyInteceptor implements HandlerInterceptor {

    //释放资源
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object arg2, Exception arg3)
            throws Exception {
    }

    //控制器执行完，生成视图之前可以做的动作，向模型中添加公共成员
    public void postHandle(HttpServletRequest request, HttpServletResponse response,
                           Object handler, ModelAndView modelAndView) throws Exception {
    }

    //拦截之前执行的方法
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {
        return false;
    }
}
