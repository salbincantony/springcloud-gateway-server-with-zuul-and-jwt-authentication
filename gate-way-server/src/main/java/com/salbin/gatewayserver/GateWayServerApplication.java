package com.salbin.gatewayserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
@EnableZuulProxy  // act as zuul proxy.
//@EnableEurekaServer //for making this application as eureka server.
@EnableEurekaClient
@SpringBootApplication
public class GateWayServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(GateWayServerApplication.class, args);
	}

}
