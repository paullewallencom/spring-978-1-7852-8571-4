package com.packtpub.springrest.booking;

import com.packtpub.springrest.booking.init.BookingServiceInitialiser;
import com.packtpub.springrest.init.InventoryServiceInitialiser;
import com.packtpub.springrest.inventory.InventoryService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class WebApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context =
                SpringApplication.run(new Object[]{WebApplication.class, "booking.xml"}, args);
        InventoryServiceInitialiser.init(context.getBean(InventoryService.class));
        BookingServiceInitialiser.init(context.getBean(BookingService.class));
    }
}
