package com.faceye.component.cms.service.impl;

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

import com.faceye.component.cms.entity.PersonLabel;
import com.faceye.component.cms.repository.mongo.PersonLabelRepository;
import com.faceye.component.cms.service.PersonLabelService;
import com.faceye.feature.repository.mongo.DynamicSpecifications;
import com.faceye.feature.service.impl.BaseMongoServiceImpl;
import com.faceye.feature.util.ServiceException;
import com.querydsl.core.types.Predicate;
@Service
public class PersonLabelServiceImpl extends BaseMongoServiceImpl<PersonLabel, Long, PersonLabelRepository> implements PersonLabelService {

	@Autowired
	public PersonLabelServiceImpl(PersonLabelRepository dao) {
		super(dao);
	}
	
	
	@Override
	public Page<PersonLabel> getPage(Map<String, Object> searchParams, int page, int size) throws ServiceException {
		if (page != 0) {
			page = page - 1;
		}
		// SimpleEntityPathResolver resolver = SimpleEntityPathResolver.INSTANCE;
		// EntityPath<PersonLabel> entityPath = resolver.createPath(entityClass);
		// PathBuilder<PersonLabel> builder = new PathBuilder<PersonLabel>(entityPath.getType(), entityPath.getMetadata());
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
		Page<PersonLabel> res = null;
		if (size != 0) {
			Pageable pageable = new PageRequest(page, size, sort);
			res = this.dao.findAll(predicate, pageable);
		} else {
			// OrderSpecifier<Comparable> orderPOrderSpecifier=new OrderSpecifier<Comparable>(new Order(), new NumberExpression<PersonLabel>("id") {
			// })
			List<PersonLabel> items = (List) this.dao.findAll(predicate);
			res = new PageImpl<PersonLabel>(items);

		}
		return res;
	}
	
}/**@generate-service-source@**/
