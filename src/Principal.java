import java.util.Scanner;

public class Principal {
    public static Scanner _scanner;

    public static void main(String[] args) {
        _scanner = new Scanner(System.in);

        System.out.println("Nome do Jogador 1:");
        Jogador jogador1 = new Jogador(_scanner.nextLine(), "X");
        System.out.println("");

        System.out.println("Nome do Jogador 2:");
        Jogador jogador2 = new Jogador(_scanner.nextLine(), "O");
        System.out.println("");

        int dimensoes = solicitaDimensoes();
        JogoDaVelha jogo = new JogoDaVelha(dimensoes);

        System.out.println("Come�a o Jogo:" + dimensoes + " X " + dimensoes);
        jogo.desenhaJogo();

        boolean fimDeJogo = false;

        do {
            if(executaPartida(jogo, jogador1, jogador2)) {
                System.out.println("Digite SAIR, para finalizar o jogo. Se quiser continuar pressione ENTER");
                String comando = _scanner.nextLine();

                if(comando != null && comando.trim().equalsIgnoreCase("SAIR")){
                    fimDeJogo = true;
                }

                jogo = new JogoDaVelha(dimensoes);
            }
        }while(!fimDeJogo);

        System.out.println("");
        System.out.println("###### FIM DE JOGO ######");
        System.out.println("#" + jogador1.getNome() + ": " + jogador1.getPontos());
        System.out.println("#" + jogador2.getNome() + ": " + jogador2.getPontos());
        System.out.println("#########################");
    }

    private static boolean executaPartida(JogoDaVelha jogo,Jogador jogador1, Jogador jogador2) {
        Jogador jogadorDaVez = jogador1;
        boolean temGanhador = false;

        do {
            boolean jogadaRealizada = solicitaJogada(jogo, jogadorDaVez);

            if(jogadaRealizada) {
                jogo.desenhaJogo();

                temGanhador = jogo.verificaGanhador();

                if(temGanhador) {
                    jogadorDaVez.setPontos(jogadorDaVez.getPontos() + 1);
                    System.out.println("O vencedor da partida � " + jogadorDaVez.getNome());
                    System.out.println("");
                    System.out.println("######### SCORE #########");
                    System.out.println("#" + jogador1.getNome() + ": " + jogador1.getPontos());
                    System.out.println("#" + jogador2.getNome() + ": " + jogador2.getPontos());
                    System.out.println("#########################");
                }
                else {
                    jogadorDaVez = (jogadorDaVez == jogador1) ? jogador2 : jogador1;
                }
            }

        }while(!temGanhador);

        return temGanhador;
    }

    private static int solicitaDimensoes() {
        int dimensoes = 0;

        do {
            try {
                System.out.println("Dimens�es do Jogo?");
                dimensoes = Integer.parseInt(_scanner.nextLine());
            } catch (Exception e) {
                System.out.println("Informe um n�mero v�lido!");
            }
        }while(dimensoes == 0);

        return dimensoes;
    }

    private static int solicitaLinha() {
        int linha = 0;

        do {
            try {
                System.out.println("Informe a linha: ");
                linha = Integer.parseInt(_scanner.nextLine());
            } catch (Exception e) {
                System.out.println("Informe uma linha v�lida!");
            }
        }while(linha == 0);

        return linha;
    }

    private static int solicitaColuna() {
        int coluna = 0;

        do {
            try {
                System.out.println("Informe a coluna: ");
                coluna = Integer.parseInt(_scanner.nextLine());
            } catch (Exception e) {
                System.out.println("Informe uma coluna v�lida!");
            }
        }while(coluna == 0);

        return coluna;
    }

    private static boolean solicitaJogada(JogoDaVelha jogo, Jogador jogador) {
        boolean jogadaRealizada = false;

        do {
            System.out.println("Vez do jogador " + jogador.getNome());
            int linha = solicitaLinha();
            int coluna = solicitaColuna();

            try {
                jogadaRealizada = jogo.realizaJogada(linha, coluna, jogador.getLetra());
            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }while(!jogadaRealizada);

        return jogadaRealizada;
    }
}
