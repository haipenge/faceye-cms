<%@ include file="/component/core/taglib/taglib.jsp"%>
<script type="text/javascript"
	src="<c:url value="/js/component/cms/member/member.js"/>"></script>
<div class="content">
	<div id="msg"></div>
	<a class="btn btn-primary btn-sm"
		href="<c:url value="/cms/member/input?teamId=${team.id }"/>"> <fmt:message
			key="cms.member.add"></fmt:message>
	</a>
	<button class="btn btn-primary btn-sm multi-remove">
		<fmt:message key="global.remove"></fmt:message>
	</button>
	<div classs="table-responsive">
		<input type="hidden" name="teamId" value="${team.id }">
		<table class="table table-bordered">
			<thead>
				<tr>
					<th><input type="checkbox" name="check-all"></th>
					<th><fmt:message key='cms.member.name'></fmt:message></th>
					<th><fmt:message key='cms.member.email'></fmt:message></th>
					<!--@generate-entity-jsp-property-desc@-->
					<th><fmt:message key="global.edit"></fmt:message></th>
					<th><fmt:message key="global.remove"></fmt:message></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${members.content}" var="member">
					<tr>
						<td><input type="checkbox" name="check-single"
							value="${member.id}"></td>
						<td>${member.name}</td>
						<td>${member.email}</td>
						<!--@generate-entity-jsp-property-value@-->
						<td><a href="<c:url value="/cms/member/edit/${member.id}"/>">
								<fmt:message key="global.edit"></fmt:message>
						</a></td>
						<td><a
							href="<c:url value="/cms/member/remove?memberId=${member.id}&teamId=${team.id }"/>"> <fmt:message
									key="global.remove"></fmt:message>
						</a></td>
					<tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<f:page page="${page}" url="/cms/member/home"
		params="<%=request.getParameterMap()%>" />
</div>
