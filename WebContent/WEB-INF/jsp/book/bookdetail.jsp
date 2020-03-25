<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link type="text/css" rel="stylesheet" href="${ctx}/css/style.css" />
<script type="text/javascript" src="${ctx}/js/jquery-1.11.0.js"></script>
<title>图书详细页</title>
</head>
<body>
<div id="header" class="wrap">
	<div id="logo">网上书城</div>
	<div id="navbar">
		<div class="userMenu">
			<ul>
			<c:if test="${sessionScope.user!=null }">
				<li class="current"><a href="${ctx}/main">首页</a></li>
				<li><a href="${ctx}/orderlist/1">我的订单</a></li>
				<li><a href="${ctx}/cart">购物车</a></li>
				<li><a href="${ctx}/logout">注销</a></li>
			</c:if>
				<c:if test="${sessionScope.admin!=null }">
				<li><a href="${ctx}/main">首页</a></li>
				<li><a href="${ctx}/orderlist/1">订单列表</a></li>
				<li  class="current"><a href="${ctx}/booklist/1">图书列表</a></li>
				<li><a href="${ctx}/showaddbook">添加图书</a></li>
				<li><a href="${ctx}/userlist/1">用户列表</a></li>
				<li><a href="${ctx}/logout">注销</a></li>
			</c:if>
			</ul>
		</div>
		<form method="get" name="searchbook" action="" id="searchbook" onsubmit="return false;">
			搜索：<input class="input-text" type="text" name="name" id="name" placeholder="请输入书名"/><input class="input-btn" type="submit" name="submit" value="" />
		</form>
	</div>
</div>
<div id="content" class="wrap">
	<div class="list bookList">
			<table>
				<tr class="title">
					<th>书名</th>
					<th class="">价格</th>
					<th class="">库存</th>
					<th class="">图片预览</th>
					<c:if test="${sessionScope.admin!=null }">
						<th>修改</th>
						<th>删除</th>
					</c:if>

				</tr>
				<tr>
					<td class="title">${book.name}</td>
					<td>￥${book.price}</td>
					<td>${book.quantity}</td>
					<td class="thumb"><img src="${ctx}/images/book/${book.img}" /></td>
					
					<c:if test="${sessionScope.admin!=null }">
					<td class="">
					
					
					<form action="${ctx}/updatebook" method="post" id="updateForm">
						<input type="hidden" name="id" id="id" value="${book.id}">
						<input class="input-btn" type="submit" name="submit" value="修改" />
					</form>
<%-- 					<a href="${ctx}/updatebook/${book.id}">修改</a> --%>
					
					</td>
					<td class="">
					<form action="" method="post" id="deleteForm" onsubmit="return false;">
						<input type="hidden" name="id" id="id" value="${book.id}">
						<input class="input-btn" type="submit" name="submit" value="删除" />
					</form>
					
<%-- 					<a href="${ctx}/deletebook/${book.id}">删除</a> --%>
					
					</td>
					</c:if>
				</tr>
				
			</table>
	</div>
</div>
<div id="footer" class="wrap">
	网上书城 &copy; 版权所有

</div>

<script type="text/javascript">
$("#searchbook").submit(function()
		{
			var name = $("#name").val();
			if(name==null||name.length==0){
				alert("书名不能为空");
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
	 					window.location.href = "${ctx}/bookdetail/"+data.msg;
	 				}
				}
			})
		})
		
$("#deleteForm").submit(function()
		{
			$.ajax(
			{
				type:"post",
				url:"${ctx}/deletebook",
				data:$("#deleteForm").serialize(),
				dataType:"json",
				success:function(data)
				{
	 				if(data.code==1){
	 					alert(data.msg);
					}
	 				else if(data.code==2){
	 					alert(data.msg);
	 					window.location.href="${ctx}/booklist/1";
	 				}
	 				else if(data.code==3){
	 					alert(data.msg);
	 					
	 				}
				}
			})
		})
		
/* $("#updateForm").submit(function()
		{
			
			$.ajax(
			{
				type:"post",
				url:"${ctx}/updatebook",
				data:$("#searchbook").serialize(),
				dataType:"json",
				success:function(data)
				{
	 				if(data.code==0){
	 					alert("图书不存在")
					}
	 				else if(data.code==1){
	 					window.location.href = "bookdetail/"+data.msg;
	 				}
				}
			})
		}) */
</script>

</body>
</html>