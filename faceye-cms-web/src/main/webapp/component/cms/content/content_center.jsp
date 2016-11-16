<%@ include file="/component/core/taglib/taglib.jsp"%>
<%@ page language="java" import="java.util.*,com.faceye.component.cms.util.*" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<link rel="stylesheet" type="text/css" href="<c:url value="/css/component/cms/content/content.css"/>" />
<script type="text/javascript" src="<c:url value="/js/component/cms/content/content.js"/>"></script>
<div class="row">
	<div class="page-heaer bg-white">
		<div class="row">
			<div class="col-md-4">
				<h2 style="padding-left: 25px;">My</h2>
			</div>
			<div class="col-md-8"></div>
		</div>
	</div>
	<div class="col-md-12 bg-white">
		<c:forEach items="${page.content}" var="content">
			<div class="row">
				<div class="col-md-3">
					<c:choose>
						<c:when test="${not empty content.imageUrls}">
							<a href="<c:url value="${host}/cms/content/detail/${content.id}.html"/>" class="thumbnail"> <img
								class="media-object" src="<%=HostUtil.getImageServer() %>${content.imageUrls[0]}" alt="${content.name}">
							</a>
						</c:when>
						<c:otherwise>
							<a href="#"> <img class="media-object" src="..." alt="...">
							</a>
						</c:otherwise>
					</c:choose>
				</div>
				<div class="col-md-9">
					<h4 class="media-heading">
						<a href="<c:url value="${host}/cms/content/detail/${content.id}.html"/>">${content.name}</a>
					</h4>
					<c:set var="c" value="${content.contentWithoutImages}"/>
					<%=JspUtil.getSummary(pageContext.getAttribute("c").toString(), 200)%>
				</div>
			</div>
		</c:forEach>
		<!-- 
		<ul class="list-unstyled">
			<c:forEach items="${page.content}" var="content">
				<li>
					<h3>
						<a href="<c:url value="${host}/cms/content/detail/${content.id}.html"/>">${content.name}</a>
					</h3>
					<div class="content">${content.content}</div>
					<div class="content">
						<fmt:message key="cms.content.createDate" />
						:
						<fmt:formatDate value="${content.createDate}" pattern="yyyy-MM-dd HH24:mm" />
					</div>
				</li>
			</c:forEach>
		</ul>
		 -->
		<f:page page="${page}" url="/cms/content/home" params="<%=request.getParameterMap()%>" />
	</div>
</div>
