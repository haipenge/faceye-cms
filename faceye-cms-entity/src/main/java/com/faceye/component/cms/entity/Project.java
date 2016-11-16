package com.faceye.component.cms.entity;

import java.io.Serializable;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.faceye.component.security.entity.User;
/**
 * Project ORM 实体<br>
 * 数据库表:cms_project<br>
 * @author @haipenge <br>
 * haipenge@gmail.com<br>
*  Create Date:2014年5月21日<br>
 */
@Document(collection="cms_project")
public class Project implements Serializable {
	private static final long serialVersionUID = 8926119711730830203L;
	@Id
	private  Long id=null;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

   /**
    * 说明:名<br>
    * 属性名: name<br>
    * 类型: String<br>
    * 数据库字段:name<br>
    * @author haipenge<br>
    */
    
	private  String name;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * 创建人
	 */
	@DBRef
	private User user=null;
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	/**
	 * 项目所属团队
	 */
	@DBRef
	private Team team=null;
	public Team getTeam() {
		return team;
	}
	public void setTeam(Team team) {
		this.team = team;
	}
	
	
}/**@generate-entity-source@**/
	
