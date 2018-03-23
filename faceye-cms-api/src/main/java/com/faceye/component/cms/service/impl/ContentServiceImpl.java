package com.faceye.component.cms.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import com.faceye.feature.repository.mongo.DynamicSpecifications;
 

import com.faceye.component.cms.entity.Content;
import com.faceye.component.cms.repository.mongo.ContentRepository;
import com.faceye.component.cms.service.ContentService;
import com.faceye.feature.service.impl.BaseMongoServiceImpl;
import com.mysema.query.types.Predicate;

/**
 * 模块:内容管理->com.faceye.compoent.cms.service.impl<br>
 * 说明:<br>
 * 实体:内容->com.faceye.component.cms.entity.entity.Content 服务实现类<br>
 * @author haipeng <br>
 * 联系:haipenge@gmail.com<br>
 * 创建日期:2015-7-25 9:40:29<br>
 */
@Service
public class ContentServiceImpl extends BaseMongoServiceImpl<Content, Long, ContentRepository> implements ContentService {

	@Autowired
	public ContentServiceImpl(ContentRepository dao) {
		super(dao);
	}
	
	/**
	 *数据分页查询
	 * @author haipeng <br>
     * 联系:haipenge@gmail.com<br>
     * 创建日期:2015-7-25 9:40:29<br>
	*/
	@Override
	public Page<Content> getPage(Map<String, Object> searchParams, int page, int size)   {
		if (page != 0) {
			page = page - 1;
		}
		// SimpleEntityPathResolver resolver = SimpleEntityPathResolver.INSTANCE;
		// EntityPath<Content> entityPath = resolver.createPath(entityClass);
		// PathBuilder<Content> builder = new PathBuilder<Content>(entityPath.getType(), entityPath.getMetadata());
		// Path path = entityPath.getRoot();
		// List<Predicate> predicates=DynamicSpecifications.buildPredicates(searchParams, entityClass);
		// Predicate predicate=DynamicSpecifications.builder(predicates);
		// NumberPath numberPath = new NumberPath(Number.class, path, "age");
		// predicates.add(numberPath.eq(15));
		Predicate predicate = DynamicSpecifications.builder(searchParams, entityClass);
		if (predicate != null) {
			logger.debug(">>FaceYe -->Query predicate is:" + predicate.toString());
		}
		Sort sort = new Sort(Direction.DESC, "id");
		Page<Content> res = null;
		if (size != 0) {
			Pageable pageable = new PageRequest(page, size, sort);
			res = this.dao.findAll(predicate, pageable);
		} else {
			// OrderSpecifier<Comparable> orderPOrderSpecifier=new OrderSpecifier<Comparable>(new Order(), new NumberExpression<Content>("id") {
			// })
			List<Content> items = (List) this.dao.findAll(predicate);
			res = new PageImpl<Content>(items);

		}
		return res;
	}
	
	
}/**@generate-service-source@**/
