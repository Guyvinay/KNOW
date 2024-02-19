package com.know.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.know.modals.Student;
import com.know.service.StudentService;


public class StudentSer implements StudentService {

	@Override
	public Student saveStudent(Student student) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Student> saveAllStudent(List<Student> students) {
		// TODO Auto-generated method stub
		return null;
					}

	@Override
	public List<Student> getAllStudent() {
		// TODO Auto-generated method stub
		System.out.println("From student ser2");

		return new ArrayList<>();
	}

	@Override
	public List<Student> getAllStudentByPagination(int page, int limit, String direction, String field) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Student getStudentById(String studentId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Student> getStudentByName(String studentname) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Student studentAdmissionBySubjects(String studentId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Student updateStudentBy(String studentId, Student student) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, String> deleteStudentBy(String studentId) {
		// TODO Auto-generated method stub
		return null;
	}

}
