package com.know.modals;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public class Profile {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String profile_id;
	
	private String email;
	
	private String password;
	
	@Enumerated(EnumType.STRING)
	private Role role;

	public Profile(String email, String password, Role role) {
		super();
		this.email = email;
		this.password = password;
		this.role = role;
	}
	
	
}
