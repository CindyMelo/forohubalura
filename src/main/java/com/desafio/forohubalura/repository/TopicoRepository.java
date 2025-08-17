package com.desafio.forohubalura.repository;

import com.desafio.forohubalura.model.Topico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicoRepository extends JpaRepository<Topico, Long> {
    boolean existsByTituloAndMensajes_contenido(String titulo, String mensaje);
    boolean existsByTitulo(String titulo);

}
