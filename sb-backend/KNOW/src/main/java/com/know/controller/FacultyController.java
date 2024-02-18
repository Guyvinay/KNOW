package com.know.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.know.modals.Faculty;
import com.know.service.FacultyService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/api/faculties")
public class FacultyController {

	@Autowired
	private FacultyService facultyService;
	
	@PostMapping()
	private ResponseEntity<?> saveFaculty(
			@Valid @RequestBody Faculty faculty, 
			@RequestParam(required = false, name = "coachingId") String coachingId,
			BindingResult bindingResult){
		
		if (bindingResult.hasErrors()) {
            // If validation errors exist, return bad request with error details
            return ResponseEntity.badRequest().body(createErrorResponse(bindingResult));
        }
		
		return new ResponseEntity<Faculty>(
				facultyService.saveFaculty(faculty, coachingId), 
				HttpStatus.ACCEPTED);
	}
	@PostMapping(value = "/saveAll")
	private ResponseEntity<?> saveAllFaculty(
			@Valid @RequestBody List<Faculty> faculties, 
			@RequestParam(required = false, name = "coachingId") String coachingId,
			BindingResult bindingResult){
		
		if (bindingResult.hasErrors()) {
            // If validation errors exist, return bad request with error details
            return ResponseEntity.badRequest().body(createErrorResponse(bindingResult));
        }
		
		return new ResponseEntity<List<Faculty>>(
				facultyService.saveAllFaculties(faculties, coachingId), 
				HttpStatus.ACCEPTED);
	}
	
	@GetMapping()
	public ResponseEntity<List<Faculty>> getAllFaculty(){
		return new ResponseEntity<List<Faculty>>(
				facultyService.getAllFaculty(), 
				HttpStatus.ACCEPTED);
	}
	
	@GetMapping(value = "/pages")
	public ResponseEntity<List<Faculty>> getAllFacultyByPagination(
			@RequestParam(required = false, defaultValue = "0", name = "page") Integer page,
			@RequestParam(required = false, defaultValue = "5", name = "limit") Integer limit,
			@RequestParam(required = false, defaultValue = "DESC", name = "direction") String direction,
			@RequestParam(required = false, defaultValue = "name", name = "field") String field
			){
		
		return new ResponseEntity<List<Faculty>>(
				facultyService.getAllFacultyByPagination(page, limit,direction, field), 
				HttpStatus.ACCEPTED);
	}
	
	@GetMapping(value = "{facultyId}")
	public ResponseEntity<Faculty> getFacultyById(@PathVariable("facultyId")String facultyId) {
		
		return new ResponseEntity<Faculty>(
				facultyService.getFacultyById(facultyId), 
				HttpStatus.ACCEPTED);
	}
	
	@GetMapping(value = "/byName/{facultyname}")
	public ResponseEntity<Faculty> getFacultyByName(@PathVariable("facultyname")String facultyname) {
		return new ResponseEntity<Faculty>(
				facultyService.getFacultyByName(facultyname), 
				HttpStatus.ACCEPTED);
	}
	
	@PutMapping(value = "/{facultyId}")
	public ResponseEntity<Faculty> updateFaculty(@PathVariable("facultyId") String facultyId, Faculty faculty) {
		return new ResponseEntity<Faculty>(
				facultyService.updateFaculty(facultyId, faculty), 
				HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping(value = "/{facultyId}")
	public ResponseEntity<String> deleteFaculty(@PathVariable("facultyId") String facultyId) {
		return new ResponseEntity<String>(
				facultyService.deleteFaculty(facultyId), 
				HttpStatus.ACCEPTED);
	}
	
	// Method to create error response from BindingResult
    private Map<String, String> createErrorResponse(BindingResult bindingResult) {
        Map<String, String> errors = new HashMap<>();
        for (FieldError error : bindingResult.getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }
        return errors;
    }
	
}
