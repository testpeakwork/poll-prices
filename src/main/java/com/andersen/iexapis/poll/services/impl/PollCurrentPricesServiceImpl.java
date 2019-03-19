package com.andersen.iexapis.poll.services.impl;

import com.andersen.iexapis.poll.dao.CompanyDao;
import com.andersen.iexapis.poll.dto.Company;
import com.andersen.iexapis.poll.dto.Stock;
import com.andersen.iexapis.poll.services.PollPricesService;
import com.andersen.iexapis.poll.services.impl.iexloader.IEXTradingService;
import com.google.cloud.Timestamp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.zankowski.iextrading4j.api.stocks.BatchStocks;

import java.text.MessageFormat;
import java.util.*;

@Service
@Slf4j
public class PollCurrentPricesServiceImpl implements PollPricesService {

    private final IEXTradingService iexTradingService;
    private final CompanyDao companyDao;

    public PollCurrentPricesServiceImpl(IEXTradingService iexTradingService, CompanyDao companyDao) {
        this.iexTradingService = iexTradingService;
        this.companyDao = companyDao;
    }

    public void run(Set<String> symbols) {
        log.info(MessageFormat.format("start getting current prices for symbols: {0}", symbols));
        Map<String, BatchStocks> results = iexTradingService.getCurrentPrices(symbols);
        Map<String, Company> map = new HashMap<>();
        for (Map.Entry<String, BatchStocks> entry : results.entrySet()) {
            Company company = map.get(entry.getKey());
            if (company == null) {
                company = new Company();
                company.setLogo(entry.getValue().getLogo().getUrl());
                company.setName(entry.getValue().getCompany().getCompanyName());
                company.setSymbol(entry.getKey());
                company.setStocks(new ArrayList<>());
                map.put(company.getSymbol(), company);
            }
            Stock stock = new Stock();
            Date date = new Date();
            stock.setDateTime(Timestamp.of(date));
            stock.setPrice(entry.getValue().getPrice().doubleValue());
            stock.setCompanySymbol(company.getSymbol());
            company.getStocks().add(stock);
        }

        for (Company company : map.values()) {
            companyDao.save(company);
        }
        log.info(MessageFormat.format("saved current prices for symbols: {0}, count: {1}", symbols, results.size()));
    }


}
