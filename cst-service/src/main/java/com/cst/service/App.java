package com.cst.service;

import java.util.List;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


/**
 * 创建时间：2015-1-27 下午10:45:38
 * 
 * @author andy
 * @version 2.2
 */
@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = { "classpath:spring.xml",
//		"classpath:spring-mybatis.xml" })
public class App {
	
//	@Autowired
//	private ParameterTypeService parameterTypeService;
//
//	private static final Logger LOGGER = Logger
//			.getLogger(App.class);
	
	String s = UUID.randomUUID().toString();
	

	
	@Test
	public void testQueryById1() {
//		int i = parameterTypeService.getParameterTypeById(1).getParameterTypeId();
		System.out.println(s);
	}

}