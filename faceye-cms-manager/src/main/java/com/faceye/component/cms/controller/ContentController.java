package com.faceye.component.cms.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.collections.MapUtils;
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
import com.faceye.component.cms.entity.Image;
import com.faceye.component.cms.service.ContentService;
import com.faceye.component.cms.service.ImageService;
import com.faceye.component.weixin.entity.Account;
import com.faceye.component.weixin.service.AccountService;
import com.faceye.feature.controller.BaseController;
import com.faceye.feature.service.PropertyService;
import com.faceye.feature.util.AjaxResult;
import com.faceye.feature.util.http.HttpUtil;
import com.faceye.feature.util.view.MessageBuilder;

/**
 * 模块:内容管理->com.faceye.compoent.cms.controller<br>
 * 说明:<br>
 * 实体:内容:com.faceye.component.cms.entity.entity.Content<br>
 * 
 * @author haipeng <br>
 *         haipenge@gmail.com<br>
 *         创建日期:2015-7-25 9:40:29<br>
 */
@Controller
@Scope("prototype")
@RequestMapping("/cms/content")
public class ContentController extends BaseController<Content, Long, ContentService> {
	@Autowired
	private ImageService imageService = null;
	@Autowired
	private PropertyService propertyService = null;
	@Autowired
	private AccountService accountService = null;

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
	 * 					haipenge@gmail.com <br>
	 *                     创建日期2015-7-25 9:40:29<br>
	 */
	@RequestMapping("/home")
	public String home(HttpServletRequest request, Model model) {
		List<Account> accounts = this.accountService.getAll();
		model.addAttribute("accounts", accounts);
		Map searchParams = HttpUtil.getRequestParams(request);
		beforeInput(model, request);
		Page<Content> page = this.service.getPage(searchParams, getPage(searchParams), getSize(searchParams));
		model.addAttribute("page", page);
		resetSearchParams(searchParams);
		model.addAttribute("searchParams", searchParams);
		return "cms.content.manager";
	}

