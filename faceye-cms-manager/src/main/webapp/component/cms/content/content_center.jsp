<%@ include file="/component/core/taglib/taglib.jsp"%>
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<link rel="stylesheet" type="text/css" href="<c:url value="/css/component/cms/content/content.css"/>" />
<script type="text/javascript" src="<c:url value="/js/component/cms/content/content.js"/>"></script>
<div class="page-head">
	<h2>
		<fmt:message key="cms.content.manager"></fmt:message>
		<a class="btn btn-primary" href="<c:url value="/cms/content/input"/>"> <fmt:message key="cms.content.add"></fmt:message>
		</a>
	</h2>
</div>
<div class="cl-mcont">
	<div class="block-flat">
		<c:import url="/component/core/template/msg/msg.jsp" />
		<div class="content">
			<form action="<c:url value="/cms/content/home"/>" method="post" role="form" class="form-horizontal">
				<fieldset>
					<div class="form-group">
						<div class="col-md-4">
							<input type="text" name="like|name" value="${searchParams.name}" placeholder="<fmt:message key="cms.content.name"></fmt:message>" class="form-control input-sm">
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
			<button class="btn btn-primary btn-sm multi-push-2-weixin">
				<fmt:message key="cms.content.push.2.weixin" />
			</button>
			<button class="btn btn-primary btn-sm multi-remove">
				<fmt:message key="global.remove"></fmt:message>
			</button>
		</div>
		<div class="content">
			<c:forEach var="account" items="${accounts}">
				<label class="checkbox-inline"> <input type="checkbox" id="${account.id }" name="weixinAccount" value="${account.id }"> ${account.name}
				</label>
			</c:forEach>
		</div>
		<div classs="table-responsive">
			<table class="table table-bordered">
				<thead>
					<tr>
						<th><input type="checkbox" name="check-all"></th>
						<th><fmt:message key='cms.content.name'></fmt:message></th>
						<th>分类</th>
						<th><fmt:message key='cms.content.clickCount'></fmt:message></th>
						<th><fmt:message key='cms.content.createDate'></fmt:message></th>
						<!--@generate-entity-jsp-property-desc@-->
						<th><fmt:message key="global.view" /></th>
						<th><fmt:message key="global.edit"></fmt:message></th>
						<th><fmt:message key="global.remove"></fmt:message></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${page.content}" var="content">
						<tr id="${content.id}">
							<td><input type="checkbox" name="check-single" value="${content.id}"></td>
							<td>${content.name}</td>
							<td>${content.category.name }</td>
							<td>${content.clickCount}</td>
							<td><fmt:formatDate value="${content.createDate}" pattern="yyyy-MM-dd HH:mm" /></td>
							<!--@generate-entity-jsp-property-value@-->
							<td><a href="<c:url value="/cms/content/detail/${content.id}.html"/>"><fmt:message key="global.view" /></a></td>
							<td><a href="<c:url value="/cms/content/edit/${content.id}"/>"> <fmt:message key="global.edit"></fmt:message>
							</a></td>
							<td><a href="<c:url value="/cms/content/remove/${content.id}"/>"> <fmt:message key="global.remove"></fmt:message>
							</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<f:page page="${page}" url="/cms/content/home" params="<%=request.getParameterMap()%>" />
		</div>
	</div>
</div>
