package com.crm.service;

import java.util.List;

import com.crm.pojo.BaseDict;
import com.crm.pojo.Customer;
import com.crm.pojo.QueryVo;

public interface CustomerService {

	public List<BaseDict> findDictByCode(String code) throws Exception;
	public List<Customer> findCustomerByVo(QueryVo vo) throws Exception;
	public Integer findCustomerByVoCount(QueryVo vo) throws Exception;
	public Customer findCustomerById(Long id) throws Exception;
}
