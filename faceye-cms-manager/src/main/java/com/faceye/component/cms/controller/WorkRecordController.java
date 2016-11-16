package com.faceye.component.cms.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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

import com.faceye.component.cms.entity.Member;
import com.faceye.component.cms.entity.Project;
import com.faceye.component.cms.entity.Team;
import com.faceye.component.cms.entity.WorkRecord;
import com.faceye.component.cms.service.MemberService;
import com.faceye.component.cms.service.ProjectService;
import com.faceye.component.cms.service.WorkRecordService;
import com.faceye.component.cms.util.Constants;
import com.faceye.component.security.entity.User;
import com.faceye.component.security.service.UserService;
import com.faceye.feature.controller.BaseController;
import com.faceye.feature.util.AjaxResult;
import com.faceye.feature.util.DateUtil;
import com.faceye.feature.util.http.HttpUtil;

/**
 * 模块:cms<br>
 * 实体:WorkRecord<br>
 * 
 * @author @haipenge <br>
 *         haipenge@gmail.com<br>
 *         Create Date:2014年12月10日<br>
 */
@Controller
@Scope("prototype")
@RequestMapping("/cms/workRecord")
public class WorkRecordController extends BaseController<WorkRecord, Long, WorkRecordService> {
	@Autowired
	private UserService userService = null;
	@Autowired
	@Qualifier("cms-projectServiceImpl")
	private ProjectService projectService = null;
	@Autowired
	private MemberService memberService = null;

	@Autowired
	public WorkRecordController(WorkRecordService service) {
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
		String submit = MapUtils.getString(searchParams, "submit", "query");
		Page<WorkRecord> page = this.service.getPage(searchParams, getPage(searchParams), getSize(searchParams));
		model.addAttribute("page", page);
		resetSearchParams(searchParams);
		model.addAttribute("searchParams", searchParams);
		this.setProjects(model);
		if (StringUtils.equals(submit, "query")) {
			return "cms.workRecord.manager";
		} else {
			// submit = export 导出txt文件
			return "";
		}
	}

	/**
	 * 工作面板
	 * 
	 * @param request
	 * @param model
	 * @return
	 * @Desc:
	 * @Author:haipenge
	 * @Date:2015年12月31日 下午10:50:03
	 */
	@RequestMapping("/panel")
	public String panel(HttpServletRequest request, Model model) {
		Map searchParams = HttpUtil.getRequestParams(request);
		Page<WorkRecord> page = this.service.getPage(searchParams, getPage(searchParams), getSize(searchParams));
		model.addAttribute("page", page);
		searchParams.put("EQ|status", Constants.WORK_RECORD_STATUS_TODO);
		Page<WorkRecord> todos = this.service.getPage(searchParams, getPage(searchParams), getSize(searchParams));
		model.addAttribute("todos", todos);
		searchParams.put("EQ|status", Constants.WORK_RECORD_STATUS_DOING);
		Page<WorkRecord> doings = this.service.getPage(searchParams, getPage(searchParams), getSize(searchParams));
		model.addAttribute("doings", doings);
		searchParams.put("EQ|status", Constants.WORK_RECORD_STATUS_DONE);
		Page<WorkRecord> dones = this.service.getPage(searchParams, getPage(searchParams), getSize(searchParams));
		model.addAttribute("dones", dones);
		resetSearchParams(searchParams);
		model.addAttribute("searchParams", searchParams);
		this.setProjects(model);
		return "cms.workRecord.panel";
	}

	private void setProjects(Model model) {
		Page<Project> projects = this.projectService.getPage(null, 1, 0);
		model.addAttribute("projects", projects);
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
			WorkRecord entity = this.service.get(id);
			beforeInput(model, entity, request);

			model.addAttribute("workRecord", entity);
		}
		return "cms.workRecord.update";
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
	public String input(WorkRecord workRecord, Model model, HttpServletRequest request) {
		beforeInput(model, workRecord, request);
		return "cms.workRecord.update";
	}

	/**
	 * 数据保存<br>
	 */
	@RequestMapping("/save")
	public String save(@Valid WorkRecord workRecord, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model, HttpServletRequest request) {
		if (bindingResult.hasErrors()) {
			beforeInput(model, workRecord, request);
			return "cms.workRecord.update";
		} else {
			this.beforeSave(workRecord, request);
			this.service.save(workRecord);
			return "redirect:/cms/workRecord/home";
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
		return "redirect:/cms/workRecord/home";
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
			WorkRecord entity = this.service.get(id);
			model.addAttribute("workRecord", entity);
		}
		return "cms.workRecord.detail";
	}

