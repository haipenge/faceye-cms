<%@ include file="/component/core/taglib/taglib.jsp"%>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/css/component/cms/smtp/smtp.css"/>" />
<script type="text/javascript"
	src="<c:url value="/js/component/cms/smtp/smtp.js"/>"></script>
<div class="block-flat">
	<div class="header">
		<h3>
			<fmt:message key="cms.smtp.detail"></fmt:message>
		</h3>
	</div>
	<div class="content">
		<div classs="table-responsive">
			<table class="table table-bordered table-hover">
			 <tr>
	<td><fmt:message key="cms.smtp.smtp"></fmt:message></td>
	<td>${smtp.smtp}</td>
</tr>
<tr>
	<td><fmt:message key="cms.smtp.isSSL"></fmt:message></td>
	<td>${smtp.isSSL}</td>
</tr>
<tr>
	<td><fmt:message key="cms.smtp.port"></fmt:message></td>
	<td>${smtp.port}</td>
</tr>
<!--@generate-entity-jsp-property-detail@-->



			</table>
		</div>
	</div>
</div>
