package br.ufsc.facebookapp.iu;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class GraphInterface
{
	private JPanel painel = new JPanel();
	private JPanel painel2 = new JPanel();
	private JFrame janela = new JFrame("Tretas APP");
	
	private JButton login = new JButton("Log in");
	private JButton sair = new JButton("Sair");
	private JButton postar = new JButton("Postar");
	private JButton feed = new JButton("Feed");
	private JButton timeline = new JButton("Timeline");
	private JButton amigos = new JButton("Amigos");
	private JButton direcao = new JButton("Para:");
	
	JTextField post = new JTextField(50);
	JTextField para = new JTextField(20);
	
	JTextArea mensagens = new JTextArea(40,70);
	JTextArea grupos = new JTextArea(40,20);
	
	JScrollPane barra = new JScrollPane(mensagens, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	JScrollPane barra2 = new JScrollPane(grupos, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	
	public GraphInterface ()
	{
		painel.add(barra);
		janela.add(painel2, BorderLayout.SOUTH);
		janela.add(barra2, BorderLayout.WEST);
		painel.add(post, BorderLayout.NORTH);
		painel.add(postar);
		painel.add(direcao, BorderLayout.SOUTH);
		painel.add(para, BorderLayout.CENTER);
		painel2.add(login, BorderLayout.SOUTH);
		painel2.add(feed, BorderLayout.SOUTH);
		painel2.add(timeline, BorderLayout.SOUTH);
		painel2.add(amigos, BorderLayout.SOUTH);
		painel2.add(sair, BorderLayout.SOUTH);
		janela.add(painel);
		janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		sair.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
		});
		
		direcao.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
			para.setVisible(true);
			}
		});
	}

	/**
    * Retorna os JButtons em um array
	* @return Button[] na ordem {login, feed, amigos, postar, timeline}
    */
	public JButton[] getButtons()
	{
		JButton[] jb = {login, feed, amigos, postar, timeline};
		return jb;
	}
	
	/**
	 * Mostra na telas os grupo do qual o usuário faz parte
	 * @param String com os grupos a ser mostrado num JTextArea
	 */
	public void setGrupos(String g)
	{
		grupos.setText(g);
	}
	
	/**
     	* Mostra a janela do app pré-login
     	*/
	public void interajaLog()
	{
		janela.setVisible(true);
		janela.setMinimumSize(new Dimension(600,500));
		mensagens.setText("Bem vindo!\nEste é o seu APP desktop feito para acesso ao Facebook.\nAutorize-nos clicando abaixo no botão Log in.\n Obrigado pela preferancia\n");
		mensagens.setEditable(false);
		mensagens.setLineWrap(true);
		mensagens.setWrapStyleWord(true);
		mensagens.setBackground(janela.getBackground());
		grupos.setEditable(false);
		grupos.setLineWrap(true);
		grupos.setWrapStyleWord(true);
		grupos.setBackground(janela.getBackground());
		timeline.setVisible(false);
		feed.setVisible(false);
		amigos.setVisible(false);
		postar.setVisible(false);
		barra.setVisible(true);
		barra2.setVisible(false);
		post.setVisible(false);
		para.setVisible(false);
		direcao.setVisible(false);
	}
	
	/**
     	* Mostra a janela do app pós-login
	* @param String com a mensagem a ser mostrado num JTextArea
     	*/
	public void interajaLoged(String s)
	{
		mensagens.setText(s);
		timeline.setVisible(true);
		feed.setVisible(true);
		amigos.setVisible(true);
		postar.setVisible(true);
		login.setVisible(false);
		post.setVisible(true);
		para.setVisible(false);
		barra2.setVisible(true);
		direcao.setVisible(true);
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

	public String[] getTexts() 
	{
		String[] jt = {post.getText(),para.getText()};
		return jt;
	}
}

