package com.know.modals;

import jakarta.persistence.Entity;
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
    
	
    
    
	
}
