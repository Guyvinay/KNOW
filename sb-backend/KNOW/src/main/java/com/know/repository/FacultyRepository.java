package com.know.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.know.modals.Faculty;

public interface FacultyRepository extends JpaRepository<Faculty, String> {

	@Query("SELECT f FROM Faculty f where UPPER(f.name) LIKE UPPER(CONCAT('%',:name,'%'))")
	public List<Faculty> findByName(@Param("name")String name);
	
}
