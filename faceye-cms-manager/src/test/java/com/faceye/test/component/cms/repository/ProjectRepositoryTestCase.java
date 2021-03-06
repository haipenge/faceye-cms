package com.faceye.test.component.cms.repository;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.Assert;

import com.faceye.component.cms.entity.Project;
import com.faceye.component.cms.repository.mongo.ProjectRepository;
import com.faceye.test.feature.repository.BaseRepositoryTestCase;
/**
 * Project DAO 测试
 * @author @haipenge 
 * haipenge@gmail.com
*  Create Date:2014年5月26日
 */
public class ProjectRepositoryTestCase extends BaseRepositoryTestCase {
	@Autowired
	private ProjectRepository projectRepository = null;

	@Before
	public void before() throws Exception {
		//this.projectRepository.deleteAll();
	}

	@After
	public void after() throws Exception {

	}

	@Test
	public void testSave() throws Exception {
		Project entity = new Project();
		this.projectRepository.save(entity);
		Iterable<Project> entities = this.projectRepository.findAll();
		Assert.assertTrue(entities.iterator().hasNext());
	}

	@Test
	public void testDelete() throws Exception {
		Project entity = new Project();
		this.projectRepository.save(entity);
        this.projectRepository.deleteById(entity.getId());
        Iterable<Project> entities = this.projectRepository.findAll();
		Assert.assertTrue(!entities.iterator().hasNext());
	}

	@Test
	public void testFindOne() throws Exception {
		Project entity = new Project();
		this.projectRepository.save(entity);
		Project project=this.projectRepository.findById(entity.getId()).get();
		Assert.assertTrue(project!=null);
	}

	
}
