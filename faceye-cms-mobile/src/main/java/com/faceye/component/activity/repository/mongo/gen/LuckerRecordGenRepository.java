package com.faceye.component.activity.repository.mongo.gen;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.faceye.component.activity.entity.LuckerRecord;
import com.faceye.component.activity.repository.mongo.customer.LuckerRecordCustomerRepository;
import com.faceye.feature.repository.mongo.BaseMongoRepository;
/**
 * LuckerRecord 实体DAO<br>
 * @author @haipenge <br>
 * haipenge@gmail.com<br>
*  Create Date:2014年5月20日<br>
 */
public interface LuckerRecordGenRepository extends BaseMongoRepository<LuckerRecord,Long>  {
	 
	
}/**@generate-repository-source@**/
