<%@ include file="/component/core/taglib/taglib.jsp"%>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/css/component/cms/member/member.css"/>" />
<script type="text/javascript"
	src="<c:url value="/js/component/cms/member/member.js"/>"></script>
<div class="block-flat">
	<div class="header">
		<h3>
			<c:choose>
				<c:when test="${empty member.id}">
					<fmt:message key="cms.member.add"></fmt:message>
				</c:when>
				<c:otherwise>
					<fmt:message key="cms.member.edit"></fmt:message>
				</c:otherwise>
			</c:choose>
		</h3>
	</div>
	<div class="content">
		<form:form action="/cms/member/save" method="post" role="form"
			cssClass="form-horizontal" commandName="member">
			<form:hidden path="id" />
			<fieldset>
				<div class="form-group">
					<label class="col-md-2 control-label" for="name"> <spring:message
							code="cms.member.name" />
					</label>
					<div class="col-md-6">
						<form:input path="name" cssClass="form-control" />
						<form:errors path="name" cssClass="error" />
					</div>
				</div>
			</fieldset>
			<fieldset>
				<div class="form-group">
					<label class="col-md-2 control-label" for="email"> <spring:message
							code="cms.member.email" />
					</label>
					<div class="col-md-6">
						<form:input path="email" cssClass="form-control" />
						<form:errors path="email" cssClass="error" />
					</div>
				</div>
			</fieldset>
			<fieldset>
				<div class="form-group">
					<label class="col-md-2 control-label"><fmt:message
							key="cms.team" /></label>
					<div class="col-md-6">
						<c:choose>
							<c:when test="${not empty team}">
							    <input type="hidden" value="${team.id}" name="teamId"/>
							    ${team.name}
							</c:when>
							<c:otherwise>
								<select name="teamId" class="form-control">
									<option value="0">Please Select Team</option>
									<c:forEach var="team" items="${teams.content }">
										<option value="${team.id}">${team.name }</option>
									</c:forEach>
								</select>
							</c:otherwise>
						</c:choose>

					</div>
				</div>
			</fieldset>
			<!--@generate-entity-jsp-property-update@-->
			<fieldset>
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
