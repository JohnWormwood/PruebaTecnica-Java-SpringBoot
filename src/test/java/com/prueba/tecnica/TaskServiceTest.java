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

class TaskServiceTest {

    private TaskRepository repo;
    private TaskService service;

    @BeforeEach
    void setUp() {
        repo = Mockito.mock(TaskRepository.class);
        service = new TaskService(repo);
    }

    @Test
    void createTask() {
        Task input = new Task();
        input.setTitle("Test");
        input.setDescription("desc");

        Task saved = new Task();
        saved.setId(42L);
        saved.setTitle(input.getTitle());
        saved.setDescription(input.getDescription());

        when(repo.save(any(Task.class))).thenReturn(saved);

        Task result = service.saveTask(input);

        assertNotNull(result.getId());
        assertEquals(42L, result.getId());
        assertEquals("Test", result.getTitle());
        verify(repo, times(1)).save(input);
    }

    @Test
    void listTask() {
        when(repo.findAll()).thenReturn(List.of(new Task(), new Task()));

        List<Task> list = service.listTask();

        assertEquals(2, list.size());
        verify(repo, times(1)).findAll();
    }

    @Test
    void getTaskByIdWhenExist() {
        Task t = new Task();
        t.setId(5L);
        when(repo.findById(5L)).thenReturn(Optional.of(t));

        Optional<Task> opt = service.getTaskById(5L);

        assertTrue(opt.isPresent());
        assertEquals(5L, opt.get().getId());
    }

    @Test
    void getTaskByIdWhenNotExist() {
        when(repo.findById(99L)).thenReturn(Optional.empty());

        Optional<Task> opt = service.getTaskById(99L);

        assertTrue(opt.isEmpty());
    }
}
