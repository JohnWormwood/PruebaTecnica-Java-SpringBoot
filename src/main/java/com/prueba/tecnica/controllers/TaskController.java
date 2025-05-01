package com.prueba.tecnica.controllers;

import com.prueba.tecnica.models.Task;
import com.prueba.tecnica.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/task")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping
    public List<Task> listarTareas() {
        return taskService.listarTareas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> obtenerTarea(@PathVariable Long id) {
        return taskService.obtenerTareaPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Task crearTarea(@Valid @RequestBody Task task) {
        return taskService.guardarTarea(task);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> actualizarTarea(@PathVariable Long id, @Valid @RequestBody Task taskDetalles) {
        return taskService.obtenerTareaPorId(id)
                .map(tarea -> {
                    tarea.setTitulo(taskDetalles.getTitulo());
                    tarea.setDescripcion(taskDetalles.getDescripcion());
                    tarea.setCompletada(taskDetalles.isCompletada());
                    taskService.guardarTarea(tarea);
                    return ResponseEntity.ok(tarea);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarTarea(@PathVariable Long id) {
        if (taskService.obtenerTareaPorId(id).isPresent()) {
            taskService.eliminarTarea(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
