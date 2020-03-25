<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>用户登录</title>  
<!--     <link rel="stylesheet" href="css/bootstrap.min.css">  -->
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
<div id="login">
	<h2>用户登录</h2>
	<form method="post" action="" id="loginForm" onsubmit="return false;">
		<dl>
			<dt>用户名：</dt>
			<dd><input class="input-text" type="text" name="username" id="username" onfocus="myFunction1()"/><span id="usernametip"></span></dd>
			<dt>密　码：</dt>
			<dd><input class="input-text" type="password" name="password" id="password" onfocus="myFunction2()"/><span id="passwordtip"></span></dd>
			<dt>类　型：</dt>
			<dd>
			<select name="type" id="type">
			<option value="user">用户</option>
            <option value="admin">管理员</option>
            </select>
            </dd>
			<dd class="button">
			<input class="input-btn" type="submit" name="submit" value="" />
			<input class="input-reg" type="button" name="register" value="" onclick="window.location.href='registerForm';" />
			</dd>
		</dl>
	</form>
	<center><font color="red">${message}</font></center>
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

	$("#loginForm").submit(function()
	{
		var username = $("#username").val();
		var password = $("#password").val();
		
		if(username==null||username.length==0){
			$("#usernametip").html("必须输入用户名");
			return;
		}
		
		
		if(password==null||password.length==0){
			$("#passwordtip").html("必须输入密码");
			return;
		}
		
		if((password==null||password.length==0)&&(username==null||username.length==0)){
			$("#usernametip").html("必须输入用户名");
			$("#passwordtip").html("必须输入密码");
			return;
		}
		
		$.ajax(
		{
			type:"post",
			url:"login",
			data:$("#loginForm").serialize(),
			dataType:"json",
			success:function(data)
			{
 				if(data.code==0){
 					document.getElementById('usernametip').innerHTML="用户不存在";
				}
 				else if(data.code==1){
 					document.getElementById('passwordtip').innerHTML="密码错误";
 				}
 				else if(data.code==2){
 					window.location.href = "${ctx}/main";
 				}
 				else if(data.code==3){
 					window.location.href = "${ctx}/main";
 				}
 				else if(data.code==8){//根据用户名查询失败
 					alert(data.msg);
 				}
 				else if(data.code==9){//账号锁定
 					alert(data.msg);
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
			url:"${ctx}/searchbook",
			data:$("#searchbook").serialize(),
			dataType:"json",
			success:function(data)
			{
 				if(data.code==0){
 					alert("图书不存在")
				}
 				else if(data.code==1){
 					window.location.href = "${ctx}/bookdetail?id="+data.msg;
 				}
			}
		})
	})
</script>
</body>
</html>