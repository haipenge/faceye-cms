package com.faceye.component.cms.repository.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.faceye.component.cms.entity.Team;
import com.faceye.feature.repository.mongo.BaseMongoRepository;
/**
 * Team 实体DAO<br>
 * @author @haipenge <br>
 * haipenge@gmail.com<br>
*  Create Date:2014年5月20日<br>
 */
public interface TeamRepository extends BaseMongoRepository<Team,Long> {
	
	
}/**@generate-repository-source@**/
