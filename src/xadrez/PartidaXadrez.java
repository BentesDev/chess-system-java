package xadrez;


import jogotabuleiro.Peca;
import jogotabuleiro.Posicao;
import jogotabuleiro.Tabuleiro;
import pecas.xadrez.Rei;
import pecas.xadrez.Torre;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PartidaXadrez {
     
	private int turno;
	private Color jogadorAtual;
	private Tabuleiro tabuleiro;
	private boolean check;
	
	private List<Peca> pecasNoTabuleiro = new ArrayList<>();
 	private List<Peca> pecasCapturadas = new ArrayList<>();
	
	public PartidaXadrez() {
		tabuleiro = new Tabuleiro(8, 8);
		turno = 1;
		jogadorAtual = Color.WHITE;
		setupInicial();
	}
	
	public int getTurno() {
 		return turno;
 	}
 	
 	public Color getJogadorAtual() {
 		return jogadorAtual;
 	}
 	
 	public boolean getCheck() {
 		return check;
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
		
		if (testCheck(jogadorAtual)) {
 			desfazerMovimento(original, proxima, pecaCapturada);
 			throw new XadrezException("Voce não consegue deixar voce mesmo em check");
 		}
 		
 		check = (testCheck(opponent(jogadorAtual))) ? true : false;
 		
		nextTurno();
		return (PecaXadrez)pecaCapturada;
	}
	
	private Peca fazerMovimento(Posicao original, Posicao proxima) {
		Peca p = tabuleiro.removePeca(original);
		Peca pecaCapturada = tabuleiro.removePeca(proxima);
		tabuleiro.placePeca(p, proxima);
		
		if (pecaCapturada != null) {
 			pecasNoTabuleiro.remove(pecaCapturada);
 			pecasCapturadas.add(pecaCapturada);
 		}
		return pecaCapturada;
	}
	
	private void desfazerMovimento(Posicao original, Posicao proxima, Peca pecaCapturada) {
 		Peca p = tabuleiro.removePeca(proxima);
 		tabuleiro.placePeca(p, original);
 		
 		if (pecaCapturada != null) {
 			tabuleiro.placePeca(pecaCapturada, proxima);
 			pecasCapturadas.remove(pecaCapturada);
 			pecasNoTabuleiro.add(pecaCapturada);
 		}
 	}
	
	private void validarOriginalPosicao(Posicao posicao) {
		if(!tabuleiro.temUmaPeca(posicao)) {
			throw new XadrezException("Não tem peça no ponto original");
		}
		if (jogadorAtual != ((PecaXadrez)tabuleiro.peca(posicao)).getColor()) {
 			throw new XadrezException("A peça escolhida não é sua, otario XD");
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
	
	private void nextTurno() {
 		turno++;
 		jogadorAtual = (jogadorAtual == Color.WHITE) ? Color.BLACK : Color.WHITE;
 	}
	private Color opponent(Color color) {
 		return (color == Color.WHITE) ? Color.BLACK : Color.WHITE;
 	}
 	
 	private PecaXadrez rei(Color color) {
 		List<Peca> list = pecasNoTabuleiro.stream().filter(x -> ((PecaXadrez)x).getColor() == color).collect(Collectors.toList());
 		for (Peca p : list) {
 			if (p instanceof Rei) {
 				return (PecaXadrez)p;
 			}
 		}
 		throw new IllegalStateException("não tem o " + color + " rei no tabuleiro");
 	}
 	
 	private boolean testCheck(Color color) {
 		Posicao reiPosicao = rei(color).getPosicaoXadrez().toPosicao();
 		List<Peca> pecasOponente = pecasNoTabuleiro.stream().filter(x -> ((PecaXadrez)x).getColor() == opponent(color)).collect(Collectors.toList());
 		for (Peca p : pecasOponente) {
 			boolean[][] mat = p.possibilidadesMovimentos();
 			if (mat[reiPosicao.getLinha()][reiPosicao.getColuna()]) {
 				return true;
 			}
 		}
 		return false;
 	}
	
	private void lugarNovaPeca(char coluna, int linha, PecaXadrez peca ) {
		tabuleiro.placePeca(peca, new PosicaoXadrez(coluna, linha).toPosicao());
		pecasNoTabuleiro.add(peca);
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
