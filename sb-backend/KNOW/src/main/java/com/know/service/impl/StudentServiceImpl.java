package com.know.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.know.exception.CoachingNotFoundException;
import com.know.exception.StudentNotFoundException;
import com.know.modals.Coaching;
import com.know.modals.Faculty;
import com.know.modals.Student;
import com.know.modals.Subject;
import com.know.repository.CoachingRepository;
import com.know.repository.StudentRepository;
import com.know.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentRepository studentRepositiry;
	
	@Autowired
	private CoachingRepository coachingRepository;
	
	@Override
	public Student saveStudent(Student student) {
		return studentRepositiry.save(student);
	}
	
	@Override
	public List<Student> saveAllStudent(List<Student> students) {
				return studentRepositiry.saveAll(students);
	}

	@Override
	public List<Student> getAllStudent() {
		System.out.println("From student ser1");

		List<Student> students = studentRepositiry.findAll();
		if(students.isEmpty())
			throw new StudentNotFoundException("No Students Found!!!");
		return students;
	}

	@Override
	public List<Student> getAllStudentByPagination(int page, int limit,String direction, String field) {
		Pageable pageRequest = PageRequest.of(page, limit, Sort.by(direction.equals("ASC")?Sort.Direction.ASC:Sort.Direction.DESC, field));
		
		Page<Student> pages = studentRepositiry.findAll(pageRequest);
		List<Student> students = pages.getContent();
		
		if(students.isEmpty())
			throw new StudentNotFoundException("No Students Found!!!");
		
		return students;
	}

	@Override
	public Student getStudentById(String studentId) {
		Student student = studentRepositiry.findById(studentId).orElseThrow(()->new StudentNotFoundException("Student with id: "+studentId+", not found!!!"));
		return student;
	}

	@Override
	public List<Student> getStudentByName(String studentname) {
		List<Student> students = studentRepositiry.findByName(studentname);
		if(students.isEmpty())
			throw new StudentNotFoundException("No Student Found with name:"+studentname);
		return students;
	}

	@Override
	public Student studentAdmissionBySubjects(String studentId) {
		
		Student retrievedStudent = studentRepositiry.findById(studentId).orElseThrow(()->new StudentNotFoundException("Student with id: "+studentId+", not found!!!"));
		
		
		List<Coaching> city = coachingRepository.findByCity(retrievedStudent.getCity());
		
		if(city.isEmpty())
			throw new CoachingNotFoundException("Coaching not found in your city: "+retrievedStudent.getCity());
		
		Coaching coaching = city.get(new Random().nextInt(city.size()));
		
		retrievedStudent.getCoachings().add(coaching);
		
		List<Faculty> faculties = coaching.getFaculties();
		
		for(Subject subject : retrievedStudent.getSubjects()) {
			for(Faculty faculty: faculties) {
				if(faculty.getSubject().equals(subject)) {
					retrievedStudent.getFaculties().add(faculty);
					faculty.getStudents().add(retrievedStudent);
				}
			}
		}
		return retrievedStudent;
	}
	
	
	@Override
	public Student updateStudentBy(String studentId, Student student) {
		
		Student retrievedStudent = studentRepositiry.findById(studentId).orElseThrow(()->new StudentNotFoundException("Student with id: "+studentId+", not found!!!"));
		
		if(student.getAdditionalInfo()!=null) 
			retrievedStudent.setAdditionalInfo(student.getAdditionalInfo());
		
		if(student.getCity()!=null)
			retrievedStudent.setCity(student.getCity());
		
		if(student.getContact()!=null) 
			retrievedStudent.setContact(student.getContact());
		
		if(student.getName()!=null)
			retrievedStudent.setName(student.getName());
		
		if(student.getProfilePicture()!=null)
			retrievedStudent.setProfilePicture(student.getProfilePicture());
		
		if(student.getSubjects().size()!=retrievedStudent.getSubjects().size())
			retrievedStudent.setSubjects(student.getSubjects());
		
		return studentRepositiry.save(retrievedStudent);
	}

	@Override
	public Map<String, String> deleteStudentBy(String studentId) {
		Map<String, String> map = new HashMap<>();
		Student retrievedStudent = studentRepositiry.findById(studentId).orElseThrow(()->new StudentNotFoundException("Student with id: "+studentId+", not found!!!"));
		studentRepositiry.delete(retrievedStudent);
		 map.put("message", "Student : "+retrievedStudent.getName()+", deleted");
		 return map;
	}

	

}
