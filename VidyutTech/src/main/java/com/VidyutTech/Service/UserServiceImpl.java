package com.VidyutTech.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.VidyutTech.Repo.BatteryRepo;
import com.VidyutTech.Repo.BatteryToServerRepo;
import com.VidyutTech.models.Battery;
import com.VidyutTech.models.BatteryToServer;

@Service
public class UserServiceImpl implements UserService{

	
	@Autowired
	private BatteryRepo batteryDb;
	
	
	@Autowired
	private BatteryToServerRepo btToServerDb;
	
	@Override
	public Optional<Battery> getBatteryAllInfo(Integer batteryId) {
		 
		Optional<Battery> batteryInfo = batteryDb.findById(batteryId);
		System.out.println(batteryInfo.get().getTime());
		
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
			String str = "your battery "+ type  + " is " + value +" ";
			return str;
		}
		if(type.equalsIgnoreCase("time")) {
			String value = batteryInfo.get().getTime();
			String str = "your battery "+ type  + " is " + value;
			return str;
		}
			
		
		else {
			return "No data found for given type of BatteryId = " + batteryId;
		}
	}
	

	@Override
	public String sendDataToServerEveryMinute() {
		
//		BatteriesInfo btInfo = new BatteriesInfo();
//		List<Battery> batteries = batteryDb.findAll();
//		System.out.println(batteries);
//	    List<Battery> btSet = btInfo.getBatteriesSet();
//
////		batteries.stream().map(x -> btInfo.getBatteriesSet().add(x)).collect(Collectors.toSet());
//		
//		for(Battery bt : batteries) {
//			btSet.add(bt);
//		}
//		
//		batteriesInfoDb.save(btInfo);
//		
//		
//		System.out.println("battery Set"+ btSet);
		
		List<Battery> batteries = batteryDb.findAll();
		
		LocalDateTime BatterydateTime = LocalDateTime.now();
		DateTimeFormatter btDateTime = DateTimeFormatter.ofPattern("dd-MM-YYYY hh:mm");
		
		for(Battery bt : batteries) {
			BatteryToServer btServer = new BatteryToServer();
			btServer.setBatteryID(bt.getBatteryID());
			btServer.setCurrent(bt.getCurrent());
			btServer.setTemp(bt.getTemp());
			btServer.setVoltage(bt.getVoltage());
			btServer.setTime(BatterydateTime.format(btDateTime));
			
			btToServerDb.save(btServer);
		
		}
		
	
		
		return "Your Batteries are Sending Data to Server once every Minute.";
	}



}
