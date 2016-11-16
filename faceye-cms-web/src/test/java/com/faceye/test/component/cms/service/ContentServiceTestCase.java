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

import com.faceye.component.cms.entity.Content;
import com.faceye.component.cms.service.ContentService;
import com.faceye.test.feature.service.BaseServiceTestCase;



/**
 * Content  服务层测试用例
 * 
 * @author haipeng 
 * 联系人:haipenge@gmail.com 
 * 创建日期:2015-7-25 9:40:29
 */
public class ContentServiceTestCase extends BaseServiceTestCase {
	@Autowired
	private ContentService contentService = null;
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
		Assert.isTrue(contentService != null);
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
		//this.contentService.removeAllInBatch();
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
		Content content = new Content();
		this.contentService.save(content);
		List<Content> contents = this.contentService.getAll();
		Assert.isTrue(CollectionUtils.isNotEmpty(contents));
	}

	@Test
	public void testSaveAndFlush() throws Exception {
		Content content = new Content();
		this.contentService.save(content);
		List<Content> contents = this.contentService.getAll();
		Assert.isTrue(CollectionUtils.isNotEmpty(contents));
	}

	@Test
	public void testMultiSave() throws Exception {
		for (int i = 0; i < 5; i++) {
			Content content = new Content();
			this.contentService.save(content);
		}
		List<Content> contents = this.contentService.getAll();
		Assert.isTrue(CollectionUtils.isNotEmpty(contents) && contents.size() == 5);
	}

	@Test
	public void testRemoveById() throws Exception {
		Content content = new Content();
		this.contentService.save(content);
		logger.debug(">>Entity id is:" + content.getId());
		Content e = this.contentService.get(content.getId());
		Assert.isTrue(e != null);
	}

	@Test
	public void testRemoveEntity() throws Exception {
		Content content = new Content();
		this.contentService.save(content);
		this.contentService.remove(content);
		List<Content> contents = this.contentService.getAll();
		Assert.isTrue(CollectionUtils.isEmpty(contents));
	}

	@Test
	public void testRemoveAllInBatch() throws Exception {
		for (int i = 0; i < 5; i++) {
			Content content = new Content();
			this.contentService.save(content);
		}
		List<Content> contents = this.contentService.getAll();
		Assert.isTrue(CollectionUtils.isNotEmpty(contents) && contents.size() == 5);
		this.contentService.removeAllInBatch();
		contents = this.contentService.getAll();
		Assert.isTrue(CollectionUtils.isEmpty(contents));
	}

	@Test
	public void testRemoveAll() throws Exception {
		for (int i = 0; i < 5; i++) {
			Content content = new Content();
			this.contentService.save(content);
		}
		this.contentService.removeAll();
		List<Content> contents = this.contentService.getAll();
		Assert.isTrue(CollectionUtils.isEmpty(contents));
	}

	@Test
	public void testRemoveListInBatch() throws Exception {
		List<Content> contents = new ArrayList<Content>();
		for (int i = 0; i < 5; i++) {
			Content content = new Content();
			
			this.contentService.save(content);
			contents.add(content);
		}
		this.contentService.removeInBatch(contents);
		contents = this.contentService.getAll();
		Assert.isTrue(CollectionUtils.isEmpty(contents));
	}

	@Test
	public void testGetAll() throws Exception {
		for (int i = 0; i < 5; i++) {
			Content content = new Content();
			this.contentService.save(content);
		}
		List<Content> contents = this.contentService.getAll();
		Assert.isTrue(CollectionUtils.isNotEmpty(contents) && contents.size() == 5);
	}

	@Test
	public void testGetPage() throws Exception {
		for (int i = 0; i < 25; i++) {
			Content content = new Content();
			this.contentService.save(content);
		}
		Map<String, Object> searchParams = new HashMap<String, Object>();
		Page<Content> page = this.contentService.getPage(searchParams, 1, 5);
		Assert.isTrue(page != null && page.getSize() == 5);
		searchParams.put("EQ_name", "test-10");
		page = this.contentService.getPage(searchParams, 1, 5);
		Assert.isTrue(page != null && page.getTotalElements() == 1);
		searchParams = new HashMap<String, Object>();
		searchParams.put("LIKE_name", "test");
		page = this.contentService.getPage(searchParams, 1, 5);
		Assert.isTrue(page != null && page.getTotalElements() == 25 && page.getNumberOfElements() == 5);
	}

	@Test
	public void testGet() throws Exception {
		Long id = null;
		for (int i = 0; i < 25; i++) {
			Content content = new Content();
			this.contentService.save(content);
			id = content.getId();
		}
		Content e = this.contentService.get(id);
		Assert.isTrue(e != null);
	}

	@Test
	public void testGetByIds() throws Exception {
		List<Long> ids = new ArrayList<Long>();
		for (int i = 0; i < 25; i++) {
			Content content = new Content();
			this.contentService.save(content);
			if (i < 5) {
				ids.add(content.getId());
			}
		}
		List<Content> contents = this.contentService.getAll(ids);
		Assert.isTrue(contents != null && contents.size() == 5);
	}
}
