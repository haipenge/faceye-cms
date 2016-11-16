package com.faceye.component.cms.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.faceye.component.cms.service.WeixinAppService;
import com.faceye.feature.util.GlobalEntity;
import com.faceye.feature.util.http.HttpUtil;

@Controller("cms-weixinAppController")
@RequestMapping("/weixinApp")
public class WeixinAppController {
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private WeixinAppService weixinAppService = null;

	/**
	 * 设置个人签名所需资源
	 * 
	 * @return
	 * @Desc:
	 * @Author:haipenge
	 * @Date:2016年7月16日 下午1:13:45
	 */
	@RequestMapping("/setSourceForGeneratePersonalSign")
	public String setSourceForGeneratePersonalSign(HttpServletRequest request, HttpServletResponse response, Model model) {
		GlobalEntity global = new GlobalEntity();
		global.setTitle("生成个性标签");
		global.setDesc("生成个性标签");
		global.setKeywords("生成个性标签");
		model.addAttribute("global", global);
		return "weixin.weixin.setSourceForGeneratePersonalSign";
	}
	
	@RequestMapping("/setSourceForGeneratePersonalXingzuoSign")
	public String setSourceForGeneratePersonalXingzuoSign(HttpServletRequest request, HttpServletResponse response, Model model) {
		GlobalEntity global = new GlobalEntity();
		global.setTitle("生成星座标签");
		global.setDesc("生成星座标签");
		global.setKeywords("生成星座标签");
		model.addAttribute("global", global);
		return "weixin.weixin.setSourceForGeneratePersonalXingzuoSign";
	}

	/**
	 * 个人信息提交与生成
	 * 
	 * @param request
	 * @return
	 * @Desc:
	 * @Author:haipenge
	 * @Date:2016年7月16日 下午1:34:29
	 */
	@RequestMapping("/onPersonalSignSubmit")
	public void onPersonalSignSubmit(HttpServletRequest request, HttpServletResponse response) {
		Map params = HttpUtil.getRequestParams(request);
		ServletOutputStream op = null;
		try {
			op = response.getOutputStream();
			response.setContentType("image/jpg");
//			this.weixinAppService.writePersonal2Stream(params, 480, 618, op);
			this.weixinAppService.writePersonal2Stream(params, 600, 900, op);
			op.flush();
		} catch (IOException e) {
			logger.error(">>FaceYe Throws Exception:", e);
		} finally {
			if (op != null) {
				try {
					op.close();
				} catch (IOException e) {
					logger.error(">>FaceYe Throws Exception:", e);
				}
			}
		}
		// return "";
	}
	
	@RequestMapping("/onPersonalXingzuoSignSubmit")
	public void onPersonalXingzuoSignSubmit(HttpServletRequest request, HttpServletResponse response) {
		Map params = HttpUtil.getRequestParams(request);
		ServletOutputStream op = null;
		try {
			op = response.getOutputStream();
			response.setContentType("image/jpg");
//			this.weixinAppService.writePersonalXinzuoceshi2Stream(params, 480, 618, op);
			this.weixinAppService.writePersonalXinzuoceshi2Stream(params, 580, 728, op);
			op.flush();
		} catch (IOException e) {
			logger.error(">>FaceYe Throws Exception:", e);
		} finally {
			if (op != null) {
				try {
					op.close();
				} catch (IOException e) {
					logger.error(">>FaceYe Throws Exception:", e);
				}
			}
		}
		// return "";
	}
	
	/////制作我的个性主页
}
