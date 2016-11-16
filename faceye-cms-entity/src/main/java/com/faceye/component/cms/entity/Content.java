package com.faceye.component.cms.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Id;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 模块:内容管理->cms->Content<br>
 * 说明:<br>
 * 实体:内容->com.faceye.component.cms.entity.Content Mongo 对像<br>
 * mongo数据集:cms_content<br>
 * @author haipeng <br>
 * 联系:haipenge@gmail.com<br>
 * 创建日期:2015-7-25 10:01:52<br>
 *spring-data-mongo支持的注释类型<br>
 *@Id - 文档的唯一标识，在mongodb中为ObjectId，它是唯一的，通过时间戳+机器标识+进程ID+自增计数器（确保同一秒内产生的Id不会冲突）构成。<br>
 *@Document - 把一个java类声明为mongodb的文档，可以通过collection参数指定这个类对应的文档。<br>
 *@DBRef - 声明类似于关系数据库的关联关系。ps：暂不支持级联的保存功能，当你在本实例中修改了DERef对象里面的值时，单独保存本实例并不能保存DERef引用的对象，它要另外保存<br>
 *@Indexed - 声明该字段需要索引，建索引可以大大的提高查询效率。<br>
 *@CompoundIndex - 复合索引的声明，建复合索引可以有效地提高多字段的查询效率。<br>
 *@GeoSpatialIndexed - 声明该字段为地理信息的索引。<br>
 *@Transient - 映射忽略的字段，该字段不会保存到<br>
 *@PersistenceConstructor - 声明构造函数，作用是把从数据库取出的数据实例化为对象。该构造函数传入的值为从DBObject中取出的数据。<br>
 *@CompoundIndexes({
 *    @CompoundIndex(name = "age_idx", def = "{'lastName': 1, 'age': -1}")
 *})
 *@Indexed(unique = true)
 */

@Document(collection = "cms_content")
public class Content implements Serializable {
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
	* 说明:标题<br>
	* 属性名: name<br>
	* 类型: java.lang.String<br>
	* 数据库字段:name<br>
	* @author haipeng<br>
	*/
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	* 说明:内容<br>
	* 属性名: content<br>
	* 类型: java.lang.String<br>
	* 数据库字段:content<br>
	* @author haipeng<br>
	*/
	private String content;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Transient
	private String contentWithoutImages = "";

	public String getContentWithoutImages() {
		String regexp = "<img[^>]*?src=[\"\\']?([^\"\\'>]+)[\"\\']?[^>]*\\/>";
		if (StringUtils.isNotEmpty(content)) {
			contentWithoutImages = StringUtils.replacePattern(content, regexp, "");
		}
		return contentWithoutImages;
	}

	public void setContentWithoutImages(String contentWithoutImages) {
		this.contentWithoutImages = contentWithoutImages;
	}

	/**
	* 说明:关键字<br>
	* 属性名: keywords<br>
	* 类型: java.lang.String<br>
	* 数据库字段:keywords<br>
	* @author haipeng<br>
	*/
	private String keywords = "";

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	/**
	* 说明:描述<br>
	* 属性名: description<br>
	* 类型: java.lang.String<br>
	* 数据库字段:description<br>
	* @author haipeng<br>
	*/
	private String description = "";

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * 说明:创建日期<br>
	 * 属性名: createDate<br>
	 * 类型: Date<br>
	 * 数据库字段:createDate<br>
	 * @author haipeng<br>
	 */

	private Date createDate = new Date();

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	/**
	 * 点击量
	 */
	private Integer clickCount = 0;

	public Integer getClickCount() {
		return clickCount;
	}

	public void setClickCount(Integer clickCount) {
		this.clickCount = clickCount;
	}

	/**
	 * 文章中的图片链接集合
	 */
	private List<String> imageUrls = new ArrayList<String>(0);

	public List<String> getImageUrls() {
		return imageUrls;
	}

	public void setImageUrls(List<String> imageUrls) {
		this.imageUrls = imageUrls;
	}

}
/**@generate-entity-source@**/

