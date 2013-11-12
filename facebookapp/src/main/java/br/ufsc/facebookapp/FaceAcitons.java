package br.ufsc.facebookapp;

import java.util.ArrayList;
import java.util.HashMap;
import facebook4j.Friend;
import facebook4j.Group;
/*import facebook4j.InboxResponseList;
import facebook4j.Message;*/
import facebook4j.Poke;
import facebook4j.Post;
import facebook4j.ResponseList;
//import br.ufsc.facebookapp.excecoes.NomeErrado;
import br.ufsc.facebookapp.excecoes.PostEmBranco;

public class FaceAcitons {

	HashMap<String,Integer> poker;
	/*private HashMap<String,Friend> friends;
	private HashMap<String,Group> grupos;
	private ResponseList<Friend> friends;
	private ResponseList<Group> grupos;
	private String grupoId;
	private String friendsId;*/
	
 	public FaceAcitons()
	{
		
	}
	
	 	
	private HashMap<String, Integer> getPoker() 
	{
		return poker;
	}
	
	@SuppressWarnings("null")
	public String maiorPoker( ResponseList<Poke> pokes, String nome)
	{
		int tam = 50;
		if(pokes.size()<50)
			tam = pokes.size();
		ArrayList<String> pk = null;
		String aux;
		for(int i = 0; i < tam; i++)
		{
			aux = pokes.get(i).getFrom().getName();
			if(i !=0)
			{
				if(aux.equalsIgnoreCase(nome)&& !pk.contains(aux))
					pk.add(aux);
				if(!getPoker().containsKey(aux))
					getPoker().put(aux, 1);
				else
					getPoker().put(aux, (getPoker().get(aux).intValue()+1));
			}
			else
				pk.add(aux);
		}
		return maiorValor(pk);
	}
	
	private String maiorValor(ArrayList<String> nomes)
	{
		int maior = getPoker().get(nomes.get(0));
		int tam = nomes.size();
		int aux = 0;
		String nomeAux; 
		String nome = null; 
		for(int i = 1; i < tam; i++)
		{
			nomeAux = nomes.get(i);
			aux = getPoker().get(nome);
			if(maior < aux)
			{
				maior = aux;
				nome = nomeAux;
			}
		}
		return "Seu maior 'pokeador' é: " + nome + " com " + maior + " pokes.";
	}
 	
	/**
     * Posta uma mensagem na timeline do usuário
     * @param String s mensagem
     * f==null se for na timeline do usuário
     * @return String[] sendo:
     * indíce 0 a mensagem 
     * @throws PostEmBranco 
	 */
	
	public String postar(String s) throws PostEmBranco
	{
		s = s.trim();
		
		if(s.length() == 0)
			throw new PostEmBranco();
		else
			return s;
	}
    

	/**
     * Retorna uma lista com os nomes de todos amigos
     * @param ResponseList<Friend> Com os amigos na conta do usuário
     * @return String
     */
	public String friendList (ResponseList<Friend> friend)
	{
		//this.setFriend2(friend);
		String f = "Amigos:\n";
		int tam = friend.size();
		for (int i= 0; i< tam; i++)
		{
			f += "\n" + friend.get(i).getName();
			
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
     * @param InboxResponseList<Message> Com as mensagens da inbox
     * @return String
     */
	/*public String inbox (InboxResponseList<Message> inbox)
	{
		String f="Inbox:";
		
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
	public String grupos (ResponseList<Group> grupo)
	{
		//this.setGroup2(grupo);
		String g="Grupos: \n";
		int quant = grupo.size();
		for (int i= 0; i< quant; i++)
			g += "\n" + grupo.get(i).getName();
		return g;
	}
}
