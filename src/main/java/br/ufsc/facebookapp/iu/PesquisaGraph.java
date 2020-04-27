package br.ufsc.facebookapp.iu;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.AbstractCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

import facebook4j.User;

public class PesquisaGraph extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel painel = new JPanel();
	private JPanel painel2 = new JPanel();
	private JPanel painel3 = new JPanel();

	private JComboBox<String> sexo;
	private JComboBox<String> idade;
	private JComboBox<String> formacao;
	private JComboBox<String> relacionamento;
	private JComboBox<String> trabalho;
	private JComboBox<String> cidadeNatal;
	private JComboBox<String> cidadeAtual;

	private JTextField nome;

	private JButton pesquisa;
	
	private JTable resultados;
	
	private JScrollPane barra;


	public PesquisaGraph(JFrame jp, String[] cno,String[] cao, String[] to,String[] co){
		super(jp, "Pesquisa");

		add(painel, BorderLayout.NORTH);
		add(painel2, BorderLayout.SOUTH);
		add(painel3, BorderLayout.CENTER);
		setSize(new Dimension(1000,800));
		inicializador(cno, cao, to, co);
		sexo = this.setJComboBox(new JComboBox<String>(),"Feminino,Masculino".split("\\,"), "Sexo" );
		idade = this.setJComboBox(new JComboBox<String>(), "13-17,18-25,26-30,30-40,40+".split("\\,"),"Idade");
		relacionamento = this.setJComboBox(new JComboBox<String>(), "Solteiro,Noivo,Viúvo,Relacionamento Aberto,Namorando,Separado,Divorciado".split("\\,"), "Relacionamento");
		nome = new JTextField (25);
		pesquisa = new JButton("Pesquisar");
		add();
		visible();
		

	}
	
	/**
	 * adiciona os componentes a janela pesquisa
	 */
	private void add()
	{
		painel.add(sexo);
		painel.add(idade);
		painel.add(formacao);
		painel.add(relacionamento);
		painel.add(trabalho);
		painel.add(cidadeNatal);
		painel.add(cidadeAtual);
		painel2.add(nome);
		painel2.add(pesquisa);
	}
	
	/**
	 * set da visibilidade de todos os componentes
	 */
	private void visible()
	{
		sexo.setVisible(true);
		idade.setVisible(true);
		formacao.setVisible(true);
		relacionamento.setVisible(true);
		trabalho.setVisible(true);
		cidadeNatal.setVisible(true);
		cidadeAtual.setVisible(true);
		nome.setVisible(true);
		pesquisa.setVisible(true);
		painel2.setVisible(true);
		setVisible(true);
	}

	/**
	 * 
	 * @param String[] contendo as opções de cidades natais
	 * @param String[] contendo as opções de cidades atuais
	 * @param String[] contendo as opções de empresas
	 * @param String[] contendo as opções de instituições de ensino
	 */
	private void inicializador( String[] cno,String[] cao, String[] to,String[] co)
	{
		cidadeNatal = setJComboBox(new JComboBox<String>(), cno, "Cidade Natal");
		cidadeAtual = setJComboBox(new JComboBox<String>(), cao, "Cidade Atual");
		trabalho = setJComboBox(new JComboBox<String>(), to, "Trabalho");
		formacao = setJComboBox(new JComboBox<String>(), co, "Formação");
	}
	
	/**
	 * inicializa um JComboBox<String>
	 * @param JComboBox<String>
	 * @param String[] contendo as opções
	 * @param String contendo a primeira opção (nome do ComboBox)
	 * @return JComboBox<String>
	 */
	private JComboBox<String> setJComboBox (JComboBox<String> jcb, String[] opcoes, String primeiraOpcao){
		int tam = opcoes.length;
		jcb.addItem(primeiraOpcao);
		for(int i = 0; i< tam; i++)
			jcb.addItem(opcoes[i]);
		return jcb;
	}
	
	/**
	 * Retorna todos os JComboBox na ordem {sexo, idade, formacao, relacionamento, trabalho, cidadeNatal, cidadeAtual}
	 * @return JComboBox[]
	 */
	@SuppressWarnings("rawtypes")
	private JComboBox[] getBox()
	{
		JComboBox[] jcb ={sexo, idade, formacao, relacionamento, trabalho, cidadeNatal, cidadeAtual};
		return jcb;
	}

	/**
	 * retorna os itens selecionados nos JComboBox
	 * @return String[]
	 */
	public String[] getItemSelecionado(){
		String[] selecao = new String[7];
		@SuppressWarnings("rawtypes")
		JComboBox[] jcb = getBox();
		for(int i = 0; i<7; i++)
			selecao[i] = jcb[i].getSelectedItem().toString();
		return selecao;
	}

	/**
	 * Retorna o botão pesquisa
	 * @return JButton
	 */
	public JButton getPesquisa() {
		return pesquisa;
	}

	/**
	 * Retorna o nome a ser pesquisado
	 * @return String
	 */
	public String getNome() {
		return nome.getText();
	}

	/**
	 * Constroi a tabela onde será impresso os resultados da pesquisa
	 * @param ArrayList<User>
	 */
	public void setTable(ArrayList<User> refino) {
		Iterator<User> iter = refino.iterator();
		resultados = new JTable(refino.size(),2);
		resultados.getColumnModel().getColumn(0).setHeaderValue("Usuário");
		resultados.getColumnModel().getColumn(1).setHeaderValue("Página");
		resultados.setEnabled(true);
		resultados.setRowSelectionAllowed(true);
		int linha = 0;
		while(iter.hasNext()){
			setInfo(iter.next(), linha);
			linha++;
		}
		barra = new JScrollPane(resultados, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		barra.setVisible(true);
		painel3.add(barra, BorderLayout.CENTER);
		
	}

	/**
	 * Organiza as informações do usuário na linha específica da tabela
	 * @param User
	 * @param int com a linha
	 */
	private void setInfo(User next, int linha) {
		String dados= next.getName() + "\n";
		if(next.getGender()!=null) dados += next.getGender() + "\n";
		if(next.getBirthday()!=null) dados += next.getBirthday() + "\n";
		if(next.getEducation()!=null && next.getEducation().size()!=0) dados += next.getEducation().get(0) + "\n";
		if(next.getRelationshipStatus()!=null) dados += next.getRelationshipStatus()+ "\n";
		if(next.getWork()!=null && next.getWork().size()!=0) dados += next.getWork().get(0) + "\n";
		if(next.getHometown()!=null) dados += next.getHometown().getName() + "\n";
		if(next.getLocation()!=null) dados += next.getLocation().getName() + "\n";
		resultados.setValueAt(dados, linha, 0);
		resultados.setValueAt("<HTML><a href= 'http://www.facebook.com/"+next.getId()+"'> Adicionar</a></HTML>", linha, 1);
		ButtonColumn buttonColumn = new ButtonColumn(resultados, 1); 
		resultados.add(new JButton(), 0);
	}
}

