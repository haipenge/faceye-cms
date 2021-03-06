<%@ include file="/component/core/taglib/taglib.jsp"%>
<%@ page language="java" import="java.util.*,com.faceye.feature.util.*,com.faceye.feature.util.host.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/css/component/activity/luckItem/luckItem.css"/>" />
<script type="text/javascript"
	src="<c:url value="/js/component/activity/luckItem/luckItem.js"/>"></script>
<div class="block-flat">
	<div class="header">
		<h3>
			<fmt:message key="activity.luckItem.detail"></fmt:message>
		</h3>
	</div>
	<div class="content">
		<div classs="table-responsive">
			<table class="table table-bordered">
			 <tr>
	<td class＝"bg-title-col width-p-20"><fmt:message key="activity.luckItem.name"></fmt:message></td>
	<td>${luckItem.name}</td>
</tr>
<tr>
	<td class＝"bg-title-col width-p-20"><fmt:message key="activity.luckItem.mark"></fmt:message></td>
	<td>${luckItem.mark}</td>
</tr>
<tr>
	<td class＝"bg-title-col width-p-20"><fmt:message key="activity.luckItem.maxCount"></fmt:message></td>
	<td>${luckItem.maxCount}</td>
</tr>
<tr>
	<td class＝"bg-title-col width-p-20"><fmt:message key="activity.luckItem.currentCount"></fmt:message></td>
	<td>${luckItem.currentCount}</td>
</tr>
<!--@generate-entity-jsp-property-detail@-->




			</table>
		</div>
	</div>
</div>