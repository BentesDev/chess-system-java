package application;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import xadrez.PartidaXadrez;
import xadrez.PecaXadrez;
import xadrez.PosicaoXadrez;
import xadrez.XadrezException;

public class Program {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		PartidaXadrez partidaXadrez = new PartidaXadrez();
		List<PecaXadrez> capturado = new ArrayList<>();
		
		while (!partidaXadrez.getCheckMate()) {
			try {
			UI.limparTela();
			UI.printPartida(partidaXadrez, capturado);
			System.out.println();
			System.out.print("Peça para mover:");
			PosicaoXadrez original = UI.lerPosicaoXadrez(sc);
			
			boolean[][] movimentosPossiveis = partidaXadrez.movimentosPossiveis(original);
            UI.limparTela();
            UI.printTabuleiro(partidaXadrez.getPecas(), movimentosPossiveis);
            
			System.out.println();
			System.out.print("Destino: ");
			PosicaoXadrez proxima = UI.lerPosicaoXadrez(sc);
			PecaXadrez pecaCapturada = partidaXadrez.movimentoXadrez(original, proxima);
			
			if (pecaCapturada != null) {
				capturado.add(pecaCapturada);
			}
	}
			
		
			catch (XadrezException e) {
				System.out.println(e.getMessage());
				sc.nextLine();
			}
			catch (InputMismatchException e) {
				System.out.println(e.getMessage());
				sc.nextLine();
			}

}
		UI.limparTela();
 		UI.printPartida(partidaXadrez, capturado);
}
}