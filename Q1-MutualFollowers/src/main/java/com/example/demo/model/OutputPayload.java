package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.List;

@Data
@AllArgsConstructor
public class OutputPayload {
    private String regNo;
    private List<List<Integer>> outcome;
}