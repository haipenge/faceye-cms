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
import org.junit.Assert;

import com.faceye.component.activity.entity.InnerCustomer;
import com.faceye.component.activity.service.InnerCustomerService;
import com.faceye.test.feature.service.BaseServiceTestCase;


/**
 * InnerCustomer  服务层测试用例
 * 
 * @author @haipenge haipenge@gmail.com Create Date:2014年5月20日
 */
public class InnerCustomerServiceTestCase extends BaseServiceTestCase {
	@Autowired
	private InnerCustomerService innerCustomerService = null;
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
		Assert.assertTrue(innerCustomerService != null);
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
		//this.innerCustomerService.removeAllInBatch();
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
		InnerCustomer entity = new InnerCustomer();
		this.innerCustomerService.save(entity);
		List<InnerCustomer> entites = this.innerCustomerService.getAll();
		Assert.assertTrue(CollectionUtils.isNotEmpty(entites));
	}

	@Test
	public void testSaveAndFlush() throws Exception {
		InnerCustomer entity = new InnerCustomer();
		this.innerCustomerService.save(entity);
		List<InnerCustomer> entites = this.innerCustomerService.getAll();
		Assert.assertTrue(CollectionUtils.isNotEmpty(entites));
	}

	@Test
	public void testMultiSave() throws Exception {
		for (int i = 0; i < 5; i++) {
			InnerCustomer entity = new InnerCustomer();
			this.innerCustomerService.save(entity);
		}
		List<InnerCustomer> entities = this.innerCustomerService.getAll();
		Assert.assertTrue(CollectionUtils.isNotEmpty(entities) && entities.size() == 5);
	}

	@Test
	public void testRemoveById() throws Exception {
		InnerCustomer entity = new InnerCustomer();
		this.innerCustomerService.save(entity);
		logger.debug(">>Entity id is:" + entity.getId());
		InnerCustomer e = this.innerCustomerService.get(entity.getId());
		Assert.assertTrue(e != null);
	}

	@Test
	public void testRemoveEntity() throws Exception {
		InnerCustomer entity = new InnerCustomer();
		this.innerCustomerService.save(entity);
		this.innerCustomerService.remove(entity);
		List<InnerCustomer> entities = this.innerCustomerService.getAll();
		Assert.assertTrue(CollectionUtils.isEmpty(entities));
	}

	@Test
	public void testRemoveAllInBatch() throws Exception {
		for (int i = 0; i < 5; i++) {
			InnerCustomer entity = new InnerCustomer();
			this.innerCustomerService.save(entity);
		}
		List<InnerCustomer> entities = this.innerCustomerService.getAll();
		Assert.assertTrue(CollectionUtils.isNotEmpty(entities) && entities.size() == 5);
		this.innerCustomerService.removeAllInBatch();
		entities = this.innerCustomerService.getAll();
		Assert.assertTrue(CollectionUtils.isEmpty(entities));
	}

	@Test
	public void testRemoveAll() throws Exception {
		for (int i = 0; i < 5; i++) {
			InnerCustomer entity = new InnerCustomer();
			this.innerCustomerService.save(entity);
		}
		this.innerCustomerService.removeAll();
		List<InnerCustomer> entities = this.innerCustomerService.getAll();
		Assert.assertTrue(CollectionUtils.isEmpty(entities));
	}

	@Test
	public void testRemoveListInBatch() throws Exception {
		List<InnerCustomer> entities = new ArrayList<InnerCustomer>();
		for (int i = 0; i < 5; i++) {
			InnerCustomer entity = new InnerCustomer();
			
			this.innerCustomerService.save(entity);
			entities.add(entity);
		}
		this.innerCustomerService.removeInBatch(entities);
		entities = this.innerCustomerService.getAll();
		Assert.assertTrue(CollectionUtils.isEmpty(entities));
	}

	@Test
	public void testGetAll() throws Exception {
		for (int i = 0; i < 5; i++) {
			InnerCustomer entity = new InnerCustomer();
			this.innerCustomerService.save(entity);
		}
		List<InnerCustomer> entities = this.innerCustomerService.getAll();
		Assert.assertTrue(CollectionUtils.isNotEmpty(entities) && entities.size() == 5);
	}

	@Test
	public void testGetPage() throws Exception {
		for (int i = 0; i < 25; i++) {
			InnerCustomer entity = new InnerCustomer();
			this.innerCustomerService.save(entity);
		}
		Map<String, Object> searchParams = new HashMap<String, Object>();
		Page<InnerCustomer> page = this.innerCustomerService.getPage(searchParams, 1, 5);
		Assert.assertTrue(page != null && page.getSize() == 5);
		searchParams.put("EQ_name", "test-10");
		page = this.innerCustomerService.getPage(searchParams, 1, 5);
		Assert.assertTrue(page != null && page.getTotalElements() == 1);
		searchParams = new HashMap<String, Object>();
		searchParams.put("LIKE_name", "test");
		page = this.innerCustomerService.getPage(searchParams, 1, 5);

		Assert.assertTrue(page != null && page.getTotalElements() == 25 && page.getNumberOfElements() == 5);

	}

	@Test
	public void testGet() throws Exception {
		Long id = null;
		for (int i = 0; i < 25; i++) {
			InnerCustomer entity = new InnerCustomer();
			this.innerCustomerService.save(entity);
			id = entity.getId();
		}
		InnerCustomer e = this.innerCustomerService.get(id);
		Assert.assertTrue(e != null);
	}

	@Test
	public void testGetByIds() throws Exception {
		List<Long> ids = new ArrayList<Long>();
		for (int i = 0; i < 25; i++) {
			InnerCustomer entity = new InnerCustomer();
			this.innerCustomerService.save(entity);
			if (i < 5) {
				ids.add(entity.getId());
			}
		}
		List<InnerCustomer> entities = this.innerCustomerService.getAll(ids);
		Assert.assertTrue(entities != null && entities.size() == 5);
	}
}
