package com.faceye.test.component.cms.service;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.faceye.component.cms.service.WeixinAppService;
import com.faceye.test.feature.service.BaseServiceTestCase;

public class WeixinAppServiceTestCase extends BaseServiceTestCase {

	@Autowired
	private WeixinAppService weixinAppService=null;
	@Test
	public void testGenerateImage() throws Exception {
		this.weixinAppService.writePersonal2Stream(null, 600, 710, null);
	}
	@Test
	public void testWritePersonalXinzuoceshi2Stream()throws Exception{
		Map params=new HashMap();
		params.put("month", "12");
		params.put("day", "1");
		params.put("name", "张三");
		this.weixinAppService.writePersonalXinzuoceshi2Stream(params, 480, 618, null);
	}
}
