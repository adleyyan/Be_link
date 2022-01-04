package com.hq.bms;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;


/**
 * @author Adley.yan
 */
@EnableEurekaServer
@SpringBootApplication(exclude = {DruidDataSourceAutoConfigure.class,DataSourceAutoConfiguration.class, DataSourceTransactionManagerAutoConfiguration.class })
public class HoneycombBootstrap
{
    public static void main( String[] args )
    {
        SpringApplication.run(HoneycombBootstrap.class,args);
    }
}
