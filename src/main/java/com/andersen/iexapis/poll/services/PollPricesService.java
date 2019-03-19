package com.andersen.iexapis.poll.services;

import java.util.Set;

/**
 * Service for polling prices from IEXTrade to database
 *
 * @author Dzemianchyk_AI
 */
public interface PollPricesService {
    /**
     * Poll IEXTrade prices of companies from symbols set and save result database
     *
     * @param symbols companies symbols that will be included in the result
     */
    void run(Set<String> symbols);
}
