package com.tw.controller;

import com.tw.domain.SmsData;
import com.tw.repository.SmsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * User: imransm, Date: 28 Apr, 2011, Time: 12:17:10 PM
 * Problem:
 */
@Controller
@Scope("prototype")
public class SmsController {

    @Autowired
    private SmsRepository repository;
    static final String SMS_SEND_SUCCESS = "Status: Sent\nOK: ";
    static final String MESSAGE_BOARD_VIEW = "message_board";
    static final String SMS_SEND_SUCCESS_VIEW = "success";
    static final String PRESENTER = "presenter";
    static final Integer MAX_MESSAGES_TO_SHOW = 50;

    public SmsController() {
    }

    public SmsController(SmsRepository repository) {
        this.repository = repository;
    }

    @RequestMapping(method = RequestMethod.POST, value = {"/message/send/", "/message/send"})
    public ModelAndView sendMessage(@ModelAttribute SmsData smsData) {
        repository.save(smsData);
        ModelAndView modelAndView = new ModelAndView(SMS_SEND_SUCCESS_VIEW);
        modelAndView.addObject(PRESENTER, SMS_SEND_SUCCESS + smsData.getTo());
        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.GET, value = {"/message/show/", "/message/show"})
    public ModelAndView showLatestMessages() {
        List<SmsData> entries = repository.getLatestMessages(MAX_MESSAGES_TO_SHOW);
        ModelAndView modelAndView = new ModelAndView(MESSAGE_BOARD_VIEW);
        modelAndView.addObject(PRESENTER, entries);
        return modelAndView;
    }
}
