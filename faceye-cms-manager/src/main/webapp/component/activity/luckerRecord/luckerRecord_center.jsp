<%@ include file="/component/core/taglib/taglib.jsp"%>
<%@ page language="java" import="java.util.*,com.faceye.feature.util.*,com.faceye.feature.util.host.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<link rel="stylesheet" type="text/css" href="<c:url value="/css/component/activity/luckerRecord/luckerRecord.css"/>" />
<script type="text/javascript" src="<c:url value="/js/component/activity/luckerRecord/luckerRecord.js"/>"></script>
<div class="page-head">
	<h2>
		<fmt:message key="activity.luckerRecord.manager"></fmt:message>
		
	</h2>
</div>
<div class="cl-mcont">
	<!-- 
	<div class="header">
		<h2>
			<fmt:message key="security.role.manager"></fmt:message>
		</h2>
		<a class="btn btn-default" href="<c:url value="/security/role/input"/>"> <fmt:message key="security.role.add"></fmt:message>
		</a>
	</div>
	 -->
	<div class="block-flat">
		<div class="content">
			<form action="<c:url value="/activity/luckerRecord/home"/>" method="post" role="form" class="form-horizontal">
				<fieldset>
					<div class="form-group">

						<div class="col-md-1">
							<input type="text" name="EQ|customer" value="${searchParams.customer}" placeholder="<fmt:message key="activity.luckerRecord.customer"></fmt:message>"
								class="form-control input-sm">
						</div>

						<div class="col-md-1">
							<input type="text" name="EQ|luckItem" value="${searchParams.luckItem}" placeholder="<fmt:message key="activity.luckerRecord.luckItem"></fmt:message>"
								class="form-control input-sm">
						</div>
						<!--@generate-entity-jsp-query-detail@-->
						<div class="col-md-1">
							<button type="submit" class="btn btn-sm btn-primary">
								<fmt:message key="global.search"></fmt:message>
							</button>
						</div>
					</div>
				</fieldset>
			</form>
		</div>
		<div class="content">
			<div id="msg"></div>
			<button class="btn btn-primary btn-sm multi-remove">
				<fmt:message key="global.remove"></fmt:message>
			</button>
			<div classs="table-responsive">
				<table class="table table-bordered">
					<thead>
						<tr>
							<th><input type="checkbox" name="check-all"></th>
							<th><fmt:message key='activity.luckerRecord.customer'></fmt:message></th>
							<th><fmt:message key='activity.luckerRecord.luckItem'></fmt:message></th>
							<!--@generate-entity-jsp-property-desc@-->
							<th><fmt:message key="global.edit"></fmt:message></th>
							<th><fmt:message key="global.remove"></fmt:message></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${page.content}" var="luckerRecord">
							<tr>
								<td><input type="checkbox" name="check-single" value="${luckerRecord.id}"></td>
								<td>${luckerRecord.customer.phone}</td>
								<td>${luckerRecord.luckItem.name}</td>
								<!--@generate-entity-jsp-property-value@-->
								<td><a href="<c:url value="/activity/luckerRecord/edit/${luckerRecord.id}"/>"> <fmt:message key="global.edit"></fmt:message>
								</a></td>
								<td><a href="<c:url value="/activity/luckerRecord/remove/${luckerRecord.id}"/>"> <fmt:message key="global.remove"></fmt:message>
								</a></td>
							<tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<f:page page="${page}" url="/activity/luckerRecord/home" params="<%=request.getParameterMap()%>" />
		</div>
	</div>
</div>