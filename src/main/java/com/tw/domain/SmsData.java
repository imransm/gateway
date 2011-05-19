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
    
    @Column(name="username")
    private String username;

    @Column(name="password")
    private String password;

    @Column(name="text", length = 1000)
    private String text;

    @Column(name="source")
    private String from;

    @Column(name="concat")
    private String concat;

    @Column(name="destination")
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

    public String toString() {
        return "To: " + to + " From: " + from + " Text: " + text;
    }
}
