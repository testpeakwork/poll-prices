package com.andersen.iexapis.poll.dao;

import com.andersen.iexapis.poll.dto.Company;
import org.springframework.cloud.gcp.data.datastore.repository.DatastoreRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyDao extends DatastoreRepository<Company, String> {


}
