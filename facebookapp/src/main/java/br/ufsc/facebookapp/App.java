package br.ufsc.facebookapp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JMenuItem;

import facebook4j.Facebook;
import facebook4j.FacebookException;
import facebook4j.Post;
import facebook4j.ResponseList;
import facebook4j.User;
import facebook4j.User.Education;
import facebook4j.User.Work;

import br.ufsc.facebookapp.excecoes.*;
import br.ufsc.facebookapp.logica.*;
import br.ufsc.facebookapp.iu.*;

class App{
	
	GraphInterface gi;
	FacebookLogin log;
	Facebook face;
	FaceAcitons fc;
	PesquisaGraph pg;
	
	protected App(){
		gi = new GraphInterface();
		gi.interajaLog();
		logar();
	}
	
	/**
	 * realiza o login e set de demais funções
	 */
	private void logar() {
		gi.getButtons()[0].addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				log = new FacebookLogin();
				log.facebookTokenGenerator();
				log.setToken(gi.pecaTexto("Insira a URL:"));
				if(log.login())	{
					face = log.getFacebook();
					fc = new FaceAcitons();
					User usuario= null;
					try {
						usuario = face.getMe();
					} catch (FacebookException e1) {
						gi.erro("Falha na conecção com o Facebook.");
					}
					posLog();
					post(usuario);
					setMenus(gi.getMenus(), usuario);					
				}
			}
		});
	}

	/**
	 * set da função dos botões postar e foto
	 * @param usuario
	 * @param jButtons 
	 */
	private void post(User usuario, JButton[] jButtons)	{
		
		jButtons[1].addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				if(clicado) {
					postarFoto();
					clicado = false;
				}
				else postarMensagem();
			}

		});
		
		jButtons[2].addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				up = new UploadDownload();
				gi.setLocal(up.upFoto());
				clicado = true;
			}
			
		});
	}
	
	/**
	 * posta uma foto na timeline do usuário
	 */
	private void postarFoto() {
		try {
			face.postPhoto(new PhotoUpdate(new Media(up.getArquivo())));
		} catch (FacebookException e) {
			gi.erro("Falha na conecção com o Facebook.");
		}
		gi.zeraTextos();
		
	}	
	
	/**
	 * posta uma mensagem na timeline do usuário
	 */
	private void postarMensagem() {
		try{
			String[] p = fc.postar(gi.getTexts(), face.getMe().getId());
			face.postStatusMessage(p[1],p[0]);
		} catch (PostEmBranco e2) {
			gi.erro("Você não pode postar em branco");
		}	catch (FacebookException e1) {
			gi.erro("Falha na conecção com o Facebook.");
		} catch (NomeErrado e1) {
			gi.erro("Você digitou o nome errado.");
		}
		gi.zeraTextos();
		
	}
	
	/**
	 * set de todas as funções dos JMenuItem
	 */
	private void setMenus(JMenuItem[] menus, final User usuario){
		menus[0].addActionListener(new ActionListener()
		{

			public void actionPerformed(ActionEvent e) {
				try {
					gi.interajaLoged(fc.newsFeedOrTimeLine(face.getHome(), 2));
				} catch (FacebookException e2) {
					gi.erro("Falha na conecção com o Facebook.");
				}
			}
		});

		menus[1].addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				gi.interajaLoged(fc.friendList());
			}
		});
		
		menus[2].addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				try {
					gi.interajaLoged(fc.newsFeedOrTimeLine(face.getFeed(), 1));
				} catch (FacebookException e2) {
					gi.erro("Falha na conecção com o Facebook.");
				}
			}
		});

		menus[3].addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				try {
					gi.interajaLoged(fc.maiorPoker(face.getPokes(), face.getMe().getName()));
				} catch (FacebookException e1) {
					gi.erro("Falha na conecção com o Facebook.");
				} catch (NaoHaPokes e1) {
					gi.erro("Não há pokes recentes");
				}
			}
		});
		
		menus[4].addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				try {
					gi.interajaLoged(fc.inbox(face.getInbox(face.getMe().getId())));
				} catch (FacebookException e1) {
					gi.erro("Falha na conecção com o Facebook.");
				}
			}
		});
		
		menus[5].addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(gi.aviso("Você tem certeza disto?")==0){
					try {
						fc.removePermissoes(face.getPermissions(), face);
					} catch (FacebookException e1) {
						System.exit(0);
					}
					System.exit(0);
				}
			}
		});
		
		menus[6].addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				String[] cidades = {usuario.getHometown().getName(),usuario.getLocation().getName()};
				List<Education> escola= usuario.getEducation();
				List<Work> trabalho = usuario.getWork();
				pg = new PesquisaGraph (gi, cidades, cidades, stringArrayFormedWo(trabalho), stringArrayFormedEd(escola));
				setFuncao(pg.getPesquisa());
				
			}

			private String[] stringArrayFormedWo(List<Work> trabalho) {
				int tam = trabalho.size();
				String[] s = new String[tam];
				for(int i = 0; i < tam; i++)
					s[i] = trabalho.get(i).getEmployer().getName();
				return s;
			}

			private String[] stringArrayFormedEd(List<Education> escola) {
				int tam = escola.size();
				String[] s = new String[tam];
				for(int i = 0; i < tam; i++)
					s[i] = escola.get(i).getSchool().getName();
				return s;
			}
		});
	}
	/**
	 * set da função do botão pesquisa
	 * @param JButton
	 */
	private void setFuncao(JButton pesquisa) {
		pesquisa.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				Pesquisas pesq = new Pesquisas(pg.getItemSelecionado());
				try {
					pesq.pesquisar(face.searchUsers(pg.getNome()));
				} catch (FacebookException e1) {
					gi.erro("Falha na conecção com o Facebook.");
				} catch (NomeErrado e1) {
					gi.erro("Houve algum problema quanto ao nome digitado.");
				}
				pg.setTable(pesq.getRefino());
			}

		});
		
	}
}
