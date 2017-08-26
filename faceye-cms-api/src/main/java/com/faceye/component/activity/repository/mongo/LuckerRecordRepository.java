package com.faceye.component.activity.repository.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;



import com.faceye.component.activity.repository.mongo.customer.LuckerRecordCustomerRepository;
import com.faceye.component.activity.repository.mongo.gen.LuckerRecordGenRepository;
import com.faceye.feature.repository.mongo.BaseMongoRepository;
/**
 * LuckerRecord 实体DAO<br>
 * @author @haipenge <br>
 * haipenge@gmail.com<br>
*  Create Date:2014年5月20日<br>
 */
public interface LuckerRecordRepository extends LuckerRecordGenRepository{
	
	
}/**@generate-repository-source@**/
