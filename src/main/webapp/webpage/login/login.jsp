<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="org.jeecgframework.core.util.SysThemesUtil,org.jeecgframework.core.enums.SysThemesEnum"%>
<%@include file="/context/mytags.jsp"%>
<%
  session.setAttribute("lang","zh-cn");
  SysThemesEnum sysTheme = SysThemesUtil.getSysTheme(request);
  String lhgdialogTheme = SysThemesUtil.getLhgdialogTheme(sysTheme);
%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
  <meta charset="utf-8" />
  <title><t:mutiLang langKey="jeect.platform"/></title>
   <link rel="shortcut icon" href="images/favicon.ico">
  <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
  <meta name="renderer" content="webkit" />
  <meta name="force-rendering" content="webkit" />
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
  <!-- bootstrap & fontawesome -->
  <link rel="stylesheet" href="plug-in/ace/css/bootstrap.css" />
  <link rel="stylesheet" href="plug-in/ace/css/font-awesome.css" />
  <link rel="stylesheet" type="text/css" href="plug-in/accordion/css/accordion.css">
  <!-- text fonts -->
  <link rel="stylesheet" href="plug-in/ace/css/ace-fonts.css" />

  <link rel="stylesheet" href="plug-in/ace/css/jquery-ui.css" />
  <!-- ace styles -->
  <link rel="stylesheet" href="plug-in/ace/css/ace.css" class="ace-main-stylesheet" id="main-ace-style" />
  <!--[if lte IE 9]>
  <link rel="stylesheet" href="plug-in/ace/css/ace-part2.css" class="ace-main-stylesheet" />
  <![endif]-->

  <!--[if lte IE 9]>
  <link rel="stylesheet" href="plug-in/ace/css/ace-ie.css" />
  <![endif]-->
  <!-- ace settings handler -->
  <script src="plug-in/ace/js/ace-extra.js"></script>

  <!--[if lte IE 8]>
  <script src="plug-in/ace/js/html5shiv.js"></script>
  <script src="plug-in/ace/js/respond.js"></script>
  <![endif]-->
  <style>
    .hide {
      display: none !important;
    }
    .light-login-xx {
      background: #dfe0e2 url(images/airport-taxiway-long-queue-of-planes-picjumbo-com@2x.png) no-repeat;
      background-position: center bottom;
      background-size: cover;
    }
    .login-layout .main-content {
      padding: 0;
    }
    .login-container {
      width: 33%;
      min-width: 375px;
      max-width: 427px;
      padding-top: 48px;
    }
    .login-header {
      width: 100%;
      padding-top: 34.66%;
      background: url(images/login-header.png) no-repeat;
      background-position: center;
      background-size: auto 100%;
      border-radius: 24px 24px 0 0;
      overflow: hidden;
      box-shadow: 0 0 20px rgba(0,0,0,.3);
    }
    .position-relative {
      border-radius: 0 0 24px 24px;
      overflow: hidden;
      box-shadow: 0 10px 10px rgba(0,0,0,.3);
    }
    .login-layout .widget-box {
      margin: 0;
      padding: 0;
    }
    .login-layout .widget-box .widget-main {
      background-color: #FFF;
      padding: 36px 17.8%;
    }
    .login-layout label {
      margin-bottom: 20px;
    }
    .login-layout .input-icon {
      border: 1px solid #ddd;
      border-radius: 5px;
      overflow: hidden;
    }
    .login-layout .input-icon > input {
      font-size: 16px;
      height: 49px !important;
      border: none;
      padding: 8px 8px 9px 18px;
      color: #9b9b9b;
    }
    .login-layout .input-icon-right > input {
      padding: 8px 36px 9px 20px;
    }
    .input-icon > .ace-icon {
      font-size: 24px;
      padding: 8px;
    }
    .input-group-addon {
      padding: 0 0 0 10px;
      background-color: transparent;
      border: none;
    }
    .login-layout label.inline input[type=checkbox].ace + .lbl,
    .login-layout label.inline input[type=checkbox].ace + .lbl font{
      font-size: 14px;
      color: #707070;
    }
    .login-layout label.inline input[type=checkbox].ace + .lbl::before {
      font-size: 18px;
      line-height: 24px;
      width: 24px;
      height: 24px;
      color: #005841;
      border-radius: 3px;
      background-color: #FFF;
      vertical-align: middle;
      margin-right: 10px;
    }
    .login-layout label.inline input[type=checkbox].ace:hover + .lbl::before {
      border-color: #005841;
    }
    .btn-login {
      height: 48px;
      background: #38B6FF !important;
      background: -webkit-linear-gradient(to bottom, #38CEFF, #38B6FF) !important;
      background: linear-gradient(to bottom, #38CEFF, #38B6FF) !important;
      border: none;
      border-radius: 10px;
    }
    .btn-login span,
    .btn-login font{
      font-size: 20px;
     }
  </style>
</head>
<body class="login-layout light-login-xx">
<div class="main-container">
  <div class="main-content">
        <div class="login-container">
          <div class="center hide">
            <h1 id="id-text2" class="grey">
              <img src="images/favicon.ico">
               <h2 class="grey">飞行区运行管理系统</h2>
            </h1>
            <%--<h4 class="blue" id="id-company-text">www.jeecg.org</h4>--%>
          </div>
          <div class="space-6 hide"></div>
          <div class="login-header"></div>
          <div class="position-relative">
            <div id="login-box" class="login-box visible widget-box no-border">
              <div class="widget-body">
                <form id="loinForm" class="form-horizontal"    method="post">
                <!-- 单点登录参数 -->
                <input type="hidden" id="ReturnURL"  name="ReturnURL" value="${ReturnURL }"/>
                <div class="widget-main">
                 <div class="alert alert-warning alert-dismissible" role="alert" id="errMsgContiner">
				  <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
				  <div id="showErrMsg"></div>
				</div>
                  <h4 class="header blue lighter bigger hide">
                    <i class="ace-icon fa fa-coffee green"></i>
                	    用户登录
                  </h4>
                  <div class="space-6 hide"></div>
                      <label class="block clearfix">
								<span class="block input-icon input-icon-right">
									<input type="text"  name="userName" iscookie="true" class="form-control" placeholder="请输入用户名"  id="userName" value=""/>
									<i class="ace-icon fa fa-user"></i>
								</span>
                      </label>
                      <label class="block clearfix">
								<span class="block input-icon input-icon-right">
									<input type="password" name="password" class="form-control" placeholder="请输入密码" id="password" value=""/>
									<i class="ace-icon fa fa-lock"></i>
								</span>
                      </label>
                      <label class="block clearfix">
                        <div class="input-group">
                          <span class="block input-icon">
                            <input type="text" name="randCode" class="form-control" placeholder="请输入验证码"  id="randCode"/>
                          </span>
                          <span class="input-group-addon"><img id="randCodeImage" src="randCodeImage"  /></span>
                        </div>
                      </label>
                      <div class="space hide"></div>
                      <div class="block clearfix">
                        <label class="inline">
                          <input type="checkbox" checked="checked" class="ace" id="on_off"  name="remember" value="yes"/>
                          <span class="lbl">记住用户名</span>
                        </label>
                        <%--<span> | <a href="http://demo.jeecg.org/mLoginController.do?login&from=singlemessage&isappinstalled=0"><i class="ace-icon fa fa-location-arrow"></i><font color='#428bca'>移动OA</font></a></span>--%>
                         <%--<span> | <a href="http://yun.jeecg.org" target="_blank"><i class="ace-icon fa fa-cube"></i><font color='#428bca'>插件中心</font></a></span>--%>
                      </div>
                        <div class="block clearfix">
                        <button type="button" id="but_login"  onclick="checkUser()" class="btn btn-block btn-login">
                          <i class="ace-icon fa fa-key hide"></i>
                          <span>登 录</span>
                        </button>
                        <%--<a href="loginController.do?goResetPwdMail" class="btn btn-link">忘记密码 ?</a>--%>
                      </div>
                      <div class="space-4 hide"></div>

                </div>
                <div class="toolbar clearfix hide">
                  <div style="float: right">
                    <a href="#"  class="forgot-password-link">
                    	  语言
                      <i class="ace-icon fa fa-arrow-right"></i>
                      <t:dictSelect id="langCode" field="langCode" typeGroupCode="lang" hasLabel="false" extendJson="{style:'padding:2px; width:80px;'}" defaultVal="zh-cn"></t:dictSelect>
                    </a>
                  </div>
                </div>
                </form>
              </div>
            </div>
            <div class="center hide"><h4 class="blue" id="id-company-text">&copy; 广东机场白云信息科技有限公司</h4></div>
            <div class="navbar-fixed-top align-right">
              <%--<br />--%>
              <%--&nbsp;--%>
              <%--<a id="btn-login-dark" class="blue" href="#" onclick="darkStyle()">Dark</a>--%>
              <%--&nbsp;--%>
              <%--<span class="blue">/</span>--%>
              <%--&nbsp;--%>
              <%--<a id="btn-login-blur" class="blue" href="#" onclick="blurStyle()">Blur</a>--%>
              <%--&nbsp;--%>
              <%--<span class="blue">/</span>--%>
              <%--&nbsp;--%>
              <%--<a id="btn-login-light" class="blue" href="#" onclick="lightStyle()">Light</a>--%>
              <%--&nbsp; &nbsp; &nbsp;--%>
            </div>
              </div>
            </div>
      </div>
    </div>

<script type="text/javascript" src="plug-in/jquery/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="plug-in/jquery/jquery.cookie.js"></script>
<script type="text/javascript" src="plug-in/mutiLang/en.js"></script>
<script type="text/javascript" src="plug-in/mutiLang/zh-cn.js"></script>
<script type="text/javascript" src="plug-in/login/js/jquery.tipsy.js"></script>
<script type="text/javascript" src="plug-in/login/js/iphone.check.js"></script>
<script type="text/javascript" src="plug-in/layer/layer.js"></script>
<script type="text/javascript" src="webpage/login/login-ace.js"></script>
<%=lhgdialogTheme %>
<script type="text/javascript">
  var u=navigator.userAgent;
  if(u.indexOf('AppleWebKit')==-1){
      var index =layer.open({
          title:['温馨提醒'],
          offset: '200px',
          content: '推荐使用谷歌浏览器访问',
          btn: ['下载', '我知道了'],
          yes: function(index, layero){
              window.open("upload/chrome/chrome.exe");
              layer.close(index);
          },
          btn2: function(index, layero){
          }
      })
  }
	$(function(){
		//getCookie();
		optErrMsg();
	});
	$("#errMsgContiner").hide();

   //输入验证码，回车登录
  $(document).bind('keyup', function(event) {
	　　if (event.keyCode == "13") {
	　　　　$('#but_login').click();
	　　}
  });

  //验证用户信息
  function checkUser(){
    if(!validForm()){
      return false;
    }
    newLogin();
  }
  
  /**
   * 刷新验证码
   */
  $('#randCodeImage').click(function(){
	    reloadRandCodeImage();
  });
	
</script>

<script>
var _hmt = _hmt || [];
(function() {
  var hm = document.createElement("script");
  hm.src = "https://hm.baidu.com/hm.js?098e6e84ab585bf0c2e6853604192b8b";
  var s = document.getElementsByTagName("script")[0]; 
  s.parentNode.insertBefore(hm, s);
})();
</script>

</body>
</html>