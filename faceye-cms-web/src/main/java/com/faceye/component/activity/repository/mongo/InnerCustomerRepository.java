package com.faceye.component.activity.repository.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;



import com.faceye.component.activity.repository.mongo.customer.InnerCustomerCustomerRepository;
import com.faceye.component.activity.repository.mongo.gen.InnerCustomerGenRepository;
import com.faceye.feature.repository.mongo.BaseMongoRepository;
/**
 * InnerCustomer 实体DAO<br>
 * @author @haipenge <br>
 * haipenge@gmail.com<br>
*  Create Date:2014年5月20日<br>
 */
public interface InnerCustomerRepository extends InnerCustomerGenRepository{
	
	
}/**@generate-repository-source@**/
