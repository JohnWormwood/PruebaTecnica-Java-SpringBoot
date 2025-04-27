package com.prueba.tecnica.service;

import com.prueba.tecnica.models.Tarea;
import com.prueba.tecnica.repository.TareaRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TareaService {

    private TareaRepository tareaRepository;
    public TareaService(TareaRepository repo) { this.tareaRepository = repo; }


    public List<Tarea> listarTareas() {
        return tareaRepository.findAll();
    }

    public Optional<Tarea> obtenerTareaPorId(Long id) {
        return tareaRepository.findById(id);
    }

    public Tarea guardarTarea(Tarea tarea) {
        return tareaRepository.save(tarea);
    }

    public void eliminarTarea(Long id) {
        tareaRepository.deleteById(id);
    }
}
