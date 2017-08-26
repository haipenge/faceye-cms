<%@ include file="/component/core/taglib/taglib.jsp"%>
<%@ page language="java" import="java.util.*,com.faceye.feature.util.*,com.faceye.feature.util.host.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<link rel="stylesheet" type="text/css" href="<c:url value="/css/component/activity/luckItem/luckItem.css"/>" />
<script type="text/javascript" src="<c:url value="/js/component/activity/luckItem/luckItem.js"/>"></script>
<div class="block-flat">
	<div class="header">
		<h3>
			<c:choose>
				<c:when test="${empty luckItem.id}">
					<fmt:message key="activity.luckItem.add"></fmt:message>
				</c:when>
				<c:otherwise>
					<fmt:message key="activity.luckItem.edit"></fmt:message>
				</c:otherwise>
			</c:choose>
		</h3>
	</div>
	<div class="content">
		<form:form action="/activity/luckItem/save" method="post" role="form" cssClass="form-horizontal" commandName="luckItem">
			<form:hidden path="id" />
			<fieldset>
				<div class="form-group">
					<label class="col-md-2 control-label" for="name"> <spring:message code="activity.luckItem.name" />
					</label>
					<div class="col-md-6">
						<form:input path="name" cssClass="form-control" />
						<form:errors path="name" cssClass="error" />
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-2 control-label" for="mark"> <spring:message code="activity.luckItem.mark" />
					</label>
					<div class="col-md-6">
						<form:input path="mark" cssClass="form-control" />
						<form:errors path="mark" cssClass="error" />
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-2 control-label" for="maxCount"> <spring:message code="activity.luckItem.maxCount" />
					</label>
					<div class="col-md-6">
						<form:input path="maxCount" cssClass="form-control" />
						<form:errors path="maxCount" cssClass="error" />
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-2 control-label" for="currentCount"> <spring:message code="activity.luckItem.currentCount" />
					</label>
					<div class="col-md-6">
						<form:input path="currentCount" cssClass="form-control" />
						<form:errors path="currentCount" cssClass="error" />
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-2 control-label" for="currentCount"> 排序值
					</label>
					<div class="col-md-6">
						<form:input path="orderIndex" cssClass="form-control" />
						<form:errors path="orderIndex" cssClass="error" />
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