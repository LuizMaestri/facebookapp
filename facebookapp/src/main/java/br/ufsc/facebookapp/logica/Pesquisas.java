package br.ufsc.facebookapp.logica;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;

import br.ufsc.facebookapp.excecoes.NomeErrado;

import facebook4j.IdNameEntity;
import facebook4j.ResponseList;
import facebook4j.User;
import facebook4j.User.Education;
import facebook4j.User.Work;

public class Pesquisas {
	
	private ArrayList <User> refino;
	private String[] args;
	
	public Pesquisas(String[] args){
		refino = new ArrayList <User>();
		setArgs(args);
	}
	
	/**
	 * set atributo args
	 * @param String[]
	 */
	private void setArgs(String[] arg) {
		args = arg;
	}

	public ArrayList<User> getRefino() {
		return refino;
	}

	/**
	 * adiciona 40 resultados ao atributo refino
	 * @param ResponseList<User>
	 * @throws NomeErrado
	 */
	public void pesquisar(ResponseList<User> pesquisa) throws NomeErrado{
		if (pesquisa.equals(null)) throw new NomeErrado();
		Iterator<User> iter = pesquisa.iterator();
		while(iter.hasNext())
		{
			if(refino.size()!= 40)
			{
				User teste = iter.next();
				if(verificarItens(teste, args))
					refino.add(teste);
			}
			else
				break;
		}
	}

	/**
	 * verifica a compatibilidade de todos os critérios de refino
	 * obs: qualquer null será tido como verdade
	 * @param User
	 * @param String
	 * @return boolean
	 */
	private boolean verificarItens(User next, String[] args) {
		if(sexo(next.getGender(), args[0]) && idade(next.getBirthday(),args[1]) && 
			formacao(next.getEducation(),args[2]) && relacionamento(next.getRelationshipStatus(), args[3]) &&
			trabalho(next.getWork(), args[4])&& cidadeNatal(next.getHometown(),args[5]) && 
			cidadeAtual(next.getLocation(),args[6]))		
				return true;
		return false;
	}

	/**
	 * verifica a compatibilidade do critério cidade atual
	 * obs: qualquer null será tido como verdade
	 * @param IdNameEntity contendo a cidade atual
	 * @param String contendo o critério
	 * @return boolean
	 */
	private boolean cidadeAtual(IdNameEntity location, String string) {
		if(string==null||string.equals("Cidade Atual"))return true;
		if(location==null) return true;
		if(location.getName().equals(string))return true;
		return false;
	}

	/**
	 * verifica a compatibilidade do critério cidade natal
	 * obs: qualquer null será tido como verdade
	 * @param IdNameEntity contendo a cidade natal
	 * @param String contendo o critério
	 * @return boolean
	 */
	private boolean cidadeNatal(IdNameEntity hometown, String string) {
		if(string==null||string.equals("Cidade Natal")) return true;
		if(hometown==null) return true;
		if(hometown.getName().equals(string))return true;
		return false;
	}

	/**
	 * verifica a compatibilidade do critério trabalho
	 * obs: qualquer null será tido como verdade
	 * @param List<Work> contendo o todas as empresas onde já trabalho
	 * @param String contendo o critério
	 * @return boolean
	 */
	private boolean trabalho(List<Work> work, String string) {
		if(work==null) return true;
		if(string==null||string.equals("Trabalho")) return true;
		Iterator<Work> iter = work.iterator();
		while(iter.hasNext())
			if(iter.next().getEmployer().getName().equals(string))
				return true;
		return false;
	}

	/**
	 * verifica a compatibilidade do critério formação
	 * obs: qualquer null será tido como verdade
	 * @param List<Education> contendo o todas as instituições de ensino
	 * @param String contendo o critério
	 * @return boolean
	 */
	private boolean formacao(List<Education> education, String string) {
		if(education==null) return true;
		if(string.equals(null)||string.equals("Formação")) return true;
		Iterator<Education> iter = education.iterator();
		while(iter.hasNext())
			if(iter.next().getSchool().getName().equals(string))
				return true;
		return false;
	}
	
	/**
	 * verifica a compatibilidade do critério relacionamento
	 * obs: qualquer null será tido como verdade
	 * @param string contendo o Status de relacionamento
	 * @param String contendo o critério
	 * @return boolean
	 */
	private boolean relacionamento(String relationshipStatus, String string) {
		if(relationshipStatus==null) return true;
		string = type(string);
		if (string.equals(null)) return true;
		if(relationshipStatus.equals(string))return true;
		return false;
	}
	
	/**
	 * traduz do português para inglês por fins de comparação
	 * @param String contendo o critério relação
	 * @return String
	 */
	private String type(String string) {
		switch(string)
		{
		case "Solteiro" : return "single";
		case "Noivo" : return "engaged";
		case "Relacionamento Aberto" : return "in a open relationship";
		case "Namorando" : return "in a relationship";
		case "Separado" : return "separated";
		case "Divorciado": return "divorced";
		default : return null;
		}
	}

	/**
	 * verifica a compatibilidade do critério idade
	 * obs: qualquer null será tido como verdade
	 * @param string contendo o Aniversário
	 * @param String contendo o critério
	 * @return boolean
	 */
	private boolean idade(String birthday, String string) {
		if(birthday==null) return true;
		if(string==null||string.equals("Idade")) return true;
		int min = Integer.parseInt(string.substring(0, 1));
		int max = 0;
		GregorianCalendar referencia = new GregorianCalendar();
		int ano = referencia.get(Calendar.YEAR);
		int aux;
		if (string.length()>3) max = Integer.parseInt(string.substring(3, 4));
		if (existeAno(birthday)){
			aux = getAno(birthday);
			if(max !=0) if(aux - ano <= min && aux - ano >= max)return true;
			else if(aux - ano <= min)return true;
		}
		else return true;
		return false;
	}

	/**
	 * verifica a existencia ou não do ano na data
	 * @param String contendo a data de aniversário
	 * @return boolean
	 */
	private boolean existeAno(String birthday) {
		int tam = birthday.length();
		if (tam<6||tam == 4)
			return true;
		return false;
	}
	
	/**
	 * retorna o ano de aniversário
	 * @param String contendo a data de aniversário
	 * @return int
	 */
	private int getAno(String birthday) {
		int tam = birthday.length();
		if(tam==10)
			return Integer.parseInt(birthday.substring(6));
		return Integer.parseInt(birthday.substring(3));
		
	}
	
	/**
	 * verifica a compatibilidade do critério sexo
	 * obs: qualquer null será tido como verdade
	 * @param string contendo o sexo
	 * @param String xontendo o critério
	 * @return boolean
	 */
	private boolean sexo(String gender, String string) {
		if(gender==null)return true;
		switch(gender)
		{
		case "sexo" : gender = null;
			break;
		case "Feminino" : gender = "female";
			break;
		case "Masculino" : gender = "male";
			break;
		}
		if(string.equals(gender)||string.equals(null))
			return true;
		return false;
	}

}
