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

import com.faceye.component.cms.entity.Member;
import com.faceye.component.cms.service.MemberService;
import com.faceye.test.feature.service.BaseServiceTestCase;


/**
 * Member  服务层测试用例
 * 
 * @author @haipenge haipenge@gmail.com Create Date:2014年5月20日
 */
public class MemberServiceTestCase extends BaseServiceTestCase {
	@Autowired
	private MemberService memberService = null;
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
		Assert.isTrue(memberService != null);
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
		//this.memberService.removeAllInBatch();
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
		Member entity = new Member();
		this.memberService.save(entity);
		List<Member> entites = this.memberService.getAll();
		Assert.isTrue(CollectionUtils.isNotEmpty(entites));
	}

	@Test
	public void testSaveAndFlush() throws Exception {
		Member entity = new Member();
		this.memberService.save(entity);
		List<Member> entites = this.memberService.getAll();
		Assert.isTrue(CollectionUtils.isNotEmpty(entites));
	}

	@Test
	public void testMultiSave() throws Exception {
		for (int i = 0; i < 5; i++) {
			Member entity = new Member();
			this.memberService.save(entity);
		}
		List<Member> entities = this.memberService.getAll();
		Assert.isTrue(CollectionUtils.isNotEmpty(entities) && entities.size() == 5);
	}

	@Test
	public void testRemoveById() throws Exception {
		Member entity = new Member();
		this.memberService.save(entity);
		logger.debug(">>Entity id is:" + entity.getId());
		Member e = this.memberService.get(entity.getId());
		Assert.isTrue(e != null);
	}

	@Test
	public void testRemoveEntity() throws Exception {
		Member entity = new Member();
		this.memberService.save(entity);
		this.memberService.remove(entity);
		List<Member> entities = this.memberService.getAll();
		Assert.isTrue(CollectionUtils.isEmpty(entities));
	}

	@Test
	public void testRemoveAllInBatch() throws Exception {
		for (int i = 0; i < 5; i++) {
			Member entity = new Member();
			this.memberService.save(entity);
		}
		List<Member> entities = this.memberService.getAll();
		Assert.isTrue(CollectionUtils.isNotEmpty(entities) && entities.size() == 5);
		this.memberService.removeAllInBatch();
		entities = this.memberService.getAll();
		Assert.isTrue(CollectionUtils.isEmpty(entities));
	}

	@Test
	public void testRemoveAll() throws Exception {
		for (int i = 0; i < 5; i++) {
			Member entity = new Member();
			this.memberService.save(entity);
		}
		this.memberService.removeAll();
		List<Member> entities = this.memberService.getAll();
		Assert.isTrue(CollectionUtils.isEmpty(entities));
	}

	@Test
	public void testRemoveListInBatch() throws Exception {
		List<Member> entities = new ArrayList<Member>();
		for (int i = 0; i < 5; i++) {
			Member entity = new Member();
			
			this.memberService.save(entity);
			entities.add(entity);
		}
		this.memberService.removeInBatch(entities);
		entities = this.memberService.getAll();
		Assert.isTrue(CollectionUtils.isEmpty(entities));
	}

	@Test
	public void testGetAll() throws Exception {
		for (int i = 0; i < 5; i++) {
			Member entity = new Member();
			this.memberService.save(entity);
		}
		List<Member> entities = this.memberService.getAll();
		Assert.isTrue(CollectionUtils.isNotEmpty(entities) && entities.size() == 5);
	}

	@Test
	public void testGetPage() throws Exception {
		for (int i = 0; i < 25; i++) {
			Member entity = new Member();
			this.memberService.save(entity);
		}
		Map<String, Object> searchParams = new HashMap<String, Object>();
		Page<Member> page = this.memberService.getPage(searchParams, 1, 5);
		Assert.isTrue(page != null && page.getSize() == 5);
		searchParams.put("EQ_name", "test-10");
		page = this.memberService.getPage(searchParams, 1, 5);
		Assert.isTrue(page != null && page.getTotalElements() == 1);
		searchParams = new HashMap<String, Object>();
		searchParams.put("LIKE_name", "test");
		page = this.memberService.getPage(searchParams, 1, 5);

		Assert.isTrue(page != null && page.getTotalElements() == 25 && page.getNumberOfElements() == 5);

	}

	@Test
	public void testGet() throws Exception {
		Long id = null;
		for (int i = 0; i < 25; i++) {
			Member entity = new Member();
			this.memberService.save(entity);
			id = entity.getId();
		}
		Member e = this.memberService.get(id);
		Assert.isTrue(e != null);
	}

	@Test
	public void testGetByIds() throws Exception {
		List<Long> ids = new ArrayList<Long>();
		for (int i = 0; i < 25; i++) {
			Member entity = new Member();
			this.memberService.save(entity);
			if (i < 5) {
				ids.add(entity.getId());
			}
		}
		List<Member> entities = this.memberService.getAll(ids);
		Assert.isTrue(entities != null && entities.size() == 5);
	}
}
