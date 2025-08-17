package com.desafio.forohubalura.service;

import com.desafio.forohubalura.dto.DatosActualizarTopico;
import com.desafio.forohubalura.dto.DatosNuevoMensaje;
import com.desafio.forohubalura.dto.DatosRegistroTopico;
import com.desafio.forohubalura.dto.DatosTodosLosTopicos;
import com.desafio.forohubalura.model.Mensaje;
import com.desafio.forohubalura.model.Topico;
import com.desafio.forohubalura.repository.TopicoRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.antlr.v4.runtime.misc.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class TopicoService {

    @Autowired
    private TopicoRepository topicoRepository;

    // Crear un nuevo tópico
    public Topico crearTopico(DatosRegistroTopico datosRegistroTopico) {
        // Verifica si ya existe un tópico con el mismo título
        if (topicoRepository.existsByTitulo(datosRegistroTopico.titulo())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El tópico ya existe.");
        }

        Topico nuevoTopico = new Topico(datosRegistroTopico);
        return topicoRepository.save(nuevoTopico);
    }

    // Listar todos los tópicos
    public List<DatosTodosLosTopicos> listarTodosLosTopicos() {
        return topicoRepository.findAll()
                .stream()
                .map(DatosTodosLosTopicos::new)
                .toList();
    }

    // Obtener un tópico por ID
    public Topico obtenerTopicoPorId(Long id) {
        return topicoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tópico no encontrado"));
    }


    @Transactional
    public Topico actualizarTopico(Long id, DatosActualizarTopico datos) {
        Topico topico = topicoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tópico no existe"));


        if (datos.titulo() != null && !datos.titulo().isBlank()) {
            topico.setTitulo(datos.titulo());
        }


        if (datos.mensaje() != null && !datos.mensaje().isBlank()) {
            // Crear DTO para el mensaje
            DatosNuevoMensaje datosMensaje = new DatosNuevoMensaje(
                    datos.mensaje(),
                    "AutorDelMensaje" // reemplazar por autor real si es necesario
            );


            Mensaje mensaje = new Mensaje(datosMensaje, topico);

            // Agregar el mensaje al tópico usando el método de la entidad
            topico.agregarMensaje(mensaje);
        }


        if (datos.statusTopico() != null) {
            topico.setStatusTopico(datos.statusTopico());
        }


        return topicoRepository.save(topico);
    }

    // Eliminar un tópico
    public boolean eliminarTopico(Long id) {
        if (topicoRepository.existsById(id)) {
            topicoRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
