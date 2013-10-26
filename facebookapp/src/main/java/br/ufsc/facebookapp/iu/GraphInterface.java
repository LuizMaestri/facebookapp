package br.ufsc.facebookapp.iu;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class GraphInterface
{
	JPanel painel = new JPanel();
	JFrame janela = new JFrame("Tretas APP");
	JButton login = new JButton("Log in");
	JButton sair = new JButton("Sair");
	JButton postar = new JButton("Postar");
	JButton feed = new JButton("Feed");
	JButton amigos = new JButton("Amigos");
	JTextArea mensagens = new JTextArea();
	
	public GraphInterface ()
	{
		painel.add(mensagens);
		painel.add(login);
		painel.add(feed);
		painel.add(amigos);
		painel.add(postar);
		painel.add(sair);
		janela.add(painel);
		janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		sair.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
		});
		
	}
	
	/**
     	* Retorna os JButtons em um array
	* @return Button[]
     	*/
	public JButton[] getButtons()
	{
		JButton[] jb = {login, feed, amigos, postar};
		return jb;
	}
	
	/**
     	* Mostra a janela do app pré-login
     	*/
	public void interajaLog()
	{
		janela.setVisible(true);
		janela.setMinimumSize(new Dimension(400,350));
		mensagens.setSize(200,200);
		mensagens.setText("Bem vindo!\nEste é o seu APP desktop feito para acesso ao Facebook.\nAutorize-nos clicando abaixo no botão Log in.\n Obrigado pela preferancia");
		mensagens.setEditable(false);
		feed.setVisible(false);
		amigos.setVisible(false);
		postar.setVisible(false);
		
	}
	
	/**
     	* Mostra a janela do app pós-login
	* @param String com a mensagem a ser mostrado num JTextArea
     	*/
	public void interajaLoged(String s)
	{
		janela.setVisible(true);
		janela.setMinimumSize(new Dimension(500,400));
		mensagens.setText(s);
		feed.setVisible(true);
		amigos.setVisible(true);
		postar.setVisible(true);
		login.setVisible(false);
	}
	
	/**
     	* Retorna uma String digitado pelo usuário
	* @param String com a mensagem na janela de texto
     	* @return String
     	*/
	public String pecaTexto(String s)
	{
		return JOptionPane.showInputDialog(s);
	}
	
	/**
     	* Mostra uma janela de erro
	* @param String com a mensagem de erro
     	*/
	public void erro(String s)
	{
		JOptionPane.showMessageDialog(null, s, "Erro", JOptionPane.ERROR_MESSAGE);
	}
}
