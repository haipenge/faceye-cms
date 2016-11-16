package com.faceye.test.component.cms.repository;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.faceye.component.cms.entity.Member;
import com.faceye.component.cms.repository.mongo.MemberRepository;
import com.faceye.test.feature.repository.BaseRepositoryTestCase;
/**
 * Member DAO 测试
 * @author @haipenge 
 * haipenge@gmail.com
*  Create Date:2014年5月26日
 */
public class MemberRepositoryTestCase extends BaseRepositoryTestCase {
	@Autowired
	private MemberRepository memberRepository = null;

	@Before
	public void before() throws Exception {
		//this.memberRepository.deleteAll();
	}

	@After
	public void after() throws Exception {

	}

	@Test
	public void testSave() throws Exception {
		Member entity = new Member();
		this.memberRepository.save(entity);
		Iterable<Member> entities = this.memberRepository.findAll();
		Assert.isTrue(entities.iterator().hasNext());
	}

	@Test
	public void testDelete() throws Exception {
		Member entity = new Member();
		this.memberRepository.save(entity);
        this.memberRepository.delete(entity.getId());
        Iterable<Member> entities = this.memberRepository.findAll();
		Assert.isTrue(!entities.iterator().hasNext());
	}

	@Test
	public void testFindOne() throws Exception {
		Member entity = new Member();
		this.memberRepository.save(entity);
		Member member=this.memberRepository.findOne(entity.getId());
		Assert.isTrue(member!=null);
	}

	
}
