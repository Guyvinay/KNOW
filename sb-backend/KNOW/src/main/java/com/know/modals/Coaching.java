package com.know.modals;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Coaching {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String coachingId;
	
	@NotBlank(message = "Student name cannot be blank!!!")
	private String name;
	
	@NotBlank(message = "City cannot be blank!!!")
    private String city;
    
    private String contactNumber;
	
    private String picture;
    
    @JsonIgnore
    @OneToMany(mappedBy = "coaching")
    @ToString.Exclude
    private List<Faculty> faculties = new ArrayList<>();

    @JsonIgnore
    @ManyToMany()
    @JoinTable(
			name = "coaching_student", 
			joinColumns = @JoinColumn(name="coachingId"),
			inverseJoinColumns = @JoinColumn(name="studentId")
			)
    @ToString.Exclude
    private List<Student> students = new ArrayList<>();
    
}
