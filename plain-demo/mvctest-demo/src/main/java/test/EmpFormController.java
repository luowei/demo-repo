package test;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @className:EmpFormController.java
 * @classDescription:表单控制器
 * @author:wei.luo
 * @createTime:2012-2-13上午02:41:45
 */
public class EmpFormController extends SimpleFormController {
    /**
     * 当表单提交是执行
     */
    @Override
    protected ModelAndView onSubmit(Object command) throws Exception {
        Emp emp=(Emp)command;	//注意，直接将command对象转换成Emp对象即可
        System.out.println(emp.toString());
        return new ModelAndView(getSuccessView());
    }

    /**
     * 此方法处理模型，向模型中加入两个列表
     */
    @Override
    protected Map referenceData(HttpServletRequest request) throws Exception {
        Map<String,Object> model=new HashMap<String,Object>();	//建立一个模型
        model.put("deptList", new String[]{"sales","manage"});	//向模型中放入一个列表
        return model;	//返回加入了deptList的模型
    }

    /**
     * 添加日期属性编辑器
     */
    @Override
    protected void initBinder(HttpServletRequest request,
                              ServletRequestDataBinder binder) throws Exception {
        //绑定日期数据，注册一个新的日期编辑器
        binder.registerCustomEditor(Date.class, new CustomDateEditor(
                new SimpleDateFormat("yyyy-MM-dd"), true));
    }
}
