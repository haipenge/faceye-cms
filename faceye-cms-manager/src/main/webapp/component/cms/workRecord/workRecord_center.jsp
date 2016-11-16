<%@ include file="/component/core/taglib/taglib.jsp"%>
<link rel="stylesheet" type="text/css" href="<c:url value="/css/component/cms/workRecord/workRecord.css"/>" />
<script type="text/javascript" src="<c:url value="/js/component/cms/workRecord/workRecord.js"/>"></script>
<div class="page-head">
	<h2>
		<fmt:message key="cms.workRecord.manager"></fmt:message>
		<a class="btn btn-primary" href="<c:url value="/cms/workRecord/input"/>"> <fmt:message key="cms.workRecord.add"></fmt:message>
		</a>
		&nbsp;&nbsp;&nbsp;&nbsp;<small><fmt:message key="cms.workRecord.tip"/></small>
		<small><a href="<c:url value="/cms/workRecord/panel"/>"><fmt:message key="cms.workRecord.panel"/></a></small>
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
			<form action="<c:url value="/cms/workRecord/home"/>" method="post" role="form" class="form-horizontal">
				<fieldset>
					<div class="form-group">
						<div class="col-md-3">
							<input type="text" name="like|content" value="${searchParams.content}"
								placeholder="<fmt:message key="cms.workRecord.content"></fmt:message>" class="form-control input-sm">
						</div>
						<div class="col-md-3">
							<label class="radio-inline"> <fmt:message key="cms.workRecord.isFinished" />&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" name="boolean|isFinished" value="true" checked="true">
								<f:boolean value="true" />
							</label> <label class="radio-inline"> &nbsp;&nbsp; <input type="radio" name="boolean|isFinished" value="false">
							<f:boolean value="false" />
							</label>
						</div>
						<div class="col-md-1">
							<input type="text" name="EQ|finishDate" value="${searchParams.finishDate}"
								placeholder="<fmt:message key="cms.workRecord.finishDate"></fmt:message>" class="form-control input-sm">
						</div>
						<!--@generate-entity-jsp-query-detail@-->
						<div class="col-md-2">
							<button type="submit" class="btn btn-sm btn-primary" name="submit" value="query">
								<fmt:message key="global.search"></fmt:message>
							</button>
							<button type="submit" class="btn btn-sm btn-success export" name="submit" value="export">
							  Export
							</button>
						</div>
					</div>
				</fieldset>
			</form>
		</div>
		<div class="content">
			<div id="msg"></div>
			<button class="btn btn-primary btn-sm multi-remove">
				<fmt:message key="global.remove"></fmt:message>
			</button>
			<div classs="table-responsive">
				<table class="table table-bordered">
					<thead>
						<tr>
							<th colspan="2"><input type="checkbox" name="check-all"></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${page.content}" var="workRecord">
							<tr id="${workRecord.id }">
								<td><input type="checkbox" name="check-single" value="${workRecord.id}"></td>
								<td><p>${workRecord.showContent}</p>
									<p style="color:gray;site:9px;">
										<fmt:message key='cms.workRecord.isFinished' />
										:
										<f:boolean value="${workRecord.isFinished}" /> &nbsp;&nbsp;&nbsp;&nbsp;
										<fmt:message key='cms.workRecord.finishDate'/>:${workRecord.finishDateShow}&nbsp;&nbsp;&nbsp;&nbsp; <a
												href="<c:url value="/cms/workRecord/edit/${workRecord.id}"/>"> <fmt:message key="global.edit"></fmt:message>
											</a>
										<a href="<c:url value="/cms/workRecord/detail/${workRecord.id}.html"/>"><fmt:message key="cms.workRecord.detail"/></a>
										<c:if test="${!workRecord.isFinished}">
										   <a href="#" class="btn btn-sm btn-primary work-done"><fmt:message key="cms.workRecord.done"/></a>
										</c:if>
									</p></td>
							<tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<f:page page="${page}" url="/cms/workRecord/home" params="<%=request.getParameterMap()%>" />
		</div>
	</div>
</div>
