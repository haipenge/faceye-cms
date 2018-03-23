package com.faceye.test.component.activity.repository;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.Assert;

import com.faceye.component.activity.entity.Customer;
import com.faceye.component.activity.repository.mongo.CustomerRepository;
import com.faceye.test.feature.repository.BaseRepositoryTestCase;
/**
 * Customer DAO 测试
 * @author @haipenge 
 * haipenge@gmail.com
*  Create Date:2014年5月26日
 */
public class CustomerRepositoryTestCase extends BaseRepositoryTestCase {
	@Autowired
	private CustomerRepository customerRepository = null;

	@Before
	public void before() throws Exception {
		//this.customerRepository.deleteAll();
	}

	@After
	public void after() throws Exception {

	}

	@Test
	public void testSave() throws Exception {
		Customer entity = new Customer();
		this.customerRepository.save(entity);
		Iterable<Customer> entities = this.customerRepository.findAll();
		Assert.assertTrue(entities.iterator().hasNext());
	}

	@Test
	public void testDelete() throws Exception {
		Customer entity = new Customer();
		this.customerRepository.save(entity);
        this.customerRepository.deleteById(entity.getId());
        Iterable<Customer> entities = this.customerRepository.findAll();
		Assert.assertTrue(!entities.iterator().hasNext());
	}

	@Test
	public void testFindOne() throws Exception {
		Customer entity = new Customer();
		this.customerRepository.save(entity);
		Customer customer=this.customerRepository.findById(entity.getId()).get();
		Assert.assertTrue(customer!=null);
	}

	
}
