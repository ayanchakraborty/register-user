package com.registration.controller;

import com.registration.dto.UserBO;
import com.registration.model.SearchResultDTO;
import com.registration.model.User;
import com.registration.service.UserRegistrationService;
import org.dozer.Mapper;
import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class UserRegistrationControllerTest {

    private static final Long USER_ID = 12345678L;
    private User user;

    @org.junit.Rule
    public final JUnitRuleMockery mockery = new JUnitRuleMockery(){
        {
            setImposteriser(ClassImposteriser.INSTANCE);
        }
    };

    private UserRegistrationController controller;

    @Before
    public void setUp() {
        controller = new UserRegistrationController();
        controller.dozerBeanMapper = mockery.mock(Mapper.class);
        controller.regsitrationService = mockery.mock(UserRegistrationService.class);
    }

    @Test
    public void getUserByUserId(){
        User user = new User();
        UserBO convertedUser = new UserBO();
        mockery.checking(new Expectations()
        {
            {
                oneOf(controller.regsitrationService).getUser(1L);
                will(returnValue(user));
                oneOf(controller.dozerBeanMapper).map(user, UserBO.class);
                will(returnValue(convertedUser));
            }
        });
        controller.getUser(1L);
    }

    @Test
    public void createUser(){
        UserBO receivedUser = new UserBO();
        User convertedUser = new User();
        mockery.checking(new Expectations(){
            {
                oneOf(controller.dozerBeanMapper).map(receivedUser, User.class);
                will(returnValue(convertedUser));
                oneOf(controller.regsitrationService).saveUser(convertedUser);
                will(returnValue(user));
            }
        });
        controller.createUser(receivedUser);
    }

    @Test
    public void getUsersWithParameters() {
        List<SearchResultDTO> resultDTOs = new ArrayList<>();
        List<String> columns = new ArrayList<>();

        mockery.checking(new Expectations()
        {
            {
                oneOf(controller.regsitrationService).getUsers(columns,0,5);
                will(returnValue(resultDTOs));
            }
        });
        controller.getUsers(columns,0,5);
    }
}
