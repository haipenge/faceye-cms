package com.faceye.test.component.activity.service;

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
import org.springframework.util.Assert;

import com.faceye.component.activity.entity.LuckerRecord;
import com.faceye.component.activity.service.LuckerRecordService;
import com.faceye.test.feature.service.BaseServiceTestCase;


/**
 * LuckerRecord  服务层测试用例
 * 
 * @author @haipenge haipenge@gmail.com Create Date:2014年5月20日
 */
public class LuckerRecordServiceTestCase extends BaseServiceTestCase {
	@Autowired
	private LuckerRecordService luckerRecordService = null;
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
		Assert.isTrue(luckerRecordService != null);
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
		//this.luckerRecordService.removeAllInBatch();
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
		LuckerRecord entity = new LuckerRecord();
		this.luckerRecordService.save(entity);
		List<LuckerRecord> entites = this.luckerRecordService.getAll();
		Assert.isTrue(CollectionUtils.isNotEmpty(entites));
	}

	@Test
	public void testSaveAndFlush() throws Exception {
		LuckerRecord entity = new LuckerRecord();
		this.luckerRecordService.save(entity);
		List<LuckerRecord> entites = this.luckerRecordService.getAll();
		Assert.isTrue(CollectionUtils.isNotEmpty(entites));
	}

	@Test
	public void testMultiSave() throws Exception {
		for (int i = 0; i < 5; i++) {
			LuckerRecord entity = new LuckerRecord();
			this.luckerRecordService.save(entity);
		}
		List<LuckerRecord> entities = this.luckerRecordService.getAll();
		Assert.isTrue(CollectionUtils.isNotEmpty(entities) && entities.size() == 5);
	}

	@Test
	public void testRemoveById() throws Exception {
		LuckerRecord entity = new LuckerRecord();
		this.luckerRecordService.save(entity);
		logger.debug(">>Entity id is:" + entity.getId());
		LuckerRecord e = this.luckerRecordService.get(entity.getId());
		Assert.isTrue(e != null);
	}

	@Test
	public void testRemoveEntity() throws Exception {
		LuckerRecord entity = new LuckerRecord();
		this.luckerRecordService.save(entity);
		this.luckerRecordService.remove(entity);
		List<LuckerRecord> entities = this.luckerRecordService.getAll();
		Assert.isTrue(CollectionUtils.isEmpty(entities));
	}

	@Test
	public void testRemoveAllInBatch() throws Exception {
		for (int i = 0; i < 5; i++) {
			LuckerRecord entity = new LuckerRecord();
			this.luckerRecordService.save(entity);
		}
		List<LuckerRecord> entities = this.luckerRecordService.getAll();
		Assert.isTrue(CollectionUtils.isNotEmpty(entities) && entities.size() == 5);
		this.luckerRecordService.removeAllInBatch();
		entities = this.luckerRecordService.getAll();
		Assert.isTrue(CollectionUtils.isEmpty(entities));
	}

	@Test
	public void testRemoveAll() throws Exception {
		for (int i = 0; i < 5; i++) {
			LuckerRecord entity = new LuckerRecord();
			this.luckerRecordService.save(entity);
		}
		this.luckerRecordService.removeAll();
		List<LuckerRecord> entities = this.luckerRecordService.getAll();
		Assert.isTrue(CollectionUtils.isEmpty(entities));
	}

	@Test
	public void testRemoveListInBatch() throws Exception {
		List<LuckerRecord> entities = new ArrayList<LuckerRecord>();
		for (int i = 0; i < 5; i++) {
			LuckerRecord entity = new LuckerRecord();
			
			this.luckerRecordService.save(entity);
			entities.add(entity);
		}
		this.luckerRecordService.removeInBatch(entities);
		entities = this.luckerRecordService.getAll();
		Assert.isTrue(CollectionUtils.isEmpty(entities));
	}

	@Test
	public void testGetAll() throws Exception {
		for (int i = 0; i < 5; i++) {
			LuckerRecord entity = new LuckerRecord();
			this.luckerRecordService.save(entity);
		}
		List<LuckerRecord> entities = this.luckerRecordService.getAll();
		Assert.isTrue(CollectionUtils.isNotEmpty(entities) && entities.size() == 5);
	}

	@Test
	public void testGetPage() throws Exception {
		for (int i = 0; i < 25; i++) {
			LuckerRecord entity = new LuckerRecord();
			this.luckerRecordService.save(entity);
		}
		Map<String, Object> searchParams = new HashMap<String, Object>();
		Page<LuckerRecord> page = this.luckerRecordService.getPage(searchParams, 1, 5);
		Assert.isTrue(page != null && page.getSize() == 5);
		searchParams.put("EQ_name", "test-10");
		page = this.luckerRecordService.getPage(searchParams, 1, 5);
		Assert.isTrue(page != null && page.getTotalElements() == 1);
		searchParams = new HashMap<String, Object>();
		searchParams.put("LIKE_name", "test");
		page = this.luckerRecordService.getPage(searchParams, 1, 5);

		Assert.isTrue(page != null && page.getTotalElements() == 25 && page.getNumberOfElements() == 5);

	}

	@Test
	public void testGet() throws Exception {
		Long id = null;
		for (int i = 0; i < 25; i++) {
			LuckerRecord entity = new LuckerRecord();
			this.luckerRecordService.save(entity);
			id = entity.getId();
		}
		LuckerRecord e = this.luckerRecordService.get(id);
		Assert.isTrue(e != null);
	}

	@Test
	public void testGetByIds() throws Exception {
		List<Long> ids = new ArrayList<Long>();
		for (int i = 0; i < 25; i++) {
			LuckerRecord entity = new LuckerRecord();
			this.luckerRecordService.save(entity);
			if (i < 5) {
				ids.add(entity.getId());
			}
		}
		List<LuckerRecord> entities = this.luckerRecordService.getAll(ids);
		Assert.isTrue(entities != null && entities.size() == 5);
	}
}