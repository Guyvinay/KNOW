package com.know.modals;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Coaching {

	private String coachingId;
	
	private String name;
	
    private String city;
    
    private String contactNumber;
	
}
