package test;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @className:StartController.java
 * @classDescription:
 * @author:wei.luo
 * @createTime:2012-2-12下午09:21:58
 */
public class StartController implements Controller {

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response) throws Exception {
        System.out.println("start controller is working !");
        return new ModelAndView("start");	//只指定一个视图的逻辑名称叫start
        //使用控制器与视图名称完全分离，只返回一个名称叫start的视图，具体返回什么视图由后续配置决定。
    }
}