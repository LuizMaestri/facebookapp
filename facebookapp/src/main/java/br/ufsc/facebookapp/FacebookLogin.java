package br.ufsc.facebookapp;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Scanner;

import facebook4j.Facebook;
import facebook4j.FacebookException;
import facebook4j.FacebookFactory;
import facebook4j.auth.AccessToken;

public class FacebookLogin {

	Facebook facebook;
	Scanner leitor = new Scanner(System.in).useDelimiter(System.getProperty("line.separator"));
	
	public Facebook getFacebook() {
		return facebook;
	}
	public boolean login() {
		facebook = new FacebookFactory().getInstance();
		facebook.setOAuthAppId("203900246459306",
				"1cd7277edca0e80784ef05fae547b608");
		facebook.setOAuthPermissions("email,publish_stream,read_stream");
		
		getFacebookToken(facebook);
		
		System.out.println("Digite o código (após 'code=') da URL:");
		
		String token = leitor.nextLine();
		
		AccessToken aT = null;
		try {
			aT = facebook.getOAuthAccessToken(token);
		} catch (FacebookException e) {
			System.out.println("Código incorreto");
		}

		facebook.setOAuthAccessToken(aT);
		System.out.println(aT.toString());		

		return true;
	}
/*+		System.out.println("Acesse o link:" + facebook.getOAuthAuthorizationURL("http://gustavomaestri.com"));
"https://www.facebook.com/dialog/oauth?app_id=203900246459306&redirect_uri=http://gustavomaestri.com"
*/
	void getFacebookToken(Facebook fb) {

		Desktop desktop = Desktop.getDesktop();
		URI uri;
		try {
			uri = new URI(fb.getOAuthAuthorizationURL("http://www.athom.com.br/"));
			desktop.browse(uri);
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (URISyntaxException use) {
			use.printStackTrace();
		}
	}

}
