package main.java.com.votacao;

public class Resultados {
    private int totalVotos;
    private Candidato vencedor;

    public Resultados(int totalVotos, Candidato vencedor) {
        this.totalVotos = totalVotos;
        this.vencedor = vencedor;
    }

    public int getTotalVotos() {
        return totalVotos;
    }

    public Candidato getVencedor() {
        return vencedor;
    }
}
