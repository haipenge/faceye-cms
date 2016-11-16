package com.faceye.component.cms.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.faceye.component.cms.entity.Content;
import com.faceye.component.cms.entity.Image;
import com.faceye.component.cms.repository.mongo.ImageRepository;
import com.faceye.component.cms.service.ImageService;
import com.faceye.feature.service.impl.BaseMongoServiceImpl;
import com.faceye.feature.util.PathUtil;

@Service("cms-imageService")
public class ImageServiceImpl extends BaseMongoServiceImpl<Image, Long, ImageRepository> implements ImageService {

	@Autowired
	public ImageServiceImpl(ImageRepository dao) {
		super(dao);
	}
	

	/**
	 * 构建图片存储路径
	 * @todo
	 * @return
	 * @author:@haipenge
	 * haipenge@gmail.com
	 * 2015年3月5日
	 */
	private String buildImgStorePath(String suffix) {
		String res = "";
		if (StringUtils.isNotEmpty(suffix)) {
			res = PathUtil.generateDynamicDirs();
			res += PathUtil.generateFileName();
			res += ".";
			res += suffix;
			logger.debug(">>FaceYe-->图片的存储路径是:" + res);
		}
		return res;
	}

	@Override
	public Image getImageByUrl(String url) {
		return this.dao.getImageByUrl(url);
	}


	@Override
	public List<Image> getImagesByContent(Content content) {
		return this.dao.getImagesByContent(content);
	}

	

}
/**@generate-service-source@**/
