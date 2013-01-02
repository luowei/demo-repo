package test;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @className:EmpMultiController.java
 * @classDescription:
 * @author:wei.luo
 * @createTime:2012-2-14
 */
public class EmpMultiController extends MultiActionController {
    private ModelAndView toAdd() {

        //viewName logic name :视图的逻辑名
        //Model 隐含了Map对象

        //构建模型的方法：
        //1.直接在new的时候，传入模型名称
        //ModelAndView mav=new ModelAndView("add_emp");

        //2.先构建模型，再执行setViewName方法
        //ModelAndView mav=new ModelAndView();
        //mav.setViewName("add_emp");

        //3.加入模型项
        //mav.addObject(attributeValue);

        //4.也可以一次性加入一个Map项
        //Map model = new HashMap;
        //mav.addAllObjects(modelMap);

        ModelAndView mav = new ModelAndView();
        mav.setViewName("add_emp");
        mav.addObject("deptList",new String[]{"salse","manage"});
        return mav;
    }

    //public ModelAndView addEmp(HttpServletRequest request,
    public String addEmp(HttpServletRequest request,
                         HttpServletResponse response,HttpSession session,Emp emp){
        System.out.println(emp);
        //return new ModelAndView("success");
        return "success";	//直接返回一个字符串，就是viewName
    }
}
