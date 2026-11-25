package org.example;

import java.util.ArrayDeque;
import java.util.Objects;
import java.util.Queue;

public final class CentralAtendimento {
    private final Queue<ComandoAtendimento> fila = new ArrayDeque<>();

    public void adicionarComando(ComandoAtendimento comando) {
        fila.add(Objects.requireNonNull(comando, "O comando é obrigatório"));
    }

    public boolean processarProximo() {
        ComandoAtendimento comando = fila.poll();
        if (comando == null) {
            return false;
        }
        comando.executar();
        return true;
    }

    public int processarTodos() {
        int processados = 0;
        while (processarProximo()) {
            processados++;
        }
        return processados;
    }

    public int quantidadePendente() {
        return fila.size();
    }
}
