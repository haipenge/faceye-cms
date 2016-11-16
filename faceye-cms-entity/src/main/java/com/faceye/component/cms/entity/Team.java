package com.faceye.component.cms.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.faceye.component.security.entity.User;

/**
 * Team ORM 实体<br>
 * 数据库表:cms_team<br>
 * 
 * @author @haipenge <br>
 *         haipenge@gmail.com<br>
 *         Create Date:2014年5月21日<br>
 */
@Document(collection = "cms_team")
public class Team implements Serializable {
	private static final long serialVersionUID = 8926119711730830203L;
	@Id
	private Long id = null;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * 说明:团队名称<br>
	 * 属性名: name<br>
	 * 类型: String<br>
	 * 数据库字段:name<br>
	 * 
	 * @author haipenge<br>
	 */

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 说明:说明<br>
	 * 属性名: remark<br>
	 * 类型: String<br>
	 * 数据库字段:remark<br>
	 * 
	 * @author haipenge<br>
	 */

	private String remark;

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * 创建人
	 */
	@DBRef
	private User user = null;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * 团队成员
	 */
	@DBRef
	private List<Member> members = new ArrayList<Member>();

	public List<Member> getMembers() {
		return members;
	}

	public void setMembers(List<Member> members) {
		this.members = members;
	}

}/** @generate-entity-source@ **/
