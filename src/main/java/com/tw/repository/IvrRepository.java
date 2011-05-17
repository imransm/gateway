package com.tw.repository;

import com.tw.domain.IvrData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * User: imransm, Date: 4 May, 2011, Time: 5:40:32 PM
 * Problem:
 */
@Repository
@Scope("prototype")
public class IvrRepository {
    @Autowired
    private DataAccessFacade dataAccessFacade;

    public IvrRepository(DataAccessFacade dataAccessFacade) {
        this.dataAccessFacade = dataAccessFacade;
    }

    public IvrRepository() {
        
    }

    public Integer save(IvrData data) {
        return (Integer) dataAccessFacade.save(data);
    }

    public IvrData get(Integer id) {
        return (IvrData) dataAccessFacade.get(IvrData.class, id);
    }

    public List<IvrData> getLatestMessages(int numberOfEntries) {
        dataAccessFacade.setMaxResults(numberOfEntries);
        List<IvrData> results = dataAccessFacade.find("from IvrData as data order by data.id desc");
        dataAccessFacade.setMaxResults(0);
        return results;
    }
}
