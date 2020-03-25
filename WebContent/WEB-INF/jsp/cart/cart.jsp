<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link type="text/css" rel="stylesheet" href="${ctx}/css/style.css" />
<link type="text/css" rel="stylesheet"
	href="${ctx}/css/bootstrap.min.css" />
<script type="text/javascript" src="${ctx}/js/jquery-1.11.0.js"></script>
<script type="text/javascript" src="${ctx}/js/bootstrap.min.js"></script>
<title>购物车</title>
</head>
<body>
	<div id="header" class="wrap">
		<div id="logo">网上书城</div>
		<div id="navbar">
			<div class="userMenu">
				<ul>
					<li><a href="${ctx}/main">首页</a></li>
					<li><a href="${ctx}/orderlist/1">我的订单</a></li>
					<li class="current"><a href="${ctx}/cart">购物车</a></li>
					<li><a href="${ctx}/logout">注销</a></li>
				</ul>
			</div>
			<form method="get" name="searchbook" action="" id="searchbook"
				onsubmit="return false;">
				搜索：<input class="input-text" type="text" name="name" id="name"
					placeholder="请输入书名" /><input class="input-btn" type="submit"
					name="submit" value="" />
			</form>
		</div>
	</div>
	<div id="content" class="wrap">
		<div class="list bookList">
			<!--购物车为空 -->
			<c:if test="${fn:length(sessionScope.cart.items)==0}">
				<tr>
					<td align="center"><center>
							<h3>你的购物车空空如也</h3>
						</center></td>
				</tr>
			</c:if>
			<!-- 				购物车不空 -->
			<c:if test="${fn:length(sessionScope.cart.items )!=0}">
				<form method="post" name="purchase" action="${ctx}/purchase">
					<table>
						<tr class="title">
							<th class="view">图片预览</th>
							<th>书名</th>
							<th class="nums">数量</th>
							<th class="price">价格</th>
						</tr>
						<c:forEach items="${sessionScope.cart.items}" var="item"
							varStatus="stat" begin="0" end="10" step="1">
							<tr>
								<td class="thumb"><img src="images/book/${item.book.img }" /></td>
								<td class="title">${item.book.name }</td>
								<td><input class="input-text" type="text" name="nums"
									value="${item.quantity}" id="num_${stat.index}"
									onchange="updateValue(this.value,${stat.index})" /></td>
								<%-- <td>￥<span>${item.book.price*item.quantity }</span>*<span id="numm_${stat.index }">1</span></td> --%>
								<td>￥<span id="bookprice_${stat.index}">${item.book.price*item.quantity}</span></td>
							</tr>
							<input type="hidden" name="realprice"
								id="realprice_${stat.index}" value="${item.book.price}" />
						</c:forEach>


					</table>
					<div class="button">
						<h4>
							总价：￥<span id="total">${cart.total}</span>元
						</h4>
						<input class="input-chart" type="submit" name="submit" value="" />
						<a href="${ctx}/clearcart"><button class="clear-chart"
								type="button" name="" value=""></button></a>
					</div>
				</form>
			</c:if>
		</div>
	</div>
	<div id="footer" class="wrap">网上书城 &copy; 版权所有</div>
	<script type="text/javascript">
	function updateValue(val,index){
		
		//var y=document.getElementById("bookprice_"+index);
		  //alert(y.innerHTML)
		//alert("输入值已更改。新值是：" + val+"index is"+index);
		//x.innerHTML = val*x.innerHTML;
		//var x=document.getElementById("num_"+index).value;
		//alert(x);
		var price = document.getElementById("realprice_"+index).value;
		 // x.innerHTML=val;
		 //alert(x.val);
			//alert(price);
		document.getElementById("bookprice_"+index).innerHTML = val*price;
	
	}
</script>

	<script type="text/javascript">
 var boxs  = $("input[type='text'][id^='book_']");
  $("#purchaseForm").submit(function(){
	   var checkedBoxs = boxs.filter(":checked");
	   if(checkedBoxs.length < 1){
		   alert("请选择图书");
	   }
	   else{
		   var ids = checkedBoxs.map(function(){
			   return this.value;
			   })
		   var r = confirm("确定加入购物车？");
		   if(r){
			  window.location.href = "addtocart?ids=" + ids.get();
		   }
		   else{
			   return;
			}
		 }
  })
  
  
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