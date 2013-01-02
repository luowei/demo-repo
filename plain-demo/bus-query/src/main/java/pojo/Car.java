package pojo;

public class Car {
    /**
     * 主键
     */
    private int id;
    /**
     * 公车名称
     */
    private String name;
    /**
     * 公车的类型
     */
    private CarType cartype;
    /**
     * 站点数据
     */
    private String remark;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public CarType getCartype() {
        return cartype;
    }
    public void setCartype(CarType cartype) {
        this.cartype = cartype;
    }
    public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
}