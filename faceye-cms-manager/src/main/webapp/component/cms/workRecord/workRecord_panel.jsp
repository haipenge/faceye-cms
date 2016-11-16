<%@ include file="/component/core/taglib/taglib.jsp"%>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/css/component/cms/workRecord/workRecord.css"/>" />
<link rel="stylesheet" type="text/css"
	href="<c:url value="/js/lib/jquery-ui/jquery-ui.min.css"/>" />
<link rel="stylesheet" type="text/css"
	href="<c:url value="/js/lib/jquery-ui/jquery-ui.theme.min.css"/>" />
<link rel="stylesheet" type="text/css"
	href="<c:url value="/js/lib/jquery-ui/jquery-ui.structure.min.css"/>" />
<script type="text/javascript"
	src="<c:url value="/js/component/cms/workRecord/workRecord.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/js/lib/jquery-ui/jquery-ui.min.js"/>"></script>
<div class="page-head">
	<div class="row">
		<small><fmt:message key="cms.workRecord.tip" /></small>
	</div>
	<div class="row">
		<form action="<c:url value="/cms/workRecord/panel"/>" method="post"
			role="form" class="form-horizontal">
			<fieldset>
				<div class="form-group">
					<div class="col-md-2">
						<input type="text" name="like|content"
							value="${searchParams.content}"
							placeholder="<fmt:message key="cms.workRecord.content"></fmt:message>"
							class="form-control input-sm">
					</div>
					<div class="col-md-2">
						<select name="EQ|project.$id" class="form-control input-sm">
							<option value=""><fmt:message key="cms.project"></fmt:message></option>
							<c:forEach items="${projects.content}" var="project">
								<option value="${project.id}">${project.name }</option>
							</c:forEach>
						</select>
					</div>
					<div class="col-md-3">
						<label class="radio-inline"> <fmt:message
								key="cms.workRecord.isFinished" />&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio"
							name="boolean|isFinished" value="true" checked="true"> <f:boolean
								value="true" />
						</label> <label class="radio-inline"> &nbsp;&nbsp; <input
							type="radio" name="boolean|isFinished" value="false"> <f:boolean
								value="false" />
						</label>
					</div>
					<div class="col-md-1">
						<input type="text" name="EQ|finishDate"
							value="${searchParams.finishDate}"
							placeholder="<fmt:message key="cms.workRecord.finishDate"></fmt:message>"
							class="form-control input-sm">
					</div>
					<!--@generate-entity-jsp-query-detail@-->
					<div class="col-md-2">
						<button type="submit" class="btn btn-sm btn-primary" name="submit"
							value="query">
							<fmt:message key="global.search"></fmt:message>
						</button>
						<button type="submit" class="btn btn-sm btn-success export"
							name="submit" value="export">Export</button>
					</div>
				</div>
			</fieldset>
		</form>
	</div>
</div>

<div class="cl-mcont height-100">
	<div class="block-flat height-100">
		<div class="row height-100" id="work-panel">
			<div class="col-md-4 todo-col work-list height-100" data-col="todo">
				<h5>
					<fmt:message key="cms.workRecord.status.todo" />
					<small><a class="pull-right"
						style="margin-right: 25px; font-size: 28px; font-weight: bold; color: blue;"
						href="<c:url value="/cms/workRecord/input"/>">+</a></small>
				</h5>
				<c:forEach items="${todos.content}" var="workRecord">
					<div class="panel panel-default task-panel" id="${workRecord.id}">
						<div class="panel-body">${workRecord.showContent}
							<div class="task-tool-bar">
								<a href="#"
									onclick="WorkRecord.resetStatus('${workRecord.id}','1');return false;"><fmt:message
										key="cms.workRecord.status.doing" /></a>
							</div>
							<div class="text-right">
								<fmt:formatDate value="${workRecord.createDate}"
									pattern="yyyy-MM-dd HH:mm" />
							</div>
						</div>

					</div>
				</c:forEach>
			</div>
			<div class="col-md-4 doing-col work-list height-100" data-col="doing">
				<h5>
					<fmt:message key="cms.workRecord.status.doing" />
				</h5>
				<c:forEach items="${doings.content}" var="workRecord">
					<div class="panel panel-default task-panel" id="${workRecord.id}">
						<div class="panel-body">${workRecord.showContent}
							<div class="task-tool-bar">
								<a href="#"
									onclick="WorkRecord.resetStatus('${workRecord.id}','2');return false;"><fmt:message
										key="cms.workRecord.status.done" /></a>
							</div>
							<div class="text-right">
								<fmt:formatDate value="${workRecord.createDate}"
									pattern="yyyy-MM-dd HH:mm" />
							</div>
						</div>
					</div>
				</c:forEach>
			</div>
			<div class="col-md-4 done-col work-list height-100" data-col="done">
				<h5>
					<fmt:message key="cms.workRecord.status.done" />
				</h5>
				<c:forEach items="${dones.content}" var="workRecord">
					<div class="panel panel-default task-panel" id="${workRecord.id}">
						<div class="panel-body">${workRecord.showContent}
							<div class="task-tool-bar">
								<a href="#"
									onclick="WorkRecord.resetStatus('${workRecord.id}','0');return false;"><fmt:message
										key="cms.workRecord.status.redo" /></a>
							</div>
							<div class="text-right">
								<fmt:formatDate value="${workRecord.finishDate}"
									pattern="yyyy-MM-dd HH:mm" />
							</div>
						</div>
						
					</div>
				</c:forEach>
			</div>
		</div>
	</div>
</div>

