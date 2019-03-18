package com.andersen.iexapis.poll.dto;

import lombok.Data;
import org.springframework.cloud.gcp.data.datastore.core.mapping.Descendants;
import org.springframework.cloud.gcp.data.datastore.core.mapping.Entity;
import org.springframework.data.annotation.Id;

import java.util.List;

@Data
@Entity
public class Company {

    @Id
    private String symbol;
    private String logo;
    private String name;

    @Descendants
    private List<Stock> stocks;
}
