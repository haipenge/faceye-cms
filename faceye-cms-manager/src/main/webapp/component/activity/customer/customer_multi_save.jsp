<%@ include file="/component/core/taglib/taglib.jsp"%>
<%@ page language="java" import="java.util.*,com.faceye.feature.util.*,com.faceye.feature.util.host.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<link rel="stylesheet" type="text/css" href="<c:url value="/css/component/activity/customer/customer.css"/>" />
<script type="text/javascript" src="<c:url value="/js/component/activity/customer/customer.js"/>"></script>
<div class="block-flat">
	<div class="header">
		<h3>
			<c:choose>
				<c:when test="${empty customer.id}">
					<fmt:message key="activity.customer.add"></fmt:message>
				</c:when>
				<c:otherwise>
					<fmt:message key="activity.customer.edit"></fmt:message>
				</c:otherwise>
			</c:choose>
		</h3>
	</div>
	<div class="content">
		<form:form action="/activity/customer/multiSave" method="post" role="form" cssClass="form-horizontal" commandName="customer">
			<form:hidden path="id" />
			<fieldset>
				<div class="form-group">
					<label class="col-md-2 control-label" for="name"> 客户手机号
					</label>
					<div class="col-md-6">
						<form:textarea path="phones" cssClass="form-control"  rows="20" />
						<form:errors path="phones" cssClass="error" />
						<span class="span-suffix">手机号,每行一个</span>
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