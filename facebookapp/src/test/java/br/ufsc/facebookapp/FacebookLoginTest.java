package br.ufsc.facebookapp;

import junit.framework.TestCase;

public class FacebookLoginTest extends TestCase {

	public void testLogin() {
		FacebookLogin facebookLogin = new FacebookLogin();

		boolean logded = facebookLogin.login();

		assertTrue(logded);
	}

	public void testGetAuthToken() {
		FacebookLogin facebookLogin = new FacebookLogin();
		String token = facebookLogin.getFacebookToken();

		assertNotNull(token);
	}

}
