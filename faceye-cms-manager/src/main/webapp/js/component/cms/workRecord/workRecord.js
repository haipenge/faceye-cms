/**
 * 说明:WorkRecord js 脚本 作者:@haipenge
 */
var WorkRecord = {
	init : function() {
		/**
		 * 全选，全不选
		 */
		$('input[name="check-all"]').click(
				function() {
					Check.onCheck($('input[name="check-all"]'),
							$('input[name="check-single"]'));
				});
		$('.multi-remove').click(function() {
			WorkRecord.multiRemove();
		});
		/**
		 * 完成工作
		 */
		$('.work-done').click(function() {
			var id = $(this).parent().parent().parent().attr('id');
			WorkRecord.done(id);
		});
		WorkRecord.onMove();
		WorkRecord.resetWorkListStyle();
		$('select[name="project.id"]').change(function(){
			var projectId=$(this).val();
			var workRecordId=$('input[name="id"]').val();
			WorkRecord.selectMembers(projectId,workRecordId);
		});

	},
	/**
	 * 批量删除
	 */
	multiRemove : function() {
		var checkedIds = Check.getCheckedIds($('input[name="check-single"]'));
		if (checkedIds != '') {
			$.ajax({
				url : '/cms/workRecord/multiRemove',
				type : 'post',
				dataType : 'json',
				data : {
					ids : checkedIds
				},
				success : function(data, textStatux, xhr) {
					var msg = new Msg({
						msg : '数据删除成功'
					});
					var idArray = checkedIds.split(',');
					for (var i = 0; i < idArray.length; i++) {
						$('#' + idArray[i]).remove();
					}
					msg.show();
				}
			});
		} else {
			var msg = new Msg({
				msg : '请选择要删除的数据',
				type : 'warning'
			});
			msg.show();
		}
	},
	/**
	 * 完成工作
	 */
	done : function(id) {
		$.ajax({
			url : '/cms/workRecord/done',
			type : 'post',
			dataType : 'json',
			data : {
				id : id
			},
			success : function(data, textStatus, xhr) {
				var msg = new Msg({
					msg : '已设置为完成。'
				});
				msg.show();
			}
		});
	},
	/**
	 * 重置工作任务状态
	 */
	resetStatus : function(id, status) {
		$.ajax({
			url : '/cms/workRecord/resetStatus',
			type : 'post',
			dataType : 'json',
			data : {
				id : id,
				status : status
			},
			success : function(data, textStatus, xhr) {
				if (status == '1') {
					if ($('.doing-col .task-panel').length > 0) {
						$('#' + id).insertBefore('.doing-col .task-panel:first');
					} else {
						$('.doing-col').append($('#' + id));
					}
					 $('.todo-col').find('#' + id).remove();
				}
				if (status == '2') {
					if ($('.done-col .task-panel').length > 0) {
					  $('#' + id).insertBefore('.done-col .task-panel:first');
					} else {
						$('.done-col').append($('#' + id));
					}
					$('.doing-col').find('#' + id).remove();
				}
				if (status == '0') {
					if ($('.todo-col .task-panel').length > 0) {
						$('#' + id).insertBefore('.todo-col .task-panel:first');
					} else {
						$('.todo-col').append($('#' + id));
					}
					$('.done-col').find($('#' + id)).remove();
				}
//				WorkRecord.init();
				WorkRecord.buildTaskToolBar(id);
			}
		});
	},
	/**
	 * 拖动事件时触发
	 */
	onMove : function() {
		$('.work-list').sortable(
				{
					items : '.task-panel',
					connectWith : '.work-list',
					placeholder : 'placer',
					forcePlaceholderSize : true,
					forceHelperSize : true,
					revert : true,
					dropOnEmpty : true,
					containment : $('#work-panel'),
					opacity : 0.5,
					cursor : 'move',
					scope : 'tasks',
					scroll : true,
					iframeFix : true,
					stop : function(event, ui) {
						var id = $(this).attr('id');
						var html = $(this).html();
						// console.log('>>This sort list html is:'+html);
						// 移出列：
						var outCol = $(this).attr('data-col');
						console.log('>>Out col is:' + outCol);
						// 获取移出列的panel 排序 id
						var outColTaskPanelIds = '';
						$(this).find('.task-panel').each(
								function(index, element) {
									var taskPanelId = $(element).attr('id');
									if (taskPanelId) {
										outColTaskPanelIds += taskPanelId;
										outColTaskPanelIds += ',';
									}
								});
						console.log('out col task panel ids is:'
								+ outColTaskPanelIds);

						// 当前被拖拽的元素
						var item = ui.item;
						var taskPanelId = item.attr('id');
						console.log('当前被拖拽元素的ID:' + item.attr('id'));
						// console.log('>>Moving in html
						// :'+item.parent().html());
						// 移入列
						var inCol = item.parent().attr('data-col');
						console.log('>>In col is :' + inCol);
						var inColTaskPanelIds = '';
						item.parent().find('.task-panel').each(
								function(index, element) {
									var taskPanelId = $(element).attr('id');
									if (taskPanelId) {
										inColTaskPanelIds += taskPanelId;
										inColTaskPanelIds += ',';
									}
								});
						console.log('in col task panel ids is:'
								+ inColTaskPanelIds);
						$.ajax({
							url : '/cms/workRecord/move',
							type : 'post',
							dataType : 'json',
							data : {
								taskPanelId : taskPanelId,
								inColType : inCol,
								outColType : outCol,
								inColTaskPanelOrderIds : inColTaskPanelIds,
								outColTaskPanelOrderIds : outColTaskPanelIds
							},
							success : function(data, textStatus, xhr) {
								WorkRecord.buildTaskToolBar(taskPanelId);
							}
						});
						WorkRecord.init();
					}
				});

	},
	/**
	 * 重置col style
	 */
	resetWorkListStyle : function() {
		// 将最后一列的右边栏设置为无线条
		var colSize = $('.work-list').length;
		$('.work-list').each(function(index, element) {
			if (index + 1 == colSize) {
				$(element).css({
					border : 'none'
				});
			}
		})
	},
	/**
	 * 构建任务面板的工具条
	 */
	buildTaskToolBar : function(workRecordId) {
		var html = "";
		var colType = $('#' + workRecordId).parent().attr('data-col');
		var colIndex = 0;
		if (colType == 'todo') {
			colIndex = 0;
		} else if (colType == 'doing') {
			colIndex = 1;
		} else if (colType == 'done') {
			colIndex = 2;
		} else {
			colType = 0;
		}
		var actionName = [ '开始', '完成', '重置' ];
		var changeStatus = 1;
		if (colIndex == 0) {
			changeStatus = 1;
		} else if (colIndex == 1) {
			changeStatus = 2;
		} else if (colIndex == 2) {
			changeStatus = 0;
		}
		console.log('col type is:'+colType+",changeStatus :"+changeStatus);
		// <a href="#"
		// onclick="WorkRecord.resetStatus('${workRecord.id}','1');return
		// false;">Doing</a>
		html += '<a href="#" onclick="WorkRecord.resetStatus(\'' + workRecordId
				+ '\',' + changeStatus + ');return false;">';
		html += actionName[colIndex];
		html += '</a>';
	    //EDIT
		html+='&nbsp;&nbsp;';
		html+='<a href="/cms/workRecord/edit/"'+workRecordId+'>编辑</a>';
		
		// alert(html);
		$('#' + workRecordId).find('.task-tool-bar').empty().append(html);
		WorkRecord.init();
	},
	/**
	 * 初始化工具条
	 */
	setTaskToolBar : function() {
		$('.task-panel').each(function(index, element) {
			var id = $(element).attr('id');
			WorkRecord.buildTaskToolBar(id);
		});
	},
	/**
	 * 取得团队成员
	 */
	selectMembers:function(projectId,workRecordId){
		$.ajax({
			url:'/cms/workRecord/getMembersOfWorkRecord',
			type:'post',
			dataType:'json',
			data:{
				projectId:projectId,
				workRecordId:workRecordId
			},
			success:function(data,textStatus,xhr){
				if(data.length>0){
					var html="";
					for(var i=0;i<data.length;i++){
						var record=data[i];
						html+='<input type="checkbox" name="member.id" value="'+record.member.id+'"';
						if(record.isCheck){
							html+=' checked';
						}
						html+='>';
						html+="&nbsp;&nbsp;";
						html+=record.member.name;
					}
					$('#select-members').empty().append(html);
				}
			}
		});
	}
};

$(document).ready(function() {
	WorkRecord.init();
	WorkRecord.setTaskToolBar();
});
