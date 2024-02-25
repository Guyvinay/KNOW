package com.know.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.know.modals.Role;
import com.know.modals.Student;
import com.know.service.StudentService;

@WebMvcTest(controllers = StudentController.class)
public class StudentTestController {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private StudentService studentService;
	
	private Student requestStudent;
	private Student responseStudent;
	/*
	{
	    "email": "john.doe.student@example.com",
	    "password": "student_password1",
	    "role": "STUDENT",
	    "name": "John Doe",
	    "additionalInfo": "Additional info for John Doe",
	    "contact": "123-456-7890",
	    "city": "New York",
	    "profilePicture":"https://avatars.githubusercontent.com/u/44674940?s=100&v=4",
	    "subjects": ["MATHS", "SCIENCE"]
	  }
	*/
	@BeforeEach
	public void studentInit() {
		requestStudent = new Student();
		requestStudent.setEmail("john.doe.student@example.com");
		requestStudent.setPassword("student_password1");
		requestStudent.setRole(Role.STUDENT);
		requestStudent.setName("John Doe");
		requestStudent.setAdditionalInfo("Additional info for John Doe");
		requestStudent.setContact("123-456-7890");
		requestStudent.setCity("New York");
		requestStudent.setProfilePicture("https://avatars.githubusercontent.com/u/44674940?s=100&v=4");
				
		
		responseStudent = new Student();
		responseStudent.setProfile_id("2345678yujg3456");
				responseStudent.setEmail("john.doe.student@example.com");
				responseStudent.setPassword("student_password1");
				responseStudent.setRole(Role.STUDENT);
				responseStudent.setName("John Done");
				responseStudent.setAdditionalInfo("Additional info for John Doe");
				responseStudent.setContact("123-456-7890");
				responseStudent.setCity("New York");
				responseStudent.setProfilePicture("https://avatars.githubusercontent.com/u/44674940?s=100&v=4");
		
	}
	
	@Test
	@DisplayName("Student Created Test")
	public void testsaveStudentHandler_whenValidDetailsProvided_returnSavedStudent() throws Exception {
		
		//ACT
		Mockito.when(studentService.saveStudent(any(Student.class))).thenReturn(responseStudent);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/students")
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(new ObjectMapper().writeValueAsString(requestStudent));
		// Act
		MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
		String responseBodyAsString = mvcResult.getResponse().getContentAsString();
//		System.out.println(responseBodyAsString);
		
		Student createdStudent = new ObjectMapper().readValue(responseBodyAsString, Student.class);
		
		// Assert
		assertEquals(responseStudent.getName(), createdStudent.getName(),"returned created Employee Name is incorrect");
		assertNotNull(createdStudent.getProfile_id(), "created Customer id should not be empty");
	}
	
	
}
