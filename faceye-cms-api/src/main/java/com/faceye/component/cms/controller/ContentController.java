package com.faceye.component.cms.controller;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.faceye.component.cms.entity.Content;
import com.faceye.component.cms.service.ContentService;
import com.faceye.feature.controller.BaseController;
import com.faceye.feature.util.http.HttpUtil;
import com.faceye.feature.util.AjaxResult;

/**
 * 模块:内容管理->com.faceye.compoent.cms.controller<br>
 * 说明:<br>
 * 实体:内容:com.faceye.component.cms.entity.entity.Content<br>
 * @author haipeng <br>
 * haipenge@gmail.com<br>
*  创建日期:2015-7-25 9:40:29<br>
 */
@Controller
@Scope("prototype")
@RequestMapping("/cms/content")
public class ContentController extends BaseController<Content, Long, ContentService> {
	
	@Autowired
	public ContentController(ContentService service) {
		super(service);
	}

	/**
	 * 首页<br>
	 * 
	 * @todo<br>
	 * @return<br>
	 * @author:haipeng<br> 
	 * haipenge@gmail.com <br>
	 * 创建日期2015-7-25 9:40:29<br>
	 */
	@RequestMapping("/home")
	@ResponseBody
	public Page<Content> home(HttpServletRequest request, Model model) {
		Map searchParams=HttpUtil.getRequestParams(request);
		beforeInput(model,request);
		Page<Content> page = this.service.getPage(searchParams, getPage(searchParams), getSize(searchParams));
		model.addAttribute("page", page);
		resetSearchParams(searchParams);
		model.addAttribute("searchParams", searchParams);
		return page;
	}

	/**
	 * 转向编辑或新增页面<br>
	 * 
	 * @todo<br>
	 * @return<br>
	 * @author:haipeng<br>
	 * haipenge @gmail.com <br>
	 * 创建日期:2015-7-25 9:40:29<br>
	 */
	@RequestMapping("/edit/{id}")
	@ResponseBody
	public Content edit(@PathVariable("id") Long id,Model model,HttpServletRequest request) {
		beforeInput(model,request);
		Content content=this.service.get(id);
		return content;
	}
	
	/**
	 * 转向新增页面<br>
	 * @todo<br>
	 * @param model<br>
	 * @return<br>
	 * @author:haipeng<br>
	 * haipenge @gmail.com <br>
	 * 创建日期:2015-7-25 9:40:29<br>
	 */
	@RequestMapping(value="/input")
	public String input(Content content, Model model,HttpServletRequest request){
		beforeInput(model,request);
		return "cms.content.update";
	}
	

	/**
	 * 数据保存<br>
	 * @author:haipeng<br>
	 * haipenge @gmail.com <br>
	 * 创建日期:2015-7-25 9:40:29<br>
	 */
	@RequestMapping("/save")
	@ResponseBody
	public String save(@Valid Content content,BindingResult bindingResult,Model model,HttpServletRequest request, RedirectAttributes redirectAttributes) {
		if(bindingResult.hasErrors()){
		  beforeInput(model,request);
		  return AjaxResult.getInstance().buildDefaultResult(false);
		}else{
		  this.beforeSave(content,request);
	      this.service.save(content);
	      this.afterSave(content,request);
	      return  AjaxResult.getInstance().buildDefaultResult(true);
		}
	}

	/**
	 * 数据删除<br>
	 * 
	 * @todo<br>
	 * @return<br>
	  * @author:haipeng<br>
	 * haipenge @gmail.com <br>
	 * 创建日期:2015-7-25 9:40:29<br>
	 */
	@RequestMapping("/remove/{id}")
	@ResponseBody
	public String remove(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
		if(id!=null){
			this.service.remove(id);
		}
		return AjaxResult.getInstance().buildDefaultResult(true);
	}
	
	/**
	 * 数据批量删除<br>
	 * 
	 * @todo<br>
	 * @return<br>
	  * @author:haipeng<br>
	 * haipenge @gmail.com <br>
	 * 创建日期:2015-7-25 9:40:29<br>
	 */
	@RequestMapping("/multiRemove")
	@ResponseBody
	public String remove(@RequestParam(required=true) String  ids, RedirectAttributes redirectAttributes) {
		if(StringUtils.isNotEmpty(ids)){
			String [] idArray=ids.split(",");
			for(String id:idArray){
				this.service.remove(Long.parseLong(id));
			}
		}
		return AjaxResult.getInstance().buildDefaultResult(true);
	}
	
	/**
	 * 取得数据明细<br>
	 * @todo<br>
	 * @param id<br>
	 * @param model<br>
	 * @return<br>
	 * @author:haipeng<br>
	 * haipenge @gmail.com <br>
	 * 创建日期:2015-7-25 9:40:29<br>
	 */
	@RequestMapping("/detail/{id}")
	@ResponseBody
	public Content detail(@PathVariable Long id,Model model){
		Content content=this.service.get(id);
		return content;
	}
	
	///////////////////////////////////////////////以下为回调函数////////////////////////////////////////////
	/**
	 * 新增、编辑页面的前置逻辑处理<br>
	 * @todo<br>
	 * @param model<br>
	 * @param request<br>
	 * @author:haipeng<br>
	 * haipenge @gmail.com <br>
	 * 创建日期:2015-7-25 9:40:29<br>
	 */
	protected void beforeInput(Model model,HttpServletRequest request){
	}
	
	/**
	 * 保存前的数据回调
	 * @todo
	 * @param content
	 * @param request
	 * @author:haipeng
	 * 联系:haipenge@gmail.com
	 * 创建日期:2015-7-25 9:40:29
	 */
	protected void beforeSave(Content content,HttpServletRequest request){
	}
	
	
	
	/**
	 * 保存后的数据回调
	 * @todo
	 * @param content
	 * @param request
	 * @author:haipeng
	 * 联系:haipenge@gmail.com
	 * 创建日期:2015-7-25 9:40:29
	 */
	protected void afterSave(Content content,HttpServletRequest request){
	   
	}
	

}
