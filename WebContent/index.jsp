<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!-- 启动项目后，进入index.jsp，然后forward到主页面，若session里没有用户信息，则被拦截器拦截，跳转到loginForm进行登录操作。 -->
<jsp:forward page="main"/>