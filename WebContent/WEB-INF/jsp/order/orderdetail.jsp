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
<title>订单详细页</title>
</head>
<body>
<div id="header" class="wrap">
	<div id="logo">网上书城</div>
	<div id="navbar">
		<div class="userMenu">
			<ul>
			<c:if test="${sessionScope.user!=null }">
				<li ><a href="main">首页</a></li>
				<li class="current"><a href="${ctx}/orderlist/1">我的订单</a></li>
				<li><a href="${ctx}/cart">购物车</a></li>
				<li><a href="${ctx}/usercenter">个人中心</a></li>
				<li><a href="${ctx}/logout">注销</a></li>
			</c:if>
				<c:if test="${sessionScope.admin!=null }">
				<li><a href="${ctx}/main">首页</a></li>
				<li class="current"><a href="${ctx}//orderlist/1">订单列表</a></li>
				<li><a href="${ctx}//booklist/1">图书列表</a></li>
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
	<div class="list orderList">
			<table>
				<tr class="title">
					<th class="orderId">订单编号</th>
					<th>订单商品</th>
					<th class="userName">收货人</th>
					<th class="price">订单金额</th>
					<th class="createTime">下单时间</th>
					<th class="status">订单状态</th>
				</tr>
				
					<c:forEach items="${order.orderitems}" var="orderitem" varStatus="stat" begin="0" end="20" step="1">
					<tr>
						<td>${order.id }</td>
						<td class="thumb"><img src="${ctx}/images/book/${orderitem.book.img}" /></td>
						<td>${order.user.username}</td>
						<td>￥${orderitem.book.price} x ${orderitem.quantity}</td>
						<td><fmt:formatDate value="${order.createdate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
						<td>
						<c:if test="${order.state==0 }">未发货</c:if>
						<c:if test="${order.state==1 }">已发货未签收</c:if>
						<c:if test="${order.state==2 }">已签收</c:if>
						<c:if test="${order.state==3 }">正在退货</c:if>
						<c:if test="${order.state==4 }">已退货</c:if>						
						</td>
					</tr>
            		</c:forEach>
            	<tr>
					<td colspan="6" align="right">
						<c:if test="${order.state==0 }">
							总计金额：￥${order.amount } <button>订单未发货</button></c:if>
						<c:if test="${order.state==1 }">
							总计金额：￥${order.amount } <button>确认签收</button></c:if>
						<c:if test="${order.state==2 }">
							总计金额：￥${order.amount } <button>已签收</button> <button>申请退货</button></c:if>
						<c:if test="${order.state==3 }">						
							总计金额：￥${order.amount } <button>正在退货</button></c:if>
						<c:if test="${order.state==4 }">						
							总计金额：￥${order.amount } <button>已退货</button></c:if>	
					</td>

				</tr>
				
			</table>
	</div>
</div>
<div id="footer" class="wrap">
	XXXXXXXXX &copy; 版权所有

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
</script>
</body>
</html>