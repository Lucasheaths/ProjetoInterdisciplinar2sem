package Solucao;

import javax.swing.JOptionPane;

public abstract class Controle {
	
	double codVal [][] = new double [8][200];		
	String nomeProd [] = new String [200];			
	int indiceVazio=0, op;	
	
	public Controle(){}
	
	public void setNomeProd(String[] nomeProd) {
		this.nomeProd = nomeProd;
	}
	
	public String[] getNomeProd() {
		return nomeProd;
	}
	
	
	public void setCodVal(double[][] codVal) {
		this.codVal = codVal;
	}
	
	public double[][] getCodVal() {
		return codVal;
	}
	
	
	public void setIndiceVazio(int indiceVazio) {
		this.indiceVazio = indiceVazio;
	}
	
	public int getIndiceVazio() {
		return indiceVazio;
	}
}
