package com.faceye.component.cms.controller;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import com.faceye.component.cms.entity.Content;
import com.faceye.component.cms.service.ContentService;
import com.faceye.feature.controller.BaseController;
import com.faceye.feature.util.AjaxResult;
import com.faceye.feature.util.GlobalEntity;
import com.faceye.feature.util.http.HttpUtil;
import com.faceye.feature.util.view.MessageBuilder;

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

	@RequestMapping("/index")
	public String index() {
		return "default.cms.web";
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
	public String home(HttpServletRequest request, Model model) {
		Map searchParams = HttpUtil.getRequestParams(request);
		beforeInput(model, request);
		Page<Content> page = this.service.getPage(searchParams, getPage(searchParams), getSize(searchParams));
		model.addAttribute("page", page);
		resetSearchParams(searchParams);
		model.addAttribute("searchParams", searchParams);
		GlobalEntity global=new GlobalEntity();
		global.setTitle(this.getI18N("faceye.cms.title"));
		global.setKeywords(this.getI18N("faceye.cms.keywords"));
		global.setDesc(this.getI18N("faceye.cms.description"));
		model.addAttribute("global", global);
		return "cms.content.home";
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
//	@RequestMapping("/edit/{id}")
//	public String edit(@PathVariable("id") Long id, Model model, HttpServletRequest request) {
//		if (id != null) {
//			beforeInput(model, request);
//			Content content = this.service.get(id);
//			model.addAttribute("content", content);
//		}
//		return "cms.content.update";
//	}

	/**
	 * 转向新增页面<br>
	 * @todo<br>
	 * @param model<br>
	 * @return<br>
	 * @author:haipeng<br>
	 * haipenge @gmail.com <br>
	 * 创建日期:2015-7-25 9:40:29<br>
	 */
//	@RequestMapping(value = "/input")
//	public String input(Content content, Model model, HttpServletRequest request) {
//		beforeInput(model, request);
//		return "cms.content.update";
//	}

	/**
	 * 数据保存<br>
	 * @author:haipeng<br>
	 * haipenge @gmail.com <br>
	 * 创建日期:2015-7-25 9:40:29<br>
	 */
//	@RequestMapping("/save")
//	public String save(@Valid Content content, BindingResult bindingResult, Model model, HttpServletRequest request,
//			RedirectAttributes redirectAttributes) {
//		if (bindingResult.hasErrors()) {
//			beforeInput(model, request);
//			return "cms.content.update";
//		} else {
//			this.beforeSave(content, request);
//			this.service.save(content);
//			this.afterSave(content, request);
//			return "redirect:/cms/content/home";
//		}
//	}

	/**
	 * 数据删除<br>
	 * 
	 * @todo<br>
	 * @return<br>
	  * @author:haipeng<br>
	 * haipenge @gmail.com <br>
	 * 创建日期:2015-7-25 9:40:29<br>
	 */
//	@RequestMapping("/remove/{id}")
//	public String remove(@PathVariable("id") Long id, RedirectAttributes redirectAttributes, RedirectAttributesModelMap model) {
//		if (id != null) {
//			Content content = this.service.get(id);
//			if (content != null) {
//				if (beforeRemove(content, model)) {
//					this.service.remove(content);
//					// MessageBuilder.getInstance().setMessage(model,content.getName()+" "+ this.getI18N("global.remove.success"));
//				}
//			}
//		}
//		return "redirect:/cms/content/home";
//	}

	/**
	 * 数据批量删除<br>
	 * 
	 * @todo<br>
	 * @return<br>
	  * @author:haipeng<br>
	 * haipenge @gmail.com <br>
	 * 创建日期:2015-7-25 9:40:29<br>
	 */
//	@RequestMapping("/multiRemove")
//	@ResponseBody
//	public String remove(@RequestParam(required = true) String ids, RedirectAttributes redirectAttributes, Model model) {
//		if (StringUtils.isNotEmpty(ids)) {
//			String[] idArray = ids.split(",");
//			for (String id : idArray) {
//				Content content = this.service.get(Long.parseLong(id));
//				if (content != null) {
//					if (beforeRemove(content, model)) {
//						this.service.remove(content);
//						// MessageBuilder.getInstance().setMessage(model,content.getName()+" "+ this.getI18N("global.remove.success"));
//					}
//				}
//			}
//		}
//		String messages = MessageBuilder.getInstance().getMessages(model);
//		return AjaxResult.getInstance().buildDefaultResult(StringUtils.isEmpty(messages), messages);
//	}

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
	public String detail(@PathVariable Long id, Model model) {
		GlobalEntity global=new GlobalEntity();
		if (id != null) {
			Content content = this.service.get(id);
			if (content != null) {
				content.setClickCount(content.getClickCount() + 1);
				this.service.save(content);
				global.setTitle(content.getName()+"-"+this.getI18N("faceye.cms"));
				global.setKeywords(content.getKeywords());
				global.setDesc(content.getDescription());
				model.addAttribute("global", global);
			}
			model.addAttribute("content", content);
		}
		return "cms.content.detail";
	}
	
	@RequestMapping("/weixin/{id}.html")
	public String weixin(@PathVariable Long id, Model model) {
		GlobalEntity global=new GlobalEntity();
		if (id != null) {
			Content content = this.service.get(id);
			if (content != null) {
				content.setClickCount(content.getClickCount() + 1);
				this.service.save(content);
				global.setTitle(content.getName());
				//"-"+this.getI18N("faceye.cms")
				global.setKeywords(content.getKeywords());
				global.setDesc(content.getDescription());
				model.addAttribute("global", global);
			}
			model.addAttribute("content", content);
		}
		return "weixin.weixin.detail";
	}

	// /////////////////////////////////////////////以下为回调函数////////////////////////////////////////////
	/**
	 * 新增、编辑页面的前置逻辑处理<br>
	 * @todo<br>
	 * @param model<br>
	 * @param request<br>
	 * @author:haipeng<br>
	 * haipenge @gmail.com <br>
	 * 创建日期:2015-7-25 9:40:29<br>
	 */
	protected void beforeInput(Model model, HttpServletRequest request) {
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
	protected void beforeSave(Content content, HttpServletRequest request) {
	}

	/**
	 * 删除前 数据回调
	 */
	protected boolean beforeRemove(Content content, Model model) {
		boolean res = true;

		return res;
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
	protected void afterSave(Content content, HttpServletRequest request) {

	}

}
