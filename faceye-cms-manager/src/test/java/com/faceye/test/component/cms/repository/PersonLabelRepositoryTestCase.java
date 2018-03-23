package com.faceye.test.component.cms.repository;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.Assert;

import com.faceye.component.cms.entity.PersonLabel;
import com.faceye.component.cms.repository.mongo.PersonLabelRepository;
import com.faceye.test.feature.repository.BaseRepositoryTestCase;
/**
 * PersonLabel DAO 测试
 * @author @haipenge 
 * haipenge@gmail.com
*  Create Date:2014年5月26日
 */
public class PersonLabelRepositoryTestCase extends BaseRepositoryTestCase {
	@Autowired
	private PersonLabelRepository personLabelRepository = null;

	@Before
	public void before() throws Exception {
		//this.personLabelRepository.deleteAll();
	}

	@After
	public void after() throws Exception {

	}

	@Test
	public void testSave() throws Exception {
		PersonLabel entity = new PersonLabel();
		this.personLabelRepository.save(entity);
		Iterable<PersonLabel> entities = this.personLabelRepository.findAll();
		Assert.assertTrue(entities.iterator().hasNext());
	}

	@Test
	public void testDelete() throws Exception {
		PersonLabel entity = new PersonLabel();
		this.personLabelRepository.save(entity);
        this.personLabelRepository.deleteById(entity.getId());
        Iterable<PersonLabel> entities = this.personLabelRepository.findAll();
		Assert.assertTrue(!entities.iterator().hasNext());
	}

	@Test
	public void testFindOne() throws Exception {
		PersonLabel entity = new PersonLabel();
		this.personLabelRepository.save(entity);
		PersonLabel personLabel=this.personLabelRepository.findById(entity.getId()).get();
		Assert.assertTrue(personLabel!=null);
	}

	
}
