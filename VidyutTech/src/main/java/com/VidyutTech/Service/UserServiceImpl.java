package com.VidyutTech.Service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.VidyutTech.Repo.BatteryRepo;
import com.VidyutTech.models.Battery;

import lombok.val;

@Service
public class UserServiceImpl implements UserService{

	
	@Autowired
	private BatteryRepo batteryDb;
	
	@Override
	public Optional<Battery> getBatteryAllInfo(Integer batteryId) {
		 
		Optional<Battery> batteryInfo = batteryDb.findById(batteryId);
		
		return batteryInfo;
	}

	@Override
	public String getSpecificInfo(Integer batteryId, String type) {
		
		Optional<Battery> batteryInfo = batteryDb.findById(batteryId);
		
		if(type.equalsIgnoreCase("voltage")) {
			double value = batteryInfo.get().getVoltage();
			String str = "your battery "+ type  + " is " + value;
			return str;
		}
		
		if(type.equalsIgnoreCase("current")) {
			double value = batteryInfo.get().getTemp();
			String str = "your battery "+ type  + " is " + value + " C. ";
			return str;
		}
		if(type.equalsIgnoreCase("temp")) {
			Integer value = batteryInfo.get().getCurrent();
			String str = "your battery "+ type  + " is " + value;
			return str;
		}
		if(type.equalsIgnoreCase("time")) {
			LocalDateTime value = batteryInfo.get().getTime();
			String str = "your battery "+ type  + " is " + value;
			return str;
		}
			
		
		else {
			return "No data found for given type of BatteryId = " + batteryId;
		}
	}

}
