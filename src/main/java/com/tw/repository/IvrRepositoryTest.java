package com.tw.repository;

import com.tw.domain.IvrData;
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
import static org.apache.commons.lang.builder.EqualsBuilder.reflectionEquals;
import static org.junit.Assert.assertTrue;

/**
 * User: imransm, Date: 4 May, 2011, Time: 5:51:26 PM
 * Problem:
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/testContext.xml"})
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class})
public class IvrRepositoryTest {

    private IvrRepository repository;

    @Autowired
    private DataAccessFacade dataAccessFacade;

    @Before
    public void setUp() {
        repository = new IvrRepository(dataAccessFacade);
    }

    @Test
    public void shouldSavePostedXmlData() {
        String xmlData = "sample data";
        IvrData data = new IvrData(xmlData);
        Integer id = repository.save(data);
        assertNotNull(id);
        IvrData expected = repository.get(id);
        assertTrue(reflectionEquals(expected, data));
    }

    @Test
    public void shouldRetrieveLatestIvrMessages() {
        createEntries();
        List<IvrData> entries = repository.getLatestMessages(5);
        assertEquals(Integer.valueOf(10), entries.get(0).getId());
        assertEquals(Integer.valueOf(9), entries.get(1).getId());
        assertEquals(Integer.valueOf(8), entries.get(2).getId());
        assertEquals(Integer.valueOf(7), entries.get(3).getId());
        assertEquals(Integer.valueOf(6), entries.get(4).getId());
    }

    private void createEntries() {
        for(int i=0; i<10; i++) {
            repository.save(new IvrData("sample" + i));
        }
    }
}
