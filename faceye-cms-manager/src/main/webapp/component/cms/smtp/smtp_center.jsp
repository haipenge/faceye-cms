<%@ include file="/component/core/taglib/taglib.jsp"%>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/css/component/cms/smtp/smtp.css"/>" />
<script type="text/javascript"
	src="<c:url value="/js/component/cms/smtp/smtp.js"/>"></script>
<div class="page-head">
	<h2>
		<fmt:message key="cms.smtp.manager"></fmt:message>
		<a class="btn btn-primary" href="<c:url value="/cms/smtp/input"/>"> <fmt:message
				key="cms.smtp.add"></fmt:message>
		</a>
	</h2>
</div>
<div class="cl-mcont">
	<!-- 
	<div class="header">
		<h2>
			<fmt:message key="security.role.manager"></fmt:message>
		</h2>
		<a class="btn btn-default" href="<c:url value="/security/role/input"/>"> <fmt:message key="security.role.add"></fmt:message>
		</a>
	</div>
	 -->
	<div class="block-flat">
		<div class="content">
			<form action="<c:url value="/cms/smtp/home"/>" method="post" role="form"
				class="form-horizontal">
				<fieldset>
					<div class="form-group">
						
<div class="col-md-1">
	<input type="text" name="EQ|smtp" value="${searchParams.smtp}"
		placeholder="<fmt:message key="cms.smtp.smtp"></fmt:message>"
		class="form-control input-sm">
</div>

<div class="col-md-1">
	<input type="text" name="EQ|isSSL" value="${searchParams.isSSL}"
		placeholder="<fmt:message key="cms.smtp.isSSL"></fmt:message>"
		class="form-control input-sm">
</div>

<div class="col-md-1">
	<input type="text" name="EQ|port" value="${searchParams.port}"
		placeholder="<fmt:message key="cms.smtp.port"></fmt:message>"
		class="form-control input-sm">
</div>
<!--@generate-entity-jsp-query-detail@-->



						<div class="col-md-1">
							<button type="submit" class="btn btn-sm btn-primary">
								<fmt:message key="global.search"></fmt:message>
							</button>
						</div>
					</div>
				</fieldset>
			</form>
		</div>
		<div class="content">
		  <div id="msg"></div>
	       <button class="btn btn-primary btn-sm multi-remove"><fmt:message key="global.remove"></fmt:message></button>
			<div classs="table-responsive">
				<table class="table table-bordered">
					<thead>
						<tr>
						   <th><input type="checkbox" name="check-all"></th>
							<th><fmt:message key='cms.smtp.smtp'></fmt:message></th>   
 <th><fmt:message key='cms.smtp.isSSL'></fmt:message></th>   
 <th><fmt:message key='cms.smtp.port'></fmt:message></th>   
 <!--@generate-entity-jsp-property-desc@-->
							<th><fmt:message key="global.edit"></fmt:message></th>
							<th><fmt:message key="global.remove"></fmt:message></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${page.content}" var="smtp">
							<tr>
							   <td><input type="checkbox" name="check-single" value="${smtp.id}"></td>
								<td>${smtp.smtp}</td>   
 <td>${smtp.isSSL}</td>   
 <td>${smtp.port}</td>   
 <!--@generate-entity-jsp-property-value@-->
								<td><a href="<c:url value="/cms/smtp/edit/${smtp.id}"/>">
										<fmt:message key="global.edit"></fmt:message>
								</a></td>
								<td><a href="<c:url value="/cms/smtp/remove/${smtp.id}"/>">
										<fmt:message key="global.remove"></fmt:message>
								</a></td>
							<tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<f:page page="${page}" url="/cms/smtp/home" params="<%=request.getParameterMap()%>" />
		</div>
	</div>
</div>
