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
	
	private JMenuBar menu = new JMenuBar();
	
	private JButton login = new JButton("Log in");
	private JButton sair = new JButton("Sair");
	private JButton postar = new JButton("Postar");
	private JButton direcao = new JButton("Para:");
	
	private JMenuItem feed = new JMenuItem("Feed",4);
	private JMenuItem timeline = new JMenuItem("Timeline");
	private JMenuItem maiorPoke = new JMenuItem("Maior Poke");
	private JMenuItem amigos = new JMenuItem("Amigos");
	private JMenuItem pesquisa = new JMenuItem("Pesquisar");
	private JMenuItem sair2 = new JMenuItem("Sair",1);
	
	private JMenu extras = new JMenu("Extras");
	
	private JTextField post = new JTextField(45);
	private JTextField para = new JTextField(20);
	
	private JTextArea mensagens = new JTextArea(40,70);
	private JTextArea grupos = new JTextArea(20,20);
	
	private JScrollPane barra = new JScrollPane(mensagens, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	private JScrollPane barra2 = new JScrollPane(grupos, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	
	private PesquisaGraph janelaPesquisa;
	
	public GraphInterface ()
	{
		janela.setJMenuBar(menu);
		menu.add(feed);
		menu.add(timeline);
		menu.add(amigos);
		menu.add(pesquisa);
		extras.add(maiorPoke);
		menu.add(extras);
		menu.add(sair2);
		painel.add(barra);
		janela.add(barra2, BorderLayout.WEST);
		janela.add(painel2, BorderLayout.SOUTH);
		painel.add(post, BorderLayout.NORTH);
		painel.add(postar, BorderLayout.NORTH);
		painel.add(direcao, BorderLayout.SOUTH);
		painel.add(para, BorderLayout.SOUTH);
		painel2.add(login, BorderLayout.SOUTH);
		painel2.add(sair, BorderLayout.SOUTH);
		janela.add(painel);
		janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setToolTip();
		sair.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
		});
		
		sair2.addActionListener(new ActionListener(){
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
	* Adiciona um ToolTip algumas funções
	*/
	private void setToolTip()
	{
		post.setToolTipText("O que você pensando?");
		para.setToolTipText("Digite o nome do contato/grupo onde quer postar");
		direcao.setToolTipText("Função desativada");
		pesquisa.setToolTipText("Função desativada");
		maiorPoke.setToolTipText("Descubra quem te deu mais pokes atualmente");
	}

	/**
    * Retorna os JButtons em um array
	* @return Button[] na ordem {login, postar}
    */
	public JButton[] getButtons()
	{
		JButton[] jb = {login,  postar};
		return jb;
	}
	
	/**
	 * Retorna os JMenuItem em um array
	 * @return JMenuItem[] na ordem {feed, amigos, timeline, maiorPoke}
	 */
	public JMenuItem[] getMenus()
	{
		JMenuItem[] menus = {feed, amigos, timeline, maiorPoke};
		return menus;
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
	 * zera os JTextFields post e para
	 */
	public void zeraTextos()
	{
		post.setText("");
		para.setText("");
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
		pesquisa.setVisible(false);
		extras.setVisible(false);
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
		post.setVisible(true);
		para.setVisible(false);
		barra2.setVisible(true);
		direcao.setVisible(true);
		pesquisa.setVisible(true);
		extras.setVisible(true);
		painel2.setVisible(false);
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

class PesquisaGraph extends JFrame
{
	private JPanel painel;
	
	private JComboBox sexo;
	private JComboBox idade;
	private JComboBox colegio;
	private JComboBox relacionamento;
	private JComboBox trabalho;
	private JComboBox cidadeNatal;
	private JComboBox cidadeAtual;
	
	private JTextField nome;
	
	private JButton pesquisa;
	
	public PesquisaGraph(String[] cno,String[] cao, String[] to,String[] co)
	{
		super("Pesquisa");
		
			
		painel= new JPanel();
		sexo = this.setJComboBox(new JComboBox(),"Sexo,Feminino,Masculino".split("\\,") );
		idade = this.setJComboBox(new JComboBox(), "Idade,13-17,18-25,26-30,30-40,40+".split("\\,"));
		relacionamento = this.setJComboBox(new JComboBox(), "Relacionamento,Solteiro,Noivo,Viúvo,Relacionamento Aberto,Namorando,Separado,Divorciado".split("\\,"));
		cidadeNatal = this.setJComboBox(new JComboBox(), cno);
		cidadeAtual = this.setJComboBox(new JComboBox(), cao);
		trabalho = this.setJComboBox(new JComboBox(), to);
		colegio = this.setJComboBox(new JComboBox(), co);
		nome = new JTextField (25);
		pesquisa = new JButton("Pesquisar");
		
	}
	
	private JComboBox setJComboBox (JComboBox jcb, String[] opcoes)
	{
		int tam = opcoes.length;
		for(int i = 0; i< tam; i++)
			jcb.addItem(opcoes[i]);
		return jcb;
	}
	
	public String getItemSelecionado (JCheckBox jcb)
	{
		return jcb.getSelectedObjects().toString();
	}
}

