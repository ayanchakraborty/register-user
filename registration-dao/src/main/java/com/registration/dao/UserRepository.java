package com.registration.dao;

import java.util.List;

public interface UserRepository {
    List<Object[]> getUsers(List<String> columns, int pageNum, int numRecordsPerPage);
}
