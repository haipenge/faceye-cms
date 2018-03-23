package com.faceye.component.cms.service;

import com.faceye.component.cms.entity.Content;
import com.faceye.feature.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import com.faceye.feature.repository.mongo.DynamicSpecifications;
 
/**
 * 模块:内容管理->com.faceye.compoent.cms.service<br>
 * 说明:<br>
 * 实体:内容->com.faceye.component.cms.entity.entity.Content 服务层接口<br>
 * @author haipeng <br>
 * 联系:haipenge@gmail.com<br>
 * 创建日期:2015-7-25 9:40:29<br>
 */
public interface ContentService extends BaseService<Content,Long>{

	/**
	 * 将内容推送到指定微信帐户
	 * @param id
	 * @param weixinId
	 * @Desc:
	 * @Author:haipenge
	 * @Date:2016年6月14日 上午11:43:53
	 */
	public void saveAndPush2Weixin(Long id,Long... weixinId);
	/**
	 * 批量推送
	 * @param ids
	 * @param weixinIds
	 * @Desc:
	 * @Author:haipenge
	 * @Date:2016年6月14日 下午12:13:33
	 */
	public void saveAndPush2Weixin(Long [] ids,Long [] weixinIds);
}/**@generate-service-source@**/
