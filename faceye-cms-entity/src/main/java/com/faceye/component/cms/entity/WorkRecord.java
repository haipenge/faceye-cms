package com.faceye.component.cms.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.faceye.component.security.entity.User;
import com.faceye.feature.util.DateUtil;

/**
 * WorkRecord ORM 实体<br>
 * 数据库表:cms_workRecord<br>
 * @author @haipenge <br>
 * haipenge@gmail.com<br>
*  Create Date:2014年5月21日<br>
 */
@Document(collection = "cms_work_record")
public class WorkRecord implements Serializable {
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
	 * 说明:工作内容<br>
	 * 属性名: content<br>
	 * 类型: String<br>
	 * 数据库字段:content<br>
	 * @author haipenge<br>
	 */
	@NotBlank()
	private String content = "";

	public String getContent() {
		// content=StringUtils.replacePattern(content, "\n", "<br>");
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Transient
	private String showContent = "";

	public String getShowContent() {
		showContent=StringUtils.replacePattern(content, "\n", "<br>");
		return showContent;
	}

	public void setShowContent(String showContent) {
		this.showContent = showContent;
	}

	/**
	    * 说明:是否完成<br>
	    * 属性名: isFinished<br>
	    * 类型: Boolean<br>
	    * 数据库字段:is_finished<br>
	    * @author haipenge<br>
	    */

	private Boolean isFinished=false;

	public Boolean getIsFinished() {
		return isFinished;
	}

	public void setIsFinished(Boolean isFinished) {
		this.isFinished = isFinished;
	}

	/**
	 * 说明:成日期<br>
	 * 属性名: finishDate<br>
	 * 类型: Date<br>
	 * 数据库字段:finish_date<br>
	 * @author haipenge<br>
	 */

	private Date finishDate;

	public Date getFinishDate() {
		return finishDate;
	}

	public void setFinishDate(Date finishDate) {
		this.finishDate = finishDate;
	}

	@Transient
	private String finishDateShow = "";

	public String getFinishDateShow() {
		if (this.getFinishDate() == null) {
			finishDateShow = DateUtil.formatDate(new Date(), "yyyy-MM-dd HH:mm");
		} else {
			finishDateShow = DateUtil.formatDate(this.getFinishDate(), "yyyy-MM-dd HH:mm");
		}
		return finishDateShow;
	}

	public void setFinishDateShow(String finishDateShow) {
		this.setFinishDate(DateUtil.getDateFromString(finishDateShow, "yyyy-MM-dd HH:mm"));
		this.finishDateShow = finishDateShow;
	}
	
	/**
	 * 任务状态
	 * 0，创建（TODO),1->Doing,2->Done
	 */
	private Integer status=0;

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	
	/**
	 * 工作的排序值
	 */
	private Integer orderIndex=0;

	public Integer getOrderIndex() {
		return orderIndex;
	}

	public void setOrderIndex(Integer orderIndex) {
		this.orderIndex = orderIndex;
	}
	
	
	/**
	 * 所属项目
	 */
	@DBRef
	private Project project=null;

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
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
	@DBRef
	private List<Member> members=new ArrayList<Member>();

	public List<Member> getMembers() {
		return members;
	}

	public void setMembers(List<Member> members) {
		this.members = members;
	}
	

	private Date createDate=new Date();

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	

}
/**@generate-entity-source@**/

