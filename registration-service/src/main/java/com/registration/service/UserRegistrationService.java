package com.registration.service;

import com.registration.dao.RegistrationDao;
import com.registration.exception.RegistrationException;
import com.registration.exception.ResourceNotFoundException;
import com.registration.model.SearchResultDTO;
import com.registration.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserRegistrationService {

    @Autowired
    RegistrationDao registrationDao;

    public User getUser(Long userId){
        return registrationDao.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id" , userId));
    }

    public List<SearchResultDTO> getUsers(List<String> columns, int pageNum, int numRecordsPerPage){
        return registrationDao.getUsers(columns, pageNum, numRecordsPerPage);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public User saveUser(User user){
        User newUser = null;
        try{
            newUser = registrationDao.save(user);
        }
        catch(DataIntegrityViolationException ex){
            throw new RegistrationException(formatErrorMessage(ex));
        }
        catch(Exception ex){
            throw new RegistrationException(formatErrorMessage(ex));
        }
        return newUser;
    }

    private String formatErrorMessage (Exception ex){
        StringBuilder sb = new StringBuilder();
        sb.append("User creation failed. ").append(ex.getMessage());
        return sb.toString();
    }
}
