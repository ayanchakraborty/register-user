package com.registration.dao.impl;

import com.registration.dao.UserRepository;
import com.registration.model.User;
import org.springframework.util.CollectionUtils;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class UserRepositoryImpl implements UserRepository {

    private static final int DEFAULT_FIRST_RESULT_INDEX = 0;
    private static final int DEFAULT_MAX_REC_PER_PAGE = 10;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Object[]> getUsers(List<String> columnList, int pageNum, int numRecordsPerPage){
        int firstResult = DEFAULT_FIRST_RESULT_INDEX;
        int maxResult = DEFAULT_MAX_REC_PER_PAGE;
        if(pageNum < 0){
            pageNum = DEFAULT_FIRST_RESULT_INDEX;
        }
        if(numRecordsPerPage < 0){
            numRecordsPerPage = DEFAULT_MAX_REC_PER_PAGE;
        }
        if(numRecordsPerPage > 0){
            firstResult = pageNum * numRecordsPerPage;
            maxResult = numRecordsPerPage;
        }
        StringBuilder queryBuilder = new StringBuilder();
        String columns = "u";
        if(!CollectionUtils.isEmpty(columnList)){
            columns = String.join(",", columnList);
        }
        String query = queryBuilder.append("select ")
                                    .append(columns)
                                    .append(" from User u")
                                    .toString();

        return entityManager.createQuery(query)
                .setFirstResult(firstResult)
                .setMaxResults(maxResult)
                .getResultList();
    }
}
