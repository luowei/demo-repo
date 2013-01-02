package test;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractCommandController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @className:EmpController.java
 * @classDescription:Emp控制器
 * @author:wei.luo
 * @createTime:2012-2-13上午01:14:47
 */
/*public class EmpController extends AbstractController {	//实现简单的Controller抽象类

	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {	//
		String name=request.getParameter("name");
		String phone=request.getParameter("phone");
		Emp emp=new Emp();
		emp.setName(name);
		emp.setPhone(phone);
		//empService.add(emp);
		return null;
	}

}*/

public class EmpController extends AbstractCommandController {
    @Override
    protected ModelAndView handle(HttpServletRequest request,
                                  HttpServletResponse response, Object command, BindException errors)
            throws Exception {
        Emp emp=(Emp)command;

        System.out.println(emp.toString());
        return new ModelAndView("success");
    }

    /**
     * 初始化数据绑定器
     */
    @Override
    protected void initBinder(HttpServletRequest request,
                              ServletRequestDataBinder binder) throws Exception {
        //绑定日期数据，注册一个新的日期编辑器
        binder.registerCustomEditor(Date.class, new CustomDateEditor(
                new SimpleDateFormat("yyyy-MM-dd"), true));
    }
}







