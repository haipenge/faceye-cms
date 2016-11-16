package com.faceye.component.cms.service;

import java.util.Map;

import com.faceye.component.cms.entity.WorkRecord;
import com.faceye.feature.service.BaseService;

/**
 * WorkRecord 服务接品<br>
 * 
 * @author @haipenge <br>
 *         haipenge@gmail.com<br>
 *         Create Date:2014年5月20日<br>
 */
public interface WorkRecordService extends BaseService<WorkRecord, Long> {

	/**
	 * 移动工作记录
	 * 
	 * @param params
	 * @return
	 * @Desc:
	 * @Author:haipenge
	 * @Date:2016年1月3日 下午10:23:58
	 */
	public boolean moveWorkRecord(Map params);
	
	/**
	 * 更新任务状态
	 * @param id
	 * @param status
	 * @return
	 * @Desc:
	 * @Author:haipenge
	 * @Date:2016年1月4日 下午6:07:47
	 */
	public boolean updateWorkRecordStatus(Long id,Integer status);
}/** @generate-service-source@ **/
