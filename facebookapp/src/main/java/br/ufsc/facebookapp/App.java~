package br.ufsc.facebookapp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import facebook4j.Facebook;
import facebook4j.FacebookException;
import facebook4j.Friend;
import facebook4j.Post;
import facebook4j.ResponseList;
import br.ufsc.facebookapp.excecoes.*;
import br.ufsc.facebookapp.iu.*;

public class App 
{
	public static void main( String[] args )
    {

        final GraphInterface gi = new GraphInterface();
        gi.interajaLog();
 
        //if (iu.interfaceLogin() ==1)
        //{
        gi.getButtons()[0].addActionListener(new ActionListener()
        {
        	
        	public void actionPerformed(ActionEvent e) 
			{
        		// TODO Auto-generated method stub
				//TextInterface iu = new TextInterface();
				FacebookLogin log = new FacebookLogin();
	        	log.facebookTokenGenerator();
	        	log.setToken(gi.pecaTexto("Insira a URL:"));
	        	if(log.login())
	        	{
	        		final FaceAcitons fc = new FaceAcitons();        	
	        		final Facebook face = log.getFacebook();
       				ResponseList<Post> feed = null;
    				
	        		try {
					feed = face.getHome();
				} catch (FacebookException e2) {
				gi.erro("Falha na conecção com o Facebook.");
    				}
	        		
        			try {
					gi.interajaLoged(fc.newsFeedOrTimeLine(feed, 2));
				} catch (IllegalStateException e1) {
					e1.printStackTrace();
				}
	        		
	        			gi.getButtons()[1].addActionListener(new ActionListener()
	        	        	{
	        	        	
	        	        	public void actionPerformed(ActionEvent e) 
	        				{
	        	        		ResponseList<Post> feed2 = null;
		        				try {
		    						feed2 = face.getHome();
		    					} catch (FacebookException e2) {
		    						gi.erro("Falha na conecção com o Facebook.");
		        				}
		        				gi.interajaLoged(fc.newsFeed(feed2, 2));
	        	        	}
	        			});

					gi.getButtons()[4].addActionListener(new ActionListener()
	        	        	{
	        	        	
	        	        	public void actionPerformed(ActionEvent e) 
	        				{
	        	        		ResponseList<Post> feed2 = null;
		        				try {
		    						feed2 = face.getFeed();
		    					} catch (FacebookException e2) {
		    						gi.erro("Falha na conecção com o Facebook.");
		        				}
		        				gi.interajaLoged(fc.newsFeedOrTimeLine(feed2, 1));
	        	        	}
	        			});
	        			
	        			gi.getButtons()[3].addActionListener(new ActionListener()
	        	        	{
	        	        	
	        	        	public void actionPerformed(ActionEvent e) 
	        				{
	        	        		String s= "";
		        				try{
		        					s = fc.postar();
		        			} catch (PostEmBranco e2) {
		        				gi.erro("Você não pode postar em branco");
		        			}
		        				try {
		        					face.postStatusMessage(s);
		        				} catch (FacebookException e2) {
		        					gi.erro("Falha na conecção com o Facebook.");
		        				}
	        	        	}
	        			});
	        			
	        			gi.getButtons()[2].addActionListener(new ActionListener()
	        	        	{
	        	        	
	        	        	public void actionPerformed(ActionEvent e) 
	        				{
	        	        		ResponseList<Friend> d = null;
		        				try {
		        					d = face.getFriends();
		        				} catch (FacebookException e2){
		        					gi.erro("Falha na conecção com o Facebook.");
		        				}
		        				gi.interajaLoged(fc.friendList(d));
	        	        	}
	        			});
	        		
	        		/*while (true)
	        		{

	        			switch (iu.interfaceLoged())
	        			{
	        			case 1  : {
	        				iu.sussesso(1);
	        				ResponseList<Post> feed2 = null;
	        				try {
	    						feed2 = face.getHome();
	    					} catch (FacebookException e2) {
	    						gi.erro("Falha na conecção com o Facebook.");
	        				}
	        				gi.interajaLoged(fc.newsFeed(feed2));
	        			}
	        				break;
	        			
	        			case 2  :  {
	        				String s= "";
	        				try{
	        					s = fc.postar();
	        			} catch (PostEmBranco e2) {
	        				gi.erro("Você não pode postar em branco");
	        				break;
	        			}
	        				try {
	        					face.postStatusMessage(s);
	        				} catch (FacebookException e2) {
	        					gi.erro("Falha na conecção com o Facebook.");
	        				}
	        				iu.sussesso(2);
	        			}
	        				break;
	        			
	        			case 3  :{
	        				iu.sussesso(3);
	        				ResponseList<Friend> d = null;
	        				try {
	        					d = face.getFriends();
	        				} catch (FacebookException e2){
	        					gi.erro("Falha na conecção com o Facebook.");
	        				}
	        				gi.interajaLoged(fc.friendList(d));
	        			}
	        				break;
	        			}
	        		}*/
	        	}
			}
        });
    }
}
        

        		
        			
        			/*case 4  : {
        				iu.sussesso(4);
        				System.exit(4);
        			}
        				break;
        				
        			default  :  System.out.println("digite um valor válido");*/
        			
        /*}
        else
        {
        	iu.sussesso(4);
        	System.exit(2);
        }*/
