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
<script type="text/javascript" src="<c:url value="/js/component/activity/luckItem/luckItem.js"/>"></script>

<style type="text/css">
body {
	
}
</style>
</head>
<body>
	<div class="container-fluid bg-success" style="background: #f0ad4e; height: 150px;">

		<h1 style="margin-left: 25px; color:">幸运大抽奖</h1>
		<p style="margin-left: 50px;">我们就要撞大奖</p>
		<input type="hidden" name="draw_status" value="0"> <input type="hidden" name="luckItemId" value="${luckItem.id}">
	</div>
	<div class="container-fluid">
		<div class="row" style="margin-top: 15px;">
			<div class="col-md-8">
				<div class="panel panel-success">
					<div class="panel-heading">
						<h3 class="panel-title">抽奖</h3>
					</div>
					<div class="panel-body">
						<div class="table-responsive">
							<table class="table" id="lucker-phones">
								<c:forEach items="${customers}" var="customer" varStatus="status">
									<c:if test="${status.index !=0  and status.index mod 4 ==0 && !status.last }">
										</tr>
										<tr style="border: 0;">
									</c:if>
									<c:if test="${status.index==0 }">
										<tr style="border: 0;">
									</c:if>
									<td class="text-center"><b>${customer.phone}</b></td>
									<c:if test="${status.last and status.index mod 4 ne 0 }">
										</tr>
									</c:if>
								</c:forEach>
							</table>
						</div>
					</div>
				</div>

			</div>
			<div class="col-md-4" style="">

				<div class="panel panel-success">
					<div class="panel-heading">
						<h3 class="panel-title">${luckItem.name}</h3>
					</div>
					<div class="panel-body">
						<div class="content">
							${luckItem.mark }<br>
						</div>
						<div class="content" style="margin-top: 15px;">
							<p>
								<b>中奖手机号码</b>:
							</p>
							<c:forEach items="${luckers}" var="lucker">
								<p style="color: red;">
									<b>${lucker.customer.phone }</b>
								</p>
							</c:forEach>
						</div>
					</div>
					</p>
				</div>
				<div class="panel panel-danger" style="padding: 25px;">
					<button type="button" class="btn btn-block btn-success btn-lg start-draw">开始</button>
					<button type="button" class="btn btn-block btn-danger btn-lg stop-draw" style="margin-top: 35px;">停止</button>

					<div class="panel-body text-center">
						<a href="<c:url value="/activity/luckItem/doLucker"/>">回抽奖首页</a>
					</div>
				</div>

			</div>
		</div>
	</div>
	<footer class="footer" style="height: 200px;">
		<div class="container">
			<hr>
			<p class="text-center">-&gt;幸运大抽奖</p>
		</div>
	</footer>

</body>
</html>
