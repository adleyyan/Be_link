package com.hq.bms;
import com.hq.bms.Configuration.RsaKeyProperties;
import com.hq.bms.common.i18n.MyLocaleResolver;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.LocaleResolver;

/**
 * ${DESCRIPTION}
 *
 * @author wanghaobin
 * @create 2017-05-25 12:44
 */
//@SpringBootApplication(exclude = {JacksonAutoConfiguration.class})

//@EnableConfigurationProperties(RsaKeyProperties.class)
@SpringBootApplication()
@MapperScan("com.hq.bms.mapper")
@EnableTransactionManagement
@EnableCaching
@EnableAsync
@EnableEurekaClient
@EnableDiscoveryClient
public class UserBootstrap {
    @Autowired
    private RestTemplateBuilder builder;
    public static void main(String[] args) {
        SpringApplication.run(UserBootstrap.class, args);
    }

    @LoadBalanced
    @Bean
    public RestTemplate restTemplate() {
        return builder.build();
    }
    @Bean
    public LocaleResolver localeResolver(){
        return new MyLocaleResolver();
    }
    @Bean
    public MessageConverter getMessageConverter(){
        return new Jackson2JsonMessageConverter();
    }
}
