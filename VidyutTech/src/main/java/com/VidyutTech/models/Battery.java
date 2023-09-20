package com.VidyutTech.models;



import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Battery {

	@Id
	private Integer batteryID;
	private Integer current;
	private Double voltage;
	private Double temp;
	private String time;

	
}
