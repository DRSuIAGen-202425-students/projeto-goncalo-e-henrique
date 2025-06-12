package main.java.com.votacao;

import java.util.*;

public class SistemaVotacao {
    private Map<String, String> credenciais = new HashMap<>();
    private Map<Integer, Candidato> candidatos = new HashMap<>();
    private Map<String, Eleitor> eleitores = new HashMap<>();
    private boolean votacaoAberta = false;
    private Map<Integer, Integer> votos = new HashMap<>();
    private String utilizadorAtual;

    public SistemaVotacao() {
        credenciais.put("admin", hashPassword("admin123"));
        credenciais.put("eleitor1", hashPassword("senha123"));
        eleitores.put("eleitor1", new Eleitor("Maria", "eleitor1", hashPassword("senha123")));
    }

    public boolean login(String username, String password) {
        if (credenciais.containsKey(username) && verificarPassword(password, credenciais.get(username))) {
            utilizadorAtual = username;
            return true;
        }
        return false;
    }

    public void logout() {
        utilizadorAtual = null;
    }

    public boolean adicionarCandidato(String nome, String partido, int numero) {
        candidatos.put(numero, new Candidato(nome, partido, numero));
        return true;
    }

    public boolean editarCandidato(int numero, String novoNome, String novoPartido) {
        Candidato c = candidatos.get(numero);
        if (c != null) {
            c.setNome(novoNome);
            c.setPartido(novoPartido);
            return true;
        }
        return false;
    }

    public boolean removerCandidato(int numero) {
        return candidatos.remove(numero) != null;
    }

    public boolean adicionarEleitor(String nome, String username, String password) {
        credenciais.put(username, hashPassword(password));
        eleitores.put(username, new Eleitor(nome, username, hashPassword(password)));
        return true;
    }

    public boolean editarEleitor(String username, String novoNome) {
        Eleitor e = eleitores.get(username);
        if (e != null) {
            e.setNome(novoNome);
            return true;
        }
        return false;
    }

    public boolean removerEleitor(String username) {
        credenciais.remove(username);
        return eleitores.remove(username) != null;
    }

    public boolean iniciarVotacao() {
        votacaoAberta = true;
        return true;
    }

    public boolean encerrarVotacao() {
        votacaoAberta = false;
        return true;
    }

    public boolean votar(int numeroCandidato) {
        Eleitor e = eleitores.get(utilizadorAtual);
        if (e != null && votacaoAberta && !e.isVotou()) {
            votos.put(numeroCandidato, votos.getOrDefault(numeroCandidato, 0) + 1);
            e.setVotou(true);
            return true;
        }
        return false;
    }

    public Resultados apurarResultados() {
        int total = votos.values().stream().mapToInt(Integer::intValue).sum();
        int vencedor = votos.entrySet().stream().max(Map.Entry.comparingByValue()).get().getKey();
        return new Resultados(total, candidatos.get(vencedor));
    }

    public String hashPassword(String password) {
        return Integer.toHexString(password.hashCode());
    }

    public boolean verificarPassword(String password, String hash) {
        return hashPassword(password).equals(hash);
    }

    public void salvarEstado() {
        // Simulação
    }

    public static SistemaVotacao carregarEstado() {
        return new SistemaVotacao();
    }

    public void simularFalha() {
        // Simulação
    }

    public boolean recuperarEstado() {
        return true;
    }

    public Map<String, Eleitor> getEleitores() {
        return eleitores;
    }
}
