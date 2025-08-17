package com.desafio.forohubalura.model;

import com.desafio.forohubalura.dto.DatosNuevoMensaje;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Table(name = "mensajes")
@Entity(name = "Mensaje")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Mensaje {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String contenido;
    private LocalDateTime fechaDeCreacion;
    private String autor;
    @ManyToOne
    @JoinColumn(name = "topico_id")
    @JsonBackReference
    private Topico topico;


    public Mensaje(DatosNuevoMensaje datos, Topico topico) {
        this.contenido = datos.contenido();
        this.autor = datos.autor();
        this.fechaDeCreacion = LocalDateTime.now();
        this.topico = topico;
    }

}
