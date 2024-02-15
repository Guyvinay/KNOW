package com.know.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.know.modals.Coaching;

public interface CoachingRepository extends JpaRepository<Coaching, String> {

}
