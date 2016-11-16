package com.faceye.component.cms.entity;

import java.io.Serializable;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.faceye.component.security.entity.User;

/**
 * Smtp ORM 实体<br>
 * 数据库表:cms_smtp<br>
 * 
 * @author @haipenge <br>
 *         haipenge@gmail.com<br>
 *         Create Date:2014年5月21日<br>
 */
@Document(collection = "cms_smtp")
public class Smtp implements Serializable {
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
	 * 说明:SMTP服务器<br>
	 * 属性名: smtp<br>
	 * 类型: String<br>
	 * 数据库字段:smtp<br>
	 * 
	 * @author haipenge<br>
	 */

	private String smtp;

	public String getSmtp() {
		return smtp;
	}

	public void setSmtp(String smtp) {
		this.smtp = smtp;
	}

	/**
	 * 说明:是否启用SSL<br>
	 * 属性名: isSSL<br>
	 * 类型: Boolean<br>
	 * 数据库字段:is_ssl<br>
	 * 
	 * @author haipenge<br>
	 */

	private Boolean isSSL;

	public Boolean getIsSSL() {
		return isSSL;
	}

	public void setIsSSL(Boolean isSSL) {
		this.isSSL = isSSL;
	}

	/**
	 * 说明:smtpPort<br>
	 * 属性名: port<br>
	 * 类型: String<br>
	 * 数据库字段:port<br>
	 * 
	 * @author haipenge<br>
	 */

	private String port;

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	@DBRef
	private User user = null;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}/** @generate-entity-source@ **/
