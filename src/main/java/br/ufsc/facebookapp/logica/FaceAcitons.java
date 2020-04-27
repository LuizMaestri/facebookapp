package br.ufsc.facebookapp.logica;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import facebook4j.Facebook;
import facebook4j.FacebookException;
import facebook4j.Friend;
import facebook4j.Group;
import facebook4j.InboxResponseList;
import facebook4j.Message;
import facebook4j.Poke;
import facebook4j.Post;
import facebook4j.ResponseList;
import facebook4j.Permission;

import br.ufsc.facebookapp.excecoes.*;

public class FaceAcitons {

	private HashMap<String,Integer> poker = new HashMap<String,Integer>();
	private List<Friend> friends = new  ArrayList<Friend>();
	/*private HashMap<String,Friend> friends;
	private HashMap<String,Group> grupos;
	private ResponseList<Group> grupos;
	private String grupoId;
	private String friendsId;*/

	public FaceAcitons(){		
	}

	/**
	 * Retorna o atributo poker
	 * @return HashMap<String, Integer>
	 */ 	
	private HashMap<String, Integer> getPoker(){
		return poker;
	}

	/**
	 * Adiciona mais um item ao atributo poker
	 * @param String com a key
	 * @param Integer com o valor a ser guardado
	 */ 
	private void setPoker(String k, Integer i){
		getPoker().put(k, i);
	}

	/**
	 * retorna o atributo friends
	 * @return List<Friend>
	 */
	public List<Friend> getFriends() {
		return friends;
	}
	
	/**
	 * set do atributo friends
	 * @param ResponseList<Friend> contendo os amigos do usuário
	 */
	public void setFriends(ResponseList<Friend> friends) {
		this.friends = friends;
	}

	/**
	 * Retorna o nome da pessoa que mais deu no usuário pokes e a quantidade de pokes dados
	 * @note conta somente os últimos 20 pokes não deletados
	 * @param ResponseList<Poke>
	 * @param String contendo o nome do usuário
	 * @return String
	 * @throws NaoHaPokes
	 */
	public String maiorPoker( ResponseList<Poke> pokes, String nome) throws NaoHaPokes{
		if (pokes == null) throw new NaoHaPokes();
		int tam = 20;
		if(pokes.size()<20)
			tam = pokes.size();
		ArrayList<String> pk = new ArrayList<String>();
		pk.add(nome);
		String aux= "";
		setPoker(nome, 0);
		for(int i = 0; i < tam; i++)
		{
			aux = pokes.get(i).getFrom().getName();
			if(i!=0){
				if(aux.equalsIgnoreCase(nome)&& !pk.contains(aux))
					pk.add(aux);
				if(!getPoker().containsKey(aux))
					setPoker(aux, 1);
				else
					setPoker(aux, (getPoker().get(aux).intValue()+1));
			}
			else{
				if (!aux.equalsIgnoreCase(nome)){
					pk.remove(0);
					pk.add(aux);
					setPoker(pk.get(0),1);
				}
			}
		}

		getPoker().remove(nome);

		return maiorValor(pk);
	}

	/**
	 * Pesquisa no atributo poker o nome do maior 'poker' do usuário e a quantidade de pokes
	 * @param ArrayList<String>
	 * @return String
	 */
	private String maiorValor(ArrayList<String> nomes){
		Iterator<String> iter = nomes.iterator();
		int maior = getPoker().get(nomes.get(0));
		int aux = 0;
		String nomeAux; 
		String nome = nomes.get(0); 
		while(iter.hasNext()){
			nomeAux = iter.next();
			aux = getPoker().get(nome);
			if(maior < aux){
				maior = aux;
				nome = nomeAux;
			}
		}
		return "Seu maior 'pokeador' é: " + nome + " com " + maior + " poke(s).";
	}

	/**
	 * Returna uma mensagem a ser postada  na timeline do usuário
	 * @param String[] contendo a mensagem e o nome de onde será postado
	 * @param String com o id do usuário
	 * s[1]==null se for na timeline do usuário
	 * @return String[]
	 * 0 - mensagem  1-Id
	 * @throws PostEmBranco 
	 */

	public String[] postar(String[] s, String userId) throws PostEmBranco, NomeErrado
	{
		s[0] = s[0].trim();
		s[1] = s[1].trim();

		if(s[0].length() == 0)
			throw new PostEmBranco();
		if(s[1].equalsIgnoreCase(null))
			s[1]= userId;
		return s;
	}


	/**
	 * Retorna uma lista com os nomes de todos amigos
	 * @param ResponseList<Friend> Com os amigos na conta do usuário
	 * @return String
	 */
	public String friendList (){
		String f = "Amigos:\n";
		Iterator<Friend> iter = getFriends().iterator();
		while(iter.hasNext())
			f += "\n" + iter.next().getName();
		return f;
	}

	/**
	 * Retorna uma lista com os últimos 10 post do news feed ou 
	 * os 20 últimos posts realizados pelo usuário ou postados em sua Timeline
	 * @param ResponseList<Post> Com os posts do news feed ou da Timeline
	 * @param int com a opção, 1 - Timeline, 2 News Feed
	 * @return String
	 */
	public String newsFeedOrTimeLine (ResponseList<Post> feed, int opcao){
		String f;
		int quant = 10;
		if(1==opcao){
			f = "Timeline:\n";
			quant = 20;
		}
		else
			f = "Feed de notícias:\n";
		for (int i= 0; i< quant; i++){
			f += "\n" + feed.get(i).getFrom().getName() + ":";

			if(feed.get(i).getType().equals("photo")){
				f += "\n" + (feed.get(i).getPicture()) + "\n";
				if (feed.get(i).getMessage() != null)
					f +=feed.get(i).getMessage() + "\n";
			}
			else{
				if(feed.get(i).getType().equals("link")){
					f += "\n" + feed.get(i).getLink().toString() + "\n";
					if (feed.get(i).getMessage() != null)
						f +=feed.get(i).getMessage() + "\n";
				}

				else
					f +="\n" + feed.get(i).getMessage() + "\n";

			}
		}
		return f;
	}

	/**
	 * Retorna uma lista com as últimas mensagens recebidas pelo usuário
	 * @param InboxResponseList<Message> Com as mensagens da inbox
	 * @return String
	 */
	public String inbox (InboxResponseList<Message> inbox){
		String f="Inbox:";
		Message m;
		for (int i= 0; i< 10; i++){
			m = inbox.get(i);
			f += "\n" + m.getFrom().getName() + ":\n" + m.getMessage() + "\n";
		}
		return f;
	}

	/**
	 * Retorna uma lista com os grupos dos quais o usuário participa
	 * @param ResponseList<Group> Com os grupos do usuário
	 * @return String
	 */
	public String grupos (ResponseList<Group> grupo){
		String g = "Amigos:\n";
		Iterator<Group> iter = grupo.iterator();
		while(iter.hasNext())
			g += "\n" + iter.next().getName();
		return g;
	}
	
	/**
	 * Desativa todas as permissões do aplicativo na conta do usuário
	 * @param List<Permission> contendo todas as permissões
	 * @param Facebook
	 * @throws FacebookException
	 */
	public void removePermissoes( List<Permission> permissoes, Facebook face) throws FacebookException
	{
		Iterator<Permission> iter = permissoes.iterator();
		while(iter.hasNext())
			face.revokePermission(iter.next().getName());
	}
}