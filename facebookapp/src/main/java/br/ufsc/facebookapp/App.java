package br.ufsc.facebookapp;

import br.ufsc.facebookapp.iu.*;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	int controle1 = 0;
    	int controle2 = 0;
    	FacebookLogin log = new FacebookLogin();
        TextInterface iu = new TextInterface();
        FaceAcitions fc= null;
        controle1 = iu.interfaceLogin();
        if (controle1 ==1)
        {
        	if(log.login())
        	{
        		fc = new FaceAcitions();        	
        		while (controle2 != 4)
        		{
        			controle2 = iu.interfaceLoged();
        			switch (controle2)
        			{
        			case 1  : 
        				break;
        			
        			case 2  :
        				break;
        			
        			case 3  :
        				break;
        			
        			case 4  :  System.exit(controle2);
        				break;
        				
        			default  :  System.out.println("digite um valor válido");
        			}
        		}
        	}
        }
    }
}
