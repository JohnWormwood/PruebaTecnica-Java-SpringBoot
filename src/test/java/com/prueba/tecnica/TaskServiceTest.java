package com.prueba.tecnica;

import com.prueba.tecnica.models.Task;
import com.prueba.tecnica.repository.TaskRepository;
import com.prueba.tecnica.services.TaskService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

// 1. Configuramos el test de TaskService
class TaskServiceTest {

    private TaskRepository repo;
    private TaskService service;

    @BeforeEach
    void setUp() {
        repo = Mockito.mock(TaskRepository.class);
        service = new TaskService(repo);
    }

    @Test
    void crearTarea_deberiaDevolverTareaConId() {
        // dado
        Task input = new Task();
        input.setTitulo("Test");
        input.setDescripcion("desc");
        // simulamos que JPA asigna id=42
        Task saved = new Task();
        saved.setId(42L);
        saved.setTitulo(input.getTitulo());
        saved.setDescripcion(input.getDescripcion());
        when(repo.save(any(Task.class))).thenReturn(saved);

        // cuando
        Task result = service.guardarTarea(input);

        // entonces
        assertNotNull(result.getId());
        assertEquals(42L, result.getId());
        assertEquals("Test", result.getTitulo());
        verify(repo, times(1)).save(input);
    }

    @Test
    void listarTareas_deberiaLlamarFindAll() {
        // dado
        when(repo.findAll()).thenReturn(List.of(new Task(), new Task()));

        // cuando
        List<Task> list = service.listarTareas();

        // entonces
        assertEquals(2, list.size());
        verify(repo, times(1)).findAll();
    }

    @Test
    void obtenerPorId_cuandoExiste() {
        // dado
        Task t = new Task();
        t.setId(5L);
        when(repo.findById(5L)).thenReturn(Optional.of(t));

        // cuando
        Optional<Task> opt = service.obtenerTareaPorId(5L);

        // entonces
        assertTrue(opt.isPresent());
        assertEquals(5L, opt.get().getId());
    }

    @Test
    void obtenerPorId_cuandoNoExiste() {
        when(repo.findById(99L)).thenReturn(Optional.empty());

        Optional<Task> opt = service.obtenerTareaPorId(99L);

        assertTrue(opt.isEmpty());
    }
}
