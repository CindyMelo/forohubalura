package com.desafio.forohubalura.controller;

import com.desafio.forohubalura.dto.DatosActualizarTopico;
import com.desafio.forohubalura.dto.DatosRegistroTopico;
import com.desafio.forohubalura.dto.DatosTodosLosTopicos;
import com.desafio.forohubalura.model.Topico;
import com.desafio.forohubalura.repository.TopicoRepository;
import com.desafio.forohubalura.service.TopicoService;
import jakarta.validation.Valid;
import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import com.desafio.forohubalura.dto.DatosListadoMensajes;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoService topicoService;

    // Crear un nuevo tópico
    @PostMapping
    public ResponseEntity<DatosTodosLosTopicos> crearTopico(
            @RequestBody @Valid DatosRegistroTopico datosRegistroTopico,
            UriComponentsBuilder uriComponentsBuilder) {

        Topico topico = topicoService.crearTopico(datosRegistroTopico);

        URI uri = uriComponentsBuilder.path("/topicos/{id}")
                .buildAndExpand(topico.getId())
                .toUri();

        DatosTodosLosTopicos dto = new DatosTodosLosTopicos(topico);

        return ResponseEntity.created(uri).body(dto);
    }

    // Listar todos los tópicos
    @GetMapping
    public List<DatosTodosLosTopicos> listarTodosLosTopicos() {
        return topicoService.listarTodosLosTopicos();
    }

    // Obtener un tópico por ID
    @GetMapping("/{id}")
    public ResponseEntity<DatosTodosLosTopicos> topicoPorId(@PathVariable Long id) {
        Topico topico = topicoService.obtenerTopicoPorId(id);
        DatosTodosLosTopicos dto = new DatosTodosLosTopicos(topico);
        return ResponseEntity.ok(dto);
    }

    // Actualizar un tópico
    @PutMapping("/{id}")
    public ResponseEntity<DatosTodosLosTopicos> actualizarTopico(
            @PathVariable Long id,
            @Valid @RequestBody DatosActualizarTopico datosActualizarTopico) {

        Topico topicoActualizado = topicoService.actualizarTopico(id, datosActualizarTopico);
        DatosTodosLosTopicos dto = new DatosTodosLosTopicos(topicoActualizado);

        return ResponseEntity.ok(dto);
    }

    // Eliminar un tópico
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarTopico(@PathVariable Long id) {
        boolean eliminado = topicoService.eliminarTopico(id);
        if (eliminado) {
            return ResponseEntity.noContent().build(); // 204
        } else {
            return ResponseEntity.notFound().build(); // 404
        }
    }
}
