package com.tw.repository;

import com.tw.domain.SmsData;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * User: imransm, Date: 29 Apr, 2011, Time: 11:56:55 AM
 * Problem:
 */

@Repository
@Scope("prototype")
public class SmsRepository {

    @Autowired
    private DataAccessFacade dataAccessFacade;

    public SmsRepository() {}

    public SmsRepository(DataAccessFacade dataAccessFacade) {
        this.dataAccessFacade = dataAccessFacade;
    }

    public Long save(SmsData data) {
        return (Long)dataAccessFacade.save(data);
    }

    public SmsData get(Long id) {
        return (SmsData) dataAccessFacade.get(SmsData.class,  id);
    }


    public List<SmsData> getLatestMessages(int numberOfEntries) {
        dataAccessFacade.setMaxResults(numberOfEntries);
        List<SmsData> results = dataAccessFacade.find("from SmsData as data order by data.id desc");
        dataAccessFacade.setMaxResults(0);
        return results;
    }
}
