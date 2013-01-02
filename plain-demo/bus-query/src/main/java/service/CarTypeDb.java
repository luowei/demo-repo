package service;

import db.ConnectionDb;
import pojo.CarType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * 查询CarType对象的数据类
 *
 * @author Administrator
 */
public class CarTypeDb {
    //此集合只是用于测试数据
    ArrayList list = new ArrayList();
    private ConnectionDb db = new ConnectionDb("root", "root");

    /**
     * 获取公车线路类型所有数据
     *
     * @return
     */
    public List getList() {
        //以下为模拟数据
//	if(list.size()<=0){
//	CarType c1=new CarType();
//	c1.setId(1);
//	c1.setName("市内专线");
//	CarType c2=new CarType();
//	c2.setId(2);
//	c2.setName("旅游专线");
//
//	CarType c3=new CarType();
//	c3.setId(3);
//	c3.setName("二环以内");
//	list.add(c1);
//	list.add(c2);
//	list.add(c3);
//	}
        try {
            list.clear();
            Connection con = db.getConnection();
            //得到连接后查询数据库
            //Statement是执行数据库脚本的对象
            Statement stat = con.createStatement();
            //返回数据表的遍历对象
            ResultSet set = stat.executeQuery("select * from cartype");

            //遍历数据
            //set.next()返回是否含有下一条数据, 如果存在返回为true 并且移至到下一条 否则为false
            while (set.next()) {
                CarType c = new CarType();
                //参数为你的表中的字段名称
                int id = set.getInt("id");
                String name = set.getString("name");
                c.setId(id);
                c.setName(name);
                list.add(c);
            }
            con.close();
            //测试程序
//		for(int i=0;i<list.size();i++){
//			CarType c=(CarType)list.get(i);
//			System.out.println(c.getId()+" : "+c.getName());
//		}
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return list;
    }

    public boolean remo(String... ids) {
        //模拟删除数据
//	for(String id:ids){
//		for(int i=0;i<list.size();i++){
//			CarType c=(CarType)list.get(i);
//			if(c.getId()==Integer.parseInt(id)){
//				list.remove(i);
//				break;
//			}
//		}
//	}
        try {
            Connection con = db.getConnection();
            //得到连接后查询数据库
            Statement stat = con.createStatement();
            String text = "";
            for (String id : ids) {
                text += id + ",";
            }
            //当有数据的时候才执行删除
            if (!text.equals("")) {
                //去处末尾逗号
                text = text.substring(0, text.lastIndexOf(","));
                //先删除子表
                int count = stat.executeUpdate("delete from car where type_id in(" + text + ")");
                count = stat.executeUpdate("delete from cartype where id in(" + text + ")");

                System.out.println("成功删除:" + count);
                con.close();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        /**
         delete from cartype
         where id=2;
         */

        return true;
    }

    public boolean save(CarType t) {
        try {
            //模拟增加数据
            Connection con = db.getConnection();
            //得到连接后查询数据库
            //Statement是执行数据库脚本的对象
            PreparedStatement stat = con.prepareStatement("insert into cartype(name) values( ? )");

            //返回数据表的遍历对象
            //setString是填充参数 按照指定位置
            stat.setString(1, t.getName());
            stat.execute();
        } catch (Exception ex) {

        }


        return true;
    }

    public static void main(String agr[]) {
        CarTypeDb db = new CarTypeDb();
        db.save(null);
    }
}
