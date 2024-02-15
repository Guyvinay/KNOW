package com.know.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.know.modals.Faculty;

public interface FacultyRepository extends JpaRepository<Faculty, String> {

}
