package com.faceye.component.cms.entity;

import java.io.Serializable;

import javax.persistence.Id;

import org.apache.commons.lang.StringUtils;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.faceye.feature.service.PropertyService;
import com.faceye.feature.util.bean.BeanContextUtil;

/**
 * Image ORM 实体 数据库表:parse_image
 * 
 * @author @haipenge haipenge@gmail.com Create Date:2014年5月21日
 */
@Document(collection = "cms_image")
public class Image implements Serializable {

	/**
	 * 
	 */
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
	 * 说明:存储路径 属性名: storePath 类型: String 数据库字段:store_path
	 * 
	 * @author haipenge
	 */

	private String storePath;

	public String getStorePath() {
		return storePath;
	}

	public void setStorePath(String storePath) {
		this.storePath = storePath;
	}

	/**
	 * 图片对外访问路径
	 */
	private String url = "";

	public String getUrl() {
		String imgServer = BeanContextUtil.getBean(PropertyService.class).get("image.server");
		if (!StringUtils.startsWithIgnoreCase(url, "http://")) {
			url = imgServer + url;
		}
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * 说明:原链接 属性名: sourceUrl 类型: String 数据库字段:source_url
	 * 
	 * @author haipenge
	 */

	private String sourceUrl;

	public String getSourceUrl() {
		return sourceUrl;
	}

	public void setSourceUrl(String sourceUrl) {
		this.sourceUrl = sourceUrl;
	}

	@DBRef
	private Content content = null;

	public Content getContent() {
		return content;
	}

	public void setContent(Content content) {
		this.content = content;
	}

}
/** @generate-entity-source@ **/
