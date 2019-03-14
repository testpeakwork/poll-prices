package com.andersen.iexapis.poll.tasks;

import com.andersen.iexapis.poll.services.PollCurrentPricesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@Slf4j
public class PollPricesTask {
    private PollCurrentPricesService pollCurrentPricesService;

    @Autowired
    public PollPricesTask(PollCurrentPricesService pollCurrentPricesService) {
        this.pollCurrentPricesService = pollCurrentPricesService;
    }

    @Value("${task.symbols}")
    Set<String> symbols;

    @Scheduled(cron = "${task.cron.current}")
    public void process() {
        log.info("Start process data for symbols: "+symbols);
        pollCurrentPricesService.run(symbols);
        log.info("End process data for symbols: "+symbols);
    }
}
