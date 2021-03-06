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

import com.faceye.component.cms.entity.WorkRecord;
import com.faceye.component.cms.service.WorkRecordService;
import com.faceye.test.feature.service.BaseServiceTestCase;


/**
 * WorkRecord  服务层测试用例
 * 
 * @author @haipenge haipenge@gmail.com Create Date:2014年5月20日
 */
public class WorkRecordServiceTestCase extends BaseServiceTestCase {
	@Autowired
	private WorkRecordService workRecordService = null;
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
		Assert.assertTrue(workRecordService != null);
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
		//this.workRecordService.removeAllInBatch();
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
		WorkRecord entity = new WorkRecord();
		this.workRecordService.save(entity);
		List<WorkRecord> entites = this.workRecordService.getAll();
		Assert.assertTrue(CollectionUtils.isNotEmpty(entites));
	}

	@Test
	public void testSaveAndFlush() throws Exception {
		WorkRecord entity = new WorkRecord();
		this.workRecordService.save(entity);
		List<WorkRecord> entites = this.workRecordService.getAll();
		Assert.assertTrue(CollectionUtils.isNotEmpty(entites));
	}

	@Test
	public void testMultiSave() throws Exception {
		for (int i = 0; i < 5; i++) {
			WorkRecord entity = new WorkRecord();
			this.workRecordService.save(entity);
		}
		List<WorkRecord> entities = this.workRecordService.getAll();
		Assert.assertTrue(CollectionUtils.isNotEmpty(entities) && entities.size() == 5);
	}

	@Test
	public void testRemoveById() throws Exception {
		WorkRecord entity = new WorkRecord();
		this.workRecordService.save(entity);
		logger.debug(">>Entity id is:" + entity.getId());
		WorkRecord e = this.workRecordService.get(entity.getId());
		Assert.assertTrue(e != null);
	}

	@Test
	public void testRemoveEntity() throws Exception {
		WorkRecord entity = new WorkRecord();
		this.workRecordService.save(entity);
		this.workRecordService.remove(entity);
		List<WorkRecord> entities = this.workRecordService.getAll();
		Assert.assertTrue(CollectionUtils.isEmpty(entities));
	}

	@Test
	public void testRemoveAllInBatch() throws Exception {
		for (int i = 0; i < 5; i++) {
			WorkRecord entity = new WorkRecord();
			this.workRecordService.save(entity);
		}
		List<WorkRecord> entities = this.workRecordService.getAll();
		Assert.assertTrue(CollectionUtils.isNotEmpty(entities) && entities.size() == 5);
		this.workRecordService.removeAllInBatch();
		entities = this.workRecordService.getAll();
		Assert.assertTrue(CollectionUtils.isEmpty(entities));
	}

	@Test
	public void testRemoveAll() throws Exception {
		for (int i = 0; i < 5; i++) {
			WorkRecord entity = new WorkRecord();
			this.workRecordService.save(entity);
		}
		this.workRecordService.removeAll();
		List<WorkRecord> entities = this.workRecordService.getAll();
		Assert.assertTrue(CollectionUtils.isEmpty(entities));
	}

	@Test
	public void testRemoveListInBatch() throws Exception {
		List<WorkRecord> entities = new ArrayList<WorkRecord>();
		for (int i = 0; i < 5; i++) {
			WorkRecord entity = new WorkRecord();
			
			this.workRecordService.save(entity);
			entities.add(entity);
		}
		this.workRecordService.removeInBatch(entities);
		entities = this.workRecordService.getAll();
		Assert.assertTrue(CollectionUtils.isEmpty(entities));
	}

	@Test
	public void testGetAll() throws Exception {
		for (int i = 0; i < 5; i++) {
			WorkRecord entity = new WorkRecord();
			this.workRecordService.save(entity);
		}
		List<WorkRecord> entities = this.workRecordService.getAll();
		Assert.assertTrue(CollectionUtils.isNotEmpty(entities) && entities.size() == 5);
	}

	@Test
	public void testGetPage() throws Exception {
		for (int i = 0; i < 25; i++) {
			WorkRecord entity = new WorkRecord();
			this.workRecordService.save(entity);
		}
		Map<String, Object> searchParams = new HashMap<String, Object>();
		Page<WorkRecord> page = this.workRecordService.getPage(searchParams, 1, 5);
		Assert.assertTrue(page != null && page.getSize() == 5);
		searchParams.put("EQ_name", "test-10");
		page = this.workRecordService.getPage(searchParams, 1, 5);
		Assert.assertTrue(page != null && page.getTotalElements() == 1);
		searchParams = new HashMap<String, Object>();
		searchParams.put("LIKE_name", "test");
		page = this.workRecordService.getPage(searchParams, 1, 5);

		Assert.assertTrue(page != null && page.getTotalElements() == 25 && page.getNumberOfElements() == 5);

	}

	@Test
	public void testGet() throws Exception {
		Long id = null;
		for (int i = 0; i < 25; i++) {
			WorkRecord entity = new WorkRecord();
			this.workRecordService.save(entity);
			id = entity.getId();
		}
		WorkRecord e = this.workRecordService.get(id);
		Assert.assertTrue(e != null);
	}

	@Test
	public void testGetByIds() throws Exception {
		List<Long> ids = new ArrayList<Long>();
		for (int i = 0; i < 25; i++) {
			WorkRecord entity = new WorkRecord();
			this.workRecordService.save(entity);
			if (i < 5) {
				ids.add(entity.getId());
			}
		}
		List<WorkRecord> entities = this.workRecordService.getAll(ids);
		Assert.assertTrue(entities != null && entities.size() == 5);
	}
}
