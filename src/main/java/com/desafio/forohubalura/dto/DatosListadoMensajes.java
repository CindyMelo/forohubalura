package com.desafio.forohubalura.dto;

import com.desafio.forohubalura.model.Mensaje;

import java.time.LocalDateTime;

public record DatosListadoMensajes(
        Long id,
        String contenido,
        LocalDateTime fecha,
        String autor) {
    public DatosListadoMensajes(Mensaje mensaje) {
        this(mensaje.getId(), // Asigna el id directamente
                mensaje.getContenido(),
                mensaje.getFechaDeCreacion(),
                mensaje.getAutor());
    }

    public DatosListadoMensajes(Long id, String contenido, LocalDateTime fecha, String autor) {
        this.id = id;
        this.contenido = contenido;
        this.fecha = fecha;
        this.autor = autor;
    }

}
