package com.tw.domain;


import javax.persistence.*;

/**
 * User: imransm, Date: 28 Apr, 2011, Time: 6:40:43 PM
 * Problem:
 */

@Entity
@Table(name = "sms_data")
public class SmsData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name="username", length = 25)
    private String username;

    @Column(name="password", length = 10)
    private String password;

    @Column(name="text", length = 140)
    private String text;

    @Column(name="source", length = 25)
    private String from;

    @Column(name="concat", length = 25)
    private String concat;

    @Column(name="destination", length = 25)
    private String to;

    public String getUsername() {
        return username;
    }

    public void setUsername(String userName) {
        this.username = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getConcat() {
        return concat;
    }

    public void setConcat(String concat) {
        this.concat = concat;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
