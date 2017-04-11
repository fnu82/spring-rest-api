package com.myorg.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR, reason="File not saved")
public class FileNotSavedException extends RuntimeException{
	private static final long serialVersionUID = -3353703877707876572L;

}
