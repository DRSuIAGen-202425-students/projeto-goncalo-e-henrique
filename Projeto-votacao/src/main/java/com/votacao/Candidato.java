package main.java.com.votacao;

public class Candidato {
    private String nome;
    private String partido;
    private int numero;

    public Candidato(String nome, String partido, int numero) {
        this.nome = nome;
        this.partido = partido;
        this.numero = numero;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPartido() {
        return partido;
    }

    public void setPartido(String partido) {
        this.partido = partido;
    }

    public int getNumero() {
        return numero;
    }
}