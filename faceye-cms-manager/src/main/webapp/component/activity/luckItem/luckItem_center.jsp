<%@ include file="/component/core/taglib/taglib.jsp"%>
<%@ page language="java" import="java.util.*,com.faceye.feature.util.*,com.faceye.feature.util.host.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<link rel="stylesheet" type="text/css" href="<c:url value="/css/component/activity/luckItem/luckItem.css"/>" />
<script type="text/javascript" src="<c:url value="/js/component/activity/luckItem/luckItem.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/component/core/Core.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/component/core/Window.js"/>"></script>
<div class="page-head">
	<h2>
		<fmt:message key="activity.luckItem.manager"></fmt:message>
		<a class="btn btn-primary" href="<c:url value="/activity/luckItem/input"/>"> <fmt:message key="activity.luckItem.add"></fmt:message>
		</a> <a class="btn btn-success" target="_blank" href="<c:url value="/activity/luckItem/startActivity"/>">开始抽奖</a>
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
			<form action="<c:url value="/activity/luckItem/home"/>" method="post" role="form" class="form-horizontal">
				<fieldset>
					<div class="form-group">

						<div class="col-md-1">
							<input type="text" name="EQ|name" value="${searchParams.name}" placeholder="<fmt:message key="activity.luckItem.name"></fmt:message>" class="form-control input-sm">
						</div>

						<div class="col-md-1">
							<input type="text" name="EQ|mark" value="${searchParams.mark}" placeholder="<fmt:message key="activity.luckItem.mark"></fmt:message>" class="form-control input-sm">
						</div>

						<div class="col-md-1">
							<input type="text" name="EQ|maxCount" value="${searchParams.maxCount}" placeholder="<fmt:message key="activity.luckItem.maxCount"></fmt:message>"
								class="form-control input-sm">
						</div>

						<div class="col-md-1">
							<input type="text" name="EQ|currentCount" value="${searchParams.currentCount}" placeholder="<fmt:message key="activity.luckItem.currentCount"></fmt:message>"
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
							<th><fmt:message key='activity.luckItem.name'></fmt:message></th>
							<th><fmt:message key='activity.luckItem.mark'></fmt:message></th>
							<th><fmt:message key='activity.luckItem.maxCount'></fmt:message></th>
							<th><fmt:message key='activity.luckItem.currentCount'></fmt:message></th>
							<th>排序值</th>
							<!--@generate-entity-jsp-property-desc@-->
							<th><fmt:message key="global.edit"></fmt:message></th>
							<th><fmt:message key="global.remove"></fmt:message></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${page.content}" var="luckItem">
							<tr>
								<td><input type="checkbox" name="check-single" value="${luckItem.id}"></td>
								<td>${luckItem.name}</td>
								<td>${luckItem.mark}</td>
								<td>${luckItem.maxCount}</td>
								<td>${luckItem.currentCount}</td>
								<td>${luckItem.orderIndex }</td>
								<!--@generate-entity-jsp-property-value@-->
								<td><a href="<c:url value="/activity/luckItem/edit/${luckItem.id}"/>"> <fmt:message key="global.edit"></fmt:message>
								</a></td>
								<td><a href="<c:url value="/activity/luckItem/remove/${luckItem.id}"/>"> <fmt:message key="global.remove"></fmt:message>
								</a></td>
							<tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<f:page page="${page}" url="/activity/luckItem/home" params="<%=request.getParameterMap()%>" />
		</div>
	</div>
</div>