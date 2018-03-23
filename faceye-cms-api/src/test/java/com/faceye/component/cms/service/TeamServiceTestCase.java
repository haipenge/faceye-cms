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

import com.faceye.component.cms.entity.Team;
import com.faceye.component.cms.service.TeamService;
import com.faceye.test.feature.service.BaseServiceTestCase;


/**
 * Team  服务层测试用例
 * 
 * @author @haipenge haipenge@gmail.com Create Date:2014年5月20日
 */
public class TeamServiceTestCase extends BaseServiceTestCase {
	@Autowired
	private TeamService teamService = null;
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
		Assert.assertTrue(teamService != null);
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
		//this.teamService.removeAllInBatch();
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
		Team entity = new Team();
		this.teamService.save(entity);
		List<Team> entites = this.teamService.getAll();
		Assert.assertTrue(CollectionUtils.isNotEmpty(entites));
	}

	@Test
	public void testSaveAndFlush() throws Exception {
		Team entity = new Team();
		this.teamService.save(entity);
		List<Team> entites = this.teamService.getAll();
		Assert.assertTrue(CollectionUtils.isNotEmpty(entites));
	}

	@Test
	public void testMultiSave() throws Exception {
		for (int i = 0; i < 5; i++) {
			Team entity = new Team();
			this.teamService.save(entity);
		}
		List<Team> entities = this.teamService.getAll();
		Assert.assertTrue(CollectionUtils.isNotEmpty(entities) && entities.size() == 5);
	}

	@Test
	public void testRemoveById() throws Exception {
		Team entity = new Team();
		this.teamService.save(entity);
		logger.debug(">>Entity id is:" + entity.getId());
		Team e = this.teamService.get(entity.getId());
		Assert.assertTrue(e != null);
	}

	@Test
	public void testRemoveEntity() throws Exception {
		Team entity = new Team();
		this.teamService.save(entity);
		this.teamService.remove(entity);
		List<Team> entities = this.teamService.getAll();
		Assert.assertTrue(CollectionUtils.isEmpty(entities));
	}

	@Test
	public void testRemoveAllInBatch() throws Exception {
		for (int i = 0; i < 5; i++) {
			Team entity = new Team();
			this.teamService.save(entity);
		}
		List<Team> entities = this.teamService.getAll();
		Assert.assertTrue(CollectionUtils.isNotEmpty(entities) && entities.size() == 5);
		this.teamService.removeAllInBatch();
		entities = this.teamService.getAll();
		Assert.assertTrue(CollectionUtils.isEmpty(entities));
	}

	@Test
	public void testRemoveAll() throws Exception {
		for (int i = 0; i < 5; i++) {
			Team entity = new Team();
			this.teamService.save(entity);
		}
		this.teamService.removeAll();
		List<Team> entities = this.teamService.getAll();
		Assert.assertTrue(CollectionUtils.isEmpty(entities));
	}

	@Test
	public void testRemoveListInBatch() throws Exception {
		List<Team> entities = new ArrayList<Team>();
		for (int i = 0; i < 5; i++) {
			Team entity = new Team();
			
			this.teamService.save(entity);
			entities.add(entity);
		}
		this.teamService.removeInBatch(entities);
		entities = this.teamService.getAll();
		Assert.assertTrue(CollectionUtils.isEmpty(entities));
	}

	@Test
	public void testGetAll() throws Exception {
		for (int i = 0; i < 5; i++) {
			Team entity = new Team();
			this.teamService.save(entity);
		}
		List<Team> entities = this.teamService.getAll();
		Assert.assertTrue(CollectionUtils.isNotEmpty(entities) && entities.size() == 5);
	}

	@Test
	public void testGetPage() throws Exception {
		for (int i = 0; i < 25; i++) {
			Team entity = new Team();
			this.teamService.save(entity);
		}
		Map<String, Object> searchParams = new HashMap<String, Object>();
		Page<Team> page = this.teamService.getPage(searchParams, 1, 5);
		Assert.assertTrue(page != null && page.getSize() == 5);
		searchParams.put("EQ_name", "test-10");
		page = this.teamService.getPage(searchParams, 1, 5);
		Assert.assertTrue(page != null && page.getTotalElements() == 1);
		searchParams = new HashMap<String, Object>();
		searchParams.put("LIKE_name", "test");
		page = this.teamService.getPage(searchParams, 1, 5);

		Assert.assertTrue(page != null && page.getTotalElements() == 25 && page.getNumberOfElements() == 5);

	}

	@Test
	public void testGet() throws Exception {
		Long id = null;
		for (int i = 0; i < 25; i++) {
			Team entity = new Team();
			this.teamService.save(entity);
			id = entity.getId();
		}
		Team e = this.teamService.get(id);
		Assert.assertTrue(e != null);
	}

	@Test
	public void testGetByIds() throws Exception {
		List<Long> ids = new ArrayList<Long>();
		for (int i = 0; i < 25; i++) {
			Team entity = new Team();
			this.teamService.save(entity);
			if (i < 5) {
				ids.add(entity.getId());
			}
		}
		List<Team> entities = this.teamService.getAll(ids);
		Assert.assertTrue(entities != null && entities.size() == 5);
	}
}
