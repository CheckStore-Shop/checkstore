package io.checkstore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication(scanBasePackages = {"io.checkstore.**"})
@EntityScan(basePackages = {"io.checkstore.**"})
@EnableJpaRepositories(basePackages = {"io.checkstore.**"})
@EnableAsync
public class App {

    public static void main(String[] args){
        SpringApplication.run(App.class, args);
    }
}
