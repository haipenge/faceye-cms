<%@ include file="/component/core/taglib/taglib.jsp"%>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/css/component/cms/member/member.css"/>" />
<script type="text/javascript"
	src="<c:url value="/js/component/cms/member/member.js"/>"></script>
<div class="block-flat">
	<div class="header">
		<h3>
			<fmt:message key="cms.member.detail"></fmt:message>
		</h3>
	</div>
	<div class="content">
		<div classs="table-responsive">
			<table class="table table-bordered table-hover">
			 <tr>
	<td><fmt:message key="cms.member.name"></fmt:message></td>
	<td>${member.name}</td>
</tr>
<tr>
	<td><fmt:message key="cms.member.email"></fmt:message></td>
	<td>${member.email}</td>
</tr>
<!--@generate-entity-jsp-property-detail@-->


			</table>
		</div>
	</div>
</div>
