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
	
	/**
     	* Retorna o atributo facebook da classe FacebookLogin
     	* @return Facebook
     	*/
	public Facebook getFacebook() {
		return facebook;
	}

	/**
     	* Realiza a conecção do app com o Facebook
     	* @return boolean
     	*/
	public boolean login() {
		facebook = new FacebookFactory().getInstance();
		facebook.setOAuthAppId("203900246459306",
				"1cd7277edca0e80784ef05fae547b608");
		facebook.setOAuthPermissions("email,publish_stream,read_stream");
		
		facebookTokenGenerator(getFacebook());
		
		System.out.println("Digite o código (após 'code=') da URL:");
		
		String token = getFacebookToken(leitor.nextLine());
		
		AccessToken aT = null;
		try {
			aT = facebook.getOAuthAccessToken(token);
		} catch (FacebookException e) {
			System.out.println("Código incorreto");
		}

		facebook.setOAuthAccessToken(aT);		

		return true;
	}

	/**
     	* Abre o browser padrão da máquina para a criação do token de acesso
     	* @param Facebook
     	*/
	void facebookTokenGenerator(Facebook fb) {

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

	/**
     	* Retorna o token retirado da URL aberta pelo método "void facebookTokenGenerator(Facebook fb)"
     	* @param String contendo a url gerada
     	* @return String
     	*/
	String getFacebookToken(String url)
	{
		return url.substring(30);
	}
}
