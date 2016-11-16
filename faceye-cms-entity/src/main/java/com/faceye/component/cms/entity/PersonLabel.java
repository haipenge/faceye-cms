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
 * PersonLabel ORM 实体<br>
 * 数据库表:cms_personLabel<br>
 * @author @haipenge <br>
 * haipenge@gmail.com<br>
*  Create Date:2014年5月21日<br>
 */
@Document(collection="cms_person_label")
public class PersonLabel implements Serializable {
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
    * 说明:标签<br>
    * 属性名: label<br>
    * 类型: String<br>
    * 数据库字段:label<br>
    * @author haipenge<br>
    */
    
	private  String label;
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	
}/**@generate-entity-source@**/
	
