<%@ include file="/component/core/taglib/taglib.jsp"%>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/css/component/cms/personLabel/personLabel.css"/>" />
<script type="text/javascript"
	src="<c:url value="/js/component/cms/personLabel/personLabel.js"/>"></script>
<div class="block-flat">
	<div class="header">
		<h3>
			<c:choose>
				<c:when test="${empty personLabel.id}">
					<fmt:message key="cms.personLabel.add"></fmt:message>
				</c:when>
				<c:otherwise>
					<fmt:message key="cms.personLabel.edit"></fmt:message>
				</c:otherwise>
			</c:choose>
		</h3>
	</div>
	<div class="content">
	<form:form action="/cms/personLabel/save" method="post" role="form" cssClass="form-horizontal"
			commandName="personLabel">
			<form:hidden path="id"/>
			<fieldset>
				<div class="form-group">
	<label class="col-md-2 control-label" for="label"> <spring:message
			code="cms.personLabel.label"/>
	</label>
	<div class="col-md-6">
		<form:input path="label" cssClass="form-control"/>
		<form:errors path="label" cssClass="error"/>
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
