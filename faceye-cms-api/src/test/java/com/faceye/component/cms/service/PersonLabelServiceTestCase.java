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
import org.springframework.util.Assert;

import com.faceye.component.cms.entity.PersonLabel;
import com.faceye.component.cms.service.PersonLabelService;
import com.faceye.test.feature.service.BaseServiceTestCase;


/**
 * PersonLabel  服务层测试用例
 * 
 * @author @haipenge haipenge@gmail.com Create Date:2014年5月20日
 */
public class PersonLabelServiceTestCase extends BaseServiceTestCase {
	@Autowired
	private PersonLabelService personLabelService = null;
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
		Assert.isTrue(personLabelService != null);
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
		//this.personLabelService.removeAllInBatch();
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
		PersonLabel entity = new PersonLabel();
		this.personLabelService.save(entity);
		List<PersonLabel> entites = this.personLabelService.getAll();
		Assert.isTrue(CollectionUtils.isNotEmpty(entites));
	}

	@Test
	public void testSaveAndFlush() throws Exception {
		PersonLabel entity = new PersonLabel();
		this.personLabelService.save(entity);
		List<PersonLabel> entites = this.personLabelService.getAll();
		Assert.isTrue(CollectionUtils.isNotEmpty(entites));
	}

	@Test
	public void testMultiSave() throws Exception {
		for (int i = 0; i < 5; i++) {
			PersonLabel entity = new PersonLabel();
			this.personLabelService.save(entity);
		}
		List<PersonLabel> entities = this.personLabelService.getAll();
		Assert.isTrue(CollectionUtils.isNotEmpty(entities) && entities.size() == 5);
	}

	@Test
	public void testRemoveById() throws Exception {
		PersonLabel entity = new PersonLabel();
		this.personLabelService.save(entity);
		logger.debug(">>Entity id is:" + entity.getId());
		PersonLabel e = this.personLabelService.get(entity.getId());
		Assert.isTrue(e != null);
	}

	@Test
	public void testRemoveEntity() throws Exception {
		PersonLabel entity = new PersonLabel();
		this.personLabelService.save(entity);
		this.personLabelService.remove(entity);
		List<PersonLabel> entities = this.personLabelService.getAll();
		Assert.isTrue(CollectionUtils.isEmpty(entities));
	}

	@Test
	public void testRemoveAllInBatch() throws Exception {
		for (int i = 0; i < 5; i++) {
			PersonLabel entity = new PersonLabel();
			this.personLabelService.save(entity);
		}
		List<PersonLabel> entities = this.personLabelService.getAll();
		Assert.isTrue(CollectionUtils.isNotEmpty(entities) && entities.size() == 5);
		this.personLabelService.removeAllInBatch();
		entities = this.personLabelService.getAll();
		Assert.isTrue(CollectionUtils.isEmpty(entities));
	}

	@Test
	public void testRemoveAll() throws Exception {
		for (int i = 0; i < 5; i++) {
			PersonLabel entity = new PersonLabel();
			this.personLabelService.save(entity);
		}
		this.personLabelService.removeAll();
		List<PersonLabel> entities = this.personLabelService.getAll();
		Assert.isTrue(CollectionUtils.isEmpty(entities));
	}

	@Test
	public void testRemoveListInBatch() throws Exception {
		List<PersonLabel> entities = new ArrayList<PersonLabel>();
		for (int i = 0; i < 5; i++) {
			PersonLabel entity = new PersonLabel();
			
			this.personLabelService.save(entity);
			entities.add(entity);
		}
		this.personLabelService.removeInBatch(entities);
		entities = this.personLabelService.getAll();
		Assert.isTrue(CollectionUtils.isEmpty(entities));
	}

	@Test
	public void testGetAll() throws Exception {
		for (int i = 0; i < 5; i++) {
			PersonLabel entity = new PersonLabel();
			this.personLabelService.save(entity);
		}
		List<PersonLabel> entities = this.personLabelService.getAll();
		Assert.isTrue(CollectionUtils.isNotEmpty(entities) && entities.size() == 5);
	}

	@Test
	public void testGetPage() throws Exception {
		for (int i = 0; i < 25; i++) {
			PersonLabel entity = new PersonLabel();
			this.personLabelService.save(entity);
		}
		Map<String, Object> searchParams = new HashMap<String, Object>();
		Page<PersonLabel> page = this.personLabelService.getPage(searchParams, 1, 5);
		Assert.isTrue(page != null && page.getSize() == 5);
		searchParams.put("EQ_name", "test-10");
		page = this.personLabelService.getPage(searchParams, 1, 5);
		Assert.isTrue(page != null && page.getTotalElements() == 1);
		searchParams = new HashMap<String, Object>();
		searchParams.put("LIKE_name", "test");
		page = this.personLabelService.getPage(searchParams, 1, 5);

		Assert.isTrue(page != null && page.getTotalElements() == 25 && page.getNumberOfElements() == 5);

	}

	@Test
	public void testGet() throws Exception {
		Long id = null;
		for (int i = 0; i < 25; i++) {
			PersonLabel entity = new PersonLabel();
			this.personLabelService.save(entity);
			id = entity.getId();
		}
		PersonLabel e = this.personLabelService.get(id);
		Assert.isTrue(e != null);
	}

	@Test
	public void testGetByIds() throws Exception {
		List<Long> ids = new ArrayList<Long>();
		for (int i = 0; i < 25; i++) {
			PersonLabel entity = new PersonLabel();
			this.personLabelService.save(entity);
			if (i < 5) {
				ids.add(entity.getId());
			}
		}
		List<PersonLabel> entities = this.personLabelService.getAll(ids);
		Assert.isTrue(entities != null && entities.size() == 5);
	}
}
