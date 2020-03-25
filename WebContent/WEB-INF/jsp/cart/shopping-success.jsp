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
<title>购买成功</title>
</head>
<body>
<div id="header" class="wrap">
	<div id="logo">网上书城</div>
	<div id="navbar">
		<div class="userMenu">
			<ul>
			<c:if test="${sessionScope.user!=null }">
				<li class="current"><a href="${ctx}/main">首页</a></li>
				<li ><a href="${ctx}/orderlist/1">我的订单</a></li>
				<li><a href="${ctx}/cart">购物车</a></li>
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
	<div class="success">
		<div class="information">
			<p>恭喜：购买成功！</p>
			<p><a href="${ctx}/orderdetail/${order.id}">点此查看订单详情&gt;&gt;</a></p>
		</div>
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
			url:"searchbook",
			data:$("#searchbook").serialize(),
			dataType:"json",
			success:function(data)
			{
					if(data.code==0){
						alert("图书不存在");
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