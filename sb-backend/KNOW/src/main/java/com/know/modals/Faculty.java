package com.know.modals;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Faculty extends Profile {
	
	private String name;
	
	@Enumerated(EnumType.STRING)
    private Subject subject;
	
    private String additionalInfo;
    private String contact;
    private String city;
    
	public Faculty(String email, String password, Role role, String name, Subject subject, String additionalInfo,
			String contact, String city) {
		super(email, password, role);
		this.name = name;
		this.subject = subject;
		this.additionalInfo = additionalInfo;
		this.contact = contact;
		this.city = city;
	}
    
	@ManyToOne
	private Coaching coaching;
    
	@ManyToMany()
	@JoinTable(
			name = "faculty_student", 
			joinColumns = @JoinColumn(name="facultyId"),
			inverseJoinColumns = @JoinColumn(name="studentId")
			)
    private List<Student> students = new ArrayList<>();
	
}
