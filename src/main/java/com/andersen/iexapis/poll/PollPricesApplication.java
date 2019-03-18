package com.andersen.iexapis.poll;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import pl.zankowski.iextrading4j.client.IEXTradingClient;

@SpringBootApplication
@EnableScheduling
public class PollPricesApplication {

    public static void main(String[] args) {
        SpringApplication.run(PollPricesApplication.class, args);
    }

    @Bean
    public IEXTradingClient client() {
        return IEXTradingClient.create();
    }

}
