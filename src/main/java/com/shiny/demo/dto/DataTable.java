package com.shiny.demo.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class DataTable<T> {

    private Integer total;

    private List<T> rows = new ArrayList<>();

}
