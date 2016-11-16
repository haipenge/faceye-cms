<%@ include file="/component/core/taglib/taglib.jsp"%>
<link rel="stylesheet" type="text/css" href="<c:url value="/css/component/cms/content/content.css"/>" />
<script type="text/javascript" src="<c:url value="/js/component/cms/content/content.js"/>"></script>
<div class="block-flat">
	<h3>${content.name}</h3>
	<div class="content">
		<p>
			<fmt:message key="cms.content.createDate" />
			:
			<fmt:formatDate value="${content.createDate }" pattern="yyyy-MM-dd HH24:mm" />
		</p>
		<p>
			<fmt:message key="cms.content.keywords"></fmt:message>
			:${content.keywords}
		</p>
		<p>
			<fmt:message key="cms.content.description"></fmt:message>
			:${content.description}
		</p>
		<p>
	</div>
	<div class="content">${content.content}</div>
	<h3>Images:</h3>
	<div class="content">
		<dl class="dl-horizontal">
			<c:forEach items="${images.content}" var="image">
				<dt id="${image.id}-dt">
					<img src="${image.url}" alt="..." class="img-thumbnail">
				</dt>
				<dd id="${image.id}-dd">
					<a href="#" onclick="SearchArticle.removeImage(${image.id});return false;"><fmt:message key="global.remove" /></a>
				</dd>
			</c:forEach>
		</dl>
	</div>
	<div class="content">
		<a href="<c:url value="/cms/content/edit/${content.id}"/>" class="btn btn-sm btn-primary"><fmt:message
				key="global.edit" /></a> <a href="<c:url value="/cms/content/remove/${content.id}"/>" class="btn btn-sm btn-danger"><fmt:message
				key="global.remove" /></a> <a href="<c:url value="/cms/content/home"/>" class="btn btn-sm btn-info"><fmt:message
				key="global.back" /></a>
	</div>
</div>
