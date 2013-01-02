package test;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @className:Emp.java
 * @classDescription:
 * @author:wei.luo
 * @createTime:2012-2-13上午12:45:55
 */
public class Emp {
    private int empNo;
    private String name;
    private String phone;
    private Date hireDate;
    private String dept;

    public int getEmpNo() {
        return empNo;
    }
    public void setEmpNo(int empNo) {
        this.empNo = empNo;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getHireDate() {
        return hireDate;
    }
    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }


    public String getDept() {
        return dept;
    }
    public void setDept(String dept) {
        this.dept = dept;
    }
    @Override
    public String toString() {
        return "Emp [empNo="+empNo+",name=" + name + ", phone=" + phone
                + ",hireDate="+new SimpleDateFormat("yyyy-MM-dd").format(hireDate)
                +",dept="+dept+"]";
    }

}
