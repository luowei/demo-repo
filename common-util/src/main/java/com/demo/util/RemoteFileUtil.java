package com.demo.util;

import jcifs.smb.SmbFile;
import jcifs.smb.SmbFileInputStream;
import jcifs.smb.SmbFileOutputStream;

import java.io.*;
import java.net.MalformedURLException;
import java.util.Date;
import java.util.List;

/**
 * 远程共享读写文件操作工具类
 * Created with IntelliJ IDEA.
 * User: luowei
 * Date: 12-12-29
 * Time: 下午8:30
 * To change this template use File | Settings | File Templates.
 */
public class RemoteFileUtil {

    private String remoteHostIp;  //远程主机IP
    private String account;       //登陆账户
    private String password;      //登陆密码
    private String shareDocName;  //共享文件夹名称

    //配置文件
    private PropertiesReader reader = new PropertiesReader("/uploadpath.properties");

    /**
     * 默认构造函数
     */
    public RemoteFileUtil() {
        this.remoteHostIp = reader.getProperty("REMOTE_HOST_IP");
        this.account = reader.getProperty("LOGIN_ACCOUNT");
        this.password = reader.getProperty("LOGIN_PASSWORD");
        this.shareDocName = reader.getProperty("SHARE_DOC_NAME");
    }

    /**
     * 构造函数
     *
     * @param remoteHostIp 远程主机Ip
     * @param account      登陆账户
     * @param password     登陆密码
     * @param shareDocName    共享文件夹路径
     */
    public RemoteFileUtil(String remoteHostIp, String account, String password, String shareDocName) {
        this.remoteHostIp = remoteHostIp;
        this.account = account;
        this.password = password;
        this.shareDocName = shareDocName;
    }

    /**
     * 对远程共享文件进行读取所有行
     *
     * @param remoteFileName 文件名  说明：参数为共享目录下的相对路径
     *                       若远程文件的路径为：shareDoc\test.txt,则参数为test.txt(其中shareDoc为共享目录名称);
     *                       若远程文件的路径为：shareDoc\doc\text.txt,则参数为doc\text.txt;
     * @return 文件的所有行
     */
    public SmbFile readFile(String remoteFileName) {
        SmbFile smbFile = null;
        //构建连接字符串,并取得文件连接
        String conStr = null;
        conStr = "smb://" + account + ":" + password + "@" + remoteHostIp + "/" + shareDocName + "/" + remoteFileName;
        try {
            smbFile = new SmbFile(conStr);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }


        return smbFile;
    }

    /**
     * 对远程共享文件进行写入
     *
     * @param is             本地文件的输入流
     * @param remoteFileName 远程文件名    说明：参数为共享目录下的相对路径
     *                       若远程文件的路径为：shareDoc\test.txt,则参数为test.txt(其中shareDoc为共享目录名称);
     *                       若远程文件的路径为：shareDoc\doc\text.txt,则参数为doc\text.txt;
     * @return
     */
    public boolean writeFile(InputStream is, String remoteFileName) {
        SmbFile smbFile = null;
        OutputStream os = null;
        byte[] buffer = new byte[1024 * 8];
        //构建连接字符串,并取得文件连接
        String conStr = null;
        conStr = "smb://" + account + ":" + password + "@" + remoteHostIp + "/" + shareDocName + "/" + remoteFileName;
        try {
            smbFile = new SmbFile(conStr);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return false;
        }

//获取远程文件输出流并写文件到远程共享文件夹
        try {
            os = new BufferedOutputStream(new SmbFileOutputStream(smbFile));
            while ((is.read(buffer)) != -1) {
                os.write(buffer);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }


    /**
     * 对远程共享文件进行写入重载
     *
     * @param localFileFullName  要写入的本地文件全名
     * @param remoteFileName 远程文件名    说明：参数为共享目录下的相对路径
     *                       若远程文件的路径为：shareDoc\test.txt,则参数为test.txt(其中shareDoc为共享目录名称);
     *                       若远程文件的路径为：shareDoc\doc\text.txt,则参数为doc\text.txt;
     * @return
     */
    public boolean writeFile(String localFileFullName, String remoteFileName) {
        try {
            return writeFile(new FileInputStream(new File(localFileFullName)), remoteFileName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 对远程共享文件进行写入重载
     *
     * @param localFile  要写入的本地文件
     * @param remoteFileName 远程文件名    说明：参数为共享目录下的相对路径
     *                       若远程文件的路径为：shareDoc\test.txt,则参数为test.txt(其中shareDoc为共享目录名称);
     *                       若远程文件的路径为：shareDoc\doc\text.txt,则参数为doc\text.txt;
     * @return
     */
    public boolean writeFile(File localFile, String remoteFileName) {
        try {
            return writeFile(new FileInputStream(localFile), remoteFileName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * **
     * 从smbMachine读取文件并存储到localpath指定的路径
     *
     * @param smbMachine 共享机器的文件,如smb://xxx:xxx@10.108.23.112/myDocument/测试文本.txt,xxx:xxx是共享机器的用户名密码
     * @param localpath  本地路径
     * @return
     */
    public File readFromSmb(String smbMachine, String localpath) {
        File localfile = null;
        InputStream bis = null;
        OutputStream bos = null;
        try {
            SmbFile rmifile = readFile(smbMachine);
            String filename = rmifile.getName();
            bis = new BufferedInputStream(new SmbFileInputStream(rmifile));
            localfile = new File(localpath + File.separator + filename);
            bos = new BufferedOutputStream(new FileOutputStream(localfile));
            int length = rmifile.getContentLength();
            byte[] buffer = new byte[length];
            Date date = new Date();
            bis.read(buffer);
            bos.write(buffer);
            Date end = new Date();
            int time = (int) ((end.getTime() - date.getTime()) / 1000);
            if (time > 0)
                System.out.println("用时:" + time + "秒 " + "速度:" + length / time / 1024 + "kb/秒");
        } catch (Exception e) {
            System.out.println(e.getMessage());

        } finally {
            try {
                bos.close();
                bis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return localfile;
    }

    public boolean removeFile(File file) {
        return file.delete();
    }

    public static void main(String[] args) {
//        RemoteFileUtil util = new RemoteFileUtil();
//        List<String> lines = util.readFile("test.txt");
//        for (String string : lines) {
//            System.out.println(string);
//        }
//        util.writeFile(new File("f:/a.txt"), "new.txt");
//        File file = util.readFromSmb("jdk6.ZH_cn.chm", "f:/");
//        util.writeFile(file, "123.chm");
//        //    File file=new File("f:/a.txt");
//        new File("f:/jdk6.ZH_cn.chm").delete();
    }

}
