package com.know.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;

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
	
	public void studentInit() {
		requestStudent = new Student("vinay","info", "7479856872","Patna","profil-pic");
		responseStudent = new Student("vinay","info", "7479856872","Patna","profil-pic");
		
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
		
		Student createdStudent = new ObjectMapper().readValue(responseBodyAsString, Student.class);
		
		// Assert
		assertEquals(responseStudent.getName(), createdStudent.getName(),"returned created Employee Name is incorrect");
		assertNotNull(createdStudent.getProfile_id(), "created Customer id should not be empty");
	}
	
	
}
