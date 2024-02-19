package com.know.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.PatchExchange;

import com.know.modals.Student;
import com.know.service.StudentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/api/students")
@CrossOrigin(value = "*")
public class StudentController {

	@Autowired
	private StudentService studentService;
	
	@PostMapping()
	private ResponseEntity<?> saveStudent( @RequestBody Student student, BindingResult bindingResult){
		
		if (bindingResult.hasErrors()) {
            // If validation errors exist, return bad request with error details
            return ResponseEntity.badRequest().body(createErrorResponse(bindingResult));
        }
		
		return new ResponseEntity<Student>(
				studentService.saveStudent(student), 
				HttpStatus.ACCEPTED);
	}
	@PostMapping(value = "/saveAll")
	private ResponseEntity<?> saveAllStudent(@Valid @RequestBody List<Student> students, BindingResult bindingResult){
		
		if (bindingResult.hasErrors()) {
            // If validation errors exist, return bad request with error details
            return ResponseEntity.badRequest().body(createErrorResponse(bindingResult));
        }
		
		return new ResponseEntity<List<Student>>(
				studentService.saveAllStudent(students), 
				HttpStatus.ACCEPTED);
	}
	
	@GetMapping()
	public ResponseEntity<List<Student>> getAllStudent(){
		return new ResponseEntity<List<Student>>(
				studentService.getAllStudent(), 
				HttpStatus.ACCEPTED);
	}
	
	@GetMapping(value = "/pages")
	public ResponseEntity<List<Student>> getAllStudentByPagination(
			@RequestParam(required = false, defaultValue = "0", name = "page") Integer page,
			@RequestParam(required = false, defaultValue = "5", name = "limit") Integer limit,
			@RequestParam(required = false, defaultValue = "DESC", name = "direction") String direction,
			@RequestParam(required = false, defaultValue = "name", name = "field") String field
			){
		
		return new ResponseEntity<List<Student>>(
				studentService.getAllStudentByPagination(page, limit,direction, field), 
				HttpStatus.ACCEPTED);
	}
	
	@GetMapping(value = "{studentId}")
	public ResponseEntity<Student> getStudentById(@PathVariable("studentId")String studentId) {
		
		return new ResponseEntity<Student>(
				studentService.getStudentById(studentId), 
				HttpStatus.ACCEPTED);
	}
	
	@GetMapping(value = "/byName/{studentname}")
	public ResponseEntity<List<Student>> getStudentByName(@PathVariable("studentname")String studentname) {
		return new ResponseEntity<List<Student>>(
				studentService.getStudentByName(studentname), 
				HttpStatus.ACCEPTED);
	}
	
	@PutMapping(value = "/{studentId}")
	public ResponseEntity<Student> updateStudentBy(
			@PathVariable("studentId") String studentId,@RequestBody Student student) {
		return new ResponseEntity<Student>(
				studentService.updateStudentBy(studentId, student), 
				HttpStatus.ACCEPTED);
	}
	@PatchExchange(value = "/{studentId}")
	public ResponseEntity<Student> studentAdmissionBySubjects(
			@PathVariable("studentId") String studentId) {
		return new ResponseEntity<Student>(
				studentService.studentAdmissionBySubjects(studentId), 
				HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping(value = "/{studentId}")
	public ResponseEntity<?> deleteStudentBy(@PathVariable("studentId") String studentId) {
		return new ResponseEntity<Map<String, String>>(
				studentService.deleteStudentBy(studentId), 
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
