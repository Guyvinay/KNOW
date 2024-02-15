package com.know.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.know.modals.Student;

public interface StudentRepositiry extends JpaRepository<Student, String> {

}
