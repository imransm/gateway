package com.tw.repository;

import com.tw.domain.SmsData;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

/**
 * User: imransm, Date: 29 Apr, 2011, Time: 12:11:01 PM
 * Problem:
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/testContext.xml"})
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class})
public class SmsRepositoryTest {

    @Autowired
    private DataAccessFacade facade;
    private SmsRepository repository;

    @Before
    public void setUp() {
        repository = new SmsRepository(facade);
    }

    @Test
    public void shouldWriteSmsDataToTable() {

        SmsData data = new SmsData();
        String username = "test";
        String from = "testUser";
        String to = "testRecipient";
        String textMessage = "This is a sample message";
        String password = "test password";
        String concat = "concat";

        data.setUsername(username);
        data.setFrom(from);
        data.setTo(to);
        data.setText(textMessage);
        data.setPassword(password);
        data.setConcat(concat);

        Long id = repository.save(data);
        assertNotNull(id);
        SmsData expectedSmsData = repository.get(id);
        assertNotNull(expectedSmsData);
        assertEquals(from, expectedSmsData.getFrom());
        assertEquals(to, expectedSmsData.getTo());
        assertEquals(concat, expectedSmsData.getConcat());
        assertEquals(textMessage, expectedSmsData.getText());
        assertEquals(username, expectedSmsData.getUsername());
        assertEquals(password, expectedSmsData.getPassword());
    }

    @Test
    public void shouldRetrieveTopLastFiveSmsEntries() {
        createSmsEntries(10);
        List<SmsData> entries = repository.getLatestMessages(5);
        assertEquals(5, entries.size());
        assertEquals(10, entries.get(0).getId());
        assertEquals(9, entries.get(1).getId());
        assertEquals(8, entries.get(2).getId());
        assertEquals(7, entries.get(3).getId());
        assertEquals(6, entries.get(4).getId());
    }

    private void createSmsEntries(int numberOfEntries) {
        String username = "test";
        String textMessage = "message";
        String from = "from";
        String to = "to";
        for (int i = 1; i <= numberOfEntries; i++) {
            SmsData data = new SmsData();
            data.setUsername(username + i);
            data.setText(textMessage + i);
            data.setFrom(from + i);
            data.setTo(to + i);
            repository.save(data);
        }
    }
}
