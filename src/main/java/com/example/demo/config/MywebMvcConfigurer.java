package com.example.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.demo.interceptor.BeforeActionInterceptor;
import com.example.demo.interceptor.NeedLoginterceptor;

@Configuration
public class MywebMvcConfigurer implements WebMvcConfigurer {

	private BeforeActionInterceptor beforeActionInterceptor;
	private NeedLoginterceptor needLoginterceptor;

	public MywebMvcConfigurer(BeforeActionInterceptor beforeActionInterceptor, NeedLoginterceptor needLoginterceptor) {
		this.beforeActionInterceptor = beforeActionInterceptor;
		this.needLoginterceptor = needLoginterceptor;
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(beforeActionInterceptor).addPathPatterns("/**").excludePathPatterns("/resource/**");

		registry.addInterceptor(needLoginterceptor).addPathPatterns("/usr/article/doWrite")
				.addPathPatterns("/usr/article/doDelete").addPathPatterns("/usr/article/doModify")
				.addPathPatterns("/usr/member/doLogout");
	}
}
