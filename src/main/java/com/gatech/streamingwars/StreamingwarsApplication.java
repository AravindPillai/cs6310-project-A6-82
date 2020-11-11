package com.gatech.streamingwars;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class StreamingwarsApplication {

    public static void main(String[] args) {
        SpringApplication.run(StreamingwarsApplication.class, args);
    }

}
