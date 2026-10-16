package com.vnk.order.globalexception;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {
	private int status;
	private LocalDateTime timestamp;
	private String path;
	private String error;
	private String message;
}
