<%@ include file="/component/core/taglib/taglib.jsp"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<li><a href="#"><i class="fa fa-file"></i><span>抽奖管理</span></a>
	<ul class="sub-menu">
		<li class="<%=JspUtil.isActive(request, "/activity/luckItem")%>"><a href="<c:url value="/activity/luckItem/home"/>">奖项管理</a></li>
		<li class="<%=JspUtil.isActive(request, "/activity/customer")%>"><a href="<c:url value="/activity/customer/home"/>">客户管理</a></li>
		<li class="<%=JspUtil.isActive(request, "/activity/innerCustomer")%>"><a href="<c:url value="/activity/innerCustomer/home"/>">抽奖设置</a></li>
		<li class="<%=JspUtil.isActive(request, "/activity/luckerRecord")%>"><a href="<c:url value="/activity/luckerRecord/home"/>">中奖记录</a></li>
		
	</ul></li>
