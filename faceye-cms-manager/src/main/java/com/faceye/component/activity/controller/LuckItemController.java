package com.faceye.component.activity.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
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

import com.faceye.component.activity.entity.Customer;
import com.faceye.component.activity.entity.InnerCustomer;
import com.faceye.component.activity.entity.LuckItem;
import com.faceye.component.activity.entity.LuckerRecord;
import com.faceye.component.activity.service.CustomerService;
import com.faceye.component.activity.service.InnerCustomerService;
import com.faceye.component.activity.service.LuckItemService;
import com.faceye.component.activity.service.LuckerRecordService;
import com.faceye.feature.controller.BaseController;
import com.faceye.feature.util.AjaxResult;
import com.faceye.feature.util.Json;
import com.faceye.feature.util.http.HttpUtil;

/**
 * 模块:activity<br>
 * 实体:LuckItem<br>
 * 
 * @author @haipenge <br>
 *         haipenge@gmail.com<br>
 *         Create Date:2014年12月10日<br>
 */
@Controller
@Scope("prototype")
@RequestMapping("/activity/luckItem")
public class LuckItemController extends BaseController<LuckItem, Long, LuckItemService> {

	@Autowired
	private CustomerService customerService = null;
	@Autowired
	private InnerCustomerService innerCustomerService = null;
	@Autowired
	private LuckerRecordService luckerRecordService = null;

	@Autowired
	public LuckItemController(LuckItemService service) {
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
		Page<LuckItem> page = this.service.getPage(searchParams, getPage(searchParams), getSize(searchParams));
		model.addAttribute("page", page);
		resetSearchParams(searchParams);
		model.addAttribute("searchParams", searchParams);
		return "activity.luckItem.manager";
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
			LuckItem entity = this.service.get(id);
			model.addAttribute("luckItem", entity);
		}
		return "activity.luckItem.update";
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
	public String input(LuckItem luckItem, Model model, HttpServletRequest request) {
		beforeInput(model, request);
		return "activity.luckItem.update";
	}

	/**
	 * 数据保存<br>
	 */
	@RequestMapping("/save")
	public String save(@Valid LuckItem luckItem, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model, HttpServletRequest request) {
		if (bindingResult.hasErrors()) {
			beforeInput(model, request);
			return "activity.luckItem.update";
		} else {
			this.beforeSave(luckItem, request);
			this.service.save(luckItem);
			return "redirect:/activity/luckItem/home";
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
		return "redirect:/activity/luckItem/home";
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
			LuckItem entity = this.service.get(id);
			model.addAttribute("luckItem", entity);
		}
		return "activity.luckItem.detail";
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

	}

	/**
	 *
	 * 保存数据前的回调函数
	 */
	protected void beforeSave(LuckItem luckItem, HttpServletRequest request) {

	}

	@RequestMapping("/startActivity")
	public String startActivity(HttpServletRequest request, Model model) {
		Map searchParams = HttpUtil.getRequestParams(request);
		Page<LuckItem> page = this.service.getPage(searchParams, getPage(searchParams), getSize(searchParams));
		model.addAttribute("page", page);
		resetSearchParams(searchParams);
		model.addAttribute("searchParams", searchParams);
		return "component/activity/luckItem/start_activity";
	}

	/**
	 * 抽奖页
	 * 
	 * @param request
	 * @param model
	 * @return
	 * @Desc:
	 * @Author:haipenge
	 * @Date:2017年8月26日 上午9:59:29
	 */
	@RequestMapping("/doLucker")
	public String doLucker(HttpServletRequest request, Model model) {
		Map params = HttpUtil.getRequestParams(request);
		Map cusParams = new HashMap();
		cusParams.put("EQ|isLucker", Boolean.FALSE);
		List<Customer> customers = this.customerService.getPage(cusParams, 1, 0).getContent();
		model.addAttribute("customers", customers);
		Long luckItemId = MapUtils.getLong(params, "luckItemId");
		LuckItem luckItem = this.service.get(luckItemId);
		model.addAttribute("luckItem", luckItem);
		Map searchParams = new HashMap();
		searchParams.put("EQ|luckItem.$id", luckItem.getId());
		List<LuckerRecord> luckers = this.luckerRecordService.getPage(searchParams, 1, 0).getContent();
		model.addAttribute("luckers", luckers);
		return "component/activity/luckItem/do_lucker";
	}

	@RequestMapping("/lucker")
	@ResponseBody
	public String generateLucker(HttpServletRequest request) {
		Customer lucker = null;
		Map params = HttpUtil.getRequestParams(request);
		Long luckItemId = MapUtils.getLong(params, "luckItemId");
		Map searchParams = new HashMap();
		LuckItem luckItem = this.service.get(luckItemId);
		searchParams.put("EQ|luckItem.$id", luckItemId);
		List<InnerCustomer> innerCustomers = this.innerCustomerService.getPage(searchParams, 1, 0).getContent();
		for (InnerCustomer innerCustomer : innerCustomers) {
			Customer customer = innerCustomer.getCustomer();
			if (!customer.getIsLucker()) {
				lucker = customer;
				customer.setIsLucker(true);
				this.customerService.save(customer);
				LuckerRecord luckerRecord = new LuckerRecord();
				luckerRecord.setCustomer(customer);
				luckerRecord.setLuckItem(luckItem);
				this.luckerRecordService.save(luckerRecord);
				luckItem.setCurrentCount(luckItem.getCurrentCount() + 1);
				this.service.save(luckItem);
				break;
			}
		}
		String res = "";
		if (lucker == null) {
//			res = "{\"isLucker\":\"false\"}";
			lucker=new Customer();
			res=Json.toJson(lucker);
		} else {
			res = Json.toJson(lucker);
		}
		return res;
	}

}
