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

import com.know.modals.Coaching;
import com.know.service.CoachingService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/api/coachings")
public class CoachingController {

	@Autowired
	private CoachingService coachingService;
	
	@PostMapping()
	private ResponseEntity<?> saveCoaching(@Valid @RequestBody Coaching coaching, BindingResult bindingResult){
		
		if (bindingResult.hasErrors()) {
            // If validation errors exist, return bad request with error details
            return ResponseEntity.badRequest().body(createErrorResponse(bindingResult));
        }
		
		return new ResponseEntity<Coaching>(
				coachingService.saveCoaching(coaching), 
				HttpStatus.ACCEPTED);
	}
	@PostMapping(value = "/saveAll")
	private ResponseEntity<?> saveAllCoaching(@Valid @RequestBody List<Coaching> coachings, BindingResult bindingResult){
		
		if (bindingResult.hasErrors()) {
            // If validation errors exist, return bad request with error details
            return ResponseEntity.badRequest().body(createErrorResponse(bindingResult));
        }
		
		return new ResponseEntity<List<Coaching>>(
				coachingService.saveAllCoachings(coachings), 
				HttpStatus.ACCEPTED);
	}
	
	@GetMapping()
	public ResponseEntity<List<Coaching>> getAllCoaching(){
		return new ResponseEntity<List<Coaching>>(
				coachingService.getAllCoaching(), 
				HttpStatus.ACCEPTED);
	}
	
	@GetMapping(value = "/pages")
	public ResponseEntity<List<Coaching>> getAllCoachingByPagination(
			@RequestParam(required = false, defaultValue = "0", name = "page") Integer page,
			@RequestParam(required = false, defaultValue = "10", name = "limit") Integer limit,
			@RequestParam(required = false, defaultValue = "DESC", name = "direction") String direction,
			@RequestParam(required = false, defaultValue = "name", name = "field") String field
			){
		
		return new ResponseEntity<List<Coaching>>(
				coachingService.getAllCoachingByPagination(page, limit,direction, field), 
				HttpStatus.ACCEPTED);
	}
	
	@GetMapping(value = "{coachingId}")
	public ResponseEntity<Coaching> getCoachingById(@PathVariable("coachingId")String coachingId) {
		
		return new ResponseEntity<Coaching>(
				coachingService.getCoachingById(coachingId), 
				HttpStatus.ACCEPTED);
	}
	
	@GetMapping(value = "/byName/{coachingname}")
	public ResponseEntity<Coaching> getCoachingByName(@PathVariable("coachingname")String coachingname) {
		return new ResponseEntity<Coaching>(
				coachingService.getCoachingByName(coachingname), 
				HttpStatus.ACCEPTED);
	}
	
	@PutMapping(value = "/{coachingId}")
	public ResponseEntity<Coaching> updateCoachingBy(@PathVariable("coachingId") String coachingId, Coaching coaching) {
		return new ResponseEntity<Coaching>(
				coachingService.updateCoachingBy(coachingId, coaching), 
				HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping(value = "/{coachingId}")
	public ResponseEntity<String> deleteCoachingBy(@PathVariable("coachingId") String coachingId) {
		return new ResponseEntity<String>(
				coachingService.deleteCoachingBy(coachingId), 
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
