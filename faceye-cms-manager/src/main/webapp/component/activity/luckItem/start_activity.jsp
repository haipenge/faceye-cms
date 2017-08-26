<%@ include file="/component/core/taglib/taglib.jsp"%>
<%@ page language="java" import="java.util.*,com.faceye.feature.util.*,com.faceye.feature.util.host.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<html>
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
<meta http-equiv="keywords" content="">
<meta http-equiv="description" content="">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" type="text/css" href="<c:url value="/js/lib/bootstrap/css/bootstrap.min.css"/>" />
<link rel="stylesheet" type="text/css" href="<c:url value="/js/lib/bootstrap/css/bootstrap-theme.min.css"/>" />
<script type="text/javascript" src="<c:url value="/js/lib/jquery/jquery-min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/lib/bootstrap/js/bootstrap.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/component/core/Core.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/component/core/Window.js"/>"></script>
<style type="text/css">
body {
	
}
</style>
</head>
<body>
	<div class="container-fluid bg-success" style="background: #f0ad4e; height: 150px;">

		<h1 style="margin-left: 25px; color:">幸运大抽奖</h1>
		<p style="margin-left: 50px;">我们就要撞大奖</p>

	</div>
	<div class="container-fluid">
		<div class="row" style="margin-top: 15px;">
			<div class="col-md-8" style="height: 60%;">
				<div class="panel panel-success">
					<div class="panel-heading">
						<h3 class="panel-title">抽奖</h3>
					</div>
					<div class="panel-body">
						<c:forEach items="${page.content}" var="luckItem">
							<p class="text-center">
								<a href="<c:url value="/activity/luckItem/doLucker?luckItemId=${luckItem.id}"/>" class="btn btn-lg btn-block btn-success">抽${luckItem.name}</a>
							</p>
						</c:forEach>
					</div>
				</div>
				<div class="panel panel-danger">
					<div class="panel-body">
						 
						<p>
							<b>活动说明:</b>
						</p>
						<p>本次活动解释权归活动承办方所有。</p>
					</div>
				</div>
			</div>
			<div class="col-md-4" style="">
				<c:forEach items="${page.content}" var="luckItem">
					<div class="panel panel-success">
						<div class="panel-heading">
							<h3 class="panel-title">${luckItem.name}</h3>
						</div>
						<div class="panel-body">

							${luckItem.mark }<br> 共${luckItem.maxCount}名,已抽出:${luckItem.currentCount }
						</div>
						</p>
					</div>
				</c:forEach>
			</div>
		</div>
	</div>
	<footer class="footer" style="height:200px;">
		<div class="container">
		  <hr>
		  <p class="text-center">-&gt;幸运大抽奖</p>
		</div>
	</footer>

</body>
</html>
