package pecas.xadrez;

import jogotabuleiro.Posicao;
import jogotabuleiro.Tabuleiro;
import xadrez.Color;
import xadrez.PecaXadrez;

public class Torre extends PecaXadrez {

	public Torre(Tabuleiro tabuleiro, Color color) {
		super(tabuleiro, color);
	}

	@Override
	public String toString() {
		return "T";
	}
	@Override
	public boolean[][] possibilidadesMovimentos() {
		boolean[][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
		
		Posicao p = new Posicao(0,0);
		//cima
		p.definirValor(posicao.getLinha() - 1, posicao.getColuna());
		while (getTabuleiro().posicaoExiste(p)&& !getTabuleiro().temUmaPeca(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setLinha(p.getLinha()-1);
		}
		if(getTabuleiro().posicaoExiste(p)&& temAlgumaPecaInimiga(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			
		}
		
		//esquerda
		p.definirValor(posicao.getLinha(), posicao.getColuna()- 1);
		while (getTabuleiro().posicaoExiste(p)&& !getTabuleiro().temUmaPeca(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setColuna(p.getColuna()-1);
		}
		if(getTabuleiro().posicaoExiste(p)&& temAlgumaPecaInimiga(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			
		}
		
		//direita
				p.definirValor(posicao.getLinha(), posicao.getColuna() + 1);
				while (getTabuleiro().posicaoExiste(p)&& !getTabuleiro().temUmaPeca(p)) {
					mat[p.getLinha()][p.getColuna()] = true;
					p.setColuna(p.getColuna()+1);
				}
				if(getTabuleiro().posicaoExiste(p)&& temAlgumaPecaInimiga(p)) {
					mat[p.getLinha()][p.getColuna()] = true;
					
				}
				
				//baixo
				p.definirValor(posicao.getLinha()+1 , posicao.getColuna());
				while (getTabuleiro().posicaoExiste(p)&& !getTabuleiro().temUmaPeca(p)) {
					mat[p.getLinha()][p.getColuna()] = true;
					p.setLinha(p.getLinha()+1);
				}
				if(getTabuleiro().posicaoExiste(p)&& temAlgumaPecaInimiga(p)) {
					mat[p.getLinha()][p.getColuna()] = true;
					
				}
				
		return mat;
	}
}
