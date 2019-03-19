package com.andersen.iexapis.poll;

import com.andersen.iexapis.poll.dao.CompanyDao;
import com.andersen.iexapis.poll.dao.StockDao;
import com.andersen.iexapis.poll.dto.Company;
import com.andersen.iexapis.poll.dto.Stock;
import com.google.cloud.Timestamp;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

@SpringBootTest
@RunWith(SpringRunner.class)
@TestPropertySource("classpath:application-test.properties")
public class CompanyDaoTest {

    @Autowired
    private CompanyDao companyDao;

    @Autowired
    private StockDao stockDao;

    @After
    public void cleanUp() {
        companyDao.deleteAll();
        stockDao.deleteAll();
    }

    @Test
    public void doSaveCompanyTest() {
        Calendar cal = Calendar.getInstance();
        saveCompanyPrice(cal.getTime());
        readAndCheckCompany(1);
        cal.add(Calendar.HOUR_OF_DAY, 2);
        saveCompanyPrice(cal.getTime());
        readAndCheckCompany(2);
    }

    private void saveCompanyPrice(Date date) {
        Company company = new Company();
        company.setSymbol("AAPL");
        company.setName("Apple");
        company.setLogo("http://www.appple.com/image.png");
        List<Stock> stocks = new ArrayList<>();
        Stock stock = new Stock();
        stock.setDateTime(Timestamp.of(date));
        stock.setPrice(10.11d);
        stock.setCompanySymbol(company.getSymbol());
        stocks.add(stock);
        company.setStocks(stocks);
        companyDao.save(company);
    }

    private void readAndCheckCompany(int expectedCount) {
        Company e = companyDao.findById("AAPL").orElse(new Company());
        assertEquals("Apple", e.getName());
        assertEquals(expectedCount, e.getStocks().size());
    }
}
