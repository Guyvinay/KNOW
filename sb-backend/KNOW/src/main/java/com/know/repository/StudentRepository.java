package com.know.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.know.modals.Student;

public interface StudentRepository extends JpaRepository<Student, String> {

	@Query("SELECT s FROM Student s where UPPER(s.name) LIKE UPPER(CONCAT('%',:name,'%'))")
	public List<Student> findByName(@Param("name") String name);
	
}
