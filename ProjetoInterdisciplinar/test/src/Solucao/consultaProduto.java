package Solucao;

import javax.swing.JOptionPane;

public class consultaProduto extends Controle{
	
	public static void consultaProduto (String nomeProd[], double codVal[][], int indiceVazio){
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

}
