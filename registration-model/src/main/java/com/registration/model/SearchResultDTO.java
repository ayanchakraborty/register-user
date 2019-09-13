package com.registration.model;

import java.util.List;
import java.util.Map;

public class SearchResultDTO {
    private List<Map<String,Object>> values;

    public List<Map<String,Object>> getValues() {
        return values;
    }

    public void setValues(List<Map<String,Object>> values) {
        this.values = values;
    }
}
