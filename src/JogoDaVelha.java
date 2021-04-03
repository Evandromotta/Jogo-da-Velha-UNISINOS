import javax.management.RuntimeErrorException;

public class JogoDaVelha {
    private String[][] _tabuleiro;

    public JogoDaVelha(int dimensao){
        _tabuleiro = new String[dimensao][dimensao];
    }

    public boolean realizaJogada(int linha, int coluna, String jogada) throws RuntimeException{
        if(linha < 1 || linha > _tabuleiro.length){
            throw new RuntimeErrorException(null, "Linha n�o existe.");
        }
        else if(coluna < 1 || coluna > _tabuleiro[linha-1].length) {
            throw new RuntimeErrorException(null, "Coluna n�o existe.");
        }

        if(jogada == null || jogada.trim().isEmpty()) {
            throw new RuntimeErrorException(null, "Especifique a jogada.");
        }
        else if(!(jogada.toUpperCase().equals("X") || jogada.toUpperCase().equals("O"))) {
            throw new RuntimeErrorException(null, "Informe X ou O para a jogada.");
        }

        String jogadaAnterior = _tabuleiro[linha-1][coluna-1];

        if(jogadaAnterior == null || jogadaAnterior.trim().isEmpty()) {
            _tabuleiro[linha-1][coluna-1] = jogada;

            return true;
        }

        return false;
    }

    public boolean verificaGanhador() {

        if(verificaLinhas() || verificaColunas() || verificaDiagonal() || verificaDiagonalInversa()) {
            return true;
        }

        return false;
    }

    private boolean verificaLinhas() {
        for(int i=0; i < _tabuleiro.length; i++) {
            String jogadaMestre = new String();
            int jogadasIguais = 0;

            for(int j=0; j < _tabuleiro[i].length; j++) {
                String jogadaDaCasa = _tabuleiro[i][j];

                if(jogadaDaCasa == null || jogadaDaCasa.trim().isEmpty()){
                    break;
                }
                else {
                    if(jogadaMestre == null || jogadaMestre.trim().isEmpty()){
                        jogadaMestre = jogadaDaCasa;
                    }

                    if(!jogadaDaCasa.equals(jogadaMestre)){
                        break;
                    }
                    else {
                        jogadasIguais++;
                    }
                }
            }

            if(jogadasIguais == _tabuleiro[i].length) {
                return true;
            }
        }

        return false;
    }

    private boolean verificaColunas() {
        for(int i=0; i < _tabuleiro.length; i++) {
            String jogadaMestre = new String();
            int jogadasIguais = 0;

            for(int j=0; j < _tabuleiro[i].length; j++) {
                String jogadaDaCasa = _tabuleiro[j][i];

                if(jogadaDaCasa == null || jogadaDaCasa.trim().isEmpty()){
                    break;
                }
                else {
                    if(jogadaMestre == null || jogadaMestre.trim().isEmpty()){
                        jogadaMestre = jogadaDaCasa;
                    }

                    if(!jogadaDaCasa.equals(jogadaMestre)){
                        break;
                    }
                    else {
                        jogadasIguais++;
                    }
                }
            }

            if(jogadasIguais == _tabuleiro.length) {
                return true;
            }
        }

        return false;
    }

    private boolean verificaDiagonal() {
        String jogadaMestre = new String();
        int jogadasIguais = 0;

        for(int i=0; i < _tabuleiro.length; i++) {
            String jogadaDaCasa = _tabuleiro[i][i];

            if(jogadaDaCasa == null || jogadaDaCasa.trim().isEmpty()){
                break;
            }
            else {
                if(jogadaMestre == null || jogadaMestre.trim().isEmpty()){
                    jogadaMestre = jogadaDaCasa;
                }

                if(!jogadaDaCasa.equals(jogadaMestre)){
                    break;
                }
                else {
                    jogadasIguais++;
                }
            }
        }

        if(jogadasIguais == _tabuleiro.length) {
            return true;
        }

        return false;
    }

    private boolean verificaDiagonalInversa() {
        String jogadaMestre = new String();
        int jogadasIguais = 0;

        for(int i=0; i < _tabuleiro.length; i++) {
            String jogadaDaCasa = _tabuleiro[i][_tabuleiro.length - 1 - i];

            if(jogadaDaCasa == null || jogadaDaCasa.trim().isEmpty()){
                break;
            }
            else {
                if(jogadaMestre == null || jogadaMestre.trim().isEmpty()){
                    jogadaMestre = jogadaDaCasa;
                }

                if(!jogadaDaCasa.equals(jogadaMestre)){
                    break;
                }
                else {
                    jogadasIguais++;
                }
            }
        }

        if(jogadasIguais == _tabuleiro.length) {
            return true;
        }

        return false;
    }

    public void desenhaJogo() {
        System.out.println("====================");

        for(int i=0; i < _tabuleiro.length; i++) {
            for(int j=0; j < _tabuleiro[i].length; j++) {
                String jogadaAnterior = _tabuleiro[i][j];

                System.out.print(((jogadaAnterior != null && !jogadaAnterior.trim().isEmpty()) ? jogadaAnterior : "-")  + "\t");
            }

            System.out.println("");
        }

        System.out.println("====================");
    }

    public String toString() {
        return verificaGanhador() ? "Finalizado" : "Em andamento";
    }
}
