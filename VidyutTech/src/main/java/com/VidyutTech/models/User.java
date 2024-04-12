package com.VidyutTech.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class User {

//	private int userID;
	private String name;
	@Id
	private Long number;
	private String email;
	private String password;
	
	
	
}
