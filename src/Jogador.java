
public class Jogador {
    private String _nome;
    private int _pontos;
    private String letra;

    public Jogador(String nome, String letra) {
        setNome(nome);
        setLetra(letra);

        _pontos = 0;
    }

    public String getNome() {

        return _nome;
    }

    public void setNome(String nome) {

        this._nome = nome;
    }

    public int getPontos() {
        return _pontos;
    }

    public void setPontos(int _pontos) {

        this._pontos = _pontos;
    }

    public String getLetra() {

        return letra;
    }

    public void setLetra(String letra) {

        this.letra = letra;
    }

    public String toString() {

        return "Jogador" + this.getNome() + " com " + this.getPontos() + " pontos.";
    }
}