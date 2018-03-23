package com.faceye.test.component.cms.repository;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.Assert;

import com.faceye.component.cms.entity.Smtp;
import com.faceye.component.cms.repository.mongo.SmtpRepository;
import com.faceye.test.feature.repository.BaseRepositoryTestCase;
/**
 * Smtp DAO 测试
 * @author @haipenge 
 * haipenge@gmail.com
*  Create Date:2014年5月26日
 */
public class SmtpRepositoryTestCase extends BaseRepositoryTestCase {
	@Autowired
	private SmtpRepository smtpRepository = null;

	@Before
	public void before() throws Exception {
		//this.smtpRepository.deleteAll();
	}

	@After
	public void after() throws Exception {

	}

	@Test
	public void testSave() throws Exception {
		Smtp entity = new Smtp();
		this.smtpRepository.save(entity);
		Iterable<Smtp> entities = this.smtpRepository.findAll();
		Assert.assertTrue(entities.iterator().hasNext());
	}

	@Test
	public void testDelete() throws Exception {
		Smtp entity = new Smtp();
		this.smtpRepository.save(entity);
        this.smtpRepository.deleteById(entity.getId());
        Iterable<Smtp> entities = this.smtpRepository.findAll();
		Assert.assertTrue(!entities.iterator().hasNext());
	}

	@Test
	public void testFindOne() throws Exception {
		Smtp entity = new Smtp();
		this.smtpRepository.save(entity);
		Smtp smtp=this.smtpRepository.findById(entity.getId()).get();
		Assert.assertTrue(smtp!=null);
	}

	
}
