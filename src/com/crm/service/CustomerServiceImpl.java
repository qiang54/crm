package com.crm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crm.dao.CustomerMapper;
import com.crm.dao.DictMapper;
import com.crm.pojo.BaseDict;
import com.crm.pojo.Customer;
import com.crm.pojo.QueryVo;

@Service
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	private DictMapper dictMapper;
	@Autowired
	private CustomerMapper customerMapper;
	
	@Override
	public List<BaseDict> findDictByCode(String code) throws Exception{
		List<BaseDict> list = dictMapper.findDictByCode(code);
		return list;
		
	}

	/**
	 * 高级查询
	 */
	@Override
	public List<Customer> findCustomerByVo(QueryVo vo) throws Exception {
		List<Customer> list = customerMapper.findCustomerByVo(vo);
		return list;
	}

	/**
	 * 查询所有数据总数
	 */
	@Override
	public Integer findCustomerByVoCount(QueryVo vo) throws Exception {
		Integer list = customerMapper.findCustomerByVoCount(vo);
		return list;
	}

	/**
	 * 通过id查询客户
	 */
	@Override
	public Customer findCustomerById(Long id) throws Exception {
		Customer customer = customerMapper.findCustomerById(id);
		return customer;
	}
}