/**
 * 
 * @author Anonymous
 * seta os botões da tabela
 */

class ButtonColumn extends AbstractCellEditor  
implements TableCellRenderer, TableCellEditor, ActionListener  
{  
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JTable table;  
	JButton renderButton;  
	JButton editButton;  
	String text;  

	public ButtonColumn(JTable table, int column)  
	{  
		super();  
		this.table = table;  
		renderButton = new JButton();  

		editButton = new JButton();  
		editButton.setFocusPainted( false );  
		editButton.addActionListener( (ActionListener) this );  

		TableColumnModel columnModel = table.getColumnModel();  
		columnModel.getColumn(column).setCellRenderer( this );  
		columnModel.getColumn(column).setCellEditor( (TableCellEditor) this );  
	}  

	public Component getTableCellRendererComponent(  
			JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)  
	{  
		if (hasFocus)  
		{  
			renderButton.setForeground(table.getForeground());  
			renderButton.setBackground(UIManager.getColor("Button.background"));  
		}  
		else if (isSelected)  
		{  
			renderButton.setForeground(table.getSelectionForeground());  
			renderButton.setBackground(table.getSelectionBackground());  
		}  
		else  
		{  
			renderButton.setForeground(table.getForeground());  
			renderButton.setBackground(UIManager.getColor("Button.background"));  
		}  

		renderButton.setText( (value == null) ? "" : value.toString() );  
		return renderButton;  
	}  

	public Component getTableCellEditorComponent(  
			JTable table, Object value, boolean isSelected, int row, int column)  
	{  
		text = (value == null) ? "" : value.toString();  
		editButton.setText( text );  
		return editButton;  
	}  

	public Object getCellEditorValue()  
	{  
		return text;  
	}  

	public void actionPerformed(ActionEvent e)  
	{  
		String s = table.getValueAt(table.getSelectedRow(), 1).toString();
		openBrowser(s.substring(16, s.length()-23));
	}  
	
	/**
	 * Abre o navegador no perfil desejado
	 * @param string
	 */
	private void openBrowser(String url) {
		Desktop desktop = Desktop.getDesktop();
		URI uri = null;
		try {
			uri = new URI(url);
			desktop.browse(uri);
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (URISyntaxException use) {
			use.printStackTrace();
		}
	
	}
}  