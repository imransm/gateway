package com.tw.domain;

import javax.persistence.*;

/**
 * User: imransm, Date: 4 May, 2011, Time: 5:55:42 PM
 * Problem:
 */
@Entity
@Table(name="ivr_data")
public class IvrData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "xml_data")
    private String xmlData;

    public IvrData() {
        
    }
    public IvrData(String xmlData) {
        this.xmlData = xmlData;
    }

    public Integer getId() {
        return id;
    }

    public String getXmlData() {
        return xmlData;
    }
}
