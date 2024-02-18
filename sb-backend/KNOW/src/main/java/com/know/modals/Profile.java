package com.know.modals;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
	
	@NotBlank(message = "Email cannot be blank!!!")
	@Column(unique = true)
	@Email(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "email should be in proper format i.e : johndoe@example.com")
	private String email;
	
	@NotBlank(message = "Password cannot be blank!!!")
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String password;
	
	@NotNull(message = "Role cannot be blank!!!")
	@Enumerated(EnumType.STRING)
	private Role role;

	public Profile(String email, String password, Role role) {
		super();
		this.email = email;
		this.password = password;
		this.role = role;
	}
	
	
}
