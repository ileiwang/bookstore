<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>添加图书</title>
    <script type="text/javascript" src="${ctx}/js/jquery-1.11.3.min.js"></script>   
	<link rel="stylesheet" href="${ctx}/css/bootstrap.min.css">
    <link type="text/css" rel="stylesheet" href="${ctx}/css/style.css" />
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
			搜索：<input class="input-text" type="text" name="bookname" id="bookname" placeholder="请输入书名"/><input class="input-btn" type="submit" name="submit" value="" />
		</form>
	</div>
</div>
<br>
<br>
<br>
<div class="container" align="center">
	<div class="row clearfix">
		<div class="col-md-12 column">
			<form role="form" action="" method="post" enctype="multipart/form-data" onsubmit="return false;" id="addbookForm">
			<dl>
					 <dt>书名</dt>
					 <dd><input type="text" class="form-control" id="name" name="name"  onfocus="myFunction1()"/><span id="booknametip"></span></dd>
					 <dt>价格</dt>
					 <dd><input type="text" class="form-control" id="price" name="price" onfocus="myFunction2()"/><span id="pricetip"></span></dd>
					 <dt>库存</dt>
					 <dd><input type="text" class="form-control" id="quantity" name="quantity" onfocus="myFunction3()" /><span id="quantitytip" ></span></dd>
					 <dt>图片</dt>
					 <dd><input type="file" class="form-control" id="image" name="image"/></dd>
					 <dd>
						 <button type="submit" class="btn btn-default">添加</button>
						 <button type="reset" class="btn btn-default">重置</button>
				 	 </dd>
				 </dl>
			</form>
		</div>
	</div>
</div>
<br>
<div id="footer" class="wrap">
	网上书城 &copy; 版权所有
</div>

<script type="text/javascript">

	function myFunction1(){
		var x=document.getElementById("booknametip");
		  x.innerHTML="";
	}
	
	function myFunction2(){
		var x=document.getElementById("pricetip");
		  x.innerHTML="";
	}
	
	function myFunction3(){
		var x=document.getElementById("quantitytip");
		  x.innerHTML="";
	}
	
 	$("#addbookForm").submit(function()
		{
			var name = $("#name").val();
			if(name==null||name.length==0){
				//alert("书名不能为空");
				$("#booknametip").html("<font color='red' size='4'>必须输入书名</font>");
				//return;
			}
			
			var price = $("#price").val();
			if(price==null||price.length==0){
				//alert("价格不能为空");
				$("#pricetip").html("<font color='red' size='4'>价格不能为空</font>");
				//return;
			}
			
			
			var quantity = $("#quantity").val();
			if(quantity==null||quantity.length==0){
				//alert("价格不能为空");
				$("#quantitytip").html("<font color='red' size='4'>库存不能为空</font>");
				return;
			}
			
			//Ajax文件异步上传
			//http://www.php.cn/js-tutorial-384504.html
			var formdata = new FormData($('form')[0]);
			$.ajax(
			{
				type:"post",
				url:"${ctx}/addbook",
				/* data:$("#addbookForm").serialize(), */
				data:formdata,
				contentType: false,
				processData: false,
				dataType:"json",
				success:function(data)
				{
					if(data.code==0){
						alert(data.msg);
					}
					else if(data.code==1){
						/* window.location.href = "${ctx}/bookdetail/"+data.msg; */
						alert(data.msg);
					}
					else if(data.code==2){
						alert("添加成功");
						//跳转到该图书页面
						window.location.href = "${ctx}/bookdetail/"+data.msg;
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