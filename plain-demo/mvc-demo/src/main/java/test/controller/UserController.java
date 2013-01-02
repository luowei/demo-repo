package test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import test.entity.User;

import javax.validation.Valid;

/**
 * @className:UserController.java
 * @classDescription:
 * @author:wei.luo
 * @createTime:2012-2-16上午12:59:17
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @RequestMapping(method= RequestMethod.GET)
    public String addForm(){
        return "add_user";
    }

    @RequestMapping(method= RequestMethod.POST)
    public String addUser(User user){
        //返回一个重定向视图
        return "redirect:/user_list";
    }

    //避免重复提交
    //request.forward(); a -server -> b
    //response.redirect a --> server --> client --> b

    //spring mvc的bean验证,对user对象加入验证，并绑定到一个结果集上
    @RequestMapping(method= RequestMethod.POST)
    public String addUser2(@Valid User user,BindingResult result){
        if(result.hasErrors()){	//如果验证发生错误
            return "formView";
        }
        //返回一个重定向视图
        return "redirect:/user_list";
    }

    //使用这个验证，要加入以下几个包：
    //validation-api-1.0.0.GA.jar
    //hibernate-validator-4.0.2.GA.jar
    //slf4j-api-1.5.6.jar
    //slf4j-log4j12-1.5.6.jar

}
