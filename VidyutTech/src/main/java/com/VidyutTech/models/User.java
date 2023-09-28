package com.VidyutTech.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class User {
	
	@Id
	private int userID;
	private String name;
	private String password;
	
	
	
}
