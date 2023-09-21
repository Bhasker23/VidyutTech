package com.VidyutTech.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.VidyutTech.Repo.BatteryRepo;
import com.VidyutTech.models.Battery;


@Service
public class BatteryServiceImpl implements BatteryService {

	
	@Autowired
	private BatteryRepo batterDb;
	
	
	@Override
	public Battery addBattery(Battery battery) {
		
		LocalDateTime BatterydateTime = LocalDateTime.now();
		DateTimeFormatter btDateTime = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
		
		battery.setTime(BatterydateTime.format(btDateTime));
		Battery batteryObj = batterDb.save(battery);
		
		return batteryObj;
	}

	
}
