package com.packtpub.springrest.inventory;

import com.packtpub.springrest.init.InventoryServiceInitialiser;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class WebApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context =
                SpringApplication.run(new Object[]{WebApplication.class, "inventory.xml"}, args);
        InventoryServiceInitialiser.init(context.getBean(InventoryService.class));
    }
}
