<%@ include file="/component/core/taglib/taglib.jsp"%>
<%@ page language="java" import="java.util.*,com.faceye.feature.util.*,com.faceye.feature.util.host.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/css/component/activity/luckerRecord/luckerRecord.css"/>" />
<script type="text/javascript"
	src="<c:url value="/js/component/activity/luckerRecord/luckerRecord.js"/>"></script>
<div class="block-flat">
	<div class="header">
		<h3>
			<fmt:message key="activity.luckerRecord.detail"></fmt:message>
		</h3>
	</div>
	<div class="content">
		<div classs="table-responsive">
			<table class="table table-bordered">
			 <tr>
	<td class＝"bg-title-col width-p-20"><fmt:message key="activity.luckerRecord.customer"></fmt:message></td>
	<td>${luckerRecord.customer}</td>
</tr>
<tr>
	<td class＝"bg-title-col width-p-20"><fmt:message key="activity.luckerRecord.luckItem"></fmt:message></td>
	<td>${luckerRecord.luckItem}</td>
</tr>
<!--@generate-entity-jsp-property-detail@-->


			</table>
		</div>
	</div>
</div>