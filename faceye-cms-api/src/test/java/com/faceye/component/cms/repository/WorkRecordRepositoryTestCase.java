package com.faceye.test.component.cms.repository;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.Assert;

import com.faceye.component.cms.entity.WorkRecord;
import com.faceye.component.cms.repository.mongo.WorkRecordRepository;
import com.faceye.test.feature.repository.BaseRepositoryTestCase;
/**
 * WorkRecord DAO 测试
 * @author @haipenge 
 * haipenge@gmail.com
*  Create Date:2014年5月26日
 */
public class WorkRecordRepositoryTestCase extends BaseRepositoryTestCase {
	@Autowired
	private WorkRecordRepository workRecordRepository = null;

	@Before
	public void before() throws Exception {
		//this.workRecordRepository.deleteAll();
	}

	@After
	public void after() throws Exception {

	}

	@Test
	public void testSave() throws Exception {
		WorkRecord entity = new WorkRecord();
		this.workRecordRepository.save(entity);
		Iterable<WorkRecord> entities = this.workRecordRepository.findAll();
		Assert.assertTrue(entities.iterator().hasNext());
	}

	@Test
	public void testDelete() throws Exception {
		WorkRecord entity = new WorkRecord();
		this.workRecordRepository.save(entity);
        this.workRecordRepository.deleteById(entity.getId());
        Iterable<WorkRecord> entities = this.workRecordRepository.findAll();
		Assert.assertTrue(!entities.iterator().hasNext());
	}

	@Test
	public void testFindOne() throws Exception {
		WorkRecord entity = new WorkRecord();
		this.workRecordRepository.save(entity);
		WorkRecord workRecord=this.workRecordRepository.findById(entity.getId()).get();
		Assert.assertTrue(workRecord!=null);
	}

	
}
