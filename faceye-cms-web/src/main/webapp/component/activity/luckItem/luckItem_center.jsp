<%@ include file="/component/core/taglib/taglib.jsp"%>
<%@ page language="java" import="java.util.*,com.faceye.feature.util.*,com.faceye.feature.util.host.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<link rel="stylesheet" type="text/css" href="<c:url value="/css/component/activity/luckItem/luckItem.css"/>" />
<script type="text/javascript" src="<c:url value="/js/component/activity/luckItem/luckItem.js"/>"></script>

<div class="row bg-white">
	<div class="page-header">
		<h4 style="margin-left: 25px;">幸运抽奖</h4>
	</div>
	<div class="container">
		<div class="row">
			<div class="col-md-8">
				<c:forEach items="${page.content}" var="luckItem">
					<a href="#" class="btn btn-larger btn-success">抽${luckItem.name}</a>
				</c:forEach>
			</div>
			<div class="col-md-4">
				<c:forEach items="${page.content}" var="luckItem">
					<p>
						<b>${luckItem.name}:</b>${luckItem.mark }</p>
				</c:forEach>
			</div>
		</div>
	</div>
</div>
