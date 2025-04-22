package pecas.xadrez;

import jogotabuleiro.Posicao;
import jogotabuleiro.Tabuleiro;
import xadrez.Color;
import xadrez.PecaXadrez;


public class Rei extends PecaXadrez{

	public Rei(Tabuleiro tabuleiro, Color color) {
		super(tabuleiro, color);
	}
	
	@Override
	public String toString() {
		return "R";
	}
	
	private boolean podeMover(Posicao posicao) {
		PecaXadrez p = (PecaXadrez)getTabuleiro().peca(posicao);
		return p == null || p.getColor() != getColor();
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
		
		
		
		return mat;
	}

}
