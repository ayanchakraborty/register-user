package com.registration.dao;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import com.registration.dao.config.UserConfig;
import com.registration.model.Address;
import com.registration.model.SearchResultDTO;
import com.registration.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@Transactional
@DirtiesContext
@SpringBootTest(classes = UserConfig.class)
@SqlGroup({
        @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts ="classpath:user_schema.sql"),
        @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts ="classpath:user_data.sql")
})
public class RegistrationDaoTest {

    @Autowired
    private RegistrationDao registrationDao;

    @Test
    public void givenUser_whenSave_thenGetOk() {
        User user = createUser("testFirstName", "testLastName", "testCountry", "testZipcode");
        User savedUser = registrationDao.save(user);

        User retirevedUser = registrationDao.findById(savedUser.getId()).get();
        assertEquals("name is incorrect", user.getFirstName(), retirevedUser.getFirstName());
    }

    @Test
    public void getUser_basedOn_Id(){
        User user = registrationDao.findById(1L).get();
        assertEquals("name is incorrect", "ABCfirst", user.getFirstName());
    }

    @Test
    public void getUsers_basedOn_Params(){
        String[] columns = {"firstName", "lastName"};
        int pageNum = 0;
        int recPerPage = 5;
        List<SearchResultDTO> users = registrationDao.getUsers(Arrays.asList(columns), pageNum, recPerPage);
        assertThat("size is incorrect", users.size(), lessThanOrEqualTo(recPerPage));
        assertThat("data is not correctly returned", (users.get(0).getValues().keySet().toArray()[0]).toString(), is(columns[0]));
        assertThat("data is not correctly returned", (users.get(4).getValues().values().toArray()[0]).toString(), is("MNOfirst"));
    }

    private User createUser(String firstName, String lastName, String country, String zip){
        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);

        Address address = new Address();
        address.setCountry(country);
        address.setZipcode(zip);

        user.setAddress(address);
        return user;
    }
}
