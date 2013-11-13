package br.ufsc.facebookapp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import facebook4j.Facebook;
import facebook4j.FacebookException;
import facebook4j.Post;
import facebook4j.ResponseList;
import br.ufsc.facebookapp.excecoes.*;
import br.ufsc.facebookapp.iu.GraphInterface;

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
    				
	        		try {
	        			gi.setGrupos(fc.grupos(face.getGroups()));
					} catch (FacebookException e2) {
						gi.erro("Falha na conecção com o Facebook.");
    				}
	    	        		
					ResponseList<Post> feed = null;
					try {
    					feed = face.getHome();
					} catch (FacebookException e2) {
						gi.erro("Falha na conecção com o Facebook.");
    				}
					gi.interajaLoged(fc.newsFeedOrTimeLine(feed, 2));
	        		//gi.interajaLoged("Olá"); //caso haja problema no login descomente essa linha e comente o try acima. 
	        		
	        			gi.getMenus()[0].addActionListener(new ActionListener()
	        	        {
	        	        	
	        	        	public void actionPerformed(ActionEvent e) 
	        				{
		        				try {
		        					gi.interajaLoged(fc.newsFeedOrTimeLine(face.getHome(), 2));
		    					} catch (FacebookException e2) {
		    						gi.erro("Falha na conecção com o Facebook.");
		        				}
		        			}
	        			});
	        			
	        			gi.getMenus()[2].addActionListener(new ActionListener()
	        	        {
	        	        	
	        	        	public void actionPerformed(ActionEvent e) 
	        				{
		        				try {
		        					gi.interajaLoged(fc.newsFeedOrTimeLine(face.getFeed(), 1));
		    					} catch (FacebookException e2) {
		    						gi.erro("Falha na conecção com o Facebook.");
		        				}
		        			 }
	        			});
	        			
	        			gi.getButtons()[1].addActionListener(new ActionListener()
	        	        {
	        	        	
	        	        	public void actionPerformed(ActionEvent e) 
	        				{
	        	        		try{
		        					face.postStatusMessage(fc.postar(gi.getTexts()[0]));
		        				} catch (PostEmBranco e2) {
		        					gi.erro("Você não pode postar em branco");
		        				}	catch (FacebookException e1) {
									gi.erro("Falha na conecção com o Facebook.");
								}
		        				gi.zeraTextos();
		        			}
	        	       });
	        			
	        			gi.getMenus()[1].addActionListener(new ActionListener()
	        	        {
	        	        	public void actionPerformed(ActionEvent e) 
	        				{
	        	        		try {
		        					gi.interajaLoged(fc.friendList(face.getFriends()));
		        				} catch (FacebookException e2){
		        					gi.erro("Falha na conecção com o Facebook.");
		        				}
		        			}
	        			});
	        			
	        			gi.getMenus()[3].addActionListener(new ActionListener() {
	        	        	
	        	        	public void actionPerformed(ActionEvent e) 
	        				{
	        	        		try {
									gi.interajaLoged(fc.maiorPoker(face.getPokes(), face.getMe().getName()));
								} catch (FacebookException e1) {
									gi.erro("Falha na conecção com o Facebook.");
								} catch (NaoHaPokes e1) {
									gi.erro("Não há pokes recentes");
								}
		        			}
	        			});
	        	}
			}
        });
    }
}
}
