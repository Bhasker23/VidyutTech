package com.VidyutTech.Controller;


import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.VidyutTech.Service.UserService;
import com.VidyutTech.models.Battery;
import com.VidyutTech.models.User;


@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/signUp")
	public ResponseEntity<String> userSignUp(@RequestBody User user){
		return new ResponseEntity<String> (userService.signUp(user),HttpStatus.OK);
	}
	
	@GetMapping("/getBatteryInfo")
	public ResponseEntity<Optional<Battery>> getBatteryAllInfo(@RequestParam Integer batteryId){
		
		return new ResponseEntity<Optional<Battery>>(userService.getBatteryAllInfo(batteryId),HttpStatus.OK);
	}
	
	@GetMapping("/getSpecificInfo{type}")
	public ResponseEntity<String> getSpecificDetails(@RequestParam  Integer batteryId, @PathVariable String type){
		
		return new ResponseEntity<String>(userService.getSpecificInfo(batteryId, type),HttpStatus.OK);
		
	}
	
	@GetMapping("/getSpecificInfoAtGivenTime{type}")
	public ResponseEntity<Set<String>> getSpecificInfoAtGivenTime(@RequestParam Integer batteryId,
			@RequestParam String startTime, @PathVariable String type ){
		return new ResponseEntity<Set<String>>(userService
				.getSpeInfoAtGivenTime( batteryId,
					 startTime,  type),HttpStatus.OK);
	}
	
	@GetMapping("/sendDataToServer")
	public ResponseEntity<String> sendDataToServerEveryMinute() {
		
		return new ResponseEntity<String>(userService.sendDataToServerEveryMinute(),HttpStatus.OK); 
	}

	@GetMapping("/logout")
	public ResponseEntity<String> logout(@RequestParam Long userId){
		return new ResponseEntity<String>(userService.logout(userId), HttpStatus.NO_CONTENT);
	}
}
