package com.test;

import org.apache.log4j.Logger;
import org.junit.Test;

public class TestLogLayout {

	@Test
	public void testLayout(){
		Logger log = Logger.getLogger(this.getClass());
		log.info("i am litong");
	}
}
