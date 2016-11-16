<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/component/core/taglib/taglib.jsp"%>
<html>
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
<meta http-equiv="keywords" content="">
<meta http-equiv="description" content="">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" type="text/css"
	href="<c:url value="/js/lib/bootstrap/css/bootstrap.min.css"/>" />
<link rel="stylesheet" type="text/css"
	href="<c:url value="/js/lib/bootstrap/css/bootstrap-theme.min.css"/>" />
<script type="text/javascript"
	src="<c:url value="/js/lib/jquery/jquery-min.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/js/lib/bootstrap/js/bootstrap.min.js"/>"></script>
<%@ include file="/component/core/taglib/taglib.jsp"%>
<style type="text/css">
.bg-red {
	background: red;
}

.bg-gray {
	background: gray;
}
</style>
</head>
<body>
	<div class="container">
		<div class="row">
			<h4>I am first row</h4>
			<div class="col-md-4 bg-red">I am first column</div>
			<div class="col-md-4 bg-gray">I am second.</div>
			<div class="col-md-4">Third</div>
		</div>
		<div class="row">
			<div class="panel panel-success">
				<div class="panel-heading">
					<div class="panel-title">I am a form</div>
				</div>
				<div class="panel-body">
					<form class="form-inline">
					  <fieldset>
						<div class="form-group">
							<input type="text" class="form-control"
								id="exampleInputEmail3" placeholder="please input search keywords">
						</div>
						<button type="submit" class="btn btn-default">Search</button>
						</fieldset>
					</form>
				</div>
			</div>
		</div>
		<div class="row" style="border:1px solid blue;margin-top:-35px;height:200px">I am third</div>
	</div>
</body>
</html>
