package com.know.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.know.exception.CoachingNotFoundException;
import com.know.exception.FacultyNotFoundException;
import com.know.exception.StudentNotFoundException;
import com.know.modals.Coaching;
import com.know.modals.Faculty;
import com.know.modals.Student;
import com.know.repository.CoachingRepository;
import com.know.repository.FacultyRepository;
import com.know.service.FacultyService;

@Service
public class FacultyServiceImpl implements FacultyService {

	@Autowired
	private FacultyRepository facultyRepository;
	
	@Autowired
	private CoachingRepository coachingRepository;
	
	@Override
	public Faculty saveFaculty(Faculty faculty, String coachingId) {
		if(coachingId!=null) {
			Coaching coaching = coachingRepository.findById(coachingId).orElseThrow(
					()-> new CoachingNotFoundException("Coaching with id: "+coachingId+", not found!!!"));
			
			List<Faculty> faculties = coaching.getFaculties();
			
			boolean anyMatch = faculties.stream().anyMatch((f)->f.getSubject().equals(faculty.getSubject()));
			
			if(anyMatch)
				throw new FacultyNotFoundException("Faculty for Subject:"+faculty.getSubject()+", already available!!!");
			
			coaching.getFaculties().add(faculty);
			faculty.setCoaching(coaching);
		}
		
		return facultyRepository.save(faculty);
	}
	
	@Override
	public List<Faculty> saveAllFaculties(List<Faculty> faculties, String coachingId) {
		
		if(coachingId!=null) {
			Coaching coaching = coachingRepository.findById(coachingId).orElseThrow(
					()-> new CoachingNotFoundException("Coaching with id: "+coachingId+", not found!!!"));
			
			for(Faculty faculty: faculties) {
				
				boolean anyMatch = faculties.stream().anyMatch((f)->f.getSubject().equals(faculty.getSubject()));
				
				if(anyMatch)
					throw new FacultyNotFoundException("Faculty for Subject:"+faculty.getSubject()+", already available!!!");
				
				coaching.getFaculties().add(faculty);
				faculty.setCoaching(coaching);
			}
		}
		
		return facultyRepository.saveAll(faculties);
	}
	@Override
	public List<Faculty> getAllFaculty() {
		List<Faculty> allFaculties = facultyRepository.findAll();
		if(allFaculties.isEmpty())
			throw new FacultyNotFoundException("No Faculty Found!!!");
		return allFaculties;
	}

	@Override
	public List<Faculty> getAllFacultyByPagination(int page, int limit,String direction, String field) {
			Pageable pageRequest = PageRequest.of(
					page, limit, 
					Sort.by(direction.equals("ASC")?Sort.Direction.ASC:Sort.Direction.DESC, 
							field)
					);
		
		Page<Faculty> pages = facultyRepository.findAll(pageRequest);
		List<Faculty> faculties = pages.getContent();
		
		if(faculties.isEmpty())
			throw new StudentNotFoundException("No Faculty Found!!!");
		
		return faculties;
	}

	@Override
	public Faculty getFacultyById(String facultyId) {
		Faculty faculty = facultyRepository.findById(facultyId).orElseThrow(()-> new FacultyNotFoundException("Faculty with id: "+facultyId+", not found!!!"));
		return faculty;
	}

	@Override
	public Faculty getFacultyByName(String facultyname) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Faculty updateFaculty(String facultyId, Faculty faculty) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deleteFaculty(String facultyId) {
		Faculty faculty = facultyRepository.findById(facultyId).orElseThrow(()-> new FacultyNotFoundException("Faculty with id: "+facultyId+", not found!!!"));
		facultyRepository.delete(faculty);
		return "Faculty with id: "+facultyId+", deleted";
	}

	

		
	
}
