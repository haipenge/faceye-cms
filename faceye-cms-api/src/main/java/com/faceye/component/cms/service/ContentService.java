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

	
}/**@generate-service-source@**/
