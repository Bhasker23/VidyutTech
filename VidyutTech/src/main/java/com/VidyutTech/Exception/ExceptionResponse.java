package com.VidyutTech.Exception;

import java.time.LocalDate;

import lombok.Data;

@Data
public class ExceptionResponse {

	private String message;
	private String desc;
	private LocalDate date;
	
	
}
