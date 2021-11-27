package com.example.driversandcars;

import com.example.driversandcars.controller.DriversAndCarsController;
import org.hibernate.validator.internal.engine.ConfigurationImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class DriversAndCarsApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(DriversAndCarsApplication.class, args);
    }

}
