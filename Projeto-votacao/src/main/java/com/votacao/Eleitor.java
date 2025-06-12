package main.java.com.votacao;

public class Eleitor {
    private String nome;
    private String username;
    private String passwordHash;
    private boolean votou;

    public Eleitor(String nome, String username, String passwordHash) {
        this.nome = nome;
        this.username = username;
        this.passwordHash = passwordHash;
        this.votou = false;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public boolean isVotou() {
        return votou;
    }

    public void setVotou(boolean votou) {
        this.votou = votou;
    }
}
