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
}
