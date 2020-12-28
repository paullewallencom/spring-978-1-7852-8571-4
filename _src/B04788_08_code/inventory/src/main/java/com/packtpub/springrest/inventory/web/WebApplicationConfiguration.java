package com.packtpub.springrest.inventory.web;

import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@ComponentScan
public class WebApplicationConfiguration extends WebMvcAutoConfiguration {

    // we need this class to enable automatic configuration
}
