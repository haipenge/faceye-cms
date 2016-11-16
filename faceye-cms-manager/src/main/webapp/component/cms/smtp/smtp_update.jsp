<%@ include file="/component/core/taglib/taglib.jsp"%>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/css/component/cms/smtp/smtp.css"/>" />
<script type="text/javascript"
	src="<c:url value="/js/component/cms/smtp/smtp.js"/>"></script>
<div class="block-flat">
	<div class="header">
		<h3>
			<c:choose>
				<c:when test="${empty smtp.id}">
					<fmt:message key="cms.smtp.add"></fmt:message>
				</c:when>
				<c:otherwise>
					<fmt:message key="cms.smtp.edit"></fmt:message>
				</c:otherwise>
			</c:choose>
		</h3>
	</div>
	<div class="content">
	<form:form action="/cms/smtp/save" method="post" role="form" cssClass="form-horizontal"
			commandName="smtp">
			<form:hidden path="id"/>
			<fieldset>
				<div class="form-group">
	<label class="col-md-2 control-label" for="smtp"> <spring:message
			code="cms.smtp.smtp"/>
	</label>
	<div class="col-md-6">
		<form:input path="smtp" cssClass="form-control"/>
		<form:errors path="smtp" cssClass="error"/>
	</div>
</div>
<div class="form-group">
	<label class="col-md-2 control-label" for="isSSL"> <spring:message
			code="cms.smtp.isSSL"/>
	</label>
	<div class="col-md-6">
		<form:input path="isSSL" cssClass="form-control"/>
		<form:errors path="isSSL" cssClass="error"/>
	</div>
</div>
<div class="form-group">
	<label class="col-md-2 control-label" for="port"> <spring:message
			code="cms.smtp.port"/>
	</label>
	<div class="col-md-6">
		<form:input path="port" cssClass="form-control"/>
		<form:errors path="port" cssClass="error"/>
	</div>
</div>
<!--@generate-entity-jsp-property-update@-->



				<div class="form-group">
					<div class="col-md-offset-2 col-md-10">
						<button type="submit" class="btn btn-primary">
							<fmt:message key="global.submit.save"></fmt:message>
						</button>
					</div>
				</div>
			</fieldset>
		</form:form>
	</div>
</div>
