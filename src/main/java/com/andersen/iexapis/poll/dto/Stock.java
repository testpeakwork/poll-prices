package com.andersen.iexapis.poll.dto;

import com.google.cloud.Timestamp;
import com.google.cloud.datastore.Key;
import lombok.Data;
import org.springframework.cloud.gcp.data.datastore.core.mapping.Entity;
import org.springframework.cloud.gcp.data.datastore.core.mapping.Unindexed;
import org.springframework.data.annotation.Id;

@Data
@Entity
public class Stock {

    @Id
    Key purchasedItemKey;
    private Timestamp dateTime;
    private String companySymbol;

    @Unindexed
    private double price;
}
