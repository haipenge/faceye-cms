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
		<h4 style="margin-left: 25px;">幸运抽奖</h4>
	</div>
	<div class="container">
		<div class="row">
			<div class="col-md-8">
				
			</div>
			<div class="col-md-4">
				<div class="content">
				  奖品说明
				</div>
				<div class="content">
				  <p><button type="button" class="btn btn-lg btn-success">开始</button></p> 
				  <p><button type="button" class="btn btn-lg btn-danger">停止</button></p>
				</div>
			</div>
		</div>
	</div>
</div>
