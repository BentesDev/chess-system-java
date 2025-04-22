package xadrez;


import jogotabuleiro.Peca;
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
	
	public boolean[][] movimentosPossiveis(PosicaoXadrez originalPosicao){
		Posicao posicao = originalPosicao.toPosicao();
		validarOriginalPosicao(posicao);
		return tabuleiro.peca(posicao).possibilidadesMovimentos();
	}
	
	public PecaXadrez movimentoXadrez(PosicaoXadrez originalPosicao, PosicaoXadrez proximaPosicao) {
		Posicao original = originalPosicao.toPosicao();
		Posicao proxima = proximaPosicao.toPosicao();
		validarOriginalPosicao(original);
		validarProximaPosicao(original, proxima);
		Peca pecaCapturada = fazerMovimento(original, proxima);
		return (PecaXadrez)pecaCapturada;
	}
	
	private Peca fazerMovimento(Posicao original, Posicao proxima) {
		Peca p = tabuleiro.removePeca(original);
		Peca pecaCapturada = tabuleiro.removePeca(proxima);
		tabuleiro.placePeca(p, proxima);
		return pecaCapturada;
	}
	
	private void validarOriginalPosicao(Posicao posicao) {
		if(!tabuleiro.temUmaPeca(posicao)) {
			throw new XadrezException("Não tem peça no ponto original");
		}
		if(!tabuleiro.peca(posicao).temAlgumMovimentoPossivel()) {
			throw new XadrezException("Não tem movimentos possiveis para essa peça");
		}
	}
	
	private void validarProximaPosicao(Posicao original, Posicao proxima) {
		if(!tabuleiro.peca(original).possibilidadesMovimento(proxima)) {
		throw new XadrezException("A peça escolhida não pode se mover para o destino ");
	}
	}
	private void lugarNovaPeca(char coluna, int linha, PecaXadrez peca ) {
		tabuleiro.placePeca(peca, new PosicaoXadrez(coluna, linha).toPosicao());
	}
	private void setupInicial() {
		lugarNovaPeca('c', 1, new Torre(tabuleiro, Color.WHITE));
		lugarNovaPeca('c', 2, new Torre(tabuleiro, Color.WHITE));
		lugarNovaPeca('d', 2, new Torre(tabuleiro, Color.WHITE));
		lugarNovaPeca('e', 2, new Torre(tabuleiro, Color.WHITE));
		lugarNovaPeca('e', 1, new Torre(tabuleiro, Color.WHITE));
		lugarNovaPeca('d', 1, new Rei(tabuleiro, Color.WHITE));

		lugarNovaPeca('c', 7, new Torre(tabuleiro, Color.BLACK));
		lugarNovaPeca('c', 8, new Torre(tabuleiro, Color.BLACK));
		lugarNovaPeca('d', 7, new Torre(tabuleiro, Color.BLACK));
		lugarNovaPeca('e', 7, new Torre(tabuleiro, Color.BLACK));
		lugarNovaPeca('e', 8, new Torre(tabuleiro, Color.BLACK));
		lugarNovaPeca('d', 8, new Rei(tabuleiro, Color.BLACK));
	}
}
