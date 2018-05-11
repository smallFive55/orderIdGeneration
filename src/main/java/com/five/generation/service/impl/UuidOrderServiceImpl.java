package com.five.generation.service.impl;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.five.generation.service.IOrderService;

/**
 * 
 * @author Five
 * @createTime 2018年1月29日 下午12:26:46
 * 
 */
@Service("uuidOrderServiceImpl")
public class UuidOrderServiceImpl implements IOrderService {

	public void getOrderId() {
		UUID randomUUID = UUID.randomUUID(); //UUID生成
		System.out.println("insert into order_id(id) values('"+randomUUID+"');");
	}

}
