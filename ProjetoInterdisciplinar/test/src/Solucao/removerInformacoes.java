package Solucao;

import javax.swing.JOptionPane;

public class removerInformacoes extends Controle{

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
