package com.rubber.oa.gateway.web;

import com.rubber.oa.gateway.web.servlet.OaGatewayWebServlet;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.DispatcherServlet;

@SpringBootApplication
@MapperScan("com.rubber.oa.gateway.dao.mapper")
public class RubberOaGatewayWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(RubberOaGatewayWebApplication.class, args);
	}


	@Bean(name = "dispatcherServlet")
	public DispatcherServlet dispatcherServlet(WebMvcProperties webMvcProperties) {
		OaGatewayWebServlet dispatcherServlet = new OaGatewayWebServlet();
		dispatcherServlet.setDispatchOptionsRequest(webMvcProperties.isDispatchOptionsRequest());
		dispatcherServlet.setDispatchTraceRequest(webMvcProperties.isDispatchTraceRequest());
		dispatcherServlet.setThrowExceptionIfNoHandlerFound(webMvcProperties.isThrowExceptionIfNoHandlerFound());
		dispatcherServlet.setPublishEvents(webMvcProperties.isPublishRequestHandledEvents());
		dispatcherServlet.setEnableLoggingRequestDetails(webMvcProperties.isLogRequestDetails());
		return dispatcherServlet;
	}
}
