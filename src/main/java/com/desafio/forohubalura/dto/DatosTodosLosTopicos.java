package com.desafio.forohubalura.dto;

import com.desafio.forohubalura.model.Curso;
import com.desafio.forohubalura.model.StatusTopico;
import com.desafio.forohubalura.model.Topico;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public record DatosTodosLosTopicos(
        Long id,
        String titulo,
        String autor,
        LocalDateTime fechaDeCreacion,
        Curso curso,
        StatusTopico statusTopico,
        List<DatosListadoMensajes> mensajes) {
    public DatosTodosLosTopicos(Topico topico){
        this(topico.getId(),
                topico.getTitulo(),
                topico.getAutor(),
                topico.getFechaDeCreacion(),
                topico.getCurso(),
                topico.getStatusTopico(),
                topico.getMensajes().stream()
                        .map(DatosListadoMensajes::new)
                        .collect(Collectors.toList()));


    }
}
