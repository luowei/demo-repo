package com.jxl.demo.web;

import com.jxl.demo.Entity.Student;
import com.jxl.demo.Entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 生成excel或PDF类型试图 根据参数进行数据组装，并跳转到相应的视图页面 View Controller Bean<br>
 */

@Controller
@RequestMapping("/view")
public class ViewController {

	@RequestMapping("/excel")
	public ModelAndView viewExcel(HttpServletRequest request,
			HttpServletResponse response) {
		Map model = new HashMap();		
		model.put("list", getStudents());		
		return new ModelAndView(new ViewExcel(), model);
	}
	
	private List getStudents(){
		List stuList = new ArrayList();
		// 构造数据
		Student stu1 = new Student("gaoxiang1", "male1", "20060101", 1);
		Student stu2 = new Student("gaoxiang2", "male2", "20060102", 2);
		Student stu3 = new Student("gaoxiang3", "male3", "20060103", 3);
		Student stu4 = new Student("gaoxiang4", "male4", "20060104", 4);
		Student stu5 = new Student("gaoxiang5", "male5", "20060105", 5);	
		stuList.add(stu1);
		stuList.add(stu2);
		stuList.add(stu3);
		stuList.add(stu4);
		stuList.add(stu5);
		return stuList;
	}

	@RequestMapping("/jxlExcel")
	public ModelAndView viewJxlExcel(@RequestParam("titles") String titles,@RequestParam("colums") String colums,HttpServletRequest request,
			HttpServletResponse response) {
		String [] array1 = null;
		if(null != colums && colums.indexOf(",") != -1){
			array1 = colums.split(",");
		}
		String [] array2 = null;
		if(null != titles && titles.indexOf(",") != -1){
			array2 = titles.split(",");
		}			
		Map model = new HashMap();
		// 构造数据
		List<User> users = new ArrayList<User>();
		users.add(new User("123456", "李逵", "123", "成都市", "1", 23));
		users.add(new User("123457", "李四", "124", "北京市", "2", 53));
		users.add(new User("123458", "李三", "125", "河南市", "0", 73));
		users.add(new User("123459", "李五", "126", "大路市", "3", 93));
		model.put("list", users);		
		model.put("columns", array1);
		model.put("titles", array2);		
		return new ModelAndView(new JXLExcelView(), model);
	}
	
	@RequestMapping("/pdf")
	public ModelAndView viewPDF(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map model = new HashMap();		
		model.put("list", getStudents());			
		return new ModelAndView(new ViewPDF(), model);
	}
}