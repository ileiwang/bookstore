<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>注册成功</title>
    <link type="text/css" rel="stylesheet" href="css/style.css" />
    <script type="text/javascript" src="js/jquery-1.11.0.js"></script>
</head>
<body>
<div id="header" class="wrap">
	<div id="logo">网上书城</div>
	<div id="navbar">
		<form method="get" name="searchbook" action="" id="searchbook" onsubmit="return false;">
			搜索：<input class="input-text" type="text" name="name" id="name" placeholder="请输入书名"/><input class="input-btn" type="submit" name="submit" value="" />
		</form>
	</div>
</div>
<div id="register">
	<div class="title">
		<h2>欢迎注册</h2>
	</div>
	<div class="steps">
		<ul class="clearfix">
			<li class="past">1.填写注册信息</li>
			<li class="last">2.注册成功</li>
		</ul>
	</div>
	<div class="success">
		<div class="information">
			<p>恭喜：注册成功！</p>
			<p><a href="${ctx}/loginForm">点此进入登录页面&gt;&gt;</a></p>
		</div>
	</div>
</div>
<div id="footer" class="wrap">
	网上书城 &copy; 版权所有

</div>
<script type="text/javascript">
	
	function myFunction1(){
		var x=document.getElementById("usernametip");
		  x.innerHTML="";
	}
	function myFunction2(){
		var x=document.getElementById("passwordtip");
		  x.innerHTML="";
	}
	function myFunction3(){
		var x=document.getElementById("repasswordtip");
		  x.innerHTML="";
	}
	function myFunction4(){
		var x=document.getElementById("emailtip");
		  x.innerHTML="";
	}
	
	$("#registerForm").submit(function()
	{
		var username = $("#username").val();
		var password = $("#password").val();
		var repassword = $("#repassword").val();
		var email = $("#email").val();
		
		if(username==null||username.length==0){
			$("#usernametip").html("必须输入用户名");
			//return;
		}
		
		
		if(password==null||password.length==0){
			$("#passwordtip").html("必须输入密码");
			//return;
		}
		
		if(repassword==null||repassword.length==0){
			$("#repasswordtip").html("必须输入确认密码");
			//return;
		}
		if(email==null||email.length==0){
			$("#emailtip").html("必须输入邮箱");
			return;
		}
		
		if((password.length!=0)&&(repassword.length!=0)&&(password!=repassword)){
			$("#passwordtip").html("两次输入不一致");
			$("#repasswordtip").html("两次输入不一致");
			return;
		}
		
		if(password.length<=5){
			$("#password").html("必须输入邮箱");
			return;
		}
		
		$.ajax(
		{
			type:"post",
			url:"register",
			data:$("#registerForm").serialize(),
			dataType:"json",
			success:function(data)
			{
 				if(data.code==0){
 					alert(data.msg)
				}
 				else if(data.code==1){
 					//window.location.href="main";
 					alert("用户已存在,请换个用户名试试");
 				}
 				else{
 					window.location.href="register_success";
 				}
			}
		})
	})
	
	
	$("#searchbook").submit(function()
	{
		var name = $("#name").val();
		if(name==null||name.length==0){
			alert("书名不能为空");
			return;
		}
		
		if(name!=null||name.length!=0){
			alert("登录后才能搜索");
			return;
		}
		
		$.ajax(
		{
			type:"post",
			url:"searchbook",
			data:$("#searchbook").serialize(),
			dataType:"json",
			success:function(data)
			{
 				if(data.code==0){
 					alert("图书不存在")
				}
 				else if(data.code==1){
 					window.location.href = "bookdetail?id="+data.msg;
 				}
			}
		})
	})
</script>
</body>
</html>