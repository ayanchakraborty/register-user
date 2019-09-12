package com.registration.controller;

import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.*;

import com.registration.dto.UserBO;
import com.registration.model.User;
import com.registration.service.UserRegistrationService;
import org.dozer.Mapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RunWith(MockitoJUnitRunner.Silent.class)
public class UserRegistrationControllerTest {

    private static final Long USER_ID = 12345678L;

    private MockMvc mockMvc;

    @Mock
    private UserRegistrationService regsitrationService;

    @Mock
    private Mapper mapperMock;

    @InjectMocks
    private UserRegistrationController controller;

    @Test
    public void getUserByUserId(){
        User user = new User();
        given(regsitrationService.getUser(new Long(12345))).willReturn(user);

        UserBO convertedUser = new UserBO();
        given(mapperMock.map(user, UserBO.class)).willReturn(convertedUser);

        UserBO resultUser = controller.getUser(new Long(12345));

        assertThat(resultUser, is(convertedUser));
    }

    @Test
    public void createUser(){
        UserBO userRequest = new UserBO();
        ResponseEntity responseEntity = controller.createUser(userRequest);
        assertThat("Returned response code must be Accepted", responseEntity.getStatusCode(), is(HttpStatus.CREATED));
    }

    @Test
    public void getUsersWithParameters() {
        List<Object[]> results = mockUsersList();
        List<String> columns = Arrays.asList("firstName","lastName");
        int pageNum = 0;
        int recPerPage = 5;
        when(controller.getUsers(columns, pageNum, recPerPage)).thenReturn(results);
        assertThat("Incorrect Resultsize", results.size(), is(5));
    }

    private List<Object[]> mockUsersList(){
        List<Object[]> users = new ArrayList<>();
        Object[] user1 = new Object[]{"A", "B"};
        Object[] user2 = new Object[]{"C", "D"};
        Object[] user3 = new Object[]{"E", "F"};
        Object[] user4 = new Object[]{"G", "H"};
        Object[] user5 = new Object[]{"I", "J"};
        users.add(user1);
        users.add(user2);
        users.add(user3);
        users.add(user4);
        users.add(user5);

        return users;
    }
}
