package com.desafio.forohubalura.model;


import com.desafio.forohubalura.dto.DatosRegistroTopico;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Table(name = "topicos")
@Entity(name = "Topico")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Topico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private LocalDateTime fechaDeCreacion;
    private String autor;

    @OneToMany(mappedBy = "topico", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Mensaje> mensajes = new ArrayList<>();
    @Enumerated(EnumType.STRING)
    private Curso curso;
    @Enumerated(EnumType.STRING)
    private StatusTopico statusTopico;

    public Topico(DatosRegistroTopico datos){
        this.titulo = datos.titulo();
        this.autor = datos.autor();
        this.curso = datos.curso();
        this.statusTopico = datos.statusTopico();
        this.fechaDeCreacion = LocalDateTime.now();
        this.mensajes = new ArrayList<>();
    }


    public void agregarMensaje(Mensaje mensaje) {
        mensaje.setTopico(this);
        this.mensajes.add(mensaje);
    }

}
