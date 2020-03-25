<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>更新图书</title>
    <script src="js/jquery-1.11.3.min.js"></script>   
    <link rel="stylesheet" href="css/bootstrap.min.css"> 
    <link type="text/css" rel="stylesheet" href="${ctx}/css/style.css" />
    <link type="text/css" rel="stylesheet" href="${ctx}/css/bootstrap.min.css" />
    <script type="text/javascript" src="${ctx}/js/bootstrap.min.js"></script>
</head>
<body>
<div id="header" class="wrap">
	<div id="logo">网上书城</div>
	<div id="navbar">
		<div class="userMenu">
			<ul>
				<li ><a href="${ctx}/main">首页</a></li>
				<li><a href="${ctx}/orderlist/1">订单列表</a></li>
				<li><a href="${ctx}/booklist/1">图书列表</a></li>
				<li class="current"><a href="${ctx}/showaddbook">添加图书</a></li>
				<li><a href="${ctx}/userlist/1">用户列表</a></li>
				<li><a href="${ctx}/logout">注销</a></li>
			</ul>
		</div>
		<form method="get" name="searchbook" action="" id="searchbook" onsubmit="return false;">
			搜索：<input class="input-text" type="text" name="name" id="name" placeholder="请输入书名"/><input class="input-btn" type="submit" name="submit" value="" />
		</form>
	</div>
</div>
<br>
<br>
<br>
<div class="container" align="center">
	<div class="row clearfix">
		<div class="col-md-12 column">
			<form role="form" action="${ctx}/update" method="post" enctype="multipart/form-data">
				<input type="hidden" name="id" value="${book.id}">
				<div class="form-group">
					 <label for="exampleInputEmail1">书名</label><input type="text" class="form-control" id="name" name="name" value="${book.name }"/>
				</div>
				<div class="form-group">
					 <label for="exampleInputPassword1">价格</label><input type="text" class="form-control" id="price" name="price" value="${book.price}"/>
				</div>
				<div class="form-group">
					 <label for="exampleInputPassword1">库存</label><input type="text" class="form-control" id="quantity" name="quantity" value="${book.quantity}"/>
				</div>
<!-- 				<div class="form-group">
					 <label for="exampleInputFile">图片</label><input type="file" id="image" name="image"/>
				</div> -->
				 <button type="submit" class="btn btn-default">修改</button>
				 <button type="reset" class="btn btn-default">重置</button>
			</form>
		</div>
	</div>
</div>

<script>
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
						window.location.href = "bookdetail?id="+data.msg;
					}
			}
		})
	})
</script>
</body>
</html>