package com.andersen.iexapis.poll.tasks;

import com.andersen.iexapis.poll.services.PollPricesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@Slf4j
public class PollPricesTask {
    private final Set<String> symbols;
    private final PollPricesService pollPricesService;

    @Autowired
    public PollPricesTask(PollPricesService pollPricesService, @Value("${task.symbols}") Set<String> symbols) {
        this.pollPricesService = pollPricesService;
        this.symbols = symbols;
    }

    @Scheduled(cron = "${task.cron.current}")
    public void process() {
        log.info("Start process data for symbols: " + symbols);
        pollPricesService.run(symbols);
        log.info("End process data for symbols: " + symbols);
    }
}
