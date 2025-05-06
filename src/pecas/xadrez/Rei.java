package pecas.xadrez;

import jogotabuleiro.Posicao;
import xadrez.PartidaXadrez;
import jogotabuleiro.Tabuleiro;
import xadrez.Color;
import xadrez.PecaXadrez;


public class Rei extends PecaXadrez{
	
	private PartidaXadrez partidaXadrez;

	public Rei(Tabuleiro tabuleiro, Color color, PartidaXadrez partidaXadrez) {
		super(tabuleiro, color);
		this.partidaXadrez = partidaXadrez;
	}
	
	@Override
	public String toString() {
		return "R";
	}
	
	private boolean podeMover(Posicao posicao) {
		PecaXadrez p = (PecaXadrez)getTabuleiro().peca(posicao);
		return p == null || p.getColor() != getColor();
	}
	
	private boolean testTorreRoque(Posicao posicao) {
 		PecaXadrez p = (PecaXadrez)getTabuleiro().peca(posicao);
 		return p != null && p instanceof Torre && p.getColor() == getColor() && p.getContagemMovimento() == 0;
 	}

	@Override
	public boolean[][] possibilidadesMovimentos() {
		boolean[][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
		
		Posicao p = new Posicao(0, 0);
 		
 		// cima
 		p.definirValor(posicao.getLinha() - 1, posicao.getColuna());
 		if (getTabuleiro().posicaoExiste(p) && podeMover(p)) {
 			mat[p.getLinha()][p.getColuna()] = true;
 		}
 
 		// baixo
 		p.definirValor(posicao.getLinha() + 1, posicao.getColuna());
 		if (getTabuleiro().posicaoExiste(p) && podeMover(p)) {
 			mat[p.getLinha()][p.getColuna()] = true;
 		}
 
 		// esquerda
 		p.definirValor(posicao.getLinha(), posicao.getColuna() - 1);
 		if (getTabuleiro().posicaoExiste(p) && podeMover(p)) {
 			mat[p.getLinha()][p.getColuna()] = true;
 		}
 
 		// direita
 		p.definirValor(posicao.getLinha(), posicao.getColuna() + 1);
 		if (getTabuleiro().posicaoExiste(p) && podeMover(p)) {
 			mat[p.getLinha()][p.getColuna()] = true;
 		}
 
 		// nw
 		p.definirValor(posicao.getLinha() - 1, posicao.getColuna() - 1);
 		if (getTabuleiro().posicaoExiste(p) && podeMover(p)) {
 			mat[p.getLinha()][p.getColuna()] = true;
 		}
 
 		// ne
 		p.definirValor(posicao.getLinha() - 1, posicao.getColuna() + 1);
 		if (getTabuleiro().posicaoExiste(p) && podeMover(p)) {
 			mat[p.getLinha()][p.getColuna()] = true;
 		}
 
 		// sw
 		p.definirValor(posicao.getLinha() + 1, posicao.getColuna() - 1);
 		if (getTabuleiro().posicaoExiste(p) && podeMover(p)) {
 			mat[p.getLinha()][p.getColuna()] = true;
 		}
 		
 		// se
 		p.definirValor(posicao.getLinha() + 1, posicao.getColuna() + 1);
 		if (getTabuleiro().posicaoExiste(p) && podeMover(p)) {
 			mat[p.getLinha()][p.getColuna()] = true;
 		}
 	// Movimento especial roque
 	 		if (getContagemMovimento() == 0 && !partidaXadrez.getCheck()) {
 	 			// movimento especial torre lado do rei
 	 			Posicao posT1 = new Posicao(posicao.getLinha(), posicao.getColuna() + 3);
 	 			if (testTorreRoque(posT1)) {
 	 				Posicao p1 = new Posicao(posicao.getLinha(), posicao.getColuna() + 1);
 	 				Posicao p2 = new Posicao(posicao.getLinha(), posicao.getColuna() + 2);
 	 				if (getTabuleiro().peca(p1) == null && getTabuleiro().peca(p2) == null) {
 	 					mat[posicao.getLinha()][posicao.getLinha() + 2] = true;
 	 				}
 	 			}
 	 			
 	 		// movimento especial torre lado da rainha
 	 			Posicao posT2 = new Posicao(posicao.getLinha(), posicao.getColuna() - 4);
 	 			if (testTorreRoque(posT2)) {
 	 				Posicao p1 = new Posicao(posicao.getLinha(), posicao.getColuna() - 1);
 	 				Posicao p2 = new Posicao(posicao.getLinha(), posicao.getColuna() - 2);
 	 				Posicao p3 = new Posicao(posicao.getLinha(), posicao.getColuna() - 3);
 	 				if (getTabuleiro().peca(p1) == null && getTabuleiro().peca(p2) == null && getTabuleiro().peca(p3) == null) {
 	 					mat[posicao.getLinha()][posicao.getColuna() - 2] = true;
 	 				}
 	 			}
 	 		}
		
		
		
		return mat;
	}

}
