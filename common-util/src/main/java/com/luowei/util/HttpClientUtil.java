package com.luowei.util;

import org.apache.http.*;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.params.ClientPNames;
import org.apache.http.client.params.CookiePolicy;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.ByteArrayBody;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.protocol.ExecutionContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLHandshakeException;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.security.cert.CertificateException;
import java.util.List;
import java.util.Map;

/**
 * httpclient util
 * User: administrator
 * Date: 12-7-8
 * Time: 下午5:54
 */
public abstract class HttpClientUtil {
    protected static Logger logger = (Logger) LoggerFactory.getLogger(HttpClientUtil.class);

    public static String CHARSET_ENCODING = "UTF-8";
    // private static String
    // USER_AGENT="Mozilla/4.0 (compatible; MSIE 6.0; Win32)";//ie6
    public static String USER_AGENT = "Mozilla/4.0 (compatible; MSIE 7.0; Win32)";// ie7

    // private static String
    // USER_AGENT="Mozilla/4.0 (compatible; MSIE 8.0; Win32)";//ie8

    /**
     * 获取DefaultHttpClient对象
     *
     * @param charset
     *            字符编码
     * @return DefaultHttpClient对象
     */
    private static DefaultHttpClient getDefaultHttpClient(final String charset) {
        DefaultHttpClient httpclient = new DefaultHttpClient();
        // 模拟浏览器，解决一些服务器程序只允许浏览器访问的问题
        httpclient.getParams().setParameter(CoreProtocolPNames.USER_AGENT, USER_AGENT);
        httpclient.getParams().setParameter(CoreProtocolPNames.USE_EXPECT_CONTINUE, Boolean.FALSE);
        httpclient.getParams().setParameter(CoreProtocolPNames.HTTP_CONTENT_CHARSET, charset == null ? CHARSET_ENCODING : charset);

        // 浏览器兼容性
        httpclient.getParams().setParameter(ClientPNames.COOKIE_POLICY, CookiePolicy.BROWSER_COMPATIBILITY);
        // 定义重试策略
        httpclient.setHttpRequestRetryHandler(requestRetryHandler);

        return httpclient;
    }

