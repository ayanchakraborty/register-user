package com.registration.service;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

import com.registration.dao.RegistrationDao;
import com.registration.model.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class UserRegistrationServiceTest {
    @InjectMocks
    private UserRegistrationService regsitrationService;

    @Mock
    private RegistrationDao registrationDao;

    @Before
    public void setUp() {
        User testUser = new User();
        testUser.setFirstName("testFirstName");
        testUser.setLastName("testLastName");
        testUser.setId(1L);
        when(registrationDao.findById(testUser.getId())).thenReturn(Optional.of(testUser));
        

    }

    @Test
    public void testGetUser(){
        Long userId = 1L;
        User user = regsitrationService.getUser(userId);
        assertThat(user.getId(),is(userId));
    }

    @Test
    public void testGetUsers(){

    }
}
