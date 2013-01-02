package service;

import db.ConnectionDb;
import pojo.Car;
import pojo.CarType;
import pojo.CarX;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CarDb {
    private ConnectionDb db=new ConnectionDb("root","root");
    /**
     * 此方法主要查询换乘路线
     * @param start 起点站名
     * @param end 终点站名
     */
    public List hc(String start,String end){
        /**

         得到哪些车到火车站
         select * from car where remark like'%火车站%'

         得到哪些车到农大
         select * from car where remark like'%农大%'

         */
        String sql1="select * from car where remark like'%"+start+"%'";

        String sql2="select * from car where remark like'%"+end+"%'";
        //得到连接后查询数据库
        Connection con=db.getConnection();
        try{
            //Statement是执行数据库脚本的对象
            Statement stat=con.createStatement();
            //返回数据表的遍历对象
            ResultSet set=stat.executeQuery(sql1);
            List startlist=new ArrayList();
            //便历到达起点站的公车
            while(set.next()){
                Car c=new Car();
                CarType t=new CarType();
                //参数为你的表中的字段名称
                CarX x=new CarX();
                x.setStartid(set.getInt("id"));
                x.setStartname(set.getString("name"));
                x.setRemark(set.getString("remark"));
                startlist.add(x);
            }
            //得到到达终点站的公车
            set=stat.executeQuery(sql2);
            List endlist=new ArrayList();
            //便历到达起点站的公车
            while(set.next()){
                Car c=new Car();
                CarType t=new CarType();
                //参数为你的表中的字段名称
                CarX x=new CarX();
                x.setEndid(set.getInt("id"));
                x.setEndname(set.getString("name"));
                x.setRemark(set.getString("remark"));
                endlist.add(x);
            }
            //分析2个集合数据 得到路线图
            //最终的数据
            List list=new ArrayList();
            HashMap map=new HashMap();
            //测试数据 正式运行的时候注释
            System.out.println("------------打印起点站公车-----------------");
            for(int i=0;i<startlist.size();i++){
                CarX x=(CarX)startlist.get(i);
                System.out.println(x.getStartid()+" : "+x.getStartname()+" : "+x.getRemark());
            }



            System.out.println("------------打印起点站公车-----------------");
            for(int i=0;i<endlist.size();i++){
                CarX x=(CarX)endlist.get(i);
                System.out.println(x.getEndid()+" : "+x.getEndname()+" : "+x.getRemark());
            }
            System.out.println("------------开始计算-----------------");
            for(int i=0;i<startlist.size();i++){
                CarX x=(CarX)startlist.get(i);
                //System.out.println(x.getStartid()+" : "+x.getStartname()+" : "+x.getRemark());
                String starttext=x.getRemark();
                starttext=starttext.substring(starttext.indexOf(start)+start.length());
                String zhandian[]=starttext.split(",");
                //将开始站的站点到到站公车集合中去对比看看是否含有此数据
                for(int k=0;k<zhandian.length;k++){
                    String q=zhandian[k];
                    if(!"".equals(q.trim())){
                        for(int e=0;e<endlist.size();e++){

                            CarX c=(CarX)endlist.get(e);

                            if(x.getStartid()!=c.getEndid()){
                                if(c.getRemark().indexOf(q)>-1){
                                    //	System.out.println(x.getStartname()+" 与公车 "+c.getEndname()+" 有交集");
                                    //System.out.println("------------------------------------------- ");
                                    //System.out.println(x.getStartname()+" : "+x.getRemark());
                                    //System.out.println(c.getEndname()+" : "+c.getRemark());
                                    String endtext=c.getRemark().substring(c.getRemark().indexOf(q));
                                    //System.out.println("------------------------------------------- ");
                                    //System.out.println(q+" : "+starttext.indexOf(q));
                                    String s=x.getRemark().substring(x.getRemark().indexOf(start),x.getRemark().indexOf(q)+q.length());
                                    //System.out.println("路线图:"+x.getStartname()+":"+s+" 换乘 "+c.getEndname()+" : "+endtext);
                                    list.add("路线图:"+x.getStartname()+":"+s+" 换乘 "+c.getEndname()+" : "+endtext);
                                }
                            }
                            else{
                                //map
                                if(map.get(x.getStartid())==null){
                                    String text=x.getRemark();
                                    String s="路线图:"+x.getStartname()+":"+x.getRemark().substring(x.getRemark().indexOf(start),x.getRemark().indexOf(end)+end.length());
                                    map.put(x.getStartid(), s);
                                    list.add(s);
                                }
                            }

                        }
                    }
                }
            }
            for(int i=0;i<list.size();i++){
                System.out.println(list.get(i));
            }
            return list;
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        return null;


    }
    /**
     * 本方法提供模糊查询 条件为站点
     * @param name 站点
     * @return 返回符合条件的数据
     */
    public List getList2(String name){
        List list=new ArrayList();
        String sql="select c.id as cid,c.name as cname,t.id as tid,t.name as tname,remark from " +
                "car c left join cartype t on c.type_id=t.id where c.remark like '%"+name+"%'";
        Connection con=db.getConnection();
        try
        {
            //得到连接后查询数据库
            //Statement是执行数据库脚本的对象
            Statement stat=con.createStatement();
            //返回数据表的遍历对象
            ResultSet set=stat.executeQuery(sql);
            //遍历数据
            //set.next()返回是否含有下一条数据, 如果存在返回为true 并且移至到下一条 否则为false
            while(set.next()){
                Car c=new Car();
                CarType t=new CarType();
                //参数为你的表中的字段名称
                c.setId(set.getInt("cid"));
                c.setName(set.getString("cname"));
                c.setRemark(set.getString("remark"));

                t.setId(set.getInt("tid"));
                t.setName(set.getString("tname"));
                c.setCartype(t);
                list.add(c);
            }
            con.close();
        }
        catch(Exception ex){
            ex.printStackTrace();
        }


        return list;
    }
    /**
     * 本方法提供模糊查询 条件为公车名称
     * @param name 公车名称
     * @return 返回符合条件的数据
     */
    public List getList(String name){
        List list=new ArrayList();
        String sql="select c.id as cid,c.name as cname,t.id as tid,t.name as tname,remark from " +
                "car c left join cartype t on c.type_id=t.id where c.name like '%"+name+"%'";
        Connection con=db.getConnection();
        try
        {
            //得到连接后查询数据库
            //Statement是执行数据库脚本的对象
            Statement stat=con.createStatement();
            //返回数据表的遍历对象
            ResultSet set=stat.executeQuery(sql);
            //遍历数据
            //set.next()返回是否含有下一条数据, 如果存在返回为true 并且移至到下一条 否则为false
            while(set.next()){
                Car c=new Car();
                CarType t=new CarType();
                //参数为你的表中的字段名称
                c.setId(set.getInt("cid"));
                c.setName(set.getString("cname"));
                c.setRemark(set.getString("remark"));

                t.setId(set.getInt("tid"));
                t.setName(set.getString("tname"));
                c.setCartype(t);
                list.add(c);
            }
            con.close();
        }
        catch(Exception ex){
            ex.printStackTrace();
        }


        return list;
    }
    public void save(Car car){
        try{
            //模拟增加数据
            Connection con=db.getConnection();
            //得到连接后查询数据库
            //Statement是执行数据库脚本的对象
            PreparedStatement stat;
            if(car.getCartype()!=null){
                stat=con.prepareStatement("insert into car(name,remark,type_id) values(?,?,?)");
            }
            else{
                stat=con.prepareStatement("insert into car(name,remark) values(?,?)");
            }
            //返回数据表的遍历对象
            //setString是填充参数 按照指定位置
            stat.setString(1,car.getName());
            stat.setString(2,car.getRemark());
            if(car.getCartype()!=null){
                stat.setInt(3, car.getCartype().getId());
            }
            stat.execute();
        }
        catch(Exception ex){

        }
    }
    public void delete(int ...id){

    }
    public List getList(){
        List list=new ArrayList();
        try{

            /**
             mysql> select c.id as cid,c.name as cname,t.id as tid,t.name as tname,remark fro
             m car c,cartype t where c.type_id=t.id;
             */
            String sql="select c.id as cid,c.name as cname,t.id as tid,t.name as tname,remark from " +
                    "car c left join cartype t on c.type_id=t.id";
            Connection con=db.getConnection();
            //得到连接后查询数据库
            //Statement是执行数据库脚本的对象
            Statement stat=con.createStatement();
            //返回数据表的遍历对象
            ResultSet set=stat.executeQuery(sql);

            //遍历数据
            //set.next()返回是否含有下一条数据, 如果存在返回为true 并且移至到下一条 否则为false
            while(set.next()){
                CarType t=new CarType();
                Car c=new Car();
                //参数为你的表中的字段名称
                c.setId(set.getInt("cid"));
                c.setName(set.getString("cname"));
                c.setRemark(set.getString("remark"));

                t.setId(set.getInt("tid"));
                t.setName(set.getString("tname"));
                c.setCartype(t);
                list.add(c);
            }
            con.close();
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        return list;
    }
    public static void main(String arg[]){
        CarDb c=new CarDb();
        c.hc("火车站", "河西");
    }
}
