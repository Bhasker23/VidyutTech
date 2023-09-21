package com.VidyutTech.Service;


import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.VidyutTech.models.Battery;
import com.VidyutTech.models.User;

@Service
public interface UserService {
	
	public String login(User user);

	public Optional<Battery> getBatteryAllInfo(Integer batteryId);
	
	public String getSpecificInfo(Integer batteryId, String type);

	public String sendDataToServerEveryMinute();

	public Set<String> getSpeInfoAtGivenTime(int BatteryID, String start, String end, String type);
	
	
}
