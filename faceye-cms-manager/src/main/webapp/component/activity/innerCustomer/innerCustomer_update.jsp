<%@ include file="/component/core/taglib/taglib.jsp"%>
<%@ page language="java" import="java.util.*,com.faceye.feature.util.*,com.faceye.feature.util.host.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<link rel="stylesheet" type="text/css" href="<c:url value="/css/component/activity/innerCustomer/innerCustomer.css"/>" />
<script type="text/javascript" src="<c:url value="/js/component/activity/innerCustomer/innerCustomer.js"/>"></script>
<div class="block-flat">
	<div class="header">
		<h3>
			<c:choose>
				<c:when test="${empty innerCustomer.id}">
					<fmt:message key="activity.innerCustomer.add"></fmt:message>
				</c:when>
				<c:otherwise>
					<fmt:message key="activity.innerCustomer.edit"></fmt:message>
				</c:otherwise>
			</c:choose>
		</h3>
	</div>
	<div class="content">
		<form:form action="/activity/innerCustomer/save" method="post" role="form" cssClass="form-horizontal" commandName="innerCustomer">
			<form:hidden path="id" />
			<fieldset>
				<div class="form-group">
					<label class="col-md-2 control-label" for="customer"> <spring:message code="activity.innerCustomer.customer" />手机号
					</label>
					<div class="col-md-6">
						<form:input path="customer.phone" cssClass="form-control" />
						<form:errors path="customer.phone" cssClass="error" />
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-2 control-label" for="luckItem"> <spring:message code="activity.innerCustomer.luckItem" />
					</label>
					<div class="col-md-6">
						<form:select path="luckItem.id" items="${luckItems}"cssClass="form-control" itemLabel="name" itemValue="id" />
						<form:errors path="luckItem" cssClass="error" />
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