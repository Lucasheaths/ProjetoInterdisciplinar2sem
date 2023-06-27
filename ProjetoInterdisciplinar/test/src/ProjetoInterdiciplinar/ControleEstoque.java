package ProjetoInterdiciplinar;

import javax.swing.JOptionPane;

public class ControleEstoque {

	public static void main(String[] args) {
		menu();											
		}												  
																															
	public static void menu(){							
		double codVal [][] = new double [8][200];		
		String nomeProd [] = new String [200];			
		int indiceVazio=0, op;							
														 
														 
		do{ 
			op = Integer.parseInt(JOptionPane.showInputDialog(
					"      Sistema de controle de estoque\n\n\n"+
					"            Menu principal \n\n" +
					"1 - Cadastrar produto \n"+ "" +
					"2 - Consulta de produtos (Informando código)\n" +
					"3 - Alterar preços de produto (custo/venda)\n"+
					"4 - Remover códigos cadastrados\n"+
					"5 - Sair do Sistema"));
	
			switch (op) {
			
			case 1:
				indiceVazio = cadastro(nomeProd, codVal, indiceVazio);
				break;
			
			case 2:
				consProdCod(nomeProd, codVal, indiceVazio);
				break;
			
			case 3:
				alteraValores(nomeProd, codVal, indiceVazio);
				break;
				
			case 4:
				remover(nomeProd, codVal, indiceVazio);
				break;
				
			case 5:
				JOptionPane.showMessageDialog(null, "Finalizando o sistema...");
			    break;  
				
			default:
				JOptionPane.showMessageDialog(null, "Código inválido");
				break;
			}
			
		}while(op!=5);
	}
	
	public static int remover (String nomeProd[], double codVal[][], int indiceVazio){
		
		int achou;
		char resp = 's';
		
		double codProcura = Double.parseDouble(JOptionPane.showInputDialog("Digite o código do produto que você deseja apagar: "));
		achou = procCod (nomeProd, codVal, indiceVazio, codProcura );
		
		JOptionPane.showMessageDialog(null,"As informações foram excluídas.");   

		achou = 0;
		codVal[1][achou]=0;
		nomeProd[achou]="";
		codVal[5][achou]=0;
		codVal[6][achou]=0;
		return indiceVazio;
	}
	
	private static int cadastro (String nomeProd[], double codVal[][], int indiceVazio) {
		char resp='s', confirmaCad;
		int indiceCad, indiceNomeCad;
		double prCusto, valVenda, codCad;
		String nomeCad;
		
		do {
			if (indiceVazio<nomeProd.length) {
				nomeCad = JOptionPane.showInputDialog("Digite o nome do produto: ");
				indiceCad=procNome(nomeProd, codVal, indiceVazio, nomeCad); 
				if (indiceCad != -1) { 
					JOptionPane.showMessageDialog(null, "Produto já cadastrado com o código: "+codVal[1][indiceCad]); 
				}
				else {
					codCad = Double.parseDouble(JOptionPane.showInputDialog("Digite o código do produto"));
					indiceNomeCad = procCod(nomeProd, codVal, indiceVazio, codCad); 
					if (indiceNomeCad != -1) {
						JOptionPane.showMessageDialog(null, "Código já cadastrado para o produto: "+nomeProd[indiceNomeCad]);
					}
					else {
						prCusto = Double.parseDouble(JOptionPane.showInputDialog("Digite o preço de custo: "));
						valVenda = Double.parseDouble(JOptionPane.showInputDialog("Digite o valor de venda: "));
						
						confirmaCad = (JOptionPane.showInputDialog(
								"           Confirmação de cadastro"+"\n\n"+
								"Código: "+codCad+"\n"+
								"Nome do produto: "+nomeCad+"\n"+
								"Preço de custo: R$"+prCusto+"\n"+
								"Valor de venda: R$"+valVenda+"\n\n"+
								"Confirma entrada dos dados (S/N)?")).charAt(0);
						
						if ((confirmaCad=='s')||(confirmaCad=='S')){
						
						execCad(nomeProd, codVal, indiceVazio, codCad, nomeCad, prCusto, valVenda);
						indiceVazio=(indiceVazio+1);
						}
					}
				}
			
				resp = (JOptionPane.showInputDialog("Deseja cadastrar outro produto (S/N)?")).charAt(0);
			}
			else {
				JOptionPane.showMessageDialog(null, "Limite de cadastros alcançado");
				resp = 'n';
			}
		} while ((resp=='s') || (resp=='S')); 
		return indiceVazio;
	}	
	
	private static int procCod (String nomeProd[], double codVal[][], int indiceVazio, double buscaCod ) {
		int i=0;
		
		while ((i<indiceVazio)&&(buscaCod!=codVal[1][i])) {
			i++;
		}
		if (buscaCod!=codVal[1][i]) {
			return -1;
		}
			else {
				return i;
		}
	}
	
	
	private static int procNome(String nomeProd[], double codVal[][], int indiceVazio, String buscaNome) {
		int i=0;
		
		while ((i<indiceVazio)&&(!(buscaNome.equalsIgnoreCase(nomeProd[i])))) {
			i++;
		}
		if (!(buscaNome.equalsIgnoreCase(nomeProd[i]))) {
			return -1;
		}
			else {
				return i;
			}	
		}	

	
	private static void execCad (String nomeProd[], double codVal[][], int indice, double cod, String nome, 
								double prCusto, double valVenda) {
		double lucrUn;
	
		lucrUn = valVenda - prCusto;
		nomeProd[indice] = nome;
		codVal[1][indice] = cod;
		codVal[5][indice] = prCusto;
		codVal[6][indice] = valVenda;
		codVal[7][indice] = lucrUn;
	}		
	
	
	private static void execCadEntr(String nomeProd[], double codVal[][], int indProd, double codEntr, double qtdade){
		
		codVal[2][indProd] = (codVal[2][indProd]+qtdade);
		codVal[4][indProd] = (codVal[4][indProd]+qtdade);
	}
	
	private static double verEstoque (double codVal[][], int achouProd) {
		double estoque;
		
		estoque = codVal[4][achouProd];
		
		return estoque ;
	}


	private static void execCadVenda(double codVal[][], int indProd, double qtdade){
		
		codVal[3][indProd] = (codVal[3][indProd]+qtdade);
		codVal[4][indProd] = (codVal[4][indProd]-qtdade);
	}	


	private static void consProdCod (String nomeProd[], double codVal[][], int indiceVazio){
		double codProcura;
		int achou;
		char resp = 's';
		
		do{
		codProcura = Double.parseDouble(JOptionPane.showInputDialog("Digite o código do produto: "));
		achou = procCod (nomeProd, codVal, indiceVazio, codProcura );
		if (achou == -1){
			JOptionPane.showMessageDialog(null, "Não foi encontrado produto com o código informado: "+codProcura);
		}
		else{
			JOptionPane.showMessageDialog(null, 
									"Código: "+codVal[1][achou]+"\n"+
									"Nome do produto: "+nomeProd[achou]+"\n"+
									"Preço de custo: R$"+codVal[5][achou]+"\n"+
									"Valor de venda: R$"+codVal[6][achou]+"\n");
		}
		resp = (JOptionPane.showInputDialog("Deseja pesquisar outro produto (S/N)?")).charAt(0);
		
		}while ((resp =='s') || (resp =='S')); 
	}

	private static int verMov(String nomeProd[], double codVal[][], int indiceVazio, int tipoMov, int indPesq){ //tipoMov é 2 compras, 3 vendas, 4 estoque 
		
		
		if (codVal[tipoMov][indPesq]>0){
			return indPesq;
		}
		else{
			return -1;
		}
	}

	private static void alteraValores(String nomeProd[], double codVal[][], int indiceVazio){
		double prCusto=0,valVenda=0, codProd;
		int indice;
		char confirma='n';
		
		codProd = Double.parseDouble(JOptionPane.showInputDialog("Digite o código do produto que deseja alterar: "));
		indice = procCod(nomeProd, codVal, indiceVazio, codProd);
		if (indice == -1){
			JOptionPane.showMessageDialog(null, "O código informado: "+codProd+" não foi localizado");
		}
			else{
				confirma = (JOptionPane.showInputDialog( 
						"          Dados atuais do produto: \n\n"+
						"Código: "+codProd+"\n"+
						"Nome do produto: "+nomeProd[indice]+"\n"+
						"Preço de custo: R$"+codVal[5][indice]+"\n"+
						"Valor de venda: R$"+codVal[6][indice]+"\n\n"+
						"Deseja alterar este produto (S/N)?")).charAt(0);
				if ((confirma == 's')||(confirma == 'S')){
					prCusto = Double.parseDouble(JOptionPane.showInputDialog("Informe o novo preço de custo: R$"));
					valVenda = Double.parseDouble(JOptionPane.showInputDialog("Informe o novo valor de venda: R$"));
									
					confirma = (JOptionPane.showInputDialog( 
							"          Novos dados do produto: \n\n"+
							"Código: "+codProd+"\n"+
							"Nome do produto: "+nomeProd[indice]+"\n"+
							"Preço de custo: R$"+prCusto+"\n"+
							"Valor de venda: R$"+valVenda+"\n\n"+
							"Confirma alterações (S/N)?")).charAt(0);
					if ((confirma == 's')||(confirma == 'S')){
						codVal[5][indice] = prCusto;
						codVal[6][indice] = valVenda;
						JOptionPane.showMessageDialog(null, "" +
								"                                                             AVISO!!! \n\n"+
								"  Ao gerar relatórios, os valores são baseados nos valores atuais do produto.\n"+
								" Ou seja, os valores de venda, estoque, faturamento, etc. serão baseados nos\n"+
								"                                      valores que você acabou de confirmar.");
					}
				}
				
			}
	}
	
}