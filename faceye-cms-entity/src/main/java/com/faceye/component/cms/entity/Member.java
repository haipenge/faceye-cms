package com.faceye.component.cms.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.springframework.data.mongodb.core.mapping.Document;
/**
 * Member ORM 实体<br>
 * 数据库表:cms_member<br>
 * @author @haipenge <br>
 * haipenge@gmail.com<br>
*  Create Date:2014年5月21日<br>
 */
@Document(collection="cms_member")
public class Member implements Serializable {
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
    * 说明:姓名<br>
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
    * 说明:电子邮件<br>
    * 属性名: email<br>
    * 类型: String<br>
    * 数据库字段:email<br>
    * @author haipenge<br>
    */
    
	private  String email;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
	
	
}/**@generate-entity-source@**/
	
