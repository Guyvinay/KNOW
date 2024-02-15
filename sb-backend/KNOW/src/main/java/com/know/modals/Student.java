package com.know.modals;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Student extends Profile {

	private String name;
	private String additionalInfo;
	private String contact;
	private String city;
	
	@ElementCollection(targetClass = Subject.class)
    @CollectionTable(name = "student_subjects", joinColumns = @JoinColumn(name = "student_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "subject")
    private Set<Subject> subjects  = new HashSet<>();
	
	@ManyToMany(mappedBy = "students")
	private List<Coaching> coachings = new ArrayList<>();
	
	@ManyToMany(mappedBy = "students")
	private List<Faculty> faculties = new ArrayList<>();

	
	public Student(String email, String password, Role role, String name, String additionalInfo, String contact,
			String city, Set<Subject> subjects) {
		super(email, password, role);
		this.name = name;
		this.additionalInfo = additionalInfo;
		this.contact = contact;
		this.city = city;
		this.subjects = subjects;
	}
	
	
	
}
