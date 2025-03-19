package com.mabadamo.techintcap;

import com.mabadamo.techintcap.common.config.properties.DiscountConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(DiscountConfig.class)
public class TechintcapApplication {

	public static void main(String[] args) {
		SpringApplication.run(TechintcapApplication.class, args);
	}

}
