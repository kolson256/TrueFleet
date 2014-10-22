package com.example.helloworld;

import com.google.common.base.Optional;
import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.basic.BasicCredentials;

public class LoginAuthenticator implements
		Authenticator<BasicCredentials, Boolean> {
	public Optional<Boolean> authenticate(BasicCredentials c)
			throws AuthenticationException {
		if (c.getUsername().equals("test")
				&& c.getPassword().equals("test")) {
			return Optional.of(true);
		}
		return Optional.absent();
		
		//can add functionality for if userrole = trucker/dispatch/admin
	}

}
