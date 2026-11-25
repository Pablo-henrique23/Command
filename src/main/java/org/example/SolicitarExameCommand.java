package org.example;

import java.util.Objects;

public final class SolicitarExameCommand implements ComandoAtendimento {
    private final SistemaHospitalar sistema;
    private final String nomePaciente;
    private final String exame;

    public SolicitarExameCommand(SistemaHospitalar sistema, String nomePaciente, String exame) {
        this.sistema = Objects.requireNonNull(sistema, "O sistema hospitalar é obrigatório");
        if (nomePaciente == null || nomePaciente.isBlank()) {
            throw new IllegalArgumentException("O nome do paciente é obrigatório");
        }
        if (exame == null || exame.isBlank()) {
            throw new IllegalArgumentException("O exame é obrigatório");
        }
        this.nomePaciente = nomePaciente;
        this.exame = exame;
    }

    @Override
    public void executar() {
        sistema.solicitarExame(nomePaciente, exame);
    }
}
