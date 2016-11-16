package com.faceye.component.cms.service;

import java.io.OutputStream;
import java.util.Map;

public interface WeixinAppService {
	
	public void writePersonal2Stream(Map params,int width,int height,OutputStream outputStream);
	
	public void writePersonalXinzuoceshi2Stream(Map params, int width, int height, OutputStream outputStream);
}
