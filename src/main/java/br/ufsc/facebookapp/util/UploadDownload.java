package br.ufsc.facebookapp.util;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class UploadDownload {

	private JFileChooser chooser = new JFileChooser();
	private File arquivo;

	public UploadDownload(){

	}

	/**
	 * Retorna o atributo do tipo File 'arquivo'
	 * @return File
	 */
	public File getArquivo() {
		return arquivo;
	}

	/**
	 * set do atributo arquivo
	 * @param File
	 */
	private void setArquivo(File arquivo) {
		this.arquivo = arquivo;
	}
	
	/**
	 * Abri uma janela para escolher a foto a ser postada
	 * @return String
	 */
	public String upFoto(){
		FileNameExtensionFilter filter = new FileNameExtensionFilter(
				"JPG & GIF Images", "jpg", "gif");
		chooser.setFileFilter(filter);
		if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			setArquivo(chooser.getSelectedFile());
			return arquivo.getPath();
		}
		return null;

	}
}
