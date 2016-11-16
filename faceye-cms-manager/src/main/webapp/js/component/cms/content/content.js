/**
*说明:内容管理->cms -> 内容 Content  js 脚本
*作者:haipeng
*/
var Content={
  init:function(){
       /**
       *全选、全不选
       */
       $('input[name="check-all"]').click(function(){
	     Check.onCheck($('input[name="check-all"]'),$('input[name="check-single"]'));
	    });
	    /**
	    *执行删除
	    */
	    $('.multi-remove').click(function(){
	       Content.multiRemove();
	    });
	    /**
	     * 推送到微信
	     */
	    $('.multi-push-2-weixin').click(function(){
	    	Content.multiPush2Weixin();
	    });
  },
  /**
   * 批量删除
   */
  multiRemove:function(){
	  var checkedIds=Check.getCheckedIds($('input[name="check-single"]'));
	  if(checkedIds!=''){
		  $.ajax({
			  url:'/cms/content/multiRemove',
			  type:'post',
			  dataType:'json',
			  data:{
				  ids:checkedIds
			  },
			  success:function(data,textStatux,xhr){
				  var msg=new Msg({msg:'数据删除成功'});
				  var idArray=checkedIds.split(',');
				  for(var i=0;i<idArray.length;i++){
				     var id=idArray[i];
				     $('#'+id).remove();
				  }
				  msg.show();
			  }
		  });
	  }else{
		  var msg=new Msg({msg:'请选择要删除的数据',type:'warning'});
		  msg.show();
	  }
  },
  /**
	 * 删除文章中的图片
	 */
	removeImage : function(id) {
		$.ajax({
			url : '/cms/conent/removeImage',
			type : 'post',
			dataType : 'json',
			data : {
				id : id
			},
			success : function(data, textStatus, xhr) {
				$('#' + id + '-dd').remove();
				$('#' + id + '-dt').remove();
			}
		});
	},
	/**
	 *  推送到微信
	 */
	multiPush2Weixin:function(){
		var weixinAccountIds=Check.getCheckedIds($('input[name="weixinAccount"]'));
		var contentIds=Check.getCheckedIds($('input[name="check-single"]'));
		 if(contentIds!='' && weixinAccountIds!=''){
			  $.ajax({
				  url:'/cms/content/push2Weixin',
				  type:'post',
				  dataType:'json',
				  data:{
					  weixinAccountIds:weixinAccountIds,
					  contentIds:contentIds
				  },
				  success:function(data,textStatux,xhr){
					  var msg=new Msg({msg:'推送成功'});
					  msg.show();
				  }
			  });
		  }else{
			  var msg=new Msg({msg:'请选择要推送的数据',type:'warning'});
			  msg.show();
		  }
	}
};

$(document).ready(function(){
	Content.init();
});
