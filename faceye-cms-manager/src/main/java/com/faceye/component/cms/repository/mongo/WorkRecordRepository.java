package com.faceye.component.cms.repository.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.faceye.component.cms.entity.WorkRecord;
import com.faceye.feature.repository.mongo.BaseMongoRepository;
/**
 * WorkRecord 实体DAO<br>
 * @author @haipenge <br>
 * haipenge@gmail.com<br>
*  Create Date:2014年5月20日<br>
 */
public interface WorkRecordRepository extends BaseMongoRepository<WorkRecord,Long> {
	
	
}/**@generate-repository-source@**/
