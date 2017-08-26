package com.faceye.component.activity.repository.mongo.gen;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.faceye.component.activity.entity.Customer;
import com.faceye.component.activity.repository.mongo.customer.CustomerCustomerRepository;
import com.faceye.feature.repository.mongo.BaseMongoRepository;
/**
 * Customer 实体DAO<br>
 * @author @haipenge <br>
 * haipenge@gmail.com<br>
*  Create Date:2014年5月20日<br>
 */
public interface CustomerGenRepository extends BaseMongoRepository<Customer,Long>  {
	 
	
}/**@generate-repository-source@**/
