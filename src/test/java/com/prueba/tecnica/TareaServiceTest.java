package com.prueba.tecnica;

import com.prueba.tecnica.models.Tarea;
import com.prueba.tecnica.repository.TareaRepository;
import com.prueba.tecnica.service.TareaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

// 1. Configuramos el test de TareaService
class TareaServiceTest {

    private TareaRepository repo;
    private TareaService service;

    @BeforeEach
    void setUp() {
        repo = Mockito.mock(TareaRepository.class);
        service = new TareaService(repo);
    }

    @Test
    void crearTarea_deberiaDevolverTareaConId() {
        // dado
        Tarea input = new Tarea();
        input.setTitulo("Test");
        input.setDescripcion("desc");
        // simulamos que JPA asigna id=42
        Tarea saved = new Tarea();
        saved.setId(42L);
        saved.setTitulo(input.getTitulo());
        saved.setDescripcion(input.getDescripcion());
        when(repo.save(any(Tarea.class))).thenReturn(saved);

        // cuando
        Tarea result = service.guardarTarea(input);

        // entonces
        assertNotNull(result.getId());
        assertEquals(42L, result.getId());
        assertEquals("Test", result.getTitulo());
        verify(repo, times(1)).save(input);
    }

    @Test
    void listarTareas_deberiaLlamarFindAll() {
        // dado
        when(repo.findAll()).thenReturn(List.of(new Tarea(), new Tarea()));

        // cuando
        List<Tarea> list = service.listarTareas();

        // entonces
        assertEquals(2, list.size());
        verify(repo, times(1)).findAll();
    }

    @Test
    void obtenerPorId_cuandoExiste() {
        // dado
        Tarea t = new Tarea();
        t.setId(5L);
        when(repo.findById(5L)).thenReturn(Optional.of(t));

        // cuando
        Optional<Tarea> opt = service.obtenerTareaPorId(5L);

        // entonces
        assertTrue(opt.isPresent());
        assertEquals(5L, opt.get().getId());
    }

    @Test
    void obtenerPorId_cuandoNoExiste() {
        when(repo.findById(99L)).thenReturn(Optional.empty());

        Optional<Tarea> opt = service.obtenerTareaPorId(99L);

        assertTrue(opt.isEmpty());
    }
}
