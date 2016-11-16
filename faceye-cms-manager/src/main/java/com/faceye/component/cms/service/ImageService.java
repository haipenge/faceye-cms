package com.faceye.component.cms.service;

import java.util.List;

import com.faceye.component.cms.entity.Content;
import com.faceye.component.cms.entity.Image;
import com.faceye.feature.service.BaseService;

public interface ImageService extends BaseService<Image, Long> {

	

	/**
	 * 根据图片访问路径查询
	 * @todo
	 * @param url
	 * @return
	 * @author:@haipenge
	 * haipenge@gmail.com
	 * 2015年3月30日
	 */
	public Image getImageByUrl(String url);
	
	public List<Image> getImagesByContent(Content content);


}
/**@generate-service-source@**/
