package com.know.service;

import java.util.List;
import java.util.Map;

import com.know.modals.Student;

public interface StudentService {

	public Student saveStudent(Student student);
	public List<Student> saveAllStudent(List<Student> students);
	public List<Student> getAllStudent();
	public List<Student> getAllStudentByPagination(int page, int limit ,String direction , String field);
	public Student getStudentById(String studentId);
	public List<Student> getStudentByName(String studentname);
	public Student studentAdmissionBySubjects(String studentId);
	public Student updateStudentBy(String studentId, Student student);
	public Map<String, String> deleteStudentBy(String studentId);
	
}
