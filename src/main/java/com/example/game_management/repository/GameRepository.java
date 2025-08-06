package com.example.game_management.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.game_management.model.Game;

@Repository
public interface GameRepository extends JpaRepository<Game, String> {
	@Query("""
	        SELECT DISTINCT g FROM Game g 
	        LEFT JOIN g.nameList n 
	        WHERE (:category IS NULL OR LOWER(g.category) = LOWER(:category))
	          AND (:keyword IS NULL OR LOWER(n.value) LIKE LOWER(CONCAT('%', :keyword, '%')))
	    """)
	    Page<Game> searchByCategoryAndName(@Param("category") String category,
	                                       @Param("keyword") String keyword,
	                                       Pageable pageable);
}

