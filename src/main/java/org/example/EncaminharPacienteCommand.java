package org.example;

import java.util.Objects;

public final class EncaminharPacienteCommand implements ComandoAtendimento {
    private final SistemaHospitalar sistema;
    private final String nomePaciente;
    private final String setor;

    public EncaminharPacienteCommand(SistemaHospitalar sistema, String nomePaciente, String setor) {
        this.sistema = Objects.requireNonNull(sistema, "O sistema hospitalar é obrigatório");
        if (nomePaciente == null || nomePaciente.isBlank()) {
            throw new IllegalArgumentException("O nome do paciente é obrigatório");
        }
        if (setor == null || setor.isBlank()) {
            throw new IllegalArgumentException("O setor de destino é obrigatório");
        }
        this.nomePaciente = nomePaciente;
        this.setor = setor;
    }

    @Override
    public void executar() {
        sistema.encaminharPaciente(nomePaciente, setor);
    }
}
