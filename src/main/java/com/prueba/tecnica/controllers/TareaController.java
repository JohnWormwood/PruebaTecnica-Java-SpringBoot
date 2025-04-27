package com.prueba.tecnica.controllers;

import com.prueba.tecnica.models.Tarea;
import com.prueba.tecnica.service.TareaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/tareas")
public class TareaController {

    @Autowired
    private TareaService tareaService;

    @GetMapping
    public List<Tarea> listarTareas() {
        return tareaService.listarTareas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tarea> obtenerTarea(@PathVariable Long id) {
        return tareaService.obtenerTareaPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Tarea crearTarea(@Valid @RequestBody Tarea tarea) {
        return tareaService.guardarTarea(tarea);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tarea> actualizarTarea(@PathVariable Long id, @Valid @RequestBody Tarea tareaDetalles) {
        return tareaService.obtenerTareaPorId(id)
                .map(tarea -> {
                    tarea.setTitulo(tareaDetalles.getTitulo());
                    tarea.setDescripcion(tareaDetalles.getDescripcion());
                    tarea.setCompletada(tareaDetalles.isCompletada());
                    tareaService.guardarTarea(tarea);
                    return ResponseEntity.ok(tarea);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarTarea(@PathVariable Long id) {
        if (tareaService.obtenerTareaPorId(id).isPresent()) {
            tareaService.eliminarTarea(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
