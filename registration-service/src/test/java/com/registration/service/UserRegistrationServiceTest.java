package com.registration.service;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.lessThanOrEqualTo;
import static org.junit.Assert.assertThat;

import com.registration.dao.RegistrationDao;
import com.registration.model.SearchResultDTO;
import com.registration.model.User;
import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserRegistrationServiceTest {
    private UserRegistrationService regsitrationService;
    private User user;

    @org.junit.Rule
    public final JUnitRuleMockery mockery = new JUnitRuleMockery(){
        {
            setImposteriser(ClassImposteriser.INSTANCE);
        }
    };

    @Before
    public void setUp() {
        regsitrationService = new UserRegistrationService();
        regsitrationService.registrationDao = mockery.mock(RegistrationDao.class);
    }

    @Test
    public void testGetUser(){
        User testUser = new User();
        testUser.setFirstName("testFirstName");
        testUser.setLastName("testLastName");
        testUser.setId(1L);
        mockery.checking(new Expectations()
        {
            {
                oneOf(regsitrationService.registrationDao).findById(1L);
                will(returnValue(Optional.of(testUser)));
            }
        });
        User user = regsitrationService.getUser(1L);
        assertThat("Returned same user ", user.getId(),is(testUser.getId()));
    }

    @Test
    public void testGetUsers(){
        SearchResultDTO data1 = new SearchResultDTO();
        SearchResultDTO data2 = new SearchResultDTO();

        List<SearchResultDTO> resultDTOs = new ArrayList<>();
        resultDTOs.add(data1);
        resultDTOs.add(data2);

        List<String> columns = new ArrayList<>();
        columns.add("firstName");
        columns.add("lastName");

        mockery.checking(new Expectations()
        {
            {
                oneOf(regsitrationService.registrationDao).getUsers(columns,0,5);
                will(returnValue(resultDTOs));
            }
        });
        regsitrationService.getUsers(columns,0,5);
        assertThat("Returned same result Size ", resultDTOs, hasSize(lessThanOrEqualTo(5)));
    }

    @Test
    public void testSaveUser(){
        User newUser = new User();
        newUser.setFirstName("testFirstName");
        newUser.setLastName("testLastName");

        mockery.checking(new Expectations()
        {
            {
                oneOf(regsitrationService.registrationDao).save(user);
                will(returnValue(newUser));
            }
        });
        regsitrationService.saveUser(user);
    }
}
