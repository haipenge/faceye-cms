package com.faceye.component.activity.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.collections.CollectionUtils;
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

import com.faceye.component.activity.entity.Customer;
import com.faceye.component.activity.entity.InnerCustomer;
import com.faceye.component.activity.entity.LuckItem;
import com.faceye.component.activity.service.CustomerService;
import com.faceye.component.activity.service.InnerCustomerService;
import com.faceye.component.activity.service.LuckItemService;
import com.faceye.feature.controller.BaseController;
import com.faceye.feature.util.AjaxResult;
import com.faceye.feature.util.http.HttpUtil;

/**
 * 模块:activity<br>
 * 实体:InnerCustomer<br>
 * 
 * @author @haipenge <br>
 *         haipenge@gmail.com<br>
 *         Create Date:2014年12月10日<br>
 */
@Controller
@Scope("prototype")
@RequestMapping("/activity/innerCustomer")
public class InnerCustomerController extends BaseController<InnerCustomer, Long, InnerCustomerService> {
	@Autowired
	private LuckItemService luckItemService = null;
	@Autowired
	private CustomerService customerService = null;

	@Autowired
	public InnerCustomerController(InnerCustomerService service) {
		super(service);
	}

	/**
	 * 首页<br>
	 * 
	 * @todo<br>
	 * @return<br>
	 * 
	 * @author:@haipenge haipenge@gmail.com 2014年5月24日<br>
	 */
	@RequestMapping("/home")
	public String home(HttpServletRequest request, Model model) {
		Map searchParams = HttpUtil.getRequestParams(request);
		Page<InnerCustomer> page = this.service.getPage(searchParams, getPage(searchParams), getSize(searchParams));
		model.addAttribute("page", page);
		resetSearchParams(searchParams);
		model.addAttribute("searchParams", searchParams);
		return "activity.innerCustomer.manager";
	}

	/**
	 * 转向编辑或新增页面<br>
	 * 
	 * @todo<br>
	 * @return<br>
	 * 
	 * @author:@haipenge haipenge@gmail.com 2014年5月24日<br>
	 */
	@RequestMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id, Model model, HttpServletRequest request) {
		if (id != null) {
			beforeInput(model, request);
			InnerCustomer entity = this.service.get(id);
			model.addAttribute("innerCustomer", entity);
		}
		return "activity.innerCustomer.update";
	}

	/**
	 * 转向新增页面<br>
	 * @todo<br>
	 * 
	 * @param model<br>
	 * @return<br>
	 * @author:@haipenge<br>
	 * 						haipenge@gmail.com<br>
	 *                       2014年5月27日<br>
	 */
	@RequestMapping(value = "/input")
	public String input(InnerCustomer innerCustomer, Model model, HttpServletRequest request) {
		beforeInput(model, request);
		return "activity.innerCustomer.update";
	}

	/**
	 * 数据保存<br>
	 */
	@RequestMapping("/save")
	public String save(@Valid InnerCustomer innerCustomer, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model, HttpServletRequest request) {
		if (bindingResult.hasErrors()) {
			beforeInput(model, request);
			return "activity.innerCustomer.update";
		} else {
			this.beforeSave(innerCustomer, request);
			Map searchParams = new HashMap();
			String phone = innerCustomer.getCustomer().getPhone();
			if (StringUtils.isNotEmpty(phone)) {
				Customer customer = this.customerService.getCustomerByPhone(phone);
				if (customer != null) {
					searchParams.put("EQ|customer.$id", customer.getId());
					List<InnerCustomer> is = this.service.getPage(searchParams, 1, 0).getContent();
					if (CollectionUtils.isEmpty(is)) {
						innerCustomer.setCustomer(customer);
						this.service.save(innerCustomer);
					}
				}
			}

			return "redirect:/activity/innerCustomer/home";
		}
	}

	/**
	 * 数据删除<br>
	 * 
	 * @todo<br>
	 * @return<br>
	 * 
	 * @author:@haipenge haipenge@gmail.com 2014年5月24日<br>
	 */
	@RequestMapping("/remove/{id}")
	public String remove(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
		if (id != null) {
			this.service.remove(id);
		}
		return "redirect:/activity/innerCustomer/home";
	}

	/**
	 * 数据批量删除<br>
	 * 
	 * @todo<br>
	 * @return<br>
	 * 
	 * @author:@haipenge haipenge@gmail.com 2014年5月24日<br>
	 */
	@RequestMapping("/multiRemove")
	@ResponseBody
	public String remove(@RequestParam(required = true) String ids, RedirectAttributes redirectAttributes) {
		if (StringUtils.isNotEmpty(ids)) {
			String[] idArray = ids.split(",");
			for (String id : idArray) {
				this.service.remove(Long.parseLong(id));
			}
		}
		return AjaxResult.getInstance().buildDefaultResult(true);
	}

	/**
	 * 取得数据明细<br>
	 * @todo<br>
	 * 
	 * @param id<br>
	 * @param model<br>
	 * @return<br>
	 * @author:@haipenge<br>
	 * 						haipenge@gmail.com<br>
	 *                       2014年5月26日<br>
	 */
	@RequestMapping("/detail/{id}")
	public String detail(@PathVariable Long id, Model model) {
		if (id != null) {
			InnerCustomer entity = this.service.get(id);
			model.addAttribute("innerCustomer", entity);
		}
		return "activity.innerCustomer.detail";
	}

	/////////////////////////////////////////////// 以下为回调函数////////////////////////////////////////////
	/**
	 * 新增、编辑页面的前置逻辑处理<br>
	 * @todo<br>
	 * 
	 * @param model<br>
	 * @param request<br>
	 * @author:@haipenge<br>
	 * 						haipenge@gmail.com<br>
	 *                       2015年4月5日<br>
	 */
	protected void beforeInput(Model model, HttpServletRequest request) {
		List<LuckItem> luckItems = this.luckItemService.getPage(null, 1, 0).getContent();
		model.addAttribute("luckItems", luckItems);
	}

	/**
	 *
	 * 保存数据前的回调函数
	 */
	protected void beforeSave(InnerCustomer innerCustomer, HttpServletRequest request) {

	}

}
