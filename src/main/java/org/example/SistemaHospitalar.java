package org.example;

import java.util.ArrayList;
import java.util.List;

public final class SistemaHospitalar {
    private final List<String> historico = new ArrayList<>();

    public void registrarPaciente(String nomePaciente) {
        historico.add("Paciente registrado: " + validar(nomePaciente, "O nome do paciente é obrigatório"));
    }

    public void solicitarExame(String nomePaciente, String exame) {
        String paciente = validar(nomePaciente, "O nome do paciente é obrigatório");
        String tipoExame = validar(exame, "O exame é obrigatório");
        historico.add("Exame solicitado: " + tipoExame + " para " + paciente);
    }

    public void encaminharPaciente(String nomePaciente, String setor) {
        String paciente = validar(nomePaciente, "O nome do paciente é obrigatório");
        String destino = validar(setor, "O setor de destino é obrigatório");
        historico.add("Paciente encaminhado: " + paciente + " para " + destino);
    }

    public List<String> consultarHistorico() {
        return List.copyOf(historico);
    }

    private String validar(String valor, String mensagem) {
        if (valor == null || valor.isBlank()) {
            throw new IllegalArgumentException(mensagem);
        }
        return valor;
    }
}
