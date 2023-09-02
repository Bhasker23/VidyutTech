package com.VidyutTech.Controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.VidyutTech.Service.UserService;
import com.VidyutTech.models.Battery;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@GetMapping("/getBatteryInfo")
	public ResponseEntity<Optional<Battery>> getBatteryAllInfo(@RequestParam Integer batteryId){
		
		return new ResponseEntity<Optional<Battery>>(userService.getBatteryAllInfo(batteryId),HttpStatus.OK);
	}
	
	@GetMapping("/getSpecificInfo{type}")
	public ResponseEntity<String> getSpecificDetails(@RequestParam  Integer batteryId, @PathVariable String type){
		
		return new ResponseEntity<String>(userService.getSpecificInfo(batteryId, type),HttpStatus.OK);
		
	}
}
