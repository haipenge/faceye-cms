/**
 * 说明:LuckItem js 脚本 作者:@haipenge
 */
var LuckItem = {
	init : function() {
		/**
		 * 全选，全不选
		 */
		$('input[name="check-all"]').click(function() {
			Check.onCheck($('input[name="check-all"]'), $('input[name="check-single"]'));
		});
		$('.multi-remove').click(function() {
			LuckItem.multiRemove();
		});

		$('.start-draw').click(function() {
			LuckItem.startDraw();
		});
		$('.stop-draw').click(function() {
			LuckItem.stopDraw();
		});
		$('body').on('click','.close',function(){location.reload();});
		

		// default-window-btn-yes

	},
	/**
	 * 批量删除
	 */
	multiRemove : function() {
		var checkedIds = Check.getCheckedIds($('input[name="check-single"]'));
		if (checkedIds != '') {
			$.ajax({
				url : '/activity/luckItem/multiRemove',
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

	startDraw : function() {
		$('input[name="draw_status"]').val('1');
		setInterval('LuckItem.startDraw0()', 150);
	},
	/**
	 * 开始抽奖
	 */
	startDraw0 : function() {
		var phoneCount = $('#lucker-phones td').length;
		// while (true) {
		var drawStatus = $('input[name="draw_status"]').val();
		// alert(drawStatus);
		if (drawStatus == '1') {
			var range = phoneCount;
			var rand = Math.random();
			var num = Math.round(rand * range)
			// alert(num+':'+range)
			if (num < range) {
				// LuckItem.changeColor(num);
				setTimeout("LuckItem.changeColor('" + num + "')", 0);

				// setTimeout("LuckItem.rebackChangeColor('"+num+"')",5000);
			}
			// LuckItem.sleep(500);
			// alert(1);
			// var now = new Date();
			// var exitTime = now.getTime() + 2500;
			// while (true) {
			// now = new Date();
			// if (now.getTime() > exitTime) {
			// alert(3);
			// break;
			// }
			// }
		}
		// alert(3);
		// break;
		if (drawStatus == '0') {
			// break;
			LuckItem.rebackChangeColor(num);
			return;
		}
		// }
	},
	changeColor : function(num) {

		// nth-child('+num+')'
		var trNum = Math.ceil(num / 4);
		var tdNum = num % 4;
		var tds = $('#lucker-phones td');
		var td = tds[num];
		$(td).css({
			'color' : 'red',
			"background-color" : "#98bf21"
		});
		// alert($('#lucker-phones tr:nth-child('+trNum+') td:nth-child('+tdNum+')').html());
		// $('#lucker-phones tr:nth-child('+trNum+')
		// td:nth-child('+tdNum+')').css({'color':'red',"background-color":"#98bf21"});
		setTimeout("LuckItem.rebackChangeColor('" + num + "')", 150);
	},
	rebackChangeColor : function(num) {
		var trNum = Math.ceil(num / 4);
		var tdNum = num % 4;
		var tds = $('#lucker-phones td');
		var td = tds[num];
		$(td).css({
			'color' : 'black',
			"background-color" : "white"
		});
	},

	/**
	 * 停止抽奖
	 */
	stopDraw : function() {
		$('input[name="draw_status"]').val('0');
		var luckItemId = $('input[name="luckItemId"]').val()
		$.ajax({
			url : '/activity/luckItem/lucker',
			type : 'post',
			data : {
				luckItemId : luckItemId
			},
			success : function(data, xhr, status) {
				var html = '';
				var obj = jQuery.parseJSON(data);
				if (obj.isLucker) {
					html += '<p>中奖手机号:<b><font color="red">' + obj.phone + '</font></b></p>';
					var win = new Modal({
						title : '中奖结果',
						footer : false
					});
					win.setBody(html);
					win.show();
				} else {
					html = '<p class="text-center">抽奖结束~~</p>';
					var win = new Modal({
						title : '中奖结果',
						footer : false
					});
					win.setBody(html);
					win.show();
				}
			}
		});
	}
};

$(document).ready(function() {
	LuckItem.init();
});