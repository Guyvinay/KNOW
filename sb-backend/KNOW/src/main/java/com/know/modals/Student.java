package com.know.modals;

import jakarta.persistence.Entity;
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
	public Student(String email, String password, Role role, String name, String additionalInfo, String contact,
			String city) {
		super(email, password, role);
		this.name = name;
		this.additionalInfo = additionalInfo;
		this.contact = contact;
		this.city = city;
	}
	
	
	
	
}
