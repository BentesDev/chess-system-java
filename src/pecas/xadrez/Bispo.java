package pecas.xadrez;

import jogotabuleiro.Posicao;
import jogotabuleiro.Tabuleiro;
import xadrez.Color;
import xadrez.PecaXadrez;

public class Bispo extends PecaXadrez {

	public Bispo(Tabuleiro tabuleiro, Color color) {
		super(tabuleiro, color);
		
	}
	
	@Override
	public String toString() {
		return "B";
	}

	@Override
	public boolean[][] possibilidadesMovimentos() {
		boolean[][] mat = new
boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
 		
 		Posicao p = new Posicao(0, 0);
 		
 		// nw
 		p.definirValor(posicao.getLinha() - 1, posicao.getColuna() - 1);
 		while (getTabuleiro().posicaoExiste(p) && !getTabuleiro().temUmaPeca(p)) {
 			mat[p.getLinha()][p.getColuna()] = true;
 			p.definirValor(p.getLinha() - 1, p.getColuna() - 1);
 		}
 		if (getTabuleiro().posicaoExiste(p) && temAlgumaPecaInimiga(p)) {
 			mat[p.getLinha()][p.getColuna()] = true;
 		}
 		
 		// ne
 		p.definirValor(posicao.getLinha() - 1, posicao.getColuna() + 1);
 		while (getTabuleiro().posicaoExiste(p) && !getTabuleiro().temUmaPeca(p)) {
 			mat[p.getLinha()][p.getColuna()] = true;
 			p.definirValor(p.getLinha() - 1, p.getColuna() + 1);
 		}
 		if (getTabuleiro().posicaoExiste(p) && temAlgumaPecaInimiga(p)) {
 			mat[p.getLinha()][p.getColuna()] = true;
 		}
 		
 		// se
 		p.definirValor(posicao.getLinha() + 1, posicao.getColuna() + 1);
 		while (getTabuleiro().posicaoExiste(p) && !getTabuleiro().temUmaPeca(p)) {
 			mat[p.getLinha()][p.getColuna()] = true;
 			p.definirValor(p.getLinha() + 1, p.getColuna() + 1);
 		}
 		if (getTabuleiro().posicaoExiste(p) && temAlgumaPecaInimiga(p)) {
 			mat[p.getLinha()][p.getColuna()] = true;
 		}
 		
 		// sw
 		p.definirValor(posicao.getLinha() + 1, posicao.getColuna() - 1);
 		while (getTabuleiro().posicaoExiste(p) && !getTabuleiro().temUmaPeca(p)) {
 			mat[p.getLinha()][p.getColuna()] = true;
 			p.definirValor(p.getLinha() + 1, p.getColuna() - 1);
 		}
 		if (getTabuleiro().posicaoExiste(p) && temAlgumaPecaInimiga(p)) {
 			mat[p.getLinha()][p.getColuna()] = true;
 		}
 		
		return mat;
	}

	
}
