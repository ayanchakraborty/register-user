package com.registration.dao;

import com.registration.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistrationDao extends JpaRepository <User, Long>, UserRepository{

}
