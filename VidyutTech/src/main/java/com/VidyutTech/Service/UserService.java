package com.VidyutTech.Service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.VidyutTech.models.Battery;

@Service
public interface UserService {

	public Optional<Battery> getBatteryAllInfo(Integer batteryId);
	
	public String getSpecificInfo(Integer batteryId, String type);
}
