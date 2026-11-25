package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CommandTest {
    private SistemaHospitalar sistema;

    @BeforeEach
    void configurar() {
        sistema = new SistemaHospitalar();
    }

    @Test
    void deveRegistrarPacientePorComando() {
        new RegistrarPacienteCommand(sistema, "Ana Souza").executar();

        assertEquals(List.of("Paciente registrado: Ana Souza"), sistema.consultarHistorico());
    }

    @Test
    void deveSolicitarExamePorComando() {
        new SolicitarExameCommand(sistema, "Ana Souza", "Hemograma").executar();

        assertEquals(List.of("Exame solicitado: Hemograma para Ana Souza"), sistema.consultarHistorico());
    }

    @Test
    void deveEncaminharPacientePorComando() {
        new EncaminharPacienteCommand(sistema, "Ana Souza", "Cardiologia").executar();

        assertEquals(List.of("Paciente encaminhado: Ana Souza para Cardiologia"), sistema.consultarHistorico());
    }

    @Test
    void deveManterComandosNaFilaAteProcessamento() {
        CentralAtendimento central = new CentralAtendimento();
        central.adicionarComando(new RegistrarPacienteCommand(sistema, "Ana Souza"));

        assertEquals(1, central.quantidadePendente());
        assertTrue(sistema.consultarHistorico().isEmpty());
    }

    @Test
    void deveProcessarComandosNaOrdemDeInsercao() {
        CentralAtendimento central = new CentralAtendimento();
        central.adicionarComando(new RegistrarPacienteCommand(sistema, "Ana Souza"));
        central.adicionarComando(new SolicitarExameCommand(sistema, "Ana Souza", "Hemograma"));
        central.adicionarComando(new EncaminharPacienteCommand(sistema, "Ana Souza", "Cardiologia"));

        assertEquals(3, central.processarTodos());
        assertEquals(List.of(
                "Paciente registrado: Ana Souza",
                "Exame solicitado: Hemograma para Ana Souza",
                "Paciente encaminhado: Ana Souza para Cardiologia"
        ), sistema.consultarHistorico());
        assertEquals(0, central.quantidadePendente());
    }

    @Test
    void deveRetornarFalsoQuandoFilaEstiverVazia() {
        CentralAtendimento central = new CentralAtendimento();

        assertFalse(central.processarProximo());
    }

    @Test
    void deveRejeitarComandoNulo() {
        CentralAtendimento central = new CentralAtendimento();

        assertThrows(NullPointerException.class, () -> central.adicionarComando(null));
    }

    @Test
    void deveRetornarHistoricoImutavel() {
        sistema.registrarPaciente("Ana Souza");
        List<String> historico = sistema.consultarHistorico();

        assertThrows(UnsupportedOperationException.class, () -> historico.add("Outra ação"));
    }
}
