package com.otus.solid.first.war.of.tanks.security;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class AuthError extends RuntimeException {
	public AuthError(String message) {
		super(message);
	}
}
