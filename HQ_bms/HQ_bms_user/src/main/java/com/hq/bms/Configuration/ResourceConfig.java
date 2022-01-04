package com.hq.bms.Configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ResourceConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //可以访问localhost:8095/static/images/image.jpg
        registry.addResourceHandler("/resources/**").addResourceLocations("classpath:/resources/");
    }
}
