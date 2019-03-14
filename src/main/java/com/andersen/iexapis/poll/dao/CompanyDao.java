package com.andersen.iexapis.poll.dao;

import com.andersen.iexapis.poll.dto.Company;
import com.andersen.iexapis.poll.dto.Stock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gcp.data.datastore.core.DatastoreTemplate;
import org.springframework.cloud.gcp.data.datastore.core.mapping.Entity;
import org.springframework.cloud.gcp.data.datastore.repository.DatastoreRepository;
import org.springframework.stereotype.Repository;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Repository
public interface CompanyDao extends DatastoreRepository<Company, String> {


}
