package com.faceye.component.cms.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.faceye.component.cms.entity.WorkRecord;
import com.faceye.component.cms.repository.mongo.WorkRecordRepository;
import com.faceye.component.cms.service.WorkRecordService;
import com.faceye.component.cms.util.Constants;
import com.faceye.component.security.service.UserService;
import com.faceye.feature.repository.mongo.DynamicSpecifications;
import com.faceye.feature.service.impl.BaseMongoServiceImpl;
import com.faceye.feature.util.ServiceException;
import com.querydsl.core.types.Predicate;

@Service
public class WorkRecordServiceImpl extends BaseMongoServiceImpl<WorkRecord, Long, WorkRecordRepository> implements WorkRecordService {

	@Autowired
	private UserService userService = null;

	@Autowired
	public WorkRecordServiceImpl(WorkRecordRepository dao) {
		super(dao);
	}

	@Override
	public Page<WorkRecord> getPage(Map<String, Object> searchParams, int page, int size)  {
		if (page != 0) {
			page = page - 1;
		}
		// SimpleEntityPathResolver resolver = SimpleEntityPathResolver.INSTANCE;
		// EntityPath<WorkRecord> entityPath = resolver.createPath(entityClass);
		// PathBuilder<WorkRecord> builder = new PathBuilder<WorkRecord>(entityPath.getType(), entityPath.getMetadata());
		// Path path = entityPath.getRoot();
		// List<Predicate> predicates=DynamicSpecifications.buildPredicates(searchParams, entityClass);
		// Predicate predicate=DynamicSpecifications.builder(predicates);
		// NumberPath numberPath = new NumberPath(Number.class, path, "age");
		// predicates.add(numberPath.eq(15));
		Predicate predicate = DynamicSpecifications.builder(searchParams, entityClass);
		if (predicate != null) {
			logger.debug(">>FaceYe -->Query predicate is:" + predicate.toString());
		}
		
		// Sort sort = new Sort(Direction.DESC, "id");
		Sort sort = new Sort(Direction.ASC, "orderIndex");
		Page<WorkRecord> res = null;
		if (size != 0) {
			Pageable pageable = new PageRequest(page, size, sort);
			res = this.dao.findAll(predicate, pageable);
		} else {
			// OrderSpecifier<Comparable> orderPOrderSpecifier=new OrderSpecifier<Comparable>(new Order(), new NumberExpression<WorkRecord>("id") {
			// })
			List<WorkRecord> items = (List) this.dao.findAll(predicate);
			res = new PageImpl<WorkRecord>(items);

		}
		return res;
	}

	@Override
	public boolean moveWorkRecord(Map params) {
		// 移出列类型:todo,doing,done
		String outColType = MapUtils.getString(params, "outColType");
		// 移入列类型:todo,doing,done
		String inColType = MapUtils.getString(params, "inColType");
		// 如果移出列与移入列相同，说明只是在同一列只变更顺序
		// 移出列的任务面板ID排序
		String outColTaskPanelOrderIds = MapUtils.getString(params, "outColTaskPanelOrderIds");
		// 移入列的任务面板ID排序
		String inColTaskPanelOrderIds = MapUtils.getString(params, "inColTaskPanelOrderIds");
		// 正在移动的task panel id
		String taskPanelId = MapUtils.getString(params, "taskPanelId");
		Integer taskPanelNewStatus = this.getTaskStatus(outColType, inColType);
		// 变更工作记录状态
		if (taskPanelNewStatus != null) {
			WorkRecord workRecord = this.get(Long.parseLong(taskPanelId));
			workRecord.setStatus(taskPanelNewStatus);
			this.save(workRecord);
		}
		// 变更task panel 排序值
		if (StringUtils.equalsIgnoreCase(inColType, outColType)) {
			this.resetWorkRecordOrder(inColType, inColTaskPanelOrderIds);
		} else {
			this.resetWorkRecordOrder(outColType, outColTaskPanelOrderIds);
			this.resetWorkRecordOrder(inColType, inColTaskPanelOrderIds);
		}
		return true;
	}

	/**
	 * 取得移动面板的新状态
	 * 
	 * @param outColType
	 * @param inColType
	 * @return
	 * @Desc:
	 * @Author:haipenge
	 * @Date:2016年1月3日 下午10:36:35
	 */
	private Integer getTaskStatus(String outColType, String inColType) {
		// 如果为null,则在本列移动顺序
		Integer status = null;
		if (!StringUtils.equals(outColType, inColType)) {
			if (StringUtils.isNotEmpty(inColType)) {
				status = this.getColType(inColType);
			}
		}
		return status;
	}

	/**
	 * 取得列的状态编码
	 * 
	 * @param colType
	 * @return
	 * @Desc:
	 * @Author:haipenge
	 * @Date:2016年1月3日 下午10:38:36
	 */
	private Integer getColType(String colType) {
		Integer status = null;
		if (StringUtils.equalsIgnoreCase(colType, "todo")) {
			status = Constants.WORK_RECORD_STATUS_TODO;
		} else if (StringUtils.equalsIgnoreCase(colType, "doing")) {
			status = Constants.WORK_RECORD_STATUS_DOING;
		} else if (StringUtils.equalsIgnoreCase(colType, "done")) {
			status = Constants.WORK_RECORD_STATUS_DONE;
		}
		return status;
	}

	/**
	 * 重新设置每列的面板排序值
	 * 
	 * @param colType
	 * @param taskPanelIds
	 * @return
	 * @Desc:
	 * @Author:haipenge
	 * @Date:2016年1月3日 下午10:50:22
	 */
	private boolean resetWorkRecordOrder(String colType, String taskPanelIds) {
		boolean res = false;
		int orderIndex = 1;
		if (StringUtils.isNotEmpty(taskPanelIds)) {
			String[] ids = StringUtils.split(taskPanelIds, ",");
			for (String id : ids) {
				if (StringUtils.isNotEmpty(id)) {
					WorkRecord workRecord = this.get(Long.parseLong(id));
					workRecord.setOrderIndex(orderIndex);
					orderIndex++;
					this.save(workRecord);
				}
			}
		}
		return res;
	}

	@Override
	public boolean updateWorkRecordStatus(Long id, Integer status) {
		WorkRecord workRecord = this.get(id);
		if (workRecord != null) {
			// 变更原来任务列的排序值
			Map searchParams = new HashMap();
			searchParams.put("EQ|status", workRecord.getStatus());
			searchParams.put("NE|id", id);
			Page<WorkRecord> workRecords = this.getPage(searchParams, 1, 100);
			if (workRecords != null && CollectionUtils.isNotEmpty(workRecords.getContent())) {
				int orderIndex = 1;
				for (WorkRecord wr : workRecords.getContent()) {
					wr.setOrderIndex(orderIndex);
					orderIndex++;
					this.save(wr);
				}
			}
			workRecord.setOrderIndex(0);
			workRecord.setStatus(status);
			this.save(workRecord);
			searchParams = new HashMap();
			searchParams.put("EQ|status", status);
			workRecords = this.getPage(searchParams, 1, 100);
			if (workRecords != null && CollectionUtils.isNotEmpty(workRecords.getContent())) {
				int orderIndex = 1;
				for (WorkRecord wr : workRecords.getContent()) {
					wr.setOrderIndex(orderIndex);
					orderIndex++;
					this.save(wr);
				}
			}

		}

		return false;
	}

}/** @generate-service-source@ **/
