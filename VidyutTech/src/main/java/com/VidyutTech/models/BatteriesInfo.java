package com.VidyutTech.models;

import java.util.ArrayList;

import java.util.List;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;


@Entity
public class BatteriesInfo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	@OneToMany(cascade = CascadeType.ALL)
	private List<Battery> batteriesSet;
	
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public List<Battery> getBatteriesSet() {
		if (batteriesSet == null) {
			return new ArrayList<>();
		}else {
			return batteriesSet;
		}
		
		
	}
	public void setBatteriesSet(List<Battery> batteriesSet) {
		this.batteriesSet = batteriesSet;
	}
	
	
	
}
