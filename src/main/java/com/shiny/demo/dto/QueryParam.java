package com.shiny.demo.dto;

import lombok.Data;

@Data
public class QueryParam {

    private Integer pageNumber;

    private Integer pageSize;

    private String searchText;

    private String sortName;

    private String sortOrder;

    private Integer startRow;

    private Integer getStartRow(){
        return (pageNumber - 1) * pageSize;
    }

}
