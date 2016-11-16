<%@ include file="/component/core/taglib/taglib.jsp"%>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/css/component/cms/team/team.css"/>" />
<script type="text/javascript"
	src="<c:url value="/js/component/cms/team/team.js"/>"></script>
<div class="block-flat">
	<div class="header">
		<h3>
			<fmt:message key="cms.team.detail"></fmt:message>
		</h3>
	</div>
	<div class="content">
		<div classs="table-responsive">
			<table class="table table-bordered table-hover">
				<tr>
					<td><fmt:message key="cms.team.name"></fmt:message></td>
					<td>${team.name}</td>
				</tr>
				<tr>
					<td><fmt:message key="cms.team.remark"></fmt:message></td>
					<td>${team.remark}</td>
				</tr>
				<!--@generate-entity-jsp-property-detail@-->
			</table>
		</div>
	</div>
	<c:import url="/component/cms/member/member_center_list.jsp"/>
</div>
