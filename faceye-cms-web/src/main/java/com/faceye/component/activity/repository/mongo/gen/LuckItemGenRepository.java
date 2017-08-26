package com.faceye.component.activity.repository.mongo.gen;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.faceye.component.activity.entity.LuckItem;
import com.faceye.component.activity.repository.mongo.customer.LuckItemCustomerRepository;
import com.faceye.feature.repository.mongo.BaseMongoRepository;
/**
 * LuckItem 实体DAO<br>
 * @author @haipenge <br>
 * haipenge@gmail.com<br>
*  Create Date:2014年5月20日<br>
 */
public interface LuckItemGenRepository extends BaseMongoRepository<LuckItem,Long>  {
	 
	
}/**@generate-repository-source@**/
