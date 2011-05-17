package com.tw.controller;

import com.tw.domain.SmsData;
import com.tw.repository.SmsRepository;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * User: Imran, ThoughtWorks, Date: 3 May, 2011, Time: 1:23:08 PM
 * Problem:
 */

public class SmsControllerTest {


    @Mock
    private SmsRepository repository;

    private SmsController controller;

    @Before
    public void setUp() {
        initMocks(this);
        controller = new SmsController(repository);
    }

    @Test
    public void shouldWriteSmsDataToDatabase() {
        SmsData smsData = new SmsData();
        String recipientNumber = "0123456789";
        smsData.setTo(recipientNumber);
        when(repository.save(smsData)).thenReturn(Long.valueOf(1));
        ModelAndView modelAndView = controller.sendMessage(smsData);
        assertEquals(SmsController.SMS_SEND_SUCCESS_VIEW, modelAndView.getViewName());
        assertEquals(SmsController.SMS_SEND_SUCCESS + recipientNumber, modelAndView.getModel().get(SmsController.PRESENTER));
        verify(repository).save(smsData);
    }

    @Test
    public void shouldShowTheLatestMessages() {
        ArrayList<SmsData> smsDataList = new ArrayList<SmsData>();
        when(repository.getLatestMessages(SmsController.MAX_MESSAGES_TO_SHOW)).thenReturn(smsDataList);
        ModelAndView modelAndView = controller.showLatestMessages();
        assertEquals(SmsController.MESSAGE_BOARD_VIEW, modelAndView.getViewName());
        verify(repository).getLatestMessages(SmsController.MAX_MESSAGES_TO_SHOW);
        assertTrue(EqualsBuilder.reflectionEquals(smsDataList, modelAndView.getModelMap().get(SmsController.PRESENTER)));
    }


}
