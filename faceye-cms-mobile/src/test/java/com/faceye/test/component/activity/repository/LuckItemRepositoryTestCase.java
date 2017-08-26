package com.faceye.test.component.activity.repository;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.faceye.component.activity.entity.LuckItem;
import com.faceye.component.activity.repository.mongo.LuckItemRepository;
import com.faceye.test.feature.repository.BaseRepositoryTestCase;
/**
 * LuckItem DAO 测试
 * @author @haipenge 
 * haipenge@gmail.com
*  Create Date:2014年5月26日
 */
public class LuckItemRepositoryTestCase extends BaseRepositoryTestCase {
	@Autowired
	private LuckItemRepository luckItemRepository = null;

	@Before
	public void before() throws Exception {
		//this.luckItemRepository.deleteAll();
	}

	@After
	public void after() throws Exception {

	}

	@Test
	public void testSave() throws Exception {
		LuckItem entity = new LuckItem();
		this.luckItemRepository.save(entity);
		Iterable<LuckItem> entities = this.luckItemRepository.findAll();
		Assert.isTrue(entities.iterator().hasNext());
	}

	@Test
	public void testDelete() throws Exception {
		LuckItem entity = new LuckItem();
		this.luckItemRepository.save(entity);
        this.luckItemRepository.delete(entity.getId());
        Iterable<LuckItem> entities = this.luckItemRepository.findAll();
		Assert.isTrue(!entities.iterator().hasNext());
	}

	@Test
	public void testFindOne() throws Exception {
		LuckItem entity = new LuckItem();
		this.luckItemRepository.save(entity);
		LuckItem luckItem=this.luckItemRepository.findOne(entity.getId());
		Assert.isTrue(luckItem!=null);
	}

	
}
