package com.prueba.tecnica;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.prueba.tecnica.controllers.TaskController;
import com.prueba.tecnica.models.Task;
import com.prueba.tecnica.services.TaskService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TaskController.class)
class TaskControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private TaskService service;

    private ObjectMapper om = new ObjectMapper();

    @BeforeEach
    void setup() {

    }

    @Test
    void post_creaTarea() throws Exception {
        Task input = new Task();
        input.setTitulo("hola");
        Task saved = new Task();
        saved.setId(1L);
        saved.setTitulo("hola");

        Mockito.when(service.guardarTarea(any(Task.class))).thenReturn(saved);

        mvc.perform(post("/api/tareas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(om.writeValueAsString(input)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.titulo").value("hola"));
    }

    @Test
    void get_listaTareas() throws Exception {
        Task t1 = new Task(); t1.setId(1L); t1.setTitulo("A");
        Task t2 = new Task(); t2.setId(2L); t2.setTitulo("B");
        Mockito.when(service.listarTareas()).thenReturn(List.of(t1, t2));

        mvc.perform(get("/api/tareas"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[1].id").value(2));
    }
}

