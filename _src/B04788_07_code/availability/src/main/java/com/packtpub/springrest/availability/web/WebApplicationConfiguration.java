package com.packtpub.springrest.availability.web;

import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.ShallowEtagHeaderFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.servlet.Filter;

@Configuration
@EnableWebMvc
@ComponentScan
public class WebApplicationConfiguration extends WebMvcAutoConfiguration {

    // we need this class to enable automatic configuration

    @Bean
    public Filter etagFilter() {
        return new ShallowEtagHeaderFilter();
    }
}
