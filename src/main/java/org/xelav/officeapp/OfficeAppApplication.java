package org.xelav.officeapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "org.xelav.officeapp.dao")
public class OfficeAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(OfficeAppApplication.class, args);
    }
}
