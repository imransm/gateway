package com.tw.controller;

import com.tw.domain.IvrData;
import com.tw.repository.IvrRepository;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * User: imransm, Date: 4 May, 2011, Time: 5:34:28 PM
 * Problem:
 */

public class IvrControllerTest {

    private IvrController controller;
    @Mock
    private IvrRepository repository;

    @Before
    public void setUp() {
        initMocks(this);
        controller = new IvrController(repository);
    }
    @Test
    public void shouldSendIvrMessage() {
        String xmlString = "test xml string";
        IvrData ivrData = new IvrData(xmlString);
        when(repository.save(ivrData)).thenReturn(1);
        ModelAndView view = controller.sendMessage(xmlString);
        assertEquals(IvrController.SEND_IVR_SUCCESS_VIEW, view.getViewName());
        verify(repository).save(any(IvrData.class));
    }


     @Test
    public void shouldShowTheLatestMessages() {
        ArrayList<IvrData> smsDataList = new ArrayList<IvrData>();
        when(repository.getLatestMessages(IvrController.MAX_MESSAGES_TO_SHOW)).thenReturn(smsDataList);
        ModelAndView modelAndView = controller.showLatestMessages();
        assertEquals(IvrController.IVR_MESSAGE_BOARD_VIEW, modelAndView.getViewName());
        verify(repository).getLatestMessages(SmsController.MAX_MESSAGES_TO_SHOW);
        assertTrue(EqualsBuilder.reflectionEquals(smsDataList, modelAndView.getModelMap().get(IvrController.PRESENTER)));
    }
}
