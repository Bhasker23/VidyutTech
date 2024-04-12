package com.VidyutTech.Exception;

import java.time.LocalDate;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalHandler {

	@ExceptionHandler(BatteryException.class)
	public ResponseEntity<ExceptionResponse> adminRegisteException(BatteryException ae, WebRequest wr){
		
		ExceptionResponse ex = new ExceptionResponse();
		ex.setMessage(ae.getMessage());
		ex.setDesc(wr.getDescription(false));
		ex.setDate(LocalDate.now());
	
		
		return new ResponseEntity<>(ex,HttpStatus.BAD_REQUEST);
	}
	
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ExceptionResponse> Exception(Exception ae, WebRequest wr){
		
		ExceptionResponse ex = new ExceptionResponse();
		ex.setMessage(ae.getMessage());
		ex.setDesc(wr.getDescription(false));
		ex.setDate(LocalDate.now());
		
		return new ResponseEntity<>(ex,HttpStatus.BAD_REQUEST);
	}

}
