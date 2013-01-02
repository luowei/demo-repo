package test.entity;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Size;

/**
 * @className:User.java
 * @classDescription:
 * @author:wei.luo
 * @createTime:2012-2-15下午11:04:10
 */
public class User {
    //给User bean属性加入验证
    @NotEmpty	//不为空
    private String name;
    @Size(max=20,min=6)
    private String password;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
