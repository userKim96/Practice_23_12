package com.example.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.demo.interceptor.BeforeActionInterceptor;

@Configuration
public class MywebMvcConfigurer implements WebMvcConfigurer {

	private BeforeActionInterceptor beforeActionInterceptor;
	
	
	public MywebMvcConfigurer(BeforeActionInterceptor beforeActionInterceptor) {
		this.beforeActionInterceptor = beforeActionInterceptor;
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(beforeActionInterceptor).addPathPatterns("/**").excludePathPatterns("/resource/**");
	}
}
