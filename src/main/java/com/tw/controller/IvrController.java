package com.tw.controller;

import com.tw.domain.IvrData;
import com.tw.repository.IvrRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * User: imransm, Date: 4 May, 2011, Time: 5:34:20 PM
 * Problem:
 */
@Controller
@Scope("prototype")
public class IvrController {
    public static final String SEND_IVR_SUCCESS = "ivr_success";

    @Autowired
    private IvrRepository repository;
    
    static final Integer MAX_MESSAGES_TO_SHOW = 50;
    static final String PRESENTER = "presenter";
    static final String IVR_MESSAGE_BOARD_VIEW = "ivr_message_board";

    public IvrController() {

    }

    public IvrController(IvrRepository repository) {
        this.repository = repository;
    }

    @RequestMapping(method = RequestMethod.POST, value = {"/ivr/send/", "/ivr/send"})
    public ModelAndView sendMessage(@RequestParam String xmlData) {
        repository.save(new IvrData(xmlData));
        return new ModelAndView(SEND_IVR_SUCCESS).addObject(PRESENTER, "<?xml version=\"1.0\"?>Success");
    }

    @RequestMapping(method = RequestMethod.GET, value = {"/ivr/show/", "/ivr/show"})
    public ModelAndView showLatestMessages() {
        List<IvrData> entries = repository.getLatestMessages(MAX_MESSAGES_TO_SHOW);
        ModelAndView modelAndView = new ModelAndView(IVR_MESSAGE_BOARD_VIEW);
        modelAndView.addObject(PRESENTER, entries);
        return modelAndView;
    }
}
