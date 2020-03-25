<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link type="text/css" rel="stylesheet" href="${ctx }/css/style.css" />
<script type="text/javascript" src="${ctx}/js/jquery-1.11.0.js"></script>
<title>用户详细资料</title>
</head>
<body>
<div id="header" class="wrap">
	<div id="logo">网上书城</div>
	<div id="navbar">
		<div class="userMenu">
			<ul>
				<li><a href="${ctx }/main">首页</a></li>
				<li><a href="${ctx }/orderlist/1">订单管理</a></li>
				<li><a href="${ctx }/booklist/1">图书管理</a></li>
				<li class="current"><a href="${ctx }/userlist/1">用户管理</a></li>
				<li><a href="${ctx }/logout">注销</a></li>

			</ul>
		</div>
		<form method="post" name="search" action="" onsubmit="return false;" id="searchuser">
			搜索：<input class="input-text" type="text" name="username" id="username" placeholder="请输入用户名" /><input class="input-btn" type="submit" name="submit" value="" />
		</form>
	</div>
</div>
<div id="content" class="wrap">
	<div class="list bookList">
			<table>
				<tr class="title">
					<th class="">用户名</th>
					<th class="">邮箱</th>
					<th class="">解锁</th>
					<th class="">修改</th>
					<th class="">删除</th>
					<th class="">查看订单</th>
				</tr>
				<tr>
					<td class="title">${user.username}</td>
					<td>${user.email}</td>
					<c:if test="${user.islocked==1}">
						<td><a href="${ctx}/unlockuser/${user.id}"><button>解锁</button></a></td>
					</c:if>
					<c:if test="${user.islocked==0}">
						<td>未锁定(错误次数：${user.errorcount})</td>
					</c:if>
					<td ><a href="${ctx}/updateuser/${user.id}" id="updateuser"><button>修改</button></a></td>
					<td ><a href="${ctx}/deleteuser/${user.id}" id="deleteuser"><button>删除</button></a></td>
					<td ><a href="${ctx}/findordersbyuserid/${user.id}/1"><button>查看订单</button></a></td>
				</tr>
			</table>
<%-- 		<td align="center" class="font3">
	  	 <bookstore:pager
	  	        pageIndex="${requestScope.pageModel.pageIndex}" 
	  	        pageSize="${requestScope.pageModel.pageSize}" 
	  	        recordCount="${requestScope.pageModel.recordCount}" 
	  	        style="digg"
	  	        submitUrl="${ctx}/findordersbyuserid/${user.id}/{0}"/>
	  	</td> --%>	
	</div>
</div>
<div id="footer" class="wrap">
	网上书城 &copy; 版权所有

</div>
<script>
$("#searchuser").submit(function()
		{
			var username = $("#username").val();
			if(username==null||username.length==0){
				alert("请输入用户名");
				return;
			}
			
			$.ajax(
			{
				type:"post",
				url:"${ctx}/finduserbyusername",
				data:$("#searchuser").serialize(),
				dataType:"json",
				success:function(data)
				{
	 				if(data.code==0){
	 					alert("用户不存在")
					}
	 				else if(data.code==1){
	 					window.location.href = "${ctx}/userdetail/"+data.msg;
	 				}
				}
			})
		})
</script>
</body>
</html>