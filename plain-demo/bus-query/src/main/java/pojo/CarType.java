package pojo;
/**
 * 公交车线路类型对象
 * @author 拟好
 *
 */
public class CarType {
    /**
     * 类型ID 主键
     */
    private int id;
    /**
     * 类型名称
     */
    private String name;
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
}