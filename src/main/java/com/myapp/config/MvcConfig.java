package com.myapp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class MvcConfig implements WebMvcConfigurer {
	
	@Override 
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		/* '/js/**'로 호출하는 자원은 '/static/js/' 폴더 아래에서 찾는다. */ 
        registry.addResourceHandler("/resources/js/**").addResourceLocations("classpath:/static/resources/js/").setCachePeriod(60 * 60 * 24 * 365); 
		/* '/css/**'로 호출하는 자원은 '/static/css/' 폴더 아래에서 찾는다. */ 
        registry.addResourceHandler("/resources/css/**").addResourceLocations("classpath:/static/resources/css/").setCachePeriod(60 * 60 * 24 * 365); 
		/* '/img/**'로 호출하는 자원은 '/static/img/' 폴더 아래에서 찾는다. */ 
        registry.addResourceHandler("/resources/img/**").addResourceLocations("classpath:/static/resources/img/").setCachePeriod(60 * 60 * 24 * 365); 
		/* '/font/**'로 호출하는 자원은 '/static/font/' 폴더 아래에서 찾는다. */ 
        registry.addResourceHandler("/resources/font/**").addResourceLocations("classpath:/static/resources/font/").setCachePeriod(60 * 60 * 24 * 365); 
	}

}
