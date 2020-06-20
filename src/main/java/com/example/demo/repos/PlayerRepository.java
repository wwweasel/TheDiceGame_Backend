package com.example.demo.repos;

import java.util.List;

import com.example.demo.dtos.ProjectionPlaya;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.Player;


@Repository
public interface PlayerRepository extends JpaRepository<Player, Integer>{

//    @Query("SELECT p AS loser, ( SELECT COUNT(g.success) * 100.0 / " +
//                                                        "( SELECT COUNT(g.success) FROM Game g WHERE g.player=p.id ) " +
//                        "FROM Game g WHERE g.player=p.id AND g.success=true ) AS successrate " +
//            "FROM Player p " +
//            "ORDER BY successrate DESC")
//    List<ProjectionPlayer> findTestLoser();

    @Query(value = "SELECT p.id, p.name, (SELECT COUNT(g.success) * 100.0 / " +
                                                                    "( SELECT COUNT(g.success) " +
                                                                    "FROM game g " +
                                                                    "WHERE g.player_id = p.id ) " +
                    "FROM game g " +
                    "WHERE g.player_id = p.id and g.success = true) AS successrate " +
                    "FROM player p " +
                    "HAVING successrate IS NOT NULL " +
                    "ORDER BY successrate " +
                    "LIMIT :limit", nativeQuery=true)
    List<ProjectionPlaya> findLoser( int limit );

    @Query(value = "SELECT p.id, p.name, (SELECT COUNT(g.success) * 100.0 / " +
            "( SELECT COUNT(g.success) " +
            "FROM game g " +
            "WHERE g.player_id = p.id ) " +
            "FROM game g " +
            "WHERE g.player_id = p.id and g.success = true) AS successrate " +
            "FROM player p " +
            "ORDER BY successrate DESC " +
            "LIMIT :limit", nativeQuery=true)
    List<ProjectionPlaya> findWinner( int limit );
}
