package com.faceye.test.component.cms.repository;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.Assert;

import com.faceye.component.cms.entity.Team;
import com.faceye.component.cms.repository.mongo.TeamRepository;
import com.faceye.test.feature.repository.BaseRepositoryTestCase;
/**
 * Team DAO 测试
 * @author @haipenge 
 * haipenge@gmail.com
*  Create Date:2014年5月26日
 */
public class TeamRepositoryTestCase extends BaseRepositoryTestCase {
	@Autowired
	private TeamRepository teamRepository = null;

	@Before
	public void before() throws Exception {
		//this.teamRepository.deleteAll();
	}

	@After
	public void after() throws Exception {

	}

	@Test
	public void testSave() throws Exception {
		Team entity = new Team();
		this.teamRepository.save(entity);
		Iterable<Team> entities = this.teamRepository.findAll();
		Assert.assertTrue(entities.iterator().hasNext());
	}

	@Test
	public void testDelete() throws Exception {
		Team entity = new Team();
		this.teamRepository.save(entity);
        this.teamRepository.deleteById(entity.getId());
        Iterable<Team> entities = this.teamRepository.findAll();
		Assert.assertTrue(!entities.iterator().hasNext());
	}

	@Test
	public void testFindOne() throws Exception {
		Team entity = new Team();
		this.teamRepository.save(entity);
		Team team=this.teamRepository.findById(entity.getId()).get();
		Assert.assertTrue(team!=null);
	}

	
}
