<%@ include file="/component/core/taglib/taglib.jsp"%>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/css/component/cms/member/member.css"/>" />
<script type="text/javascript"
	src="<c:url value="/js/component/cms/member/member.js"/>"></script>
<div class="page-head">
	<h2>
		<fmt:message key="cms.member.manager"></fmt:message>
		<a class="btn btn-primary" href="<c:url value="/cms/member/input"/>"> <fmt:message
				key="cms.member.add"></fmt:message>
		</a>
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
			<form action="<c:url value="/cms/member/home"/>" method="post" role="form"
				class="form-horizontal">
				<fieldset>
					<div class="form-group">
						
<div class="col-md-1">
	<input type="text" name="EQ|name" value="${searchParams.name}"
		placeholder="<fmt:message key="cms.member.name"></fmt:message>"
		class="form-control input-sm">
</div>

<div class="col-md-1">
	<input type="text" name="EQ|email" value="${searchParams.email}"
		placeholder="<fmt:message key="cms.member.email"></fmt:message>"
		class="form-control input-sm">
</div>
<!--@generate-entity-jsp-query-detail@-->


						<div class="col-md-1">
							<button type="submit" class="btn btn-sm btn-primary">
								<fmt:message key="global.search"></fmt:message>
							</button>
						</div>
					</div>
				</fieldset>
			</form>
		</div>
		<c:import url="/component/cms/member/member_center_list.jsp"/>
	</div>
</div>
