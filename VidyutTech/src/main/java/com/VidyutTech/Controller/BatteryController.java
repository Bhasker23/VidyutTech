package com.VidyutTech.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.VidyutTech.Service.BatteryService;
import com.VidyutTech.models.Battery;

@RestController
@RequestMapping("/battery")
@CrossOrigin(origins = "*")
public class BatteryController {

	@Autowired
	private BatteryService batteryService;
	
	@PostMapping("/addBattery")
	public ResponseEntity<Battery> addBattery(@RequestBody Battery battery){
		
		return new ResponseEntity<Battery> (batteryService.addBattery(battery), HttpStatus.CREATED);
	}
}
