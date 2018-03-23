package com.faceye.component.cms.repository.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.faceye.component.cms.entity.PersonLabel;
import com.faceye.feature.repository.mongo.BaseMongoRepository;
/**
 * PersonLabel 实体DAO<br>
 * @author @haipenge <br>
 * haipenge@gmail.com<br>
*  Create Date:2014年5月20日<br>
 */
public interface PersonLabelRepository extends BaseMongoRepository<PersonLabel,Long> {
	
	
}/**@generate-repository-source@**/
