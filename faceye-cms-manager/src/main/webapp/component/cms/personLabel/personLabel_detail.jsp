<%@ include file="/component/core/taglib/taglib.jsp"%>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/css/component/cms/personLabel/personLabel.css"/>" />
<script type="text/javascript"
	src="<c:url value="/js/component/cms/personLabel/personLabel.js"/>"></script>
<div class="block-flat">
	<div class="header">
		<h3>
			<fmt:message key="cms.personLabel.detail"></fmt:message>
		</h3>
	</div>
	<div class="content">
		<div classs="table-responsive">
			<table class="table table-bordered table-hover">
			 <tr>
	<td><fmt:message key="cms.personLabel.label"></fmt:message></td>
	<td>${personLabel.label}</td>
</tr>
<!--@generate-entity-jsp-property-detail@-->

			</table>
		</div>
	</div>
</div>
