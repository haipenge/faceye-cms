package com.faceye.component.activity.service;

import com.faceye.component.activity.entity.Customer;
import com.faceye.feature.service.BaseService;
/**
 * Customer 服务接品<br>
 * @author @haipenge <br>
 * haipenge@gmail.com<br>
*  Create Date:2014年5月20日<br>
 */
public interface CustomerService extends BaseService<Customer,Long>{
	public Customer getCustomerByPhone(String phone);
	
}/**@generate-service-source@**/
