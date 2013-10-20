package br.ufsc.facebookapp;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import facebook4j.Facebook;
import facebook4j.FacebookFactory;
import facebook4j.auth.AccessToken;

public class FacebookLogin {

	public boolean login() {
		Facebook facebook = new FacebookFactory().getInstance();
		facebook.setOAuthAppId("203900246459306",
				"1cd7277edca0e80784ef05fae547b608");
		facebook.setOAuthPermissions("email,publish_stream");

		String token = getFacebookToken();

		facebook.setOAuthAccessToken(new AccessToken(token, null));

		return true;
	}

	String getFacebookToken() {

		Desktop desktop = Desktop.getDesktop();
		URI uri;
		try {
			uri = new URI(
					"https://www.facebook.com/dialog/oauth?app_id=203900246459306&redirect_uri=http://gustavomaestri.com");
			desktop.browse(uri);
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (URISyntaxException use) {
			use.printStackTrace();
		}
		return null;
	}

}
