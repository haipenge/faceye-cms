<%@ include file="/component/core/taglib/taglib.jsp"%>
<%@ page language="java" import="java.util.*,com.faceye.feature.util.*,com.faceye.feature.util.host.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/css/component/activity/luckerRecord/luckerRecord.css"/>" />
<script type="text/javascript"
	src="<c:url value="/js/component/activity/luckerRecord/luckerRecord.js"/>"></script>
<div class="block-flat">
	<div class="header">
		<h3>
			<c:choose>
				<c:when test="${empty luckerRecord.id}">
					<fmt:message key="activity.luckerRecord.add"></fmt:message>
				</c:when>
				<c:otherwise>
					<fmt:message key="activity.luckerRecord.edit"></fmt:message>
				</c:otherwise>
			</c:choose>
		</h3>
	</div>
	<div class="content">
	<form:form action="/activity/luckerRecord/save" method="post" role="form" cssClass="form-horizontal"
			commandName="luckerRecord">
			<form:hidden path="id"/>
			<fieldset>
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