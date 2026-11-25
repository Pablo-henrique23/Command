package org.example;

import java.util.Objects;

public final class RegistrarPacienteCommand implements ComandoAtendimento {
    private final SistemaHospitalar sistema;
    private final String nomePaciente;

    public RegistrarPacienteCommand(SistemaHospitalar sistema, String nomePaciente) {
        this.sistema = Objects.requireNonNull(sistema, "O sistema hospitalar é obrigatório");
        if (nomePaciente == null || nomePaciente.isBlank()) {
            throw new IllegalArgumentException("O nome do paciente é obrigatório");
        }
        this.nomePaciente = nomePaciente;
    }

    @Override
    public void executar() {
        sistema.registrarPaciente(nomePaciente);
    }
}
