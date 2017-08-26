<%@ include file="/component/core/taglib/taglib.jsp"%>
<%@ page language="java"
	import="java.util.*,com.faceye.component.cms.util.*"
	pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/css/component/cms/content/content.css"/>" />
<script type="text/javascript"
	src="<c:url value="/js/component/cms/content/content.js"/>"></script>
<div class="row bg-white">
	<div class="page-header">
		<h4 style="margin-left: 25px;">${content.name}</h4>
	</div>
	<div class="container">
		<div class="row">
			<div class="col-md-8">
				<p>
					<a href="#" class="btn btn-lg btn-success">抽一等奖</a>
				</p>
				<p>
					<a href="#" class="btn btn-lg btn-success">抽二等奖</a>
				</p>
				<p>
					<a href="#" class="btn btn-lg btn-success">抽三等奖</a>
				</p>
			</div>
			<div class="col-md-4">
				<div class="content"></div>
			</div>
		</div>
	</div>
</div>
