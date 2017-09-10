package com.faceye.component.cms.repository.mongo.gen;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.faceye.component.cms.entity.Category;
import com.faceye.component.cms.repository.mongo.customer.CategoryCustomerRepository;
import com.faceye.feature.repository.mongo.BaseMongoRepository;
/**
 * Category 实体DAO<br>
 * @author @haipenge <br>
 * haipenge@gmail.com<br>
*  Create Date:2014年5月20日<br>
 */
public interface CategoryGenRepository extends BaseMongoRepository<Category,Long>  {
	 
	
}/**@generate-repository-source@**/