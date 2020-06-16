package com.example.demo.dtos;

import com.example.demo.entities.Player;

public interface ProjectionPlaya {
    PlayA getP();
    Double getSuccessrate();

    interface PlayA{
        Integer getId();
    }
}
