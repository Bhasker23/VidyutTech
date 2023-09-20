package com.VidyutTech.models;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class BatteryToServer {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer serverId;
	private Integer batteryID;
	private Integer current;
	private Double voltage;
	private Double temp;
	private String time;
}