	/**
	 * 转向编辑或新增页面<br>
	 * 
	 * @todo<br>
	 * @return<br>
	 * @author:haipeng<br>
	 * 					haipenge @gmail.com <br>
	 *                     创建日期:2015-7-25 9:40:29<br>
	 */
	@RequestMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id, Model model, HttpServletRequest request) {
		if (id != null) {
			beforeInput(model, request);
			Content content = this.service.get(id);
			model.addAttribute("content", content);
			Map searchImageParams = new HashMap();
			searchImageParams.put("eq|content.$id", content.getId());
			Page<Image> images = this.imageService.getPage(searchImageParams, 1, 0);
			model.addAttribute("images", images);
			String imageServer = this.propertyService.get("image.server");
			model.addAttribute("imageServer", imageServer);
		}
		return "cms.content.update";
	}

	/**
	 * 转向新增页面<br>
	 * @todo<br>
	 * 
	 * @param model<br>
	 * @return<br>
	 * @author:haipeng<br>
	 * 					haipenge @gmail.com <br>
	 *                     创建日期:2015-7-25 9:40:29<br>
	 */
	@RequestMapping(value = "/input")
	public String input(Content content, Model model, HttpServletRequest request) {
		beforeInput(model, request);
		return "cms.content.update";
	}

	/**
	 * 数据保存<br>
	 * @author:haipeng<br>
	 * 					haipenge @gmail.com <br>
	 *                     创建日期:2015-7-25 9:40:29<br>
	 */
	@RequestMapping("/save")
	public String save(@Valid Content content, BindingResult bindingResult, Model model, HttpServletRequest request, RedirectAttributes redirectAttributes) {
		if (bindingResult.hasErrors()) {
			beforeInput(model, request);
			return "cms.content.update";
		} else {
			this.beforeSave(content, request);
			this.service.save(content);
			this.afterSave(content, request);
			return "redirect:/cms/content/home";
		}
	}

	/**
	 * 数据删除<br>
	 * 
	 * @todo<br>
	 * @return<br>
	 * @author:haipeng<br>
	 * 					haipenge @gmail.com <br>
	 *                     创建日期:2015-7-25 9:40:29<br>
	 */
	@RequestMapping("/remove/{id}")
	public String remove(@PathVariable("id") Long id, RedirectAttributes redirectAttributes, RedirectAttributesModelMap model) {
		if (id != null) {
			Content content = this.service.get(id);
			if (content != null) {
				if (beforeRemove(content, model)) {
					this.service.remove(content);
					// MessageBuilder.getInstance().setMessage(model,content.getName()+" "+ this.getI18N("global.remove.success"));
				}
			}
		}
		return "redirect:/cms/content/home";
	}

	/**
	 * 数据批量删除<br>
	 * 
	 * @todo<br>
	 * @return<br>
	 * @author:haipeng<br>
	 * 					haipenge @gmail.com <br>
	 *                     创建日期:2015-7-25 9:40:29<br>
	 */
	@RequestMapping("/multiRemove")
	@ResponseBody
	public String remove(@RequestParam(required = true) String ids, RedirectAttributes redirectAttributes, Model model) {
		if (StringUtils.isNotEmpty(ids)) {
			String[] idArray = ids.split(",");
			for (String id : idArray) {
				Content content = this.service.get(Long.parseLong(id));
				if (content != null) {
					if (beforeRemove(content, model)) {
						this.service.remove(content);
						// MessageBuilder.getInstance().setMessage(model,content.getName()+" "+ this.getI18N("global.remove.success"));
					}
				}
			}
		}
		String messages = MessageBuilder.getInstance().getMessages(model);
		return AjaxResult.getInstance().buildDefaultResult(StringUtils.isEmpty(messages), messages);
	}

	/**
	 * 推送到微信
	 * 
	 * @param weixinAccountIds
	 * @param contentIds
	 * @param model
	 * @return
	 * @Desc:
	 * @Author:haipenge
	 * @Date:2016年6月14日 下午12:11:16
	 */
	@RequestMapping("/push2Weixin")
	@ResponseBody
	public String push2Weixin(@RequestParam(required = true) String weixinAccountIds, @RequestParam(required = true) String contentIds, Model model) {
		String messages = MessageBuilder.getInstance().getMessages(model);
		if (StringUtils.isNotEmpty(weixinAccountIds) && StringUtils.isNotEmpty(contentIds)) {
			String ids[] = StringUtils.split(contentIds, ",");
			String weixinIds[] = StringUtils.split(weixinAccountIds, ",");
			List<Long> idsLong = new ArrayList();
			for (String id : ids) {
				if (StringUtils.isNotEmpty(id)) {
					idsLong.add(Long.parseLong(id));
				}
			}
			List<Long> weixinIdsLong = new ArrayList();
			for (String id : weixinIds) {
				if (StringUtils.isNotEmpty(id)) {
					weixinIdsLong.add(Long.parseLong(id));
				}
			}
			this.service.saveAndPush2Weixin(idsLong.toArray(new Long[idsLong.size()]), weixinIdsLong.toArray(new Long[weixinIdsLong.size()]));
		}
		return AjaxResult.getInstance().buildDefaultResult(StringUtils.isEmpty(messages), messages);
	}

	/**
	 * 删除图片,ajax方式
	 * 
	 * @todo
	 * @author:@haipenge haipenge@gmail.com 2015年3月30日
	 */
	@RequestMapping("/removeImage")
	@ResponseBody
	public String remove(@RequestParam(required = true) Long id) {
		Image image = this.imageService.get(id);
		// FileUtil.getInstance().deleteImage(image.getStorePath());
		this.imageService.remove(image);
		return AjaxResult.getInstance().buildDefaultResult(true);
	}

	/**
	 * 取得数据明细<br>
	 * @todo<br>
	 * 
	 * @param id<br>
	 * @param model<br>
	 * @return<br>
	 * @author:haipeng<br>
	 * 					haipenge @gmail.com <br>
	 *                     创建日期:2015-7-25 9:40:29<br>
	 */
	@RequestMapping("/detail/{id}")
	public String detail(@PathVariable Long id, Model model) {
		if (id != null) {
			Content content = this.service.get(id);
			model.addAttribute("content", content);
		}
		List<Account> accounts = this.accountService.getAll();
		model.addAttribute("accounts", accounts);
		Map searchImageParams = new HashMap();
		searchImageParams.put("eq|content.$id", id);
		Page<Image> images = this.imageService.getPage(searchImageParams, 1, 0);
		model.addAttribute("images", images);
		String imageServer = this.propertyService.get("image.server");
		model.addAttribute("imageServer", imageServer);
		return "cms.content.detail";
	}
	
	@RequestMapping("/receive")
	@ResponseBody
	public String receive(HttpServletRequest request,HttpServletResponse response){
		Map params=HttpUtil.getRequestParams(request);
		String body=MapUtils.getString(params, "content");
		String name=MapUtils.getString(params, "name");
		String keywords=MapUtils.getString(params, "keywords");
		String description=MapUtils.getString(params, "description");
		Content content=new Content();
		content.setContent(body);
		content.setName(name);
		content.setKeywords(keywords);
		content.setDescription(description);
		logger.debug(">>FaceYe --> Receive push now:name is->"+name);
		this.service.save(content);
		return AjaxResult.getInstance().buildDefaultResult(true);
	}

	/////////////////////////////////////////////// 以下为回调函数////////////////////////////////////////////
	/**
	 * 新增、编辑页面的前置逻辑处理<br>
	 * @todo<br>
	 * 
	 * @param model<br>
	 * @param request<br>
	 * @author:haipeng<br>
	 * 					haipenge @gmail.com <br>
	 *                     创建日期:2015-7-25 9:40:29<br>
	 */
	protected void beforeInput(Model model, HttpServletRequest request) {

	}

	/**
	 * 保存前的数据回调
	 * 
	 * @todo
	 * @param content
	 * @param request
	 * @author:haipeng 联系:haipenge@gmail.com 创建日期:2015-7-25 9:40:29
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
	 * 
	 * @todo
	 * @param content
	 * @param request
	 * @author:haipeng 联系:haipenge@gmail.com 创建日期:2015-7-25 9:40:29
	 */
	protected void afterSave(Content content, HttpServletRequest request) {

	}

}
