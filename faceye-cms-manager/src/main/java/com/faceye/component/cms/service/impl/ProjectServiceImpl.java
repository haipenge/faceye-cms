package com.faceye.component.cms.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.faceye.component.cms.entity.Project;
import com.faceye.component.cms.repository.mongo.ProjectRepository;
import com.faceye.component.cms.service.ProjectService;
import com.faceye.component.security.entity.User;
import com.faceye.component.security.service.UserService;
import com.faceye.feature.repository.mongo.DynamicSpecifications;
import com.faceye.feature.service.impl.BaseMongoServiceImpl;
import com.faceye.feature.util.ServiceException;
import com.querydsl.core.types.Predicate;
@Service("cms-projectServiceImpl")
public class ProjectServiceImpl extends BaseMongoServiceImpl<Project, Long, ProjectRepository> implements ProjectService {
	@Autowired
	private UserService userService=null;
	@Autowired
	public ProjectServiceImpl(ProjectRepository dao) {
		super(dao);
	}
	
	
	@Override
	public Page<Project> getPage(Map<String, Object> searchParams, int page, int size)  {
		if (page != 0) {
			page = page - 1;
		}
		if(null==searchParams){
			searchParams=new HashMap();
		}
		User user=this.userService.getCurrentLoginUser();
		searchParams.put("EQ|user.$id", user.getId());
//		 SimpleEntityPathResolver resolver = SimpleEntityPathResolver.INSTANCE;
//		 EntityPath<Project> entityPath = resolver.createPath(entityClass);
//		 PathBuilder<Project> builder = new PathBuilder<Project>(entityPath.getType(), entityPath.getMetadata());
//		 Path path = entityPath.getRoot();
//		 List<Predicate> predicates=DynamicSpecifications.buildPredicates(searchParams, entityClass);
//		 NumberPath numberPath = new NumberPath(Number.class, path, "user._id");
//		 predicates.add(numberPath.eq(user.getId()));
//		 Predicate predicate=DynamicSpecifications.builder(predicates);
		Predicate predicate = DynamicSpecifications.builder(searchParams, entityClass);
		if (predicate != null) {
			logger.debug(">>FaceYe -->Query predicate is:" + predicate.toString());
		}
		Sort sort = new Sort(Direction.DESC, "id");
		Page<Project> res = null;
		if (size != 0) {
			Pageable pageable = new PageRequest(page, size, sort);
			res = this.dao.findAll(predicate, pageable);
		} else {
			// OrderSpecifier<Comparable> orderPOrderSpecifier=new OrderSpecifier<Comparable>(new Order(), new NumberExpression<Project>("id") {
			// })
			List<Project> items = (List) this.dao.findAll(predicate);
			res = new PageImpl<Project>(items);

		}
		return res;
	}
	
}/**@generate-service-source@**/
