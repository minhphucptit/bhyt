package com.nminh.bhyt;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@ComponentScan("com")
class BhytApplicationTests {

	@Test
	void contextLoads() {
	}

}
