package main.java.com.votacao;

import java.util.HashMap;
import java.util.Map;

public class GestorAutenticacao {
    private Map<String, String> credenciais = new HashMap<>();
    private String utilizadorAtual;

    public void adicionarUtilizador(String username, String password) {
        credenciais.put(username, hashPassword(password));
    }

    public boolean autenticar(String username, String password) {
        if (credenciais.containsKey(username) && verificarPassword(password, credenciais.get(username))) {
            utilizadorAtual = username;
            return true;
        }
        return false;
    }

    public void logout() {
        utilizadorAtual = null;
    }

    public String getUtilizadorAtual() {
        return utilizadorAtual;
    }

    public String hashPassword(String password) {
        return Integer.toHexString(password.hashCode()); // Substituir por hash seguro mais tarde
    }

    public boolean verificarPassword(String password, String hash) {
        return hashPassword(password).equals(hash);
    }

    public boolean existeUtilizador(String username) {
        return credenciais.containsKey(username);
    }

    public void removerUtilizador(String username) {
        credenciais.remove(username);
    }
}
