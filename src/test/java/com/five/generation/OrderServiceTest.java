package com.five.generation;

import java.util.concurrent.CountDownLatch;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.five.generation.service.IOrderService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring-servlet.xml"})
@TransactionConfiguration(defaultRollback=true)
public class OrderServiceTest {
	
	private static final int THREAD_NUM=100;
	private static final CountDownLatch cdl = new CountDownLatch(THREAD_NUM);
	
	@Autowired
	@Qualifier("redisOrderServiceImpl")
	private IOrderService orderService;
	
	@Test
	public void test1() throws Exception{
		for (int i = 0; i < 100; i++) {
			new Thread(new OrderThread()).start();
			cdl.countDown(); //cdl 计数器-1  ， 当数值为0时，释放所有等待的线程
		}
		Thread.sleep(2000);
	}
	
	class OrderThread implements Runnable{

		public void run() {
			try {
				cdl.await(); //线程等待
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			orderService.getOrderId();
		}
		
	}
}
