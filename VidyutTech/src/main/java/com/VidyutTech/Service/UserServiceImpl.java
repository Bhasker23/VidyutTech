package com.VidyutTech.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.aspectj.weaver.bcel.AtAjAttributes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.VidyutTech.Exception.BatteryException;
import com.VidyutTech.Repo.BatteryRepo;
import com.VidyutTech.Repo.BatteryToServerRepo;
import com.VidyutTech.Repo.UserRepo;
import com.VidyutTech.models.Battery;
import com.VidyutTech.models.BatteryToServer;
import com.VidyutTech.models.User;

@Service
public class UserServiceImpl implements UserService{

	int count = 1;
	
	@Autowired
	private BatteryRepo batteryDb;
	
	@Autowired
	private BatteryToServerRepo btToServerDb;
	
	@Autowired
	private UserRepo userRepo;
	
	@Override
	public String login(User user) {
		
		User userdata = new User();
		
		System.out.println(user.getName()+ " "+ user.getUserID()+" "+ user.getPassword());
		
		userdata.setUserID(user.getUserID());
		userdata.setName(user.getName());
		userdata.setPassword(user.getPassword());
		
		userRepo.save(user);
		return userdata.getName() +  " your account is created with " + userdata.getUserID();
	}
	
	@Override
	public Optional<Battery> getBatteryAllInfo(Integer batteryId) {
		 
		if(batteryDb.findById(batteryId) == null) {
			throw new BatteryException(batteryId+ " is not found");
		}
		Optional<Battery> batteryInfo = batteryDb.findById(batteryId);
		System.out.println(batteryInfo.get().getTime());
		
		return batteryInfo;
	}

	@Override
	public String getSpecificInfo(Integer batteryId, String type) {
		
		if(batteryDb.findById(batteryId) == null) {
			throw new BatteryException(batteryId+ " is not found");
		}
		
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
	@Scheduled(fixedRate = 60000)
	public String sendDataToServerEveryMinute() {
		
		
		List<Battery> batteries = batteryDb.findAll();
		
		LocalDateTime BatterydateTime = LocalDateTime.now();
		DateTimeFormatter btDateTime = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
		
		for(Battery bt : batteries) {
			BatteryToServer btServer = new BatteryToServer();
			btServer.setServerId(count++);
			btServer.setBatteryID(bt.getBatteryID());
			btServer.setCurrent(bt.getCurrent());
			btServer.setTemp(bt.getTemp());
			btServer.setVoltage(bt.getVoltage());
			btServer.setTime(BatterydateTime.format(btDateTime));
			
			btToServerDb.save(btServer);
		
		}

		return "Your Batteries are Sending Data to Server once every Minute.";
	}

	
	@Override
	public Set<String> getSpeInfoAtGivenTime(int batteryId, String start, String type) {
		
		if(batteryDb.findById(batteryId) == null) {
			throw new BatteryException(batteryId+ " is not found");
		}
		
		List<BatteryToServer>  listBtToSer = btToServerDb.findAll();
		Set<String> btInfoinTime = new LinkedHashSet<>();
		

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
		LocalDateTime startTime = LocalDateTime.parse(start, formatter);
		
		
		for(BatteryToServer btser : listBtToSer) {
			
			if(btser.getBatteryID().equals(batteryId) && LocalDateTime.parse(btser.getTime(), formatter).isAfter(startTime)) {
				
				if(type.equalsIgnoreCase("voltage")) {
					double value = btser.getVoltage();
					btInfoinTime.add("Battery "+ type + " is " + value + " at "+ btser.getTime());
				}
				
				if(type.equalsIgnoreCase("current")) {
					double value = btser.getTemp();
					btInfoinTime.add("Battery "+ type + " is " + value + " at "+ btser.getTime());
				}
				if(type.equalsIgnoreCase("temp")) {
					Integer value = btser.getCurrent();
					btInfoinTime.add("Battery "+ type + " is " + value + " at "+ btser.getTime());
				}
			}	
			
		}

		return btInfoinTime;
	}

	
}
