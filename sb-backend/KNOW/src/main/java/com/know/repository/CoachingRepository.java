package com.know.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.know.modals.Coaching;

public interface CoachingRepository extends JpaRepository<Coaching, String> {

	public List<Coaching> findByCity(String city);
	
	@Query("SELECT c FROM Coaching c where UPPER(c.name) LIKE UPPER(CONCAT('%',:name,'%'))")
	public List<Coaching> findByName(@Param("name")String name);
	
}
