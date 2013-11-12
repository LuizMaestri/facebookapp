package br.ufsc.facebookapp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import facebook4j.Facebook;
import facebook4j.FacebookException;
import facebook4j.Friend;
import facebook4j.Group;
//import facebook4j.Message;
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
        
        gi.getButtons()[0].addActionListener(new ActionListener()
        {
        	
        	public void actionPerformed(ActionEvent e) 
			{
        		// TODO Auto-generated method stub
				FacebookLogin log = new FacebookLogin();
	        	log.facebookTokenGenerator();
	        	log.setToken(gi.pecaTexto("Insira a URL:"));
	        	if(log.login())
	        	{
	        		final Facebook face = log.getFacebook();
	        		final FaceAcitons fc = new FaceAcitons();        	
	        		ResponseList<Group> grupos = null;
    				
	        		try {
	        			grupos = face.getGroups() ;
					} catch (FacebookException e2) {
						gi.erro("Falha na conecção com o Facebook.");
    				}
	    
					gi.setGrupos(fc.grupos(grupos));
					
	        		
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
		        				gi.interajaLoged(fc.newsFeedOrTimeLine(feed2, 2));
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
	        	        		String[] s= new String [2];
		        				try{
		        					s = fc.postar(gi.getTexts()[0], gi.getTexts()[1]);
		        				} catch (PostEmBranco e2) {
		        					gi.erro("Você não pode postar em branco");
		        				}
		        				if(s[1] == null)
		        					try {
		        						face.postStatusMessage(s[0]);//indície 0 sem para mensagem
		        					} catch (FacebookException e2) {
		        						gi.erro("Falha na conecção com o Facebook.");
		        					}
		        				else 
		        					try {
		        						face.postStatusMessage(s[1], s[0]);
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
	        			
	        	}
			}
        });
    }
}