	/**
	 * 完成工作
	 * 
	 * @todo
	 * @param id
	 * @return
	 * @author:@haipenge 联系:haipenge@gmail.com 创建时间:2015年8月19日
	 */
	@RequestMapping("/done")
	public String done(@RequestParam(required = true) Long id) {
		WorkRecord workRecord = this.service.get(id);
		if (workRecord != null) {
			workRecord.setIsFinished(true);
			workRecord.setStatus(Constants.WORK_RECORD_STATUS_DONE);
			workRecord.setFinishDate(new Date());
			this.service.save(workRecord);
		}
		return AjaxResult.getInstance().buildDefaultResult(true);
	}

	/**
	 * 重置工作任务状态，todo->donging->done
	 * 
	 * @return
	 * @Desc:
	 * @Author:haipenge
	 * @Date:2015年12月31日 下午11:26:33
	 */
	@RequestMapping("/resetStatus")
	@ResponseBody
	public String resetStatus(@RequestParam(required = true) Long id, @RequestParam(required = true) Integer status) {
		boolean res = this.service.updateWorkRecordStatus(id, status);
		return AjaxResult.getInstance().buildDefaultResult(res);
	}

	/**
	 * 导出text文件
	 * 
	 * @todo
	 * @return
	 * @author:@haipenge 联系:haipenge@gmail.com 创建时间:2015年8月20日
	 */
	@RequestMapping("/exportText")
	@ResponseBody
	public String exportText(HttpServletRequest request, HttpServletResponse response) {
		Map searchParams = HttpUtil.getRequestParams(request);
		Page<WorkRecord> workRecords = this.service.getPage(searchParams, 1, 0);
		if (workRecords != null && CollectionUtils.isNotEmpty(workRecords.getContent())) {
			StringBuilder sb = new StringBuilder();
			for (WorkRecord workRecord : workRecords.getContent()) {
				sb.append(DateUtil.date2String(workRecord.getFinishDate()));
				sb.append(workRecord.getContent());
				if (workRecord.getIsFinished()) {
					sb.append("  [已完成]  ");
				} else {
					sb.append("  [未完成] ** ");
				}
				sb.append("\n\r");
			}
			response.setContentType("octets/stream");
			// response.addHeader("Content-Disposition", "attachment;filename=order.xls");
			response.addHeader("Content-Disposition", "attachment;filename=work-list.txt");

		}
		return AjaxResult.getInstance().buildDefaultResult(true);
	}

	@RequestMapping("/move")
	@ResponseBody
	public String move(HttpServletRequest request) {
		Map params = HttpUtil.getRequestParams(request);
		this.service.moveWorkRecord(params);
		return AjaxResult.getInstance().buildDefaultResult(true);
	}

	@RequestMapping("/getMembersOfWorkRecord")
	@ResponseBody
	public List<Map> getMembersOfWorkRecord(@RequestParam(required = true) Long projectId, @RequestParam Long workRecordId) {
		List<Map> res = new ArrayList<Map>(0);
		Project project = this.projectService.get(projectId);
		WorkRecord workRecord = null;
		if (workRecordId != null) {
			workRecord = this.service.get(workRecordId);
		}
		if (project != null) {
			Team team = project.getTeam();
			List<Member> members = team.getMembers();
			if (CollectionUtils.isNotEmpty(members)) {
				for (Member member : members) {
					Map map = new HashMap();
					Boolean isCheck = false;
					map.put("member", member);
					if (workRecord != null && CollectionUtils.isNotEmpty(workRecord.getMembers())) {
						for (Member m : workRecord.getMembers()) {
							if (m.getId().compareTo(member.getId()) == 0) {
								isCheck = true;
								break;
							}
						}
					}
					map.put("isCheck", isCheck);
					res.add(map);
				}
			}
		}
		return res;
	}

	// /////////////////////////////////////////////以下为回调函数////////////////////////////////////////////
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
	protected void beforeInput(Model model, WorkRecord workRecord, HttpServletRequest request) {
		User user = this.userService.getCurrentLoginUser();
		Page<Project> projects = this.projectService.getPage(null, 1, 0);
		model.addAttribute("projects", projects);
		if (workRecord.getId() != null && workRecord.getId() > 0) {
			Project project = workRecord.getProject();
			if (project != null) {
				Team team = project.getTeam();
				List<Member> members = team.getMembers();
				model.addAttribute("members", members);
			}
		}

	}

	/**
	 *
	 * 保存数据前的回调函数
	 */
	protected void beforeSave(WorkRecord workRecord, HttpServletRequest request) {
		String[] memberIds = request.getParameterValues("member.id");
		List<Member> members = new ArrayList<Member>(0);
		if (memberIds != null && memberIds.length > 0) {
			for (String memberId : memberIds) {
				Member member = this.memberService.get(Long.parseLong(memberId));
				members.add(member);
			}
		}
		workRecord.setMembers(members);
		if (workRecord != null) {
			User user = this.userService.getCurrentLoginUser();
			workRecord.setUser(user);
		}
	}

}
