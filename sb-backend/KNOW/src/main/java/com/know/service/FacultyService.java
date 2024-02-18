package com.know.service;

import java.util.List;

import com.know.modals.Faculty;

public interface FacultyService {

	public Faculty saveFaculty(Faculty faculty, String coachingId);
	public List<Faculty> saveAllFaculties(List<Faculty> faculties, String coachingId);
	public List<Faculty> getAllFaculty();
	public List<Faculty> getAllFacultyByPagination(int page, int limit,String direction, String field);
	public Faculty getFacultyById(String facultyId);
	public Faculty getFacultyByName(String facultyname);
	public Faculty updateFaculty(String facultyId, Faculty faculty);
	public String deleteFaculty(String facultyId);
	
}
