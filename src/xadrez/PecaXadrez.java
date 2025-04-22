package xadrez;

import jogotabuleiro.Peca;
import jogotabuleiro.Posicao;
import jogotabuleiro.Tabuleiro;

public abstract class PecaXadrez extends Peca {

	private Color color;
	private int contagemMovimento;

	public PecaXadrez(Tabuleiro tabuleiro, Color color) {
		super(tabuleiro);
		this.color = color;
	}

	public Color getColor() {
		return color;
	}
	
	public int getContagemMovimento() {
		return contagemMovimento;
	}
	
	public void crescerContagemMovimento() {
		contagemMovimento++;
	}
	public void diminuirContagemMovimento() {
		contagemMovimento--;
	}
	
	public PosicaoXadrez getPosicaoXadrez() {
 		return PosicaoXadrez.fromPosicao(posicao);
 	}
	
	protected boolean temAlgumaPecaInimiga(Posicao posicao) {
		PecaXadrez p = (PecaXadrez)getTabuleiro().peca(posicao);
		
		return p != null && p.getColor() != color;
	}

	
	
	
}
