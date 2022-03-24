package com.otus.solid.first.war.of.tanks.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@RequiredArgsConstructor
public enum Role implements GrantedAuthority {
	GAMER("gamer"),
	NOT_GAMER("not_gamer");

	private final String name;

	@Override
	public String getAuthority() {
		return name;
	}
}
