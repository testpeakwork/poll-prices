package com.andersen.iexapis.poll.services.impl.iexloader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.zankowski.iextrading4j.api.stocks.BatchStocks;
import pl.zankowski.iextrading4j.client.IEXTradingClient;
import pl.zankowski.iextrading4j.client.rest.request.stocks.BatchMarketStocksRequestBuilder;
import pl.zankowski.iextrading4j.client.rest.request.stocks.BatchStocksType;

import java.util.Map;
import java.util.Set;

@Service
public class IEXTradingService {

    private final IEXTradingClient client;

    @Autowired
    public IEXTradingService(IEXTradingClient client) {
        this.client = client;
    }

    public final Map<String, BatchStocks> getCurrentPrices(Set<String> symbols) {
        BatchMarketStocksRequestBuilder batchRequest = buildCurrentBatchRequest(symbols);
        return client.executeRequest(batchRequest.build());
    }

    private BatchMarketStocksRequestBuilder buildCurrentBatchRequest(Set<String> symbols) {
        BatchMarketStocksRequestBuilder batchRequest = new BatchMarketStocksRequestBuilder()
                .addType(BatchStocksType.PRICE)
                .addType(BatchStocksType.LOGO)
                .addType(BatchStocksType.COMPANY);

        symbols.forEach(batchRequest::withSymbol);
        return batchRequest;
    }
}
