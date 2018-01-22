package com.hg.nve;

import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author 李通 on 2018年1月20日 TimeUnit 类学习
 */
public class TimeUnitTest {

	@Test
	public void test1() {
		long hours = TimeUnit.DAYS.toHours(1); //hours=24
		
		System.out.println(hours);
	}
	
	@Test
	public void test2(){
		long seconds = TimeUnit.HOURS.toSeconds(1);
		System.out.println(seconds);
	}
	
	@Test
	public void test3(){
		//将三天转换成小时
		long convert = TimeUnit.HOURS.convert(3, TimeUnit.DAYS);
		System.out.println(convert);
	}
	
	@Test
	public void test4(){
		//线程延时
		new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("开始延迟");
				try {
					TimeUnit.SECONDS.sleep(5);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("延时完成");
			}
		}).start();
	}
	
	@Test
	public void test5(){
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				System.out.println("开始延迟");
				try {
					Thread.sleep(5);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("延时完成");
				
			}
		}).start();
	}
	
	@Test
	public void test6(){
		Logger log = LoggerFactory.getLogger(this.getClass());
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				log.info("开始延迟");
				try {
					Thread.sleep(5);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				log.info("延迟完毕");
			}
		}).start();
	}
}