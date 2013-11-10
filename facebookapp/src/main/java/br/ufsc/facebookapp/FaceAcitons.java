package br.ufsc.facebookapp;

import java.util.HashMap;

import facebook4j.FacebookException;
import facebook4j.Friend;
import facebook4j.Group;
import facebook4j.Post;
import facebook4j.ResponseList;

import br.ufsc.facebookapp.excecoes.PostEmBranco;

public class FaceAcitons {
	private HashMap<String,String> friends;
	private HashMap<String,String> grupos;

	public FaceAcitons() 
	{

	}
	
	public void setGrupos(ResponseList<Group> grupos) 
	{
		// TODO Auto-generated method stub

		int tam = grupos.size();
		for (int i = 0; i< tam; i++)
		{
			friends.put(grupos.get(i).getName(), grupos.get(i).getId());
		}
	}

	/*private void setFriends()
	{
		int tam = d.size();
		for (int i = 0; i< tam; i++)
		{
			friends.put(d.get(i).getName(), d.get(i).getId());
		}
	}*/
	
	/**
     * Posta uma mensagem na timeline do usuário/amigo/grupo
     * @param String[] sendo indíce 0 a mensagem e indíce 1 onde será postado, 
     * 1==null se for na timeline do usuário
     * @throws PostEmBranco 
	 * @throws FacebookException 
     */
	public String[] postar(String s,String f) throws PostEmBranco
	{
		s = s.trim();
		f = f.trim();
		String[] v = {s,f};
		if(s.length() == 0)
			throw new PostEmBranco();
		else
			return v;
		
	}
	
	/**
     * Retorna uma lista com os nomes de todos amigos
     * @param ResponseList<Friend> Com os amigos na conta do usuário
     * @return String
     */
	public String friendList (ResponseList<Friend> friends2)
	{
		String f = "Amigos:";
		int tam = friends2.size();
		for (int i= 0; i< tam; i++)
		{
			f += "\n" + friends2.get(i).getName();
			
		}
		return f;
	}

	/**
     * Retorna uma lista com os últimos 10 post do news feed ou 
     * os 20 últimos posts realizados pelo usuário ou postados em sua Timeline
     * @param ResponseList<Post> Com os posts do news feed ou da Timeline
     * @param int com a opção, 1 - Timeline, 2 News Feed
     * @return String
     */
	public String newsFeedOrTimeLine (ResponseList<Post> feed, int opcao)
	{
		String f;
		int quant = 10;
		if(1==opcao)
		{
			f = "Timeline:\n";
			quant = 20;
		}
		else
			f = "Feed de notícias:\n";
		for (int i= 0; i< quant; i++)
		{
			
			f += "\n" + feed.get(i).getFrom().getName() + ":";
			
			if(feed.get(i).getType().equals("photo"))
			{
				f += "\n" + (feed.get(i).getPicture()) + "\n";
				if (feed.get(i).getMessage() != null)
					f +=feed.get(i).getMessage() + "\n";
			}
			else
			{
				if(feed.get(i).getType().equals("link"))
				{
					f += "\n" + feed.get(i).getLink().toString() + "\n";
					if (feed.get(i).getMessage() != null)
						f +=feed.get(i).getMessage() + "\n";
				}
				
				else
				{
					f +="\n" + feed.get(i).getMessage() + "\n";
				}
			}
		}
		return f;
	}
	
	/**
     * Retorna uma lista com as últimas mensagens recebidas pelo usuário 
     * os 20 últimos posts realizados pelo usuário ou postados em sua Timeline
     * @param ResponseList<Message> Com as mensagens da inbox
     * @return String
     */
	/*public String inbox (ResponseList<Message> inbox)
	{
		String f="";
		
		for (int i= 0; i< 10; i++)
		{
			
			f += "\n" + inbox.get(i).getFrom().getName() + ":";
			f +="\n" + inbox.get(i).getMessage() + "\n";
		}
		return f;
	}*/
	
	/**
     * Retorna uma lista com os grupos dos quais o usuário participa
     * os 20 últimos posts realizados pelo usuário ou postados em sua Timeline
     * @param ResponseList<Group> Com os grupos do usuário
     * @return String
     */
	public String grupos (ResponseList<Group> grupos)
	{
		String g="Grupos: \n";
		int quant = grupos.size();
		for (int i= 0; i< quant; i++)
		{
			
			g += "\n" + grupos.get(i).getName();
		}
		//setGrupos(grupos2);
		return g;
	}
}

