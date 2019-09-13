package com.registration.dao;

import com.registration.model.SearchResultDTO;

import java.util.List;

public interface UserRepository {
    List<SearchResultDTO> getUsers(List<String> columns, int pageNum, int numRecordsPerPage);
}
