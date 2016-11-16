package com.faceye.component.cms.controller;

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

import com.faceye.component.cms.entity.Member;
import com.faceye.component.cms.entity.Project;
import com.faceye.component.cms.entity.Team;
import com.faceye.component.cms.service.MemberService;
import com.faceye.component.cms.service.ProjectService;
import com.faceye.component.cms.service.TeamService;
import com.faceye.feature.controller.BaseController;
import com.faceye.feature.util.AjaxResult;
import com.faceye.feature.util.http.HttpUtil;

/**
 * 模块:cms<br>
 * 实体:Member<br>
 * 
 * @author @haipenge <br>
 *         haipenge@gmail.com<br>
 *         Create Date:2014年12月10日<br>
 */
@Controller
@Scope("prototype")
@RequestMapping("/cms/member")
public class MemberController extends BaseController<Member, Long, MemberService> {
	@Autowired
	private TeamService teamService = null;
	@Autowired
	private ProjectService projectService = null;

	@Autowired
	public MemberController(MemberService service) {
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
		Page<Member> page = this.service.getPage(searchParams, getPage(searchParams), getSize(searchParams));
		model.addAttribute("members", page);
		resetSearchParams(searchParams);
		model.addAttribute("searchParams", searchParams);
		return "cms.member.manager";
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
			Member entity = this.service.get(id);
			model.addAttribute("member", entity);
		}
		return "cms.member.update";
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
	public String input(Member member, Model model, HttpServletRequest request) {
		beforeInput(model, request);
		return "cms.member.update";
	}

	/**
	 * 数据保存<br>
	 */
	@RequestMapping("/save")
	public String save(@Valid Member member, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model, HttpServletRequest request) {
		if (bindingResult.hasErrors()) {
			beforeInput(model, request);
			return "cms.member.update";
		} else {
			this.beforeSave(member, request);
			Map params = HttpUtil.getRequestParams(request);
			Long teamId = MapUtils.getLong(params, "teamId");
			this.service.save(member);
			if (teamId > 0) {
				Team team = this.teamService.get(teamId);
				team.getMembers().add(member);
				this.teamService.save(team);
			}
			return "redirect:/cms/team/detail/" + teamId;
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
	@RequestMapping("/remove")
	public String remove(@RequestParam(required = true) Long teamId, @RequestParam(required = true) Long memberId, RedirectAttributes redirectAttributes) {
		this.doRemove(teamId, memberId);
		return "redirect:/cms/team/detail/" + teamId;
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
	public String remove(@RequestParam(required = true) String ids, @RequestParam(required = true) Long teamId, RedirectAttributes redirectAttributes) {
		if (StringUtils.isNotEmpty(ids)) {
			String[] idArray = ids.split(",");
			for (String id : idArray) {
				this.doRemove(teamId, Long.parseLong(id));
			}
		}
		return AjaxResult.getInstance().buildDefaultResult(true);
	}

	private void doRemove(Long teamId, Long memberId) {
		Team team = this.teamService.get(teamId);
		if (team != null) {
			for (Member member : team.getMembers()) {
				if (member.getId().compareTo(memberId) == 0) {
					team.getMembers().remove(member);
					this.teamService.save(team);
					break;
				}
			}
		}
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
			Member entity = this.service.get(id);
			model.addAttribute("member", entity);
		}
		return "cms.member.detail";
	}

	/**
	 * 取得项目成员
	 * 
	 * @param projectId
	 * @return
	 * @Desc:
	 * @Author:haipenge
	 * @Date:2016年1月6日 下午10:21:14
	 */
	@RequestMapping("/getMembersByProject")
	@ResponseBody
	public List<Member> getMembersByProject(@RequestParam(required = true) Long projectId) {
		List<Member> members = null;
		Project project = this.projectService.get(projectId);
		if (project != null) {
			Team team = project.getTeam();
			members = team.getMembers();
		}
		return members;
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
		Map params = HttpUtil.getRequestParams(request);
		Long teamId = MapUtils.getLong(params, "teamId");
		if (teamId!=null &&teamId > 0) {
			Team team = this.teamService.get(teamId);
			model.addAttribute("team", team);
		} else {
			Page<Team> teams = this.teamService.getPage(null, 1, 0);
			model.addAttribute("teams", teams);
		}
	}

	/**
	 *
	 * 保存数据前的回调函数
	 */
	protected void beforeSave(Member member, HttpServletRequest request) {
		if (member != null) {
			if (member.getId() == null || member.getId() <= 0) {
				String email = member.getEmail();
				Member existMember = this.service.getMemberByEmail(email);
				if (existMember != null) {
					member = existMember;
				}
			}
		}
	}

}
