<%@ include file="/component/core/taglib/taglib.jsp"%>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/css/component/cms/team/team.css"/>" />
<script type="text/javascript"
	src="<c:url value="/js/component/cms/team/team.js"/>"></script>
<div class="page-head">
	<h2>
		<fmt:message key="cms.team.manager"></fmt:message>
		<a class="btn btn-primary" href="<c:url value="/cms/team/input"/>">
			<fmt:message key="cms.team.add"></fmt:message>
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
			<form action="<c:url value="/cms/team/home"/>" method="post"
				role="form" class="form-horizontal">
				<fieldset>
					<div class="form-group">

						<div class="col-md-1">
							<input type="text" name="EQ|name" value="${searchParams.name}"
								placeholder="<fmt:message key="cms.team.name"></fmt:message>"
								class="form-control input-sm">
						</div>

						<div class="col-md-1">
							<input type="text" name="EQ|remark"
								value="${searchParams.remark}"
								placeholder="<fmt:message key="cms.team.remark"></fmt:message>"
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
							<th><fmt:message key='cms.team.name'></fmt:message></th>
							<th><fmt:message key='cms.team.remark'></fmt:message></th>
							<!--@generate-entity-jsp-property-desc@-->
							<th><fmt:message key="cms.member"/></th>
							<th><fmt:message key="global.edit"></fmt:message></th>
							<th><fmt:message key="global.remove"></fmt:message></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${page.content}" var="team">
							<tr>
								<td><input type="checkbox" name="check-single"
									value="${team.id}"></td>
								<td><a href="<c:url value="/cms/team/detail/${team.id}"/>">${team.name}</a></td>
								<td>${team.remark}</td>
								<!--@generate-entity-jsp-property-value@-->
								<td><a href="<c:url value="/cms/member/input?teamId=${team.id }"/>"><fmt:message key="cms.member"/></a></td>
								<td><a href="<c:url value="/cms/team/edit/${team.id}"/>">
										<fmt:message key="global.edit"></fmt:message>
								</a></td>
								<td><a href="<c:url value="/cms/team/remove/${team.id}"/>">
										<fmt:message key="global.remove"></fmt:message>
								</a></td>
							<tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<f:page page="${page}" url="/cms/team/home"
				params="<%=request.getParameterMap()%>" />
		</div>
	</div>
</div>
