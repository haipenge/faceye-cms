<%@ include file="/component/core/taglib/taglib.jsp"%>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/css/component/cms/workRecord/workRecord.css"/>" />
<script type="text/javascript"
	src="<c:url value="/js/component/cms/workRecord/workRecord.js"/>"></script>
<style>
.content {
	font: 15px;
}
</style>
<div class="block-flat">
	<div class="header">
		<h3>
			<c:choose>
				<c:when test="${empty workRecord.id}">
					<fmt:message key="cms.workRecord.add"></fmt:message>
				</c:when>
				<c:otherwise>
					<fmt:message key="cms.workRecord.edit"></fmt:message>
				</c:otherwise>
			</c:choose>
			<small><fmt:message key="cms.workRecord.tips" /></small>
		</h3>
	</div>
	<div class="content">
		<form:form action="/cms/workRecord/save" method="post" role="form"
			cssClass="form-horizontal" commandName="workRecord">
			<form:hidden path="id" />
			<form:hidden path="status" />
			<fieldset>
				<div class="form-group">
					<label class="col-md-1 control-label" for="content"> <spring:message
							code="cms.workRecord.content" />
					</label>
					<div class="col-md-6">
						<form:textarea path="content" rows="10"
							cssClass="form-control content content-word" />
						<form:errors path="content" cssClass="error" />
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-1 control-label" for="isFinished"> <spring:message
							code="cms.workRecord.isFinished" />
					</label>
					<div class="col-md-11">
						<f:boolean value="true" />
						&nbsp;&nbsp;
						<form:radiobutton path="isFinished" value="true" checked="true" />
						&nbsp;&nbsp;&nbsp;&nbsp;
						<f:boolean value="false" />
						&nbsp;&nbsp;
						<form:radiobutton path="isFinished" value="false" />
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-1 control-label"><fmt:message
							key="cms.project" /></label>
					<div class="col-md-4">
						<form:select path="project.id" cssClass="form-control">
							<form:option value="0" label="--Please Select project" />
							<form:options items="${projects.content}" itemValue="id"
								itemLabel="name" />
						</form:select>
					</div>
					<div class="col-md-7"></div>
				</div>
				<div class="form-group">
					<label class="col-md-1 control-label"><fmt:message
							key="cms.member" /></label>
					<div class="col-md-11" id="select-members">
						<c:if test="${not empty members }">
							<c:forEach items="${members}" var="member">
							     <c:set var="isCheck" value="false"/>
							     <c:if test="${not empty workRecord.members}">
							         <c:forEach items="${workRecord.members}" var="inMember">
							             <c:if test="${inMember.id eq member.id}">
							                 <c:set var="isCheck" value="true"/>
							             </c:if>
							         </c:forEach>
							     </c:if>
							     <input type="checkbox" name="member.id" value="${member.id}" <c:if test="${isCheck eq 'true' }">checked</c:if>>${member.name} &nbsp;&nbsp;
							</c:forEach>
						</c:if>
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-1 control-label" for="finishDate"> <spring:message
							code="cms.workRecord.finishDate" />
					</label>
					<div class="col-md-4 input-group date datetime" data-min-view="2"
						data-date-format="yyyy-mm-dd hh:mm">
						<form:input path="finishDateShow" cssClass="form-control" />
						<form:errors path="finishDateShow" cssClass="error" />
						<span class="input-group-addon btn btn-primary"><span
							class="glyphicon glyphicon-th"></span></span>
					</div>
				</div>

				<!--@generate-entity-jsp-property-update@-->
				<div class="form-group">
					<div class="col-md-offset-1 col-md-11">
						<button type="submit" class="btn btn-primary btn-lg">
							<fmt:message key="global.submit.save"></fmt:message>
						</button>
					</div>
				</div>
			</fieldset>
		</form:form>
	</div>
</div>
