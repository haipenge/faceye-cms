<%@ include file="/component/core/taglib/taglib.jsp"%>
<%@ page language="java" import="java.util.*,com.faceye.feature.util.*,com.faceye.feature.util.host.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<link rel="stylesheet" type="text/css" href="<c:url value="/css/component/activity/customer/customer.css"/>" />
<script type="text/javascript" src="<c:url value="/js/component/activity/customer/customer.js"/>"></script>
<div class="page-head">
	<h2>
		<fmt:message key="activity.customer.manager"></fmt:message>
		<a class="btn btn-primary" href="<c:url value="/activity/customer/input"/>"> <fmt:message key="activity.customer.add"></fmt:message>
		</a>
		<a class="btn btn-primary" href="<c:url value="/activity/customer/inputs"/>"> 批量添加
		</a>
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
			<form action="<c:url value="/activity/customer/home"/>" method="post" role="form" class="form-horizontal">
				<fieldset>
					<div class="form-group">

						<div class="col-md-1">
							<input type="text" name="EQ|name" value="${searchParams.name}" placeholder="<fmt:message key="activity.customer.name"></fmt:message>" class="form-control input-sm">
						</div>

						<div class="col-md-1">
							<input type="text" name="EQ|phone" value="${searchParams.phone}" placeholder="<fmt:message key="activity.customer.phone"></fmt:message>" class="form-control input-sm">
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
							<th><fmt:message key='activity.customer.name'></fmt:message></th>
							<th><fmt:message key='activity.customer.phone'></fmt:message></th>
						  <th>F</th>
							<!--@generate-entity-jsp-property-desc@-->
							<th><fmt:message key="global.edit"></fmt:message></th>
							<th><fmt:message key="global.remove"></fmt:message></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${page.content}" var="customer">
							<tr>
								<td><input type="checkbox" name="check-single" value="${customer.id}"></td>
								<td>${customer.name}</td>
								<td>${customer.phone}</td>
								<td>${customer.isLucker }</td>
								<!--@generate-entity-jsp-property-value@-->
								<td><a href="<c:url value="/activity/customer/edit/${customer.id}"/>"> <fmt:message key="global.edit"></fmt:message>
								</a></td>
								<td><a href="<c:url value="/activity/customer/remove/${customer.id}"/>"> <fmt:message key="global.remove"></fmt:message>
								</a></td>
							<tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<f:page page="${page}" url="/activity/customer/home" params="<%=request.getParameterMap()%>" />
		</div>
	</div>
</div>