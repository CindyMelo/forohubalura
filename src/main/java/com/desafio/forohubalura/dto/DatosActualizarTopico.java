package com.desafio.forohubalura.dto;

import com.desafio.forohubalura.model.StatusTopico;

public record DatosActualizarTopico(String titulo,
                                    String mensaje,
                                    StatusTopico statusTopico) {
}
