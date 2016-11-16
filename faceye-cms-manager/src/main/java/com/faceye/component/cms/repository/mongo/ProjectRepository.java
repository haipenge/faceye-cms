package com.faceye.component.cms.repository.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Repository;

import com.faceye.component.cms.entity.Project;
import com.faceye.feature.repository.mongo.BaseMongoRepository;
/**
 * Project 实体DAO<br>
 * @author @haipenge <br>
 * haipenge@gmail.com<br>
*  Create Date:2014年5月20日<br>
 */
@Repository("cms-projectRepository")
public interface ProjectRepository extends BaseMongoRepository<Project,Long> {
	
	
}/**@generate-repository-source@**/
