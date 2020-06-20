package com.example.demo.dtos;

import com.example.demo.entities.Player;
import com.fasterxml.jackson.annotation.JsonInclude;

public interface ProjectionPlaya {
    Integer getId();
    String getName();

    Double getSuccessrate();

}
