<%@ include file="/component/core/taglib/taglib.jsp"%>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/css/component/cms/project/project.css"/>" />
<script type="text/javascript"
	src="<c:url value="/js/component/cms/project/project.js"/>"></script>
<div class="block-flat">
	<div class="header">
		<h3>
			<fmt:message key="cms.project.detail"></fmt:message>
		</h3>
	</div>
	<div class="content">
		<div classs="table-responsive">
			<table class="table table-bordered table-hover">
			 <tr>
	<td><fmt:message key="cms.project.name"></fmt:message></td>
	<td>${project.name}</td>
</tr>
<!--@generate-entity-jsp-property-detail@-->

			</table>
		</div>
	</div>
</div>
