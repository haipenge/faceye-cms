package com.faceye.component.activity.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;
/**
 * InnerCustomer ORM 实体<br>
 * 数据库表:activity_innerCustomer<br>
 * @author @haipenge <br>
 * haipenge@gmail.com<br>
*  Create Date:2014年5月21日<br>
 */
@Document(collection="activity_inner_customer")
public class InnerCustomer implements Serializable {
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
	 * 说明:创建日期<br>
	 * 属性名: createDate<br>
	 * 类型: Date<br>
	 * 数据库字段:create_date<br>
	 * @author haipenge<br>
	 */
	@DateTimeFormat(pattern="yyyy-MM-dd hh24:mi:ss")
	private Date createDate=new Date();

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

   /**
    * 说明:客户<br>
    * 属性名: customer<br>
    * 类型: Customer<br>
    * 数据库字段:customer<br>
    * @author haipenge<br>
    */
    @DBRef
	private  Customer customer;
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	

	
   /**
    * 说明:奖项 <br>
    * 属性名: luckItem<br>
    * 类型: LuckItem<br>
    * 数据库字段:luck_item<br>
    * @author haipenge<br>
    */
    @DBRef
	private  LuckItem luckItem;
	public LuckItem getLuckItem() {
		return luckItem;
	}
	public void setLuckItem(LuckItem luckItem) {
		this.luckItem = luckItem;
	}
	
}/**@generate-entity-source@**/
	
