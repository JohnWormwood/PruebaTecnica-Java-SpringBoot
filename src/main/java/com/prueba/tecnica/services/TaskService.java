package com.prueba.tecnica.services;

import com.prueba.tecnica.models.Task;
import com.prueba.tecnica.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    private TaskRepository taskRepository;
    public TaskService(TaskRepository repo) { this.taskRepository = repo; }


    public List<Task> listarTareas() {
        return taskRepository.findAll();
    }

    public Optional<Task> obtenerTareaPorId(Long id) {
        return taskRepository.findById(id);
    }

    public Task guardarTarea(Task task) {
        return taskRepository.save(task);
    }

    public void eliminarTarea(Long id) {
        taskRepository.deleteById(id);
    }
}
