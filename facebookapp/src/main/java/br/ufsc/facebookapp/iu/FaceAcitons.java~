package br.ufsc.facebookapp.iu;

import javax.swing.JOptionPane;
import facebook4j.Friend;
import facebook4j.ResponseList;
import facebook4j.api.FriendMethods;

import br.ufsc.facebookapp.excecoes.PostEmBranco;

public class FaceAcitons {

	public FaceAcitons()
	{
		
	}
	
	/**
     	* Retorna a mensagem a ser postada
     	* @return String
     	* @throws PostEmBranco 
     	*/
	public String postar() throws PostEmBranco
	{
		String s = JOptionPane.showInputDialog(null, "O que você está pensando?", "Post", JOptionPane.QUESTION_MESSAGE);
		s = s.trim();
		if(s.length() == 0)
			throw new PostEmBranco();
		else
			return s;
		
	}
	
	/**
     	* Retorna uma lista com os nomes de 50 amigos
     	* @param ResponseList<Friend> Com os amigos na conta do usuário
     	* @return String
     	*/
	public String friendList (ResponseList<Friend> friends)
	{
		String f = "";
		for (int i= 0; i< 50; i++)
			f += "\n" + friends.get(i).getName();
		return f;
	}

	/**
     	* Retorna uma lista com os últimos 10 post do news feed
     	* @param ResponseList<Post> Com os posts do news feed
     	* @return String
     	*/
	public String newsFeed (ResponseList<Post> feed)
	{
		String f = "";
		for (int i= 0; i< 10; i++)
		{
			f += "\n" + feed.get(i).getFrom().getName() + ":";
			if (feed.get(i).getMessage() != null)
				f += "\n" + feed.get(i).getMessage() + "\n";
			else
				f +="\n'Nota do programador:' Ainda não conseguimos mostrar fotos, perdão.\n";
		}
		return f;
	}
}
