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
<link type="text/css" rel="stylesheet" href="${ctx}/css/bootstrap.min.css">
<script type="text/javascript" src="${ctx}/js/jquery-1.11.0.js"></script>
<script type="text/javascript" src="${ctx}/js/bootstrap.min.js"></script>
<title>图书列表页</title>
</head>
<body>
<div id="header" class="wrap">
	<div id="logo">网上书城</div>
	<div id="navbar">
		<div class="userMenu">
			<ul>
			<c:if test="${sessionScope.user!=null }">
				<li><a href="${ctx}/main">首页</a></li>
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
		<table class="table table-striped">
			<tr class="title">
				<th class="checker"></th>
				<th>书名</th>
				<th class="price">价格</th>
				<th class="store">库存</th>
				<th class="view">图片预览</th>
				<th>操作</th>
			</tr>
		<c:forEach items="${requestScope.books}" var="book" varStatus="stat" begin="0" end="17" step="1">
			<tr>
				<td><input type="checkbox" id="book_${stat.index}" value="${book.id}" /></td>
				<td class="title">${book.name}</td>
				<td>￥${book.price}</td>
				<td>${book.quantity}</td>
				<td class="thumb"><img src="${ctx}/images/book/${book.img}" /></td>
				<td><a href="${ctx}/bookdetail/${book.id}"><button>图书管理</button></a></td>
			</tr>
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
		
	  	 <bookstore:pager
	  	        pageIndex="${requestScope.pageModel.pageIndex}" 
	  	        pageSize="${requestScope.pageModel.pageSize}" 
	  	        recordCount="${requestScope.pageModel.recordCount}" 
	  	        style="page-spliter"
	  	        submitUrl="${ctx}/booklist/{0}"/>
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
	 					alert("图书不存在");
					}
	 				else if(data.code==1){
	 					window.location.href = "${ctx}/bookdetail/"+data.msg;
	 				}
				}
			})
		})
			
/* $("#deleteForm").submit(function()
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
		}) */
</script>

<!-- <script type="text/javascript">
   var boxs  = $("input[type='checkbox'][id^='book_']");
   $("#addtocart").submit(function(){
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
			  window.location = "addtocart?ids=" + ids.get();
		   }
		   else{return;}
		  }
   })
</script> -->
</body>
</html>