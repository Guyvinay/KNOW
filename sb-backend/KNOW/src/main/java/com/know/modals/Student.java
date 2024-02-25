package com.know.modals;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "students")
@PrimaryKeyJoinColumn(name = "student_id")
public class Student extends Profile {

	@NotBlank(message = "Student name cannot be blank!!!")
	private String name;
	
	private String additionalInfo;
	
	@NotBlank(message = "Contact no. cannot be blank!!!")
	private String contact;
	
	@NotBlank(message = "City cannot be blank!!!")
	private String city;
	
	private String profilePicture;
	
	@ElementCollection(targetClass = Subject.class)
    @CollectionTable(name = "student_subjects", joinColumns = @JoinColumn(name = "student_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "subject")
    private Set<Subject> subjects  = new HashSet<>();
	
//	@JsonIgnore
	@ManyToMany(mappedBy = "students")
	private List<Coaching> coachings = new ArrayList<>();
	
//	@JsonIgnore
	@ManyToMany(mappedBy = "students")
	private List<Faculty> faculties = new ArrayList<>();

	
	public Student(String email, String password, Role role, String name, String additionalInfo, String contact,
			String city, String profilePicture, Set<Subject> subjects) {
		super(email, password, role);
		this.name = name;
		this.additionalInfo = additionalInfo;
		this.contact = contact;
		this.city = city;
		this.profilePicture = profilePicture;
		this.subjects = subjects;
	}
	
	public Student(String email, String password, Role role, String name, String additionalInfo, String contact,
			String city, String profilePicture) {
		super(email, password, role);
		this.name = name;
		this.additionalInfo = additionalInfo;
		this.contact = contact;
		this.city = city;
		this.profilePicture = profilePicture;
	}
}
