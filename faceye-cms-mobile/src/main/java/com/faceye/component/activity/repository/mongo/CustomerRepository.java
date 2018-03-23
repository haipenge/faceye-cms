package com.faceye.component.activity.repository.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;



import com.faceye.component.activity.repository.mongo.customer.CustomerCustomerRepository;
import com.faceye.component.activity.repository.mongo.gen.CustomerGenRepository;
import com.faceye.feature.repository.mongo.BaseMongoRepository;
/**
 * Customer 实体DAO<br>
 * @author @haipenge <br>
 * haipenge@gmail.com<br>
*  Create Date:2014年5月20日<br>
 */
public interface CustomerRepository extends CustomerGenRepository{
	
	
}/**@generate-repository-source@**/
