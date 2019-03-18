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
public class LocalDatastoreTest {
    private final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");

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
    public void doTest() throws Exception {
        Calendar cal = Calendar.getInstance();
        doSaveCompanyTest(cal.getTime());
        doReadTest1();
        cal.add(Calendar.HOUR_OF_DAY, 2);
        doSaveCompanyTest(cal.getTime());
        doReadTest2();
    }

    public void doSaveCompanyTest(Date date) {
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

    private void doReadTest1() {
        Company e = companyDao.findById("AAPL").orElse(new Company());
        assertEquals("Apple", e.getName());
        // assertEquals(1, e.getStocks().size());
    }

    private void doReadTest2() {
        Company e = companyDao.findById("AAPL").orElse(new Company());
        assertEquals("Apple", e.getName());
        //  assertEquals(2, e.getStocks().size());
    }

}
