package com.faceye.component.cms.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.faceye.component.cms.entity.Content;
import com.faceye.component.cms.entity.Image;
import com.faceye.component.cms.repository.mongo.ContentRepository;
import com.faceye.component.cms.service.ContentService;
import com.faceye.component.cms.service.ImageService;
import com.faceye.component.weixin.entity.Account;
import com.faceye.component.weixin.entity.ResponseContent;
import com.faceye.component.weixin.entity.ResponseContentItem;
import com.faceye.component.weixin.service.AccountService;
import com.faceye.component.weixin.service.ResponseContentItemService;
import com.faceye.component.weixin.service.ResponseContentService;
import com.faceye.feature.repository.mongo.DynamicSpecifications;
import com.faceye.feature.service.PropertyService;
import com.faceye.feature.service.impl.BaseMongoServiceImpl;
import com.faceye.feature.util.JspUtil;
 
import com.faceye.feature.util.regexp.RegexpUtil;
import com.querydsl.core.types.Predicate;

/**
 * 模块:内容管理->com.faceye.compoent.cms.service.impl<br>
 * 说明:<br>
 * 实体:内容->com.faceye.component.cms.entity.entity.Content 服务实现类<br>
 * 
 * @author haipeng <br>
 *         联系:haipenge@gmail.com<br>
 *         创建日期:2015-7-25 9:40:29<br>
 */
@Service
public class ContentServiceImpl extends BaseMongoServiceImpl<Content, Long, ContentRepository> implements ContentService {
	@Autowired
	private PropertyService propertyService = null;
	@Autowired
	private ImageService imageService = null;
	@Autowired
	private AccountService accountService = null;
	@Autowired
	private ResponseContentService responseContentService = null;
	@Autowired
	private ResponseContentItemService responseContentItemService = null;
	// 微信的appid
	@Value("#{property['weixin.app.id']}")
	private String appId = "";
	@Value("#{property['faceye.cms.host']}")
	private String host = "";
	@Value("#{property['image.server']}")
	private String imageServer = "";
	private final static String DISTILL_IMG_SRC = "<img[^>]*?src=[\"\\']?([^\"\\'>]+)[\"\\']?[^>]*\\/>";

	@Autowired
	public ContentServiceImpl(ContentRepository dao) {
		super(dao);
	}

	/**
	 * 数据分页查询
	 * 
	 * @author haipeng <br>
	 *         联系:haipenge@gmail.com<br>
	 *         创建日期:2015-7-25 9:40:29<br>
	 */
	@Override
	public Page<Content> getPage(Map<String, Object> searchParams, int page, int size)  {
		if (page != 0) {
			page = page - 1;
		}
		// SimpleEntityPathResolver resolver = SimpleEntityPathResolver.INSTANCE;
		// EntityPath<Content> entityPath = resolver.createPath(entityClass);
		// PathBuilder<Content> builder = new PathBuilder<Content>(entityPath.getType(), entityPath.getMetadata());
		// Path path = entityPath.getRoot();
		// List<Predicate> predicates=DynamicSpecifications.buildPredicates(searchParams, entityClass);
		// Predicate predicate=DynamicSpecifications.builder(predicates);
		// NumberPath numberPath = new NumberPath(Number.class, path, "age");
		// predicates.add(numberPath.eq(15));
		Predicate predicate = DynamicSpecifications.builder(searchParams, entityClass);
		if (predicate != null) {
			logger.debug(">>FaceYe -->Query predicate is:" + predicate.toString());
		}
		Sort sort = new Sort(Direction.DESC, "id");
		Page<Content> res = null;
		if (size != 0) {
			Pageable pageable = new PageRequest(page, size, sort);
			res = this.dao.findAll(predicate, pageable);
		} else {
			// OrderSpecifier<Comparable> orderPOrderSpecifier=new OrderSpecifier<Comparable>(new Order(), new NumberExpression<Content>("id") {
			// })
			List<Content> items = (List) this.dao.findAll(predicate);
			res = new PageImpl<Content>(items);

		}
		return res;
	}

	@Override
	public Content save(Content entity) {
		String name = entity.getName();
		if (StringUtils.isEmpty(entity.getKeywords())) {
			entity.setKeywords(entity.getName());
		}
		if (StringUtils.isEmpty(entity.getDescription())) {
			entity.setDescription(entity.getName());
		}
		super.save(entity);
		String content = entity.getContent();
		// 提取文章内容中的全部图片链接
		List<String> imageUrls = new ArrayList<String>(0);
		try {
			List<Map<String, String>> matches = RegexpUtil.match(entity.getContent(), DISTILL_IMG_SRC);
			if (CollectionUtils.isNotEmpty(matches)) {
				for (Map<String, String> map : matches) {
					String url = map.get("1");
					String imgServer = this.propertyService.get("image.server");
					String storePath = url.replace(imgServer, "");
					imageUrls.add(storePath);
					if (StringUtils.isNotEmpty(url)) {
						Image image = this.imageService.getImageByUrl(url);
						if (null == image) {
							image = new Image();
							image.setUrl(url);
							image.setStorePath(storePath);
							image.setContent(entity);
							this.imageService.save(image);
						}
					}
				}
			}
		} catch (Exception e) {
			logger.error(">>FaceYe throws Exception: --->", e);
		}
		entity.setImageUrls(imageUrls);
		super.save(entity);
		// 发布到微信
		Account account = this.accountService.getAccountByAppId(appId);
		String url = "/cms/content/weixin/" + entity.getId() + ".html";
		if (account != null) {
			this.push2Weixin(entity, account);
		}
		return entity;
	}

	@Override
	public void saveAndPush2Weixin(Long id, Long... weixinId) {
		Content content = this.dao.findOne(id);
		if (content != null) {
			for (Long wid : weixinId) {
				Account account = this.accountService.get(wid);
				this.push2Weixin(content, account);
			}
		}
	}

	private void push2Weixin(Content content, Account account) {
		// 发布到微信
		// Account account = this.accountService.getAccountByAppId(appId);
		String url = "/cms/content/weixin/" + content.getId() + ".html";
		if (account != null) {
			ResponseContent responseContent = this.responseContentService.getDefaultResponseContent(account);
			if (responseContent != null) {
				ResponseContentItem responseContentItem = this.responseContentItemService.getResponseContentItemByResponseContentAndUrl(responseContent.getId(),url);
				if (responseContentItem == null) {
					responseContentItem = new ResponseContentItem();
					responseContentItem.setName(content.getName());
					// List<Image> images = this.imageService.getImagesByContent(entity);
					if (CollectionUtils.isNotEmpty(content.getImageUrls())) {
						responseContentItem.setPicUrl(content.getImageUrls().get(0));
					} else {
						logger.debug(">>FaceYe --> article images is empty.");
					}
					responseContentItem.setRemark(JspUtil.getSummary(content.getContent(), 40));
					responseContentItem.setResponseContent(responseContent);
					responseContentItem.setUrl(url);
					this.responseContentItemService.save(responseContentItem);
				}
			}
		}
	}

	@Override
	public void saveAndPush2Weixin(Long[] ids, Long[] weixinIds) {
		if(ids!=null && weixinIds!=null){
			for(Long id:ids){
				this.saveAndPush2Weixin(id, weixinIds);
			}
		}
	}

}
/** @generate-service-source@ **/
