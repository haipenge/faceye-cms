<%@ include file="/component/core/taglib/taglib.jsp"%>
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<link rel="stylesheet" type="text/css" href="<c:url value="/css/component/weixin/weixin/weixin.css"/>" />
<script type="text/javascript" src="<c:url value="/js/component/weixin/weixin/weixin.js"/>"></script>
<%
	String domain = JspUtil.getDomain(request);
	request.setAttribute("domain", domain);
%>
<div class="container">
	<div class="header">
		<h3>${content.name}</h3>
	</div>
	<div class="content">
		<p class="text-muted">
			<fmt:formatDate value="${content.createDate }" pattern="yyyy-MM-dd" />
		</p>
		${content.content}
	</div>
	<c:if test="${domain eq 'wx.faceye.net' }">
		<script type="text/javascript">
			/*微信-文章内容插入广告*/
			var cpro_id = "u2738243";
		</script>
		<script src="http://cpro.baidustatic.com/cpro/ui/cm.js" type="text/javascript"></script>
	</c:if>
	<div class="content">
		<c:choose>
			<c:when test="${domain eq 'wx.faceye.net' }">
				<!-- Use ke la ke qrcode -->
				<!-- 
				<img src="<c:url value="/images/qrcode/klk/qrcode_for_gh_83502fd74e75_258.jpg"/>" class="img-thumbnail center-block">
				 -->
				<img src="<c:url value="/images/qrcode/klk/klk-user-define.jpg"/>" class="img-thumbnail center-block">
				<p class="text-center text-muted" style="margin-top: 5px;">
					<fmt:message key="faceye.cms.weixin.scan" />
					<fmt:message key="faceye.cms.weixin.klk" />
				</p>
			</c:when>
			<c:when test="${domain eq 'faceye.cn' }">
				<!-- Use dian ku qrcode -->
				<img src="<c:url value="/images/qrcode/dk/qrcode_for_gh_e1a3fb82d480_258.jpg"/>" class="img-thumbnail center-block">
				<p class="text-center text-muted" style="margin-top: 5px;">
					<fmt:message key="faceye.cms.weixin.scan" />
					<fmt:message key="faceye.cms.weixin.dk" />
				</p>
			</c:when>
			<c:otherwise>
			</c:otherwise>
		</c:choose>
	</div>
</div>
<script type="text/javascript">
	var cpro_id = "u2703042";
</script>
<script type="text/javascript" src="http://cpro.baidustatic.com/cpro/ui/mi.js"></script>
