package pojo;

public class Test {

    /**
     * @param args
     */
    public static void main(String[] args) {
        Car c=new Car();
        c.setId(1);
        c.setName("9路");
        c.setRemark("火车站,长岛饭店");
        String text="<html>";
        text+="<head>" +
                "</head>" +
                "<body>" +
                "<table>" +
                "<tr>" +
                "<td>"+c.getName()+"</td>" +
                "<td>"+c.getId()+"</td>" +
                "<td>"+c.getRemark()+"</td>" +
                "</tr>" +
                "</table>"+
                "</body>";
        text+="</html>";

    }

}
