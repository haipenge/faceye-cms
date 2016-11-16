<%@ include file="/component/core/taglib/taglib.jsp"%>
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<%
	String domain = JspUtil.getDomain(request);
	request.setAttribute("domain", domain);
%>
<div class="container" id="g-container">
   <div id="msg" style="display:hide;"></div>
	<div class="header">
		<h3>生成星座标签</h3>
	</div>
	<form class="form-horizontal" action="/weixinApp/onPersonalXingzuoSignSubmit" id="form-container">
		<div class="form-group">
			<label for="name" class="col-sm-2 col-xs-2 control-label">我是</label>
			<div class="col-sm-10 col-xs-10">
				<input type="text" name="name" class="form-control" id="name" placeholder="小G哥">
			</div>
		</div>
		<div class="form-group">
			<label for="name" class="col-sm-2 col-xs-2 control-label">性别</label>
			<div class="col-sm-10 col-xs-10">
				<label class="radio-inline"> <input type="radio" name="sex" id="sex1" value="male"> 帅哥
				</label> <label class="radio-inline"> <input type="radio" name="sex" id="female" value="option2" checked="true"> 才女
				</label>
			</div>
		</div>
	
		<div class="form-group">
			<label for="name" class="col-sm-2 col-xs-2 control-label">生日</label>
			<div class="col-sm-4 col-xs-4">
				<select id="year" name="year" class="form-control">
					<c:forEach var="year" begin="1950" end="2010">
						<c:choose>
							<c:when test="${year eq '1990' }">
								<option value="${year}" selected>${year}</option>
							</c:when>
							<c:otherwise>
								<option value="${year}">${year}</option>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</select>
			</div>
			
			<div class="col-sm-3 col-xs-3">
				<select name="month" id="month" class="form-control">
					<c:forEach var="month" begin="1" end="12">
						<option value="${month}">${month}</option>
					</c:forEach>
				</select>
			</div>
			<div class="col-sm-3 col-xs-3">
				<select name="day" id="day" class="form-control">
					<c:forEach var="day" begin="1" end="31">
						<option value="${day}">${day}</option>
					</c:forEach>
				</select>
			</div>
		</div>
		<!-- 
		<div class="form-group">
			<label for="name" class="col-sm-2 control-label">个性宣言</label>
			<div class="col-sm-10">
				<textarea name="remark" class="form-control" rows="3"></textarea>
			</div>
		</div>
		 -->
		<div class="form-group" id="ad-container">
			<script type="text/javascript">
				var cpro_id = "u2703199";
			</script>
			<script type="text/javascript" src="http://cpro.baidustatic.com/cpro/ui/cm.js"></script>
		</div>
		<div class="form-group">
			<center>
				<button type="button" id="btn-submit" class="btn btn-success btn-block">生成个性标签</button>
			</center>
		</div>
	</form>
	<div class="content" id="img-container" style="display:hide;padding:0px 0px 0px 0px;margin:0px 0px 0px 0px;"></div>
</div>
<div class="container">
  <div class="content">
    <img src="/images/qrcode/klk/qrcode_for_gh_83502fd74e75_258.jpg" class="img-responsive center-block" style="max-width:100%;height:auto;">
  </div>
  <div class="center">
   <center>长按,识别二维码,分享给朋友</center>
  </div>
</div>
<script type="text/javascript">
	$(document).ready(function() {
		$('#btn-submit').click(function() {
			var name = $('input[name="name"]').val();
			var month=$('#month').val();
			var day=$("#day").val();
			var tips='';
			if(name==''){
				tips='请输入名字';
			}
			if(name && name.length > 4) {
				tips='名字为四个字符.';
			}else if(!month || month==''){
				tips+='请选择出生月份';
			}else if(!day || day==''){
				tips+='请选择出生日期';
			} 
			if(tips=='') {
				$('#msg').hide();
				var msg=new Msg({msg:'正在生成个性标签,请耐心等待...'});
				msg.show();
				window.setTimeout(only,2000);
				var img = '<img src="/weixinApp/onPersonalXingzuoSignSubmit?name=' + name + '&month='+month+'&day='+day+'" class="img-responsive" style="max-width:100%;height:auto;">';
				$('#form-container').hide();
				$("#img-container").show();
				$("#img-container").append(img);
				$('#g-container').css({'margin':'0px 0px 0px 0px;','padding':'0px 0px 0px 0px;'});
				$('#g-container').append($('#ad-container'));
				window.setTimeout(wait,1500);
			}else{
				$('#msg').show();
                var msg=new Msg({msg:tips,type:'warning'});
                msg.show();
			}
		});
	});
	function wait(){
		$('#msg').hide();
	}
	function only(){}
</script>
