package br.ufsc.facebookapp.iu;

import java.util.InputMismatchException;
import java.util.Scanner;

public class TextInterface 
{
	Scanner leitor;
	public TextInterface()
	{
		leitor = new Scanner(System.in).useDelimiter(System.getProperty("line.separator"));
	}
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
				resp = leitor.nextInt();
			} catch (InputMismatchException e){
				System.out.println("Digite um valor inteiro");
			}
			if (resp == 1 || resp ==2)
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
	
	public int interfaceLoged() 
	{
		int resp = 0;
		
		System.out.println("1 - Feed de Noticias");
		System.out.println("2 - Postar");
		System.out.println("3 - Pesquisar amigos");
		System.out.println("4 - Sair");
		try{
			resp = leitor.nextInt();
		} catch (InputMismatchException e){
			System.out.println("Digite um valor inteiro");
		}
		return resp;
	}
}
