package xadrez;

import jogotabuleiro.Posicao;
import jogotabuleiro.Tabuleiro;
import pecas.xadrez.Rei;
import pecas.xadrez.Torre;

public class PartidaXadrez {

	private Tabuleiro tabuleiro;
	
	public PartidaXadrez() {
		tabuleiro = new Tabuleiro(8, 8);
		setupInicial();
	}
	
	public PecaXadrez[][] getPecas(){
		PecaXadrez[][] mat = new PecaXadrez[tabuleiro.getLinhas()][tabuleiro.getColunas()];
		for(int i=0 ; i<tabuleiro.getLinhas() ; i++) {
			for (int j=0 ; j<tabuleiro.getColunas() ; j++ ) {
				mat[i][j] = (PecaXadrez) tabuleiro.peca(i, j);
			}
		}
		return mat;
	}
	
	private void setupInicial() {
		tabuleiro.placePeca(new Torre(tabuleiro, Color.WHITE), new Posicao(2, 1));
		tabuleiro.placePeca(new Rei(tabuleiro, Color.BLACK), new Posicao(0, 4));
		tabuleiro.placePeca(new Rei(tabuleiro, Color.WHITE), new Posicao(7, 4));
	}
}
