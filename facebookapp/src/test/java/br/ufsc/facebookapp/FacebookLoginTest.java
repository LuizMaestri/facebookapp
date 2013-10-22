package br.ufsc.facebookapp;

import junit.framework.TestCase;

public class FacebookLoginTest extends TestCase {

	public void testLogin() {
		FacebookLogin facebookLogin = new FacebookLogin();

		boolean logded = facebookLogin.login();

		assertTrue(logded);
	}

}
