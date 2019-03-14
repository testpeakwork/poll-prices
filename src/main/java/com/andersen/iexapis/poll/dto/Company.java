package com.andersen.iexapis.poll.dto;

import lombok.Data;

import java.util.List;

import org.springframework.cloud.gcp.data.datastore.core.mapping.Descendants;
import org.springframework.cloud.gcp.data.datastore.core.mapping.Entity;
import org.springframework.cloud.gcp.data.datastore.core.mapping.Field;
import org.springframework.data.annotation.Id;
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
