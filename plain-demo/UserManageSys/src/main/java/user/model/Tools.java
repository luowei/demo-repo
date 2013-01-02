/**
 * 工具类
 */

package user.model;

public class Tools {

    /**提供一个方法，将乱码转成gb2312,gbk,utf-8
     *
     * @param input
     * @return
     */
    public static String getNewString(String input)
    {
        String result="";
        try{
            result=new String(input.getBytes("gb2312"),"iso-8859-1");
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        return result;
    }
}
