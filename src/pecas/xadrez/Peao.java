package pecas.xadrez;

import jogotabuleiro.Posicao;
import jogotabuleiro.Tabuleiro;
import xadrez.Color;
import xadrez.PecaXadrez;

public class Peao extends PecaXadrez{

	public Peao(Tabuleiro tabuleiro, Color color) {
		super(tabuleiro, color);
		
	}

	@Override
	public boolean[][] possibilidadesMovimentos() {
		
	boolean[][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
	 		
	 		Posicao p = new Posicao(0, 0);
	 
	 		if (getColor() == Color.WHITE) {
	 			p.definirValor(posicao.getLinha() - 1, posicao.getColuna());
	 			if (getTabuleiro().posicaoExiste(p) && !getTabuleiro().temUmaPeca(p)) {
	 				mat[p.getLinha()][p.getColuna()] = true;
	 			}
	 			p.definirValor(posicao.getLinha() - 2, posicao.getColuna());
	 			Posicao p2 = new Posicao(posicao.getLinha() - 1, posicao.getColuna());
	 			if (getTabuleiro().posicaoExiste(p) && !getTabuleiro().temUmaPeca(p) && getTabuleiro().posicaoExiste(p2) && !getTabuleiro().temUmaPeca(p2) && getContagemMovimento() == 0) {
	 				mat[p.getLinha()][p.getColuna()] = true;
	 			}
	 			p.definirValor(posicao.getLinha() - 1, posicao.getColuna() - 1);
	 			if (getTabuleiro().posicaoExiste(p) && temAlgumaPecaInimiga(p)) {
	 				mat[p.getLinha()][p.getColuna()] = true;
	 			}			
	 			p.definirValor(posicao.getLinha() - 1, posicao.getColuna() + 1);
	 			if (getTabuleiro().posicaoExiste(p) && temAlgumaPecaInimiga(p)) {
	 				mat[p.getLinha()][p.getColuna()] = true;
	 			}			
	 		}
	 		else {
	 			p.definirValor(posicao.getLinha() + 1, posicao.getColuna());
	 			if (getTabuleiro().posicaoExiste(p) && !getTabuleiro().temUmaPeca(p)) {
	 				mat[p.getLinha()][p.getColuna()] = true;
	 			}
	 			p.definirValor(posicao.getLinha() + 2, posicao.getColuna());
	 			Posicao p2 = new Posicao(posicao.getLinha() + 1, posicao.getColuna());
	 			if (getTabuleiro().posicaoExiste(p) && !getTabuleiro().temUmaPeca(p) && getTabuleiro().posicaoExiste(p2) && !getTabuleiro().temUmaPeca(p2) && getContagemMovimento() == 0) {
	 				mat[p.getLinha()][p.getColuna()] = true;
	 			}
	 			p.definirValor(posicao.getLinha() + 1, posicao.getColuna() - 1);
	 			if (getTabuleiro().posicaoExiste(p) && temAlgumaPecaInimiga(p)) {
	 				mat[p.getLinha()][p.getColuna()] = true;
	 			}			
	 			p.definirValor(posicao.getLinha() + 1, posicao.getColuna() + 1);
	 			if (getTabuleiro().posicaoExiste(p) && temAlgumaPecaInimiga(p)) {
	 				mat[p.getLinha()][p.getColuna()] = true;
	 			}	
	 		}
	 		return mat;
	 	}
	
	@Override
	public String toString() {
		return "P";
	}
}
