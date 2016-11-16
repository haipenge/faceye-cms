<%@ include file="/component/core/taglib/taglib.jsp"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<li><a href="#"><i class="fa fa-file"></i><span>内容管理</span></a>
	<ul class="sub-menu">
		<li class="<%=JspUtil.isActive(request, "/cms/content")%>"><a href="<c:url value="/cms/content/home"/>"><fmt:message key="cms.content.manager" /></a></li>
		<li class="<%=JspUtil.isActive(request, "/cms/workRecord")%>"><a href="<c:url value="/cms/workRecord/home"/>"><fmt:message key="cms.workRecord.manager"></fmt:message></a></li>
		<li class="<%=JspUtil.isActive(request, "/cms/team")%>"><a href="<c:url value="/cms/team/home"/>"><fmt:message key="cms.team.manager"></fmt:message></a></li>
		<li class="<%=JspUtil.isActive(request, "/cms/member")%>"><a href="<c:url value="/cms/member/home"/>"><fmt:message key="cms.member.manager"></fmt:message></a></li>
		<li class="<%=JspUtil.isActive(request, "/cms/project")%>"><a href="<c:url value="/cms/project/home"/>"><fmt:message key="cms.project.manager"></fmt:message></a></li>
		<li class="<%=JspUtil.isActive(request, "/cms/smtp")%>"><a href="<c:url value="/cms/smtp/home"/>"><fmt:message key="cms.smtp.manager"></fmt:message></a></li>
		<li  class="<%=JspUtil.isActive(request, "personLabel")%>"><a  href="/cms/personLabel/home"><fmt:message key="cms.personLabel.manager"></fmt:message></a></li>
	</ul></li>
