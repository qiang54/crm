package com.crm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crm.pojo.BaseDict;
import com.crm.pojo.Customer;
import com.crm.pojo.QueryVo;
import com.crm.service.CustomerService;

import cn.itcast.utils.Page;

@Controller
@RequestMapping("/customer")
public class CustomerController {
	
	@Value("${customer.dict.source}")
	private String source;
	@Value("${customer.dict.industry}")
	private String industry;
	@Value("${customer.dict.level}")
	private String level;
	@Autowired
	private CustomerService customerService;
	
	/**
	 * 高级查询
	 * @param vo
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list")
	public String list(QueryVo vo,Model model) throws Exception {
		
		//客户来源
		List<BaseDict> sourceList = customerService.findDictByCode(source);
		//客户行业
		List<BaseDict> industryList = customerService.findDictByCode(industry);
		//客户级别
		List<BaseDict> levelList = customerService.findDictByCode(level);
		
		if(vo.getCustName() != null ) {
			vo.setCustName(new String(vo.getCustName().getBytes("iso8859-1"),"utf-8"));
		}
		
		//设置起始查询页面
		if(vo.getPage() == null) {
			vo.setPage(1);
		}
		vo.setStart((vo.getPage() - 1) * vo.getSize());
		//查询列表和数据总数
		List<Customer> list = customerService.findCustomerByVo(vo);
		Integer count = customerService.findCustomerByVoCount(vo);
		
		//分页查询
		Page<Customer> page = new Page<>();
		page.setPage(vo.getPage()); //当前页码
		page.setRows(list);         //数据列表
		page.setSize(vo.getSize()); //每页显示条数
		page.setTotal(count); //数据总数
		
		//
		model.addAttribute("page", page);
		
		//高级下拉菜单
		model.addAttribute("fromType", sourceList);
		model.addAttribute("industryType", industryList);
		model.addAttribute("levelType", levelList);
		
		//回显选中数据
		model.addAttribute("custName", vo.getCustName());
		model.addAttribute("custSource", vo.getCustSource());
		model.addAttribute("custIndustry", vo.getCustIndustry());
		model.addAttribute("custLevel", vo.getCustLevel());
		return "customer"; 
		
	}
	
	/**
	 * 按id查询
	 * @throws Exception 
	 * @ResponseBody是结果返回一个pojo类型的对象
	 */
	@RequestMapping("/edit")
	@ResponseBody
	public Customer edit(Long id) throws Exception {
		
		Customer customer = customerService.findCustomerById(id);
		return customer;
	}
	

	
	
	
	
	
	
	
	
	
	
	
	
	
}	
