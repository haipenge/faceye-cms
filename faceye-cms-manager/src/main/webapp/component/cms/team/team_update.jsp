<%@ include file="/component/core/taglib/taglib.jsp"%>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/css/component/cms/team/team.css"/>" />
<script type="text/javascript"
	src="<c:url value="/js/component/cms/team/team.js"/>"></script>
<div class="block-flat">
	<div class="header">
		<h3>
			<c:choose>
				<c:when test="${empty team.id}">
					<fmt:message key="cms.team.add"></fmt:message>
				</c:when>
				<c:otherwise>
					<fmt:message key="cms.team.edit"></fmt:message>
				</c:otherwise>
			</c:choose>
		</h3>
	</div>
	<div class="content">
	<form:form action="/cms/team/save" method="post" role="form" cssClass="form-horizontal"
			commandName="team">
			<form:hidden path="id"/>
			<fieldset>
				<div class="form-group">
	<label class="col-md-2 control-label" for="name"> <spring:message
			code="cms.team.name"/>
	</label>
	<div class="col-md-6">
		<form:input path="name" cssClass="form-control"/>
		<form:errors path="name" cssClass="error"/>
	</div>
</div>
<div class="form-group">
	<label class="col-md-2 control-label" for="remark"> <spring:message
			code="cms.team.remark"/>
	</label>
	<div class="col-md-6">
		<form:input path="remark" cssClass="form-control"/>
		<form:errors path="remark" cssClass="error"/>
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
