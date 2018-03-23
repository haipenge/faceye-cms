package com.faceye.test.component.activity.repository;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.Assert;

import com.faceye.component.activity.entity.InnerCustomer;
import com.faceye.component.activity.repository.mongo.InnerCustomerRepository;
import com.faceye.test.feature.repository.BaseRepositoryTestCase;
/**
 * InnerCustomer DAO 测试
 * @author @haipenge 
 * haipenge@gmail.com
*  Create Date:2014年5月26日
 */
public class InnerCustomerRepositoryTestCase extends BaseRepositoryTestCase {
	@Autowired
	private InnerCustomerRepository innerCustomerRepository = null;

	@Before
	public void before() throws Exception {
		//this.innerCustomerRepository.deleteAll();
	}

	@After
	public void after() throws Exception {

	}

	@Test
	public void testSave() throws Exception {
		InnerCustomer entity = new InnerCustomer();
		this.innerCustomerRepository.save(entity);
		Iterable<InnerCustomer> entities = this.innerCustomerRepository.findAll();
		Assert.assertTrue(entities.iterator().hasNext());
	}

	@Test
	public void testDelete() throws Exception {
		InnerCustomer entity = new InnerCustomer();
		this.innerCustomerRepository.save(entity);
        this.innerCustomerRepository.deleteById(entity.getId());
        Iterable<InnerCustomer> entities = this.innerCustomerRepository.findAll();
		Assert.assertTrue(!entities.iterator().hasNext());
	}

	@Test
	public void testFindOne() throws Exception {
		InnerCustomer entity = new InnerCustomer();
		this.innerCustomerRepository.save(entity);
		InnerCustomer innerCustomer=this.innerCustomerRepository.findById(entity.getId()).get();
		Assert.assertTrue(innerCustomer!=null);
	}

	
}
