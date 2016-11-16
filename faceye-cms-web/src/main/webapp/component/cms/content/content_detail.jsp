<%@ include file="/component/core/taglib/taglib.jsp"%>
<link rel="stylesheet" type="text/css" href="<c:url value="/css/component/cms/content/content.css"/>" />
<script type="text/javascript" src="<c:url value="/js/component/cms/content/content.js"/>"></script>
<div class="row bg-white">
	<div class="page-header">
		<h4 style="margin-left: 25px;">${content.name}</h4>
	</div>
	<div class="container">
		<div class="content">${content.content}</div>
		<div class="content">
			<p>
				<fmt:message key="cms.content.createDate" />
				:
				<fmt:formatDate value="${content.createDate}" pattern="yyyy-MM-dd HH:mm" />
				&nbsp;&nbsp;
				<fmt:message key="cms.content.clickCount" />
				:${content.clickCount}
			</p>
		</div>
	</div>
</div>
