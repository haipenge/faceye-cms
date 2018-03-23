package com.faceye.test.component.cms.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.junit.Assert;

import com.faceye.component.cms.entity.Smtp;
import com.faceye.component.cms.service.SmtpService;
import com.faceye.test.feature.service.BaseServiceTestCase;


/**
 * Smtp  服务层测试用例
 * 
 * @author @haipenge haipenge@gmail.com Create Date:2014年5月20日
 */
public class SmtpServiceTestCase extends BaseServiceTestCase {
	@Autowired
	private SmtpService smtpService = null;
	/**
	 * 初始化
	 * @todo
	 * @throws Exception
	 * @author:@haipenge
	 * haipenge@gmail.com
	 * 2014年5月20日
	 */
	@Before
	public void set() throws Exception {
		Assert.assertTrue(smtpService != null);
	}

	/**
	 * 清理
	 * @todo
	 * @throws Exception
	 * @author:@haipenge
	 * haipenge@gmail.com
	 * 2014年5月20日
	 */
	@After
	public void after() throws Exception {
		//this.smtpService.removeAllInBatch();
	}

	/**
	 *  保存测试
	 * @todo
	 * @throws Exception
	 * @author:@haipenge
	 * haipenge@gmail.com
	 * 2014年5月20日
	 */
	@Test
	public void testSave() throws Exception {
		Smtp entity = new Smtp();
		this.smtpService.save(entity);
		List<Smtp> entites = this.smtpService.getAll();
		Assert.assertTrue(CollectionUtils.isNotEmpty(entites));
	}

	@Test
	public void testSaveAndFlush() throws Exception {
		Smtp entity = new Smtp();
		this.smtpService.save(entity);
		List<Smtp> entites = this.smtpService.getAll();
		Assert.assertTrue(CollectionUtils.isNotEmpty(entites));
	}

	@Test
	public void testMultiSave() throws Exception {
		for (int i = 0; i < 5; i++) {
			Smtp entity = new Smtp();
			this.smtpService.save(entity);
		}
		List<Smtp> entities = this.smtpService.getAll();
		Assert.assertTrue(CollectionUtils.isNotEmpty(entities) && entities.size() == 5);
	}

	@Test
	public void testRemoveById() throws Exception {
		Smtp entity = new Smtp();
		this.smtpService.save(entity);
		logger.debug(">>Entity id is:" + entity.getId());
		Smtp e = this.smtpService.get(entity.getId());
		Assert.assertTrue(e != null);
	}

	@Test
	public void testRemoveEntity() throws Exception {
		Smtp entity = new Smtp();
		this.smtpService.save(entity);
		this.smtpService.remove(entity);
		List<Smtp> entities = this.smtpService.getAll();
		Assert.assertTrue(CollectionUtils.isEmpty(entities));
	}

	@Test
	public void testRemoveAllInBatch() throws Exception {
		for (int i = 0; i < 5; i++) {
			Smtp entity = new Smtp();
			this.smtpService.save(entity);
		}
		List<Smtp> entities = this.smtpService.getAll();
		Assert.assertTrue(CollectionUtils.isNotEmpty(entities) && entities.size() == 5);
		this.smtpService.removeAllInBatch();
		entities = this.smtpService.getAll();
		Assert.assertTrue(CollectionUtils.isEmpty(entities));
	}

	@Test
	public void testRemoveAll() throws Exception {
		for (int i = 0; i < 5; i++) {
			Smtp entity = new Smtp();
			this.smtpService.save(entity);
		}
		this.smtpService.removeAll();
		List<Smtp> entities = this.smtpService.getAll();
		Assert.assertTrue(CollectionUtils.isEmpty(entities));
	}

	@Test
	public void testRemoveListInBatch() throws Exception {
		List<Smtp> entities = new ArrayList<Smtp>();
		for (int i = 0; i < 5; i++) {
			Smtp entity = new Smtp();
			
			this.smtpService.save(entity);
			entities.add(entity);
		}
		this.smtpService.removeInBatch(entities);
		entities = this.smtpService.getAll();
		Assert.assertTrue(CollectionUtils.isEmpty(entities));
	}

	@Test
	public void testGetAll() throws Exception {
		for (int i = 0; i < 5; i++) {
			Smtp entity = new Smtp();
			this.smtpService.save(entity);
		}
		List<Smtp> entities = this.smtpService.getAll();
		Assert.assertTrue(CollectionUtils.isNotEmpty(entities) && entities.size() == 5);
	}

	@Test
	public void testGetPage() throws Exception {
		for (int i = 0; i < 25; i++) {
			Smtp entity = new Smtp();
			this.smtpService.save(entity);
		}
		Map<String, Object> searchParams = new HashMap<String, Object>();
		Page<Smtp> page = this.smtpService.getPage(searchParams, 1, 5);
		Assert.assertTrue(page != null && page.getSize() == 5);
		searchParams.put("EQ_name", "test-10");
		page = this.smtpService.getPage(searchParams, 1, 5);
		Assert.assertTrue(page != null && page.getTotalElements() == 1);
		searchParams = new HashMap<String, Object>();
		searchParams.put("LIKE_name", "test");
		page = this.smtpService.getPage(searchParams, 1, 5);

		Assert.assertTrue(page != null && page.getTotalElements() == 25 && page.getNumberOfElements() == 5);

	}

	@Test
	public void testGet() throws Exception {
		Long id = null;
		for (int i = 0; i < 25; i++) {
			Smtp entity = new Smtp();
			this.smtpService.save(entity);
			id = entity.getId();
		}
		Smtp e = this.smtpService.get(id);
		Assert.assertTrue(e != null);
	}

	@Test
	public void testGetByIds() throws Exception {
		List<Long> ids = new ArrayList<Long>();
		for (int i = 0; i < 25; i++) {
			Smtp entity = new Smtp();
			this.smtpService.save(entity);
			if (i < 5) {
				ids.add(entity.getId());
			}
		}
		List<Smtp> entities = this.smtpService.getAll(ids);
		Assert.assertTrue(entities != null && entities.size() == 5);
	}
}
