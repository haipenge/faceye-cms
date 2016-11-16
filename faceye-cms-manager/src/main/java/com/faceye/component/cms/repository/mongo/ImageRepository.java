package com.faceye.component.cms.repository.mongo;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.faceye.component.cms.entity.Content;
import com.faceye.component.cms.entity.Image;
import com.faceye.feature.repository.mongo.BaseMongoRepository;

/**
 * Image 实体DAO
 * @author @haipenge 
 * haipenge@gmail.com
*  Create Date:2014年5月20日
 */
@Repository("cms-imageRepository")
public interface ImageRepository extends BaseMongoRepository<Image, Long> {

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
/**@generate-repository-source@**/
