package com.hq.bms.Configuration;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.List;

@Configuration
//@ConditionalOnClass(Gson.class)
//@ConditionalOnMissingClass("com.fasterxml.jackson.core.JsonGenerator")
//@ConditionalOnBean(Gson.class)
@EnableWebMvc
public class GsonHttpMessageConverterConfiguration extends WebMvcConfigurationSupport {
    @Override
    protected void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.removeIf(httpMessageConverter -> httpMessageConverter instanceof MappingJackson2HttpMessageConverter);
        converters.add(new GsonHttpMessageConverter());
        super.extendMessageConverters(converters);
    }
//    @Bean
//    @ConditionalOnMissingBean
//    public GsonHttpMessageConverter gsonHttpMessageConverter(Gson gson) {
//        GsonHttpMessageConverter converter = new GsonHttpMessageConverter();
//        converter.setGson(gson);
//        return converter;
//    }
}
