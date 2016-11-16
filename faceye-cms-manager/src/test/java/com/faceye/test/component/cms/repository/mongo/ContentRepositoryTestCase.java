package com.faceye.test.component.cms.repository.mongo;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.faceye.component.cms.entity.Content;
import com.faceye.component.cms.repository.mongo.ContentRepository;
import com.faceye.test.feature.repository.BaseRepositoryTestCase;
/**
 * Content Repository 测试
 * @author haipeng 
 * 联系:haipenge@gmail.com
*  创建日期:2015-7-25 9:40:29<br>
 */
public class ContentRepositoryTestCase extends BaseRepositoryTestCase {
	@Autowired
	private ContentRepository contentRepository = null;

	@Before
	public void before() throws Exception {
		//this.contentRepository.deleteAll();
	}

	@After
	public void after() throws Exception {

	}

	@Test
	public void testSave() throws Exception {
		Content content = new Content();
		this.contentRepository.save(content);
		Iterable<Content> contents = this.contentRepository.findAll();
		Assert.isTrue(contents.iterator().hasNext());
	}

	@Test
	public void testDelete() throws Exception {
		Content content = new Content();
		this.contentRepository.save(content);
        this.contentRepository.delete(content.getId());
        Iterable<Content> contents = this.contentRepository.findAll();
		Assert.isTrue(!contents.iterator().hasNext());
	}

	@Test
	public void testFindOne() throws Exception {
		Content content = new Content();
		this.contentRepository.save(content);
		content=this.contentRepository.findOne(content.getId());
		Assert.isTrue(content!=null);
	}

	
}
