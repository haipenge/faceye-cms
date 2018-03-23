package com.faceye.component.cms.service.impl;

import java.util.HashMap;
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

import com.faceye.component.cms.entity.Team;
import com.faceye.component.cms.repository.mongo.TeamRepository;
import com.faceye.component.cms.service.TeamService;
import com.faceye.component.security.entity.User;
import com.faceye.component.security.service.UserService;
import com.faceye.feature.repository.mongo.DynamicSpecifications;
import com.faceye.feature.service.impl.BaseMongoServiceImpl;
 
import com.querydsl.core.types.Predicate;
@Service
public class TeamServiceImpl extends BaseMongoServiceImpl<Team, Long, TeamRepository> implements TeamService {

	@Autowired
	private UserService userSerivce=null;
	@Autowired
	public TeamServiceImpl(TeamRepository dao) {
		super(dao);
	}
	
	
	@Override
	public Page<Team> getPage(Map<String, Object> searchParams, int page, int size) {
//		if (page != 0) {
//			page = page - 1;
//		}
		if(null==searchParams){
			searchParams=new HashMap();
		}
		User user=this.userSerivce.getCurrentLoginUser();
		searchParams.put("EQ|user.$id", user.getId());
//		 SimpleEntityPathResolver resolver = SimpleEntityPathResolver.INSTANCE;
//		 EntityPath<Team> entityPath = resolver.createPath(entityClass);
//		 PathBuilder<Team> builder = new PathBuilder<Team>(entityPath.getType(), entityPath.getMetadata());
//		 Path<?> path = entityPath.getRoot();
//		 List<Predicate> predicates=DynamicSpecifications.buildPredicates(searchParams, entityClass);
//		 NumberPath numberPath = new NumberPath(Number.class, path, "user.id");
//		 predicates.add(numberPath.eq(user.getId()));
//		 Predicate predicate=DynamicSpecifications.builder(predicates);
		Predicate predicate = DynamicSpecifications.builder(searchParams, entityClass);
		if (predicate != null) {
			logger.debug(">>FaceYe -->Query predicate is:" + predicate.toString());
		}
		Sort sort = new Sort(Direction.DESC, "id");
		Page<Team> res = null;
		if (size != 0) {
			Pageable pageable = new PageRequest(page, size, sort);
			res = this.dao.findAll(predicate, pageable);
		} else {
			// OrderSpecifier<Comparable> orderPOrderSpecifier=new OrderSpecifier<Comparable>(new Order(), new NumberExpression<Team>("id") {
			// })
			List<Team> items = (List) this.dao.findAll(predicate);
			res = new PageImpl<Team>(items);
		}
		return res;
	}
	
}/**@generate-service-source@**/
