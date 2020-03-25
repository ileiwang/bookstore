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
<title>图书列表页</title>
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
				<c:if test="${sessionScope.admin!=null }">
				<li class="current"><a href="${ctx}/main">首页</a></li>
				<li><a href="${ctx}/orderlist/1">订单列表</a></li>
				<li><a href="${ctx}/booklist/1">图书列表</a></li>
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
		<form method="post" name="addtocart" action="" id="addtocart" onsubmit="return false;">
			<table>
				<tr class="title">
					<th class="checker"></th>
					<th>书名</th>
					<th class="price">价格</th>
					<th class="store">库存</th>
					<th class="view">图片预览</th>
				</tr>
				
				
			<c:forEach items="${sessionScope.books}" var="book" varStatus="stat">
				<c:if test="${book.isdel==0}">
				<tr>
<%-- 					<td><input type="checkbox" id="book_${stat.index+sessionScope.pageModel.pageSize*(sessionScope.pageModel.pageIndex-1)}" value="${book.id}" /></td>
					<td class="title">${book.name}</td>
					<td>￥${book.price}</td>
					<td id="quantity_${stat.index+sessionScope.pageModel.pageSize*(sessionScope.pageModel.pageIndex-1)}">${book.quantity}</td>
					<td class="thumb"><img src="${ctx}/images/book/${book.img}" /></td> --%>
					
					
					<td><input type="checkbox" id="book_${book.id}" value="${book.id}" /></td>
					<td class="title">${book.name}</td>
					<td>￥${book.price}</td>
					<td id="quantity_${book.id}">${book.quantity}</td>
					<td class="thumb"><img src="${ctx}/images/book/${book.img}" /></td>
				</tr>
				</c:if>
            </c:forEach>
				
			</table>
			
			
<!-- 			<div class="page-spliter">
				<a href="#">&lt;</a>
				<a href="#">首页</a>
				<span class="current">1</span>
				<a href="#">2</a>
				<a href="#">3</a>
				<a href="#">4</a>
				<span>...</span>
				<a href="#">尾页</a>
				<a href="#">&gt;</a>
			</div> -->
			<c:if test="${sessionScope.user!=null}">
				<div class="button"><input class="input-btn" type="submit" name="submit" value=""/></div>
			</c:if>
			
		</form>
		<!-- 		<tr valign="top"> -->
<!-- 		<td align="center" class="font3"> -->
	  	 <bookstore:pager
	  	        pageIndex="${sessionScope.pageModel.pageIndex}" 
	  	        pageSize="${sessionScope.pageModel.pageSize}" 
	  	        recordCount="${sessionScope.pageModel.recordCount}" 
	  	        style="page-spliter"
	  	        submitUrl="${ctx}/booklistbypage/{0}"/>
<!-- 	  	</td> -->
<!-- 	  	</tr> -->
	</div>
</div>

<div id="footer" class="wrap">
	网上书城 &copy; 版权所有

</div>

<script type="text/javascript">
 	   var boxs  = $("input[type='checkbox'][id^='book_']");
 	   $("#addtocart").submit(function(){
 		   var checkedBoxs = boxs.filter(":checked");
 		   if(checkedBoxs.length < 1){
 			   alert("请选择图书");
 		   }
 		   else{
 			   var ids = checkedBoxs.map(function(){
 				   return this.value;
 				   }
 			   )
 				   
 				   
/*    		var a = ids.length;alert(a);  */
/* 			如果图书库存为空，则不可再次加入购物车 */
			for(var i = 0;i<ids.length;i++){
				//alert(ids.get(i));
 				var a = ids.get(i);
				//alert(a);
 				var x = document.getElementById("quantity_"+a).innerHTML; 
				//alert(x);
 				if(x==0){
					alert("所选图书库存为空，不可加入购物车");
					return;
				} 
			} 			   
				/*所有图书都符合加入要求 */
			   //alert(ids.get());
 			   var r = confirm("确定加入购物车？");			  			
 			   if(r){
 				  window.location.href = "addtocart?ids=" + ids.get();//全部的图书id信息
 			   }
 			   else{}
 			  }
 	   })
</script>
	
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
	 					alert("图书不存在")
					}
	 				else if(data.code==1){
	 					window.location.href = "bookdetail/"+data.msg;
	 				}
				}
			})
		})
</script>

<!-- <script type="text/javascript">

$(function(){
    initData();
});

function initData()
{
	$.ajax(
		{
			type="get",
			url="${ctx}/findall",
			cache:false,
			dataType:"json",
			success:function(data){
				jQuery.each(data.msg,function(i,item){
					alert(item.id+","+item.name);
				});
			}
		})
}
</script> -->
</body>
</html>