<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'MyJsp.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
    <div id="u">
    <a href="http://www.baidu.com/gaoji/preferences.html" name="tj_setting">搜索设置</a>&nbsp;|&nbsp;
    <a href="http://passport.baidu.com/?login&tpl=mn" name="tj_login">登录</a>
    </div>
	<div id="m">
	<p id="lg">
	<img src="http://www.baidu.com/img/baidu_sylogo1.gif" width="270" height="129" usemap="#mp">
	<map name="mp">
	<area shape="rect" coords="40,25,230,95" href="http://hi.baidu.com/baidu/" target="_blank" title="点此进入 百度的空间" ></map>
	</p>
	<p id="nv"><a href="http://news.baidu.com">新&nbsp;闻</a>
	　<b>网&nbsp;页</b>　
	<a href="http://tieba.baidu.com">贴&nbsp;吧</a>　
	<a href="http://zhidao.baidu.com">知&nbsp;道</a>　
	<a href="http://mp3.baidu.com">MP3</a>　
	<a href="http://image.baidu.com">图&nbsp;片</a>　
	<a href="http://video.baidu.com">视&nbsp;频</a>　
	<a href="http://map.baidu.com">地&nbsp;图</a>
	</p>
	<div id="fm">
	<form name="f" action="/s">
	<input type="text" name="wd" id="kw" maxlength="100">
	<input type="hidden" name="rsv_bp" value="0"><input type="hidden" name="rsv_spt" value="3">
	<span class="btn_wr">
	<input type="submit" value="百度一下" id="su" class="btn" onmousedown="this.className='btn btn_h'" onmouseout="this.className='btn'">
	</span>
	</form>
	<span class="tools">
	<span id="mHolder">
	<div id="mCon">
	<span>输入法</span>
	</div>
	</span>
	</span>
	<ul id="mMenu">
	<li><a href="#" name="ime_hw">手写</a></li>
	<li><a href="#" name="ime_py">拼音</a></li>
	<li class="ln"></li><li><a href="#" name="ime_cl">关闭</a></li>
	</ul>
	</div>
	
	<p id="lk">
	<a href="http://hi.baidu.com">空间</a>　
	<a href="http://baike.baidu.com">百科</a>　
	<a href="http://www.hao123.com">hao123</a>
	<span> | <a href="/more/">更多&gt;&gt;</a></span>
	</p>
	<p id="lm"></p>
	<p>
	<a id="sh" onClick="this.setHomePage('http://www.baidu.com')" href="http://utility.baidu.com/traf/click.php?id=215&url=http://www.baidu.com" onmousedown="return ns_c({'fm':'behs','tab':'homepage','pos':0})">把百度设为主页</a>
	</p>
	<p id="lh">
	<a href="http://e.baidu.com/?refer=888">加入百度推广</a> | 
	<a href="http://top.baidu.com">搜索风云榜</a> | 
	<a href="http://home.baidu.com">关于百度</a> | 
	<a href="http://ir.baidu.com">About Baidu</a>
	</p>
	<p id="cp">&copy;2011 Baidu <a href="/duty/">使用百度前必读</a> 
	<a href="http://www.miibeian.gov.cn" target="_blank">京ICP证030173号</a> 
	<img src="http://gimg.baidu.com/img/gs.gif"></p>
	</div>
	</body>
	
	<script>
	var w=window,d=document,n=navigator,k=d.f.wd,a=d.getElementById("nv").getElementsByTagName("a"),isIE=n.userAgent.indexOf("MSIE")!=-1&&!window.opera,sh=d.getElementById("sh");
	if(isIE&&sh&&!sh.isHomePage("http://www.baidu.com"))
	{sh.style.display="inline"}
	for(var i=0;i<a.length;i++)
	{a[i].onclick=function(){if(k.value.length>0){var C=this,A=C.href,B=encodeURIComponent(k.value);if(A.indexOf("q=")!=-1){C.href=A.replace(/q=[^&\x24]*/,"q="+B)}else{this.href+="?q="+B}}}}(function(){if(/q=([^&]+)/.test(location.search)){k.value=decodeURIComponent(RegExp["\x241"])}})();if(n.cookieEnabled&&!/sug?=0/.test(d.cookie)){d.write("<script src=http://www.baidu.com/js/bdsug.js?v=1.0.3.0><\/script>")}function addEV(C,B,A){if(w.attachEvent){C.attachEvent("on"+B,A)}else{if(w.addEventListener){C.addEventListener(B,A,false)}}}function G(A){return d.getElementById(A)}function ns_c(E){var F=encodeURIComponent(window.document.location.href),D="",A="",B="",C=window["BD_PS_C"+(new Date()).getTime()]=new Image();for(v in E){A=E[v];D+=v+"="+A+"&"}B="&mu="+F;C.src="http://nsclick.baidu.com/v.gif?pid=201&pj=www&"+D+"path="+F+"&t="+new Date().getTime();return true}if(/\bbdime=[12]/.test(d.cookie)){document.write("<script src=http://www.baidu.com/cache/ime/js/openime-1.0.0.js><\/script>")}(function(){var B=G("user"),A=G("userMenu");if(B&&A){addEV(B,"click",function(C){A.style.display=A.style.display=="block"?"none":"block";window.event?C.cancelBubble=true:C.stopPropagation()});addEV(document,"click",function(){A.style.display="none"})}})();(function(){var E=G("u").getElementsByTagName("a"),C=G("nv").getElementsByTagName("a"),I=G("lk").getElementsByTagName("a"),B="";var A=["news","tieba","zhidao","mp3","img","video","map"];var H=["hi","baike","hao123","more"];if(G("un")&&G("un").innerHTML!=""){B=G("un").innerHTML}function D(J){addEV(J,"mousedown",function(L){var L=L||window.event;var K=L.target||L.srcElement;ns_c({fm:"behs",tab:K.name||"tj_user",un:encodeURIComponent(B)})})}for(var F=0;F<E.length;F++){D(E[F])}for(var F=0;F<C.length;F++){C[F].name="tj_"+A[F];D(C[F])}for(var F=0;F<I.length;F++){I[F].name="tj_"+H[F];D(I[F])}})();addEV(w,"load",function(){k.focus()});w.onunload=function(){};
	</script>
	
	
	<script type="text/javascript" src="http://www.baidu.com/cache/hps/js/hps-1.1.1.js"></script>

</html>
