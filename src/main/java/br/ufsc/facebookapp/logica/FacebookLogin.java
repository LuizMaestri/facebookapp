package br.ufsc.facebookapp.logica;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import facebook4j.Facebook;
import facebook4j.FacebookException;
import facebook4j.FacebookFactory;
import facebook4j.auth.AccessToken;

public class FacebookLogin {

	private Facebook facebook;
	private String token = null;

	public FacebookLogin(){
		facebook = new FacebookFactory().getInstance();
		facebook.setOAuthAppId("203900246459306",
				"1cd7277edca0e80784ef05fae547b608");
		facebook.setOAuthPermissions("publish_stream,read_stream,read_mailbox,user_groups,publish_actions,email,friends_work_history,friends_hometown,friends_education_history");
	}

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

		AccessToken aT = null;
		try {
			aT = facebook.getOAuthAccessToken(getFacebookToken(getToken()));
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
	public void facebookTokenGenerator() {

		Facebook fb = getFacebook();
		Desktop desktop = Desktop.getDesktop();
		URI uri = null;
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
	String getFacebookToken(String url){
		return url.substring(30, (url.length()-4));
	}

	/**
	 * Retorna o atributo token da classe FacebookLogin
	 * @return String
	 */
	public String getToken() {
		return token;
	}

	/**
	 * Set o atributo token da classe FacebookLogin
	 * @param String contendo o novo token
	 */
	public void setToken(String token) {
		this.token = token;
	}
}