    /**
     * 访问https的网站
     *
     * @param httpclient
     */
    private static void enableSSL(DefaultHttpClient httpclient) {
        // 调用ssl
        try {
            SSLContext sslcontext = SSLContext.getInstance("TLS");
            sslcontext.init(null, new TrustManager[] { truseAllManager }, null);
            SSLSocketFactory sf = new SSLSocketFactory(sslcontext);
            sf.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
            Scheme https = new Scheme("https", sf, 443);
            httpclient.getConnectionManager().getSchemeRegistry().register(https);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 重写验证方法，取消检测ssl
     */
    private static TrustManager truseAllManager = new X509TrustManager() {

        public void checkClientTrusted(java.security.cert.X509Certificate[] arg0, String arg1) throws CertificateException {


        }

        public void checkServerTrusted(java.security.cert.X509Certificate[] arg0, String arg1) throws CertificateException {


        }

        public java.security.cert.X509Certificate[] getAcceptedIssuers() {

            return null;
        }

    };

    /**
     * 异常自动恢复处理, 使用HttpRequestRetryHandler接口实现请求的异常恢复
     */
    private static HttpRequestRetryHandler requestRetryHandler = new HttpRequestRetryHandler() {
        // 自定义的恢复策略
        public boolean retryRequest(IOException exception, int executionCount, HttpContext context) {
            // 设置恢复策略，在发生异常时候将自动重试3次
            if (executionCount >= 2) {
                // 如果连接次数超过了最大值则停止重试
                return false;
            }
            if (exception instanceof NoHttpResponseException) {
                // 如果服务器连接失败重试
                return true;
            }
            if (exception instanceof SSLHandshakeException) {
                // 不要重试ssl连接异常
                return false;
            }
            HttpRequest request = (HttpRequest) context.getAttribute(ExecutionContext.HTTP_REQUEST);
            boolean idempotent = (request instanceof HttpEntityEnclosingRequest);
            if (!idempotent) {
                // 重试，如果请求是考虑幂等
                return true;
            }
            return false;
        }
    };

    /**
     * 使用ResponseHandler接口处理响应，HttpClient使用ResponseHandler会自动管理连接的释放，解决了对连接的释放管理
     */
    private static ResponseHandler<String> responseHandler = new ResponseHandler<String>() {
        // 自定义响应处理
        public String handleResponse(HttpResponse response) throws ClientProtocolException, IOException {
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                String charset = EntityUtils.getContentCharSet(entity) == null ? CHARSET_ENCODING : EntityUtils.getContentCharSet(entity);
                return new String(EntityUtils.toByteArray(entity), charset);
            } else {
                return null;
            }
        }
    };

    /**
     * 使用post方法获取相关的数据
     * @param url
     * @param paramsList
     * @return
     */
    public static String post(String url, List<NameValuePair> paramsList) {
        return httpRequest(url, paramsList, "POST", null);
    }

    /**
     * 使用post方法并且通过代理获取相关的数据
     * @param url
     * @param paramsList
     * @param proxy
     * @return
     */
    public static String post(String url, List<NameValuePair> paramsList, HttpHost proxy) {
        return httpRequest(url, paramsList, "POST", proxy);
    }

    /**
     * 使用get方法获取相关的数据
     * @param url
     * @param paramsList
     * @return
     */
    public static String get(String url, List<NameValuePair> paramsList) {
        return httpRequest(url, paramsList, "GET", null);
    }
    /**
     * 使用get方法获取相关的数据
     * @param url
     * @param url
     * @return
     */
    public static String get(String url) {
        return httpRequest(url,null, "GET", null);
    }

    /**
     * 使用get方法并且通过代理获取相关的数据
     * @param url
     * @param paramsList
     * @param proxy
     * @return
     */
    public static String get(String url, List<NameValuePair> paramsList, HttpHost proxy) {
        return httpRequest(url, paramsList, "GET", proxy);
    }

    /**
     * 提交数据到服务器
     * @param url
     * @param paramsList
     * @param method
     * @param proxy
     * @return
     */
    public static String httpRequest(String url, List<NameValuePair> paramsList, String method, HttpHost proxy) {
        String responseStr = null;
        // 判断输入的值是是否为空
        if (null == url || "".equals(url)) {
            return null;
        }
        // 创建HttpClient实例
        DefaultHttpClient httpclient = getDefaultHttpClient(CHARSET_ENCODING);

        // 判断是否是https请求
        if (url.startsWith("https")) {
            enableSSL(httpclient);
        }
        String formatParams = null;
        // 将参数进行utf-8编码
        if (null != paramsList && paramsList.size() > 0) {
            formatParams = URLEncodedUtils.format(paramsList, CHARSET_ENCODING);
        }
        // 如果代理对象不为空则设置代理
        if (null != proxy) {
            httpclient.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY, proxy);
        }
        try {
            // 如果方法为Get
            if ("GET".equalsIgnoreCase(method)) {
                if (formatParams != null) {
                    url = (url.indexOf("?")) < 0 ? (url + "?" + formatParams) : (url.substring(0, url.indexOf("?") + 1) + formatParams);
                }
                HttpGet hg = new HttpGet(url);

                responseStr = httpclient.execute(hg, responseHandler);

                // 如果方法为Post
            } else if ("POST".equalsIgnoreCase(method)) {
                HttpPost hp = new HttpPost(url);
                if (formatParams != null) {
                    StringEntity entity = new StringEntity(formatParams);
                    entity.setContentType("application/x-www-form-urlencoded");
                    hp.setEntity(entity);
                }
                responseStr = httpclient.execute(hp, responseHandler);
            }

        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return responseStr;
    }

    /**
     * 提交数据到服务器
     * @param url
     * @param fileMap
     * @param stringMap
     * @param type
     * @param proxy
     * @return
     */
    public static String httpFileRequest(String url, Map<String, String> fileMap, Map<String, String> stringMap, int type, HttpHost proxy) {
        String responseStr = null;
        // 判断输入的值是是否为空
        if (null == url || "".equals(url)) {
            return null;
        }
        // 创建HttpClient实例
        DefaultHttpClient httpclient = getDefaultHttpClient(CHARSET_ENCODING);

        // 判断是否是https请求
        if (url.startsWith("https")) {
            enableSSL(httpclient);
        }

        // 如果代理对象不为空则设置代理
        if (null != proxy) {
            httpclient.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY, proxy);
        }
        // 发送文件
        HttpPost hp = new HttpPost(url);
        MultipartEntity multiEntity = new MultipartEntity();
        try {
            // type=0是本地路径，否则是网络路径
            if (type == 0) {
                for (String key : fileMap.keySet()) {
                    multiEntity.addPart(key, new FileBody(new File(fileMap.get(key))));
                }
            } else {
                for (String key : fileMap.keySet()) {
                    multiEntity.addPart(key, new ByteArrayBody(getUrlFileBytes(fileMap.get(key)), key));
                }
            }
            // 加入相关参数 默认编码为utf-8
            for (String key : stringMap.keySet()) {
                multiEntity.addPart(key, new StringBody(stringMap.get(key), Charset.forName(CHARSET_ENCODING)));
            }
            hp.setEntity(multiEntity);
            responseStr = httpclient.execute(hp, responseHandler);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return responseStr;
    }

    /**
     * 相关文件和参数提交到相关服务器
     * @param url
     * @param fileMap
     * @param stringMap
     * @return
     */
    public static String postFile(String url, Map<String, String> fileMap, Map<String, String> stringMap) {
        return httpFileRequest(url, fileMap, stringMap, 0, null);
    }

    /**
     * 将相关文件和参数提交到相关服务器
     * @param url
     * @param urlMap
     * @param stringMap
     * @return
     */
    public static String postUrlFile(String url, Map<String, String> urlMap, Map<String, String> stringMap) {
        return httpFileRequest(url, urlMap, stringMap, 1, null);
    }

    /**
     * 获取网络文件的字节数组
     *
     * @param url
     * @return
     * @throws java.io.IOException
     * @throws org.apache.http.client.ClientProtocolException
     * @throws org.apache.http.client.ClientProtocolException
     * @throws java.io.IOException
     */
    public static byte[] getUrlFileBytes(String url) throws ClientProtocolException, IOException {
        byte[] bytes = null;
        // 创建HttpClient实例
        DefaultHttpClient httpclient = getDefaultHttpClient(CHARSET_ENCODING);
        // 获取url里面的信息
        HttpGet hg = new HttpGet(url);
        HttpResponse hr = httpclient.execute(hg);
        bytes = EntityUtils.toByteArray(hr.getEntity());
        // 转换内容为字节
        return bytes;
    }

    /**
     * 获取图片的字节数组
     *
     * @createTime 2011-11-24
     * @param url
     * @return
     * @throws java.io.IOException
     * @throws org.apache.http.client.ClientProtocolException
     * @throws org.apache.http.client.ClientProtocolException
     * @throws java.io.IOException
     */
    public static byte[] getImg(String url) throws ClientProtocolException, IOException {
        byte[] bytes = null;
        // 创建HttpClient实例
        DefaultHttpClient httpclient = getDefaultHttpClient(CHARSET_ENCODING);
        // 获取url里面的信息
        HttpGet hg = new HttpGet(url);
        HttpResponse hr = httpclient.execute(hg);
        bytes = EntityUtils.toByteArray(hr.getEntity());
        // 转换内容为字节
        return bytes;
    }

    /**
     * get StatusCode
     * @param url
     * @return
     */
    public static Integer getStatusCode(String url){
        DefaultHttpClient client = getDefaultHttpClient(CHARSET_ENCODING);
        HttpGet get = new HttpGet(url);
        HttpResponse response = null;
        Integer code = null;
        try {
            response = client.execute(get);
            code = response.getStatusLine().getStatusCode();
            get.abort();
        } catch (IOException e) {
            logger.error("connet to "+url+" exception,message info:"+e.getMessage(),e);
        }
        return code;
    }

    public static void main(String[] args) throws URISyntaxException {
//        String picUrl = "http://hiphotos.baidu.com/916820993/pic/item/d5f009b3d541228230add1b4.jpg";
//        String url = "https://graph.qq.com/photo/upload_pic";
//        // byte[]bytes=HttpClientUtil.getImg(picUrl);
//        // File file=new File("D://Chrysanthemum.jpg");
//        // picUrl="D://Chrysanthemum.jpg";
//        Map<String, String> fileMap = new HashMap();
//        Map<String, String> stringMap = new HashMap();
//        fileMap.put("picture", picUrl);
//
//        stringMap.put("oauth_consumer_key", "100240447");
//        stringMap.put("access_token", "EE4576494FCD6F3E1CBADFB91D15FFDC");
//        stringMap.put("openid", "C7747260E44BEED7AB7CBA43C3F15920");
//        stringMap.put("photodesc", "天天动听乐分享");
//        stringMap.put("title", "夏迎杰测试");
//        stringMap.put("x", "0");
//        stringMap.put("y", "0");
//        stringMap.put("format", "json");
//
//        String json = HttpClientUtil.postUrlFile(url, fileMap, stringMap);
//        // String json=HttpClientUtil.postFile(url, fileMap, stringMap);
//        System.out.println(json);

        // String url = "https://graph.qq.com/shuoshuo/add_topic";
        // // 说说表单各字段的值
        // List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        // nvps.add(new BasicNameValuePair("oauth_consumer_key", "100240447"));
        // nvps.add(new BasicNameValuePair("access_token",
        // "EE4576494FCD6F3E1CBADFB91D15FFDC"));
        // nvps.add(new BasicNameValuePair("openid",
        // "C7747260E44BEED7AB7CBA43C3F15920"));
        // nvps.add(new BasicNameValuePair("richtype", "1"));
        // nvps
        // .add(new BasicNameValuePair(
        // "richval",
        // "C7747260E44BEED7AB7CBA43C3F15920,NDFidw2rGjaEO09Ma9oY.YYLWvRiAAA!,NDFhdw2rGjaEO09Ma9oYSe4GWgACAAA!"));
        // nvps.add(new BasicNameValuePair("con", "分享一张图片~"));
        // nvps.add(new BasicNameValuePair("lbs_nm", "1"));
        // nvps.add(new BasicNameValuePair("lbs_x", "0"));
        // nvps.add(new BasicNameValuePair("lbs_y", "0"));
        // nvps.add(new BasicNameValuePair("format", "json"));
        // nvps.add(new BasicNameValuePair("third_source", "1"));
        // String json = HttpClientUtil.post(url, null);

        // HttpHost hh=null;//new HttpHost("97.87.24.113", 80);
        // List<NameValuePair>pl=new ArrayList<NameValuePair>();
        // HttpClientUtil.CHARSET_ENCODING="GBK";

        // List<NameValuePair> paramsList=new ArrayList<NameValuePair>();
        // // paramsList.add(new BasicNameValuePair("uid","123213123"));
        // paramsList.add(new BasicNameValuePair("username","hww"));
        // paramsList.add(new BasicNameValuePair("password","123"));
        // paramsList.add(new BasicNameValuePair("type","0"));
        // String url="http://a.app.qq.com/g/s?sid=Ab3mVrQjg_fbcXeP2L3lzDmq&";
        // String json=HttpClientUtil.get(url, null);
        // System.out.println(json);

        System.out.println("code:"+getStatusCode("http://picdown.ttpod.cn:9010/picsearch?title=%E9%9D%92%E8%8A%B1%E7%93%B7&artist=%E5%91%A8%E6%9D%B0%E4%BC%A6&" +
                "filename=&auto=1&x=76&y=76&maxcount=5&s=s500&v=v1.0&imei=&mid=&uid=wap&multi=1"));
    }

}
