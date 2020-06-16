package com.example.demo.dtos;

import com.example.demo.entities.Player;

public interface ProjectionPlayer {

    //Integer getId();
    //String getName();

    Loser getLoser();
    Double getSuccessrate();

    interface Loser{
        String getName();
    }

}
