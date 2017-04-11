package com.myorg.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="File not found")
public class StorageFileNotFoundException extends RuntimeException{
	private static final long serialVersionUID = -3353703877707876572L;

}
