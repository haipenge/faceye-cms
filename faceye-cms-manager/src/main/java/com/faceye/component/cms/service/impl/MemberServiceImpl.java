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

import com.faceye.component.cms.entity.Member;
import com.faceye.component.cms.entity.Team;
import com.faceye.component.cms.repository.mongo.MemberRepository;
import com.faceye.component.cms.service.MemberService;
import com.faceye.component.cms.service.TeamService;
import com.faceye.feature.repository.mongo.DynamicSpecifications;
import com.faceye.feature.service.impl.BaseMongoServiceImpl;
import com.faceye.feature.util.ServiceException;
import com.querydsl.core.types.Predicate;

@Service
public class MemberServiceImpl extends BaseMongoServiceImpl<Member, Long, MemberRepository> implements MemberService {
	@Autowired
	private TeamService teamService = null;

	@Autowired
	public MemberServiceImpl(MemberRepository dao) {
		super(dao);
	}

	@Override
	public Page<Member> getPage(Map<String, Object> searchParams, int page, int size) throws ServiceException {
		if (page != 0) {
			page = page - 1;
		}
		// SimpleEntityPathResolver resolver = SimpleEntityPathResolver.INSTANCE;
		// EntityPath<Member> entityPath = resolver.createPath(entityClass);
		// PathBuilder<Member> builder = new PathBuilder<Member>(entityPath.getType(), entityPath.getMetadata());
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
		Page<Member> res = null;
		if (size != 0) {
			Pageable pageable = new PageRequest(page, size, sort);
			res = this.dao.findAll(predicate, pageable);
		} else {
			// OrderSpecifier<Comparable> orderPOrderSpecifier=new OrderSpecifier<Comparable>(new Order(), new NumberExpression<Member>("id") {
			// })
			List<Member> items = (List) this.dao.findAll(predicate);
			res = new PageImpl<Member>(items);

		}
		return res;
	}

	@Override
	public List<Member> getMembersOfTeam(Long teamId) {
		Team team = this.teamService.get(teamId);
		List<Member> members = team.getMembers();
		return members;
	}

	@Override
	public Member getMemberByEmail(String email) {
		return dao.getMemberByEmail(email);
	}

}/** @generate-service-source@ **/
