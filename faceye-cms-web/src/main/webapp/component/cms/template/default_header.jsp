<%@ include file="/component/core/taglib/taglib.jsp"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<div class="navbar-header">
	<button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
		<span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span> <span class="icon-bar"></span> <span
			class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span>
	</button>
	<a class="navbar-brand" href="<c:url value="/"/>"><fmt:message key="global.home" /></a>
</div>

<div class="navbar-collapse collapse">
	<ul class="nav navbar-nav">
		<!-- 
		<li class="active"><a href="<c:url value="/"/>"><fmt:message key="global.home" /></a></li>
		 -->
	</ul>
<!--  
	<form action="<c:url value="/cms/content/home"/>" method="post" role="search" class="navbar-form navbar-right">
		<div class="form-group">
			<input type="text" name="like|name" value="${searchParams.name}" class="form-control"
				placeholder="Please Input Search key workds">
		</div>
		<button type="submit" class="btn btn-default">
			<fmt:message key="global.search"></fmt:message>
		</button>
	</form>
	-->
	<p class="navbar-text navbar-right"><a href="<c:url value="/web/login"/>" class ="navbar-link">登录</a>|<a href="<c:url value="/web/register"/>" class ="navbar-link">注册</a></p>
</div>
