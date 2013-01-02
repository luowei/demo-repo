package pojo;
/**
 * 换乘对象
 * @author Administrator
 *
 */
public class CarX {
    private int startid;//起点站的公车ID
    private int endid;//换乘站的公车ID
    private String startname;//起点站的公车名字
    private String endname;//换乘站的公车名字
    private String remark;//路线
    public int getStartid() {
        return startid;
    }

    public void setStartid(int startid) {
        this.startid = startid;
    }
    public int getEndid() {
        return endid;
    }
    public void setEndid(int endid) {
        this.endid = endid;
    }
    public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
    public String getStartname() {
        return startname;
    }
    public void setStartname(String startname) {
        this.startname = startname;
    }
    public String getEndname() {
        return endname;
    }
    public void setEndname(String endname) {
        this.endname = endname;
    }
}

