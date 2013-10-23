package br.ufsc.facebookapp;

import facebook4j.Facebook;
import facebook4j.FacebookException;
import facebook4j.Friend;
import facebook4j.ResponseList;
import br.ufsc.facebookapp.excecoes.*;
import br.ufsc.facebookapp.iu.*;

public class App 
{
    public static void main( String[] args )
    {
    	int controle1 = 0;
    	FacebookLogin log = new FacebookLogin();
        TextInterface iu = new TextInterface();
        FaceAcitons fc= null;
        Facebook face;

        if (iu.interfaceLogin() ==1)
        {
        	if(log.login())
        	{
			iu.sussesso(5);
        		fc = new FaceAcitons();        	
        		face = log.getFacebook();
        		while (true)
        		{

        			switch (iu.interfaceLoged())
        			{
        			case 1  : {
        				iu.sussesso(1);
        				ResponseList<Post> feed = null;
        				try {
							feed = face.getHome();
						} catch (FacebookException e) {
        					System.out.println("Erro na conecção com o Facebook.");
        				}
        				System.out.println(fc.newsFeed(feed));
        			}
        				break;
        			
        			case 2  :  {
        				String s= "";
        				System.out.println("Digite seu post:");
        				try{
        					s = fc.postar();
        			} catch (PostEmBranco e) {
        				System.out.println("Você não pode postar em branco");
        			}
        				try {
        					face.postStatusMessage(s);
        				} catch (FacebookException e) {
        					System.out.println("Erro na conecção com o Facebook.");
        				}
        				iu.sussesso(2);
        			}
        				break;
        			
        			case 3  :{
        				iu.sussesso(3);
        				ResponseList<Friend> d = null;
        				try {
        					d = face.getFriends();
        				} catch (FacebookException e){
        					System.out.println("Erro na conecção com o Facebook.");
        				}
        				System.out.println(fc.friendList(d));
        			}
        				break;
        			
        			case 4  : {
        				iu.sussesso(4);
        				System.exit(4);
        			}
        				break;
        				
        			default  :  System.out.println("digite um valor válido");
        			}
        		}
        	}
        }
        else
        		System.exit(2);
    }
}
