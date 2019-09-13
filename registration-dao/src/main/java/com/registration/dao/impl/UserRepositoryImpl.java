package com.registration.dao.impl;

import com.registration.dao.UserRepository;
import com.registration.model.SearchResultDTO;
import org.springframework.util.CollectionUtils;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Tuple;
import java.util.*;
import java.util.stream.IntStream;

public class UserRepositoryImpl implements UserRepository {

    private static final int DEFAULT_FIRST_RESULT_INDEX = 0;
    private static final int DEFAULT_MAX_REC_PER_PAGE = 10;

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<SearchResultDTO> getUsers(List<String> columnList, int pageNum, int numRecordsPerPage){
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

       List<Tuple> resultSet = entityManager.createQuery(query, Tuple.class)
                                    .setFirstResult(firstResult)
                                    .setMaxResults(maxResult)
                                    .getResultList();
       List<SearchResultDTO> resultDTOs = new ArrayList<>();

       resultSet.stream().forEach(result -> {
                    SearchResultDTO resultDTO = new SearchResultDTO();
                    resultDTO.setValues(new ArrayList<Map<String,Object>>());
                    IntStream.range(0,columnList.size())
                        .forEach(index -> {
                            Map<String,Object> data = new HashMap();
                            data.put(columnList.get(index), result.get(index));
                            resultDTO.getValues().add(data);
                        });
                    resultDTOs.add(resultDTO);
                });

       return resultDTOs;
    }
}
