package test.java.com.votacao;
import main.java.com.votacao.SistemaVotacao;
import main.java.com.votacao.Resultados;
import main.java.com.votacao.GUI;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SistemaVotacaoTest {

    private SistemaVotacao sistema;

    @BeforeEach
    public void setUp() {
        sistema = new SistemaVotacao();
    }

    @Test
    public void testRF01_autenticacaoUtilizadores() {
        Assertions.assertTrue(sistema.login("admin", "admin123"));
        Assertions.assertTrue(sistema.login("eleitor1", "senha123"));
        Assertions.assertFalse(sistema.login("invalido", "senha"));
    }

    @Test
    public void testRF02_gestaoCandidatos() {
        sistema.login("admin", "admin123");
        Assertions.assertTrue(sistema.adicionarCandidato("João", "Partido A", 1));
        Assertions.assertTrue(sistema.editarCandidato(1, "João Silva", "Partido A"));
        Assertions.assertTrue(sistema.removerCandidato(1));
    }

    @Test
    public void testRF03_gestaoEleitores() {
        sistema.login("admin", "admin123");
        Assertions.assertTrue(sistema.adicionarEleitor("Maria", "eleitor1", "senha123"));
        Assertions.assertTrue(sistema.editarEleitor("eleitor1", "Maria Silva"));
        Assertions.assertTrue(sistema.removerEleitor("eleitor1"));
    }

    @Test
    public void testRF04_inicioEncerramentoVotacao() {
        sistema.login("admin", "admin123");
        Assertions.assertTrue(sistema.iniciarVotacao());
        Assertions.assertTrue(sistema.encerrarVotacao());
    }

    @Test
    public void testRF05_processoVotacao() {
        sistema.login("admin", "admin123");
        sistema.iniciarVotacao();
        sistema.adicionarCandidato("João", "Partido A", 1);
        sistema.adicionarEleitor("Maria", "eleitor1", "senha123");
        sistema.logout();

        sistema.login("eleitor1", "senha123");
        Assertions.assertTrue(sistema.votar(1));
        Assertions.assertFalse(sistema.votar(1)); // Voto duplo
    }

    @Test
    public void testRF06_apuramentoResultados() {
        sistema.login("admin", "admin123");
        sistema.iniciarVotacao();
        sistema.adicionarCandidato("João", "Partido A", 1);
        sistema.adicionarEleitor("Maria", "eleitor1", "senha123");
        sistema.logout();

        sistema.login("eleitor1", "senha123");
        sistema.votar(1);
        sistema.logout();

        sistema.login("admin", "admin123");
        sistema.encerrarVotacao();
        Resultados resultados = sistema.apurarResultados();
        Assertions.assertEquals(1, resultados.getTotalVotos());
        Assertions.assertEquals("João", resultados.getVencedor().getNome());
    }

    @Test
    public void testRF07_prevencaoVotoDuplo() {
        sistema.login("admin", "admin123");
        sistema.iniciarVotacao();
        sistema.adicionarCandidato("João", "Partido A", 1);
        sistema.adicionarEleitor("Maria", "eleitor1", "senha123");
        sistema.logout();

        sistema.login("eleitor1", "senha123");
        Assertions.assertTrue(sistema.votar(1));
        Assertions.assertFalse(sistema.votar(1)); // Voto duplo não permitido
    }
    @Test
    public void testRNF01_segurancaCredenciais() {
        String hash = sistema.hashPassword("senha123");
        assertNotEquals("senha123", hash);
        Assertions.assertTrue(sistema.verificarPassword("senha123", hash));
    }

    @Test
    public void testRNF02_usabilidadeInterface() {
        // Simulação: verificar se main.java.com.votacao.GUI tem elementos essenciais
        GUI gui = new GUI();
        Assertions.assertTrue(gui.temCampoLogin());
        Assertions.assertTrue(gui.temBotaoVotar());
    }

    @Test
    public void testRNF03_persistenciaDados() {
        sistema.salvarEstado();
        SistemaVotacao novoSistema = SistemaVotacao.carregarEstado();
        assertNotNull(novoSistema);
        Assertions.assertEquals(sistema.getEleitores().size(), novoSistema.getEleitores().size());
    }

    @Test
    public void testRNF04_fiabilidadeComFalhas() {
        sistema.simularFalha();
        Assertions.assertTrue(sistema.recuperarEstado());
    }

    @Test
    public void testRNF05_portabilidadeJava() {
        String os = System.getProperty("os.name").toLowerCase();
        assertTrue(os.contains("win") || os.contains("mac") || os.contains("nix") || os.contains("nux"));
    }

}

// add testes de verificação de password mais arrojados
