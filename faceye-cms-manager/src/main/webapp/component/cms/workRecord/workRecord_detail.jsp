<%@ include file="/component/core/taglib/taglib.jsp"%>
<link rel="stylesheet" type="text/css" href="<c:url value="/css/component/cms/workRecord/workRecord.css"/>" />
<script type="text/javascript" src="<c:url value="/js/component/cms/workRecord/workRecord.js"/>"></script>
<div class="block-flat">
	<div class="header">
		<h3>
			<fmt:message key="cms.workRecord.detail"></fmt:message>
		</h3>
	</div>
	<div class="content">${workRecord.showContent}</div>
	<div class="content">
		<p>
			<fmt:message key="cms.workRecord.isFinished"></fmt:message>
			:
			<f:boolean value="${workRecord.isFinished}" />
			&nbsp;&nbsp;
			<fmt:message key="cms.workRecord.finishDate"></fmt:message>
			:
			<fmt:formatDate value="${workRecord.finishDate}" pattern="yyyy-MM-dd HH:mm" />
		</p>
	</div>
	<div class="content">
		<!--@generate-entity-jsp-property-detail@-->
	</div>
	<div class="content">
		<a href="<c:url value="/cms/workRecord/home"/>" class="btn btn-primary">Cms Home</a> <a
			href="<c:url value="/cms/workRecord/edit/${workRecord.id}"/>" class="btn btn-success">Edit</a>
	</div>
</div>
