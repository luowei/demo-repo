package test.controller;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.WebRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import test.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @className:TestController.java
 * @classDescription:
 * @author:wei.luo
 * @createTime:2012-2-15
 */
@Controller        //标记这个类为控制器
@RequestMapping("/test")	//类的映射路径
public class TestController<AnyType> {
    @RequestMapping("/start")	//映射到start方法
    public String start(HttpServletRequest request,HttpServletResponse response){
        String nameString=request.getParameter("name");
        System.out.println("name="+nameString);
        return "start";
    }

    //restful风格
    //http://localhost:8080/@mvc/test/start.do?name=zhangsan
    //http://localhost:8080/@mvc/test/start.do?name=lisi
    //http://localhost:8080/@mvc/test/start/zhangsan.do
    //http://localhost:8080/@mvc/test/start/lisi.do

    @RequestMapping("/start2/{name}/{age}")	//name为url模板中取出的路径变量
    public String start2(@PathVariable String name,@PathVariable("age") String age,
			/*如果采用release模式，则需要定义为  @PathVariable("name") String name
			  因为release模式不保存变量名*/
            HttpServletRequest request,HttpServletResponse response){
        System.out.println("name="+name+" age="+age);
        return "start";
    }
    //访问以下链接：
    //http://localhost:8080/@mvc/test/start2/zhangsan/20.do

    //以相同的路径，不同的请求方法访问
    @RequestMapping(value="/start3",method= RequestMethod.POST)	//映射到start方法
    public String start_post(HttpServletRequest request,HttpServletResponse response){
        System.out.println("RequestMethod.POST");
        return "start_post";
    }

    @RequestMapping(value="/start3",method= RequestMethod.GET)
    public String start_get(HttpServletRequest request,HttpServletResponse respose){
        System.out.println("RequestMethod.GET");
        return "start_get";
    }

    @RequestMapping(value="/{test}",method= RequestMethod.GET)
    public void testPathVariable(@PathVariable("test") Date test){

    }

    @InitBinder
    public void initBinder(WebRequestDataBinder binder){
        binder.registerCustomEditor(Date.class,new CustomDateEditor(
                new SimpleDateFormat("yyyyMMdd"),false));
    }

    //session前提条件，当前请求session可用,request.getSession(),session.setAttribute();
    public void testAllArguments(HttpServletRequest request,HttpServletResponse response,
                                 HttpSession session,@PathVariable AnyType xxx,@PathVariable AnyType id,
                                 @CookieValue("user-Agent") AnyType cookieName){

    }

    @RequestMapping
    public String testAllArguments(PrintWriter out,Map model){
        //out = response.getWrite();
        //out.print();
        new ModelAndView();
        //out.print("-----------");
        return "viewName";
    }

    @RequestMapping
    public String testCommand(User user,BindingResult result){
        return "xxx";
    }

    @RequestMapping("/xxx")
    public void testVoid(){
        System.out.print("xxx");
        //会生成一个隐含的viewName -- 按请求路径,如果请求是xxx.do 则返回的视图为 xxx.jsp
        //${appName }/test/xxx.do --> test/xxx --> /WEB-INF/page/test/xxx.jsp
    }

    public User testuser(){  //model("user",user)
        return null;
    }

    //除了可以单独返回一个User对象，我们也可以返回一个List对象
    public List<User> testuser2(){
        //model("userList",user) request.getAttribute("userList");
        //即默认生成的key值为 userList
        return null;
    }
}

