package br.ufsc.facebookapp.iu;

import java.util.InputMismatchException;
import java.util.Scanner;

public class TextInterface 
{
	Scanner leitor;
	@SuppressWarnings("resource")
	public TextInterface()
	{
		leitor = new Scanner(System.in).useDelimiter(System.getProperty("line.separator"));
	}

	/**
     	* Pede que o usuário digite o número de uma das opções mostradas
     	* @return INT
     	*/
	public int interfaceLogin()
	{
		int resp = 0;
		boolean continua = true;
		int test = 0;
		
		System.out.println("1 - Login");
		System.out.println("2 - Sair");
		while (continua)
		{
			try{
				test = leitor.nextInt();
			} catch (InputMismatchException e){
				System.out.println("Digite um valor inteiro");
			}
			if (test == 1 || test ==2)
			{
				continua = false;
				resp = test;
			}
			else
			{
				System.out.println("Você precisa digitar 1 ou 2");
			}
		}
		return resp;
	}
	
	/**
     	* Pede que o usuário digite o número de uma das opções mostradas
     	* @return int
     	*/
	public int interfaceLoged() 
	{
		int resp = 0;
		boolean continua = true;
		int test = 0;
		
		System.out.println("1 - Feed de Noticias");
		System.out.println("2 - Postar");
		System.out.println("3 - Pesquisar amigos");
		System.out.println("4 - Sair");
		
		while (continua)
		{
			
			try{
				test = leitor.nextInt();
			} catch (InputMismatchException e){
				System.out.println("Digite um valor inteiro");
			}
			
			if (test == 1 || test ==2 || test ==3 || test ==4)
			{
				continua = false;
				resp = test;
			}
			else
			{
				System.out.println("Você precisa digitar uma das opções.");
			}
		}
		return resp;
	}
	
	/**
     	* Mostra ao úsuario uma mensagem de sussesso na escolha da opção
     	* @param INT da opção da mensagem a ser mostrada
     	*/
	public void sussesso (int f)
	{
		switch(f)
		{
		case 1 : System.out.println("Feed de noticias:");
			break;
		case 2 : System.out.println("Postado com sussesso.");
			break;
		case 3 : System.out.println("Lista de amigos:");
			break;
		case 4 : System.out.println("Obrigado por utilizar nosso app.");
			break;
		case 5 : System.out.println("Login efeituado com sussesso.");
			break;
		}
	}
}
