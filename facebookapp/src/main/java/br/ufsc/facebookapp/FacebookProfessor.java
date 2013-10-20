package br.ufsc.facebookapp;

import java.util.Scanner;

import facebook4j.Facebook;
import facebook4j.FacebookException;
import facebook4j.FacebookFactory;
import facebook4j.auth.AccessToken;
import facebook4j.conf.Configuration;
import facebook4j.conf.ConfigurationBuilder;

public class FacebookProfessor {

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);

		ConfigurationBuilder cb = new ConfigurationBuilder();

		cb.setDebugEnabled(true)

		.setOAuthAppId("149568298586818")

		.setOAuthAppSecret("a42c8a88d755f301b7fafb1508c5b88e")

		.setOAuthPermissions("email,publish_stream");

		Configuration conf = cb.build();

		FacebookFactory ff = new FacebookFactory(conf);

		Facebook facebook = ff.getInstance();

		System.out
				.println("Acesse o link: "
						+ facebook
								.getOAuthAuthorizationURL("http://www.athom.com.br/"));

		System.out.println("Digite o codigo (Code) Informado");

		String token = scanner.next();

		AccessToken accessToken = null;

		try {

			accessToken = facebook.getOAuthAccessToken(token);

		} catch (FacebookException e1) {

			// TODO Auto-generated catch block

			e1.printStackTrace();

		}

		facebook.setOAuthAccessToken(accessToken);

		System.out.println(accessToken.toString());

		if (!facebook.getAuthorization().isEnabled()) {

			System.out.println("Erro ao conectar.");

		}

		System.out.println("Conexao efetuada.");

		try {

			facebook.postStatusMessage("Funcionou!!!!!!");

		} catch (FacebookException e) {

			// TODO Auto-generated catch block

			e.printStackTrace();

		}

	}

}