package com.faceye.test.component.activity.repository;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.faceye.component.activity.entity.LuckerRecord;
import com.faceye.component.activity.repository.mongo.LuckerRecordRepository;
import com.faceye.test.feature.repository.BaseRepositoryTestCase;
/**
 * LuckerRecord DAO 测试
 * @author @haipenge 
 * haipenge@gmail.com
*  Create Date:2014年5月26日
 */
public class LuckerRecordRepositoryTestCase extends BaseRepositoryTestCase {
	@Autowired
	private LuckerRecordRepository luckerRecordRepository = null;

	@Before
	public void before() throws Exception {
		//this.luckerRecordRepository.deleteAll();
	}

	@After
	public void after() throws Exception {

	}

	@Test
	public void testSave() throws Exception {
		LuckerRecord entity = new LuckerRecord();
		this.luckerRecordRepository.save(entity);
		Iterable<LuckerRecord> entities = this.luckerRecordRepository.findAll();
		Assert.isTrue(entities.iterator().hasNext());
	}

	@Test
	public void testDelete() throws Exception {
		LuckerRecord entity = new LuckerRecord();
		this.luckerRecordRepository.save(entity);
        this.luckerRecordRepository.delete(entity.getId());
        Iterable<LuckerRecord> entities = this.luckerRecordRepository.findAll();
		Assert.isTrue(!entities.iterator().hasNext());
	}

	@Test
	public void testFindOne() throws Exception {
		LuckerRecord entity = new LuckerRecord();
		this.luckerRecordRepository.save(entity);
		LuckerRecord luckerRecord=this.luckerRecordRepository.findOne(entity.getId());
		Assert.isTrue(luckerRecord!=null);
	}

	
}
