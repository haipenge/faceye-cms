package com.faceye.component.cms.service;

import java.util.List;

import com.faceye.component.cms.entity.Member;
import com.faceye.feature.service.BaseService;
/**
 * Member 服务接品<br>
 * @author @haipenge <br>
 * haipenge@gmail.com<br>
*  Create Date:2014年5月20日<br>
 */
public interface MemberService extends BaseService<Member,Long>{

	/**
	 * 
	 * @param teamId
	 * @return
	 * @Desc:
	 * @Author:haipenge
	 * @Date:2016年1月4日 下午9:19:13
	 */
	public List<Member> getMembersOfTeam(Long teamId);
	
	
	public Member getMemberByEmail(String email);
	
}/**@generate-service-source@**/
