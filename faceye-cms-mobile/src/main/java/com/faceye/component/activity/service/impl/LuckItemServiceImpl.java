package com.faceye.component.activity.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.faceye.component.activity.entity.LuckItem;

import com.faceye.component.activity.repository.mongo.LuckItemRepository;
import com.faceye.component.activity.repository.mongo.customer.LuckItemCustomerRepository;
import com.faceye.component.activity.service.LuckItemService;

 
import com.faceye.feature.repository.mongo.DynamicSpecifications;
import com.faceye.feature.service.impl.BaseMongoServiceImpl;
import com.querydsl.core.types.Predicate;
/**
 * LuckItem 服务实现类<br>
 * @author @haipenge <br>
 * haipenge@gmail.com<br>
*  Create Date:2014年5月20日<br>
 */

@Service
public class LuckItemServiceImpl extends BaseMongoServiceImpl<LuckItem, Long, LuckItemRepository> implements LuckItemService {
	@Autowired
	private LuckItemCustomerRepository luckItemCustomerRepository=null;
	@Autowired
	public LuckItemServiceImpl(LuckItemRepository dao) {
		super(dao);
	}
	
	
	@Override
	public Page<LuckItem> getPage(Map<String, Object> searchParams, int page, int size)   {
		if (page != 0) {
			page = page - 1;
		}
		// SimpleEntityPathResolver resolver = SimpleEntityPathResolver.INSTANCE;
		// EntityPath<LuckItem> entityPath = resolver.createPath(entityClass);
		// PathBuilder<LuckItem> builder = new PathBuilder<LuckItem>(entityPath.getType(), entityPath.getMetadata());
		// Path path = entityPath.getRoot();
		// List<Predicate> predicates=DynamicSpecifications.buildPredicates(searchParams, entityClass);
		// Predicate predicate=DynamicSpecifications.builder(predicates);
		// NumberPath numberPath = new NumberPath(Number.class, path, "age");
		// predicates.add(numberPath.eq(15));
//		Predicate predicate = DynamicSpecifications.builder(searchParams, entityClass);
//		if (predicate != null) {
//			logger.debug(">>FaceYe -->Query predicate is:" + predicate.toString());
//		}
//		Sort sort = new Sort(Direction.DESC, "id");
//		Page<LuckItem> res = null;
//		if (size != 0) {
//			Pageable pageable = new PageRequest(page, size, sort);
//			res = this.dao.findAll(predicate, pageable);
//		} else {
//			// OrderSpecifier<Comparable> orderPOrderSpecifier=new OrderSpecifier<Comparable>(new Order(), new NumberExpression<LuckItem>("id") {
//			// })
//			List<LuckItem> items = (List) this.dao.findAll(predicate);
//			res = new PageImpl<LuckItem>(items);
//
//		}
		return super.getPage(searchParams, page, size);;
	}
	
}/**@generate-service-source@**/
