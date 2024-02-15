package com.know.modals;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Coaching {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String coachingId;
	
	private String name;
	
    private String city;
    
    private String contactNumber;
	
    @OneToMany(mappedBy = "coaching")
    private List<Faculty> faculties = new ArrayList<>();

    @ManyToMany()
    @JoinTable(
			name = "coaching_student", 
			joinColumns = @JoinColumn(name="coachingId"),
			inverseJoinColumns = @JoinColumn(name="studentId")
			)
    private List<Student> students = new ArrayList<>();
    
}
