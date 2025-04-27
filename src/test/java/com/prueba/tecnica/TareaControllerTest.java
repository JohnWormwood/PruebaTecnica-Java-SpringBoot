package com.prueba.tecnica;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.prueba.tecnica.controllers.TareaController;
import com.prueba.tecnica.models.Tarea;
import com.prueba.tecnica.service.TareaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TareaController.class)
class TareaControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private TareaService service;

    private ObjectMapper om = new ObjectMapper();

    @BeforeEach
    void setup() {

    }

    @Test
    void post_creaTarea() throws Exception {
        Tarea input = new Tarea();
        input.setTitulo("hola");
        Tarea saved = new Tarea();
        saved.setId(1L);
        saved.setTitulo("hola");

        Mockito.when(service.guardarTarea(any(Tarea.class))).thenReturn(saved);

        mvc.perform(post("/api/tareas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(om.writeValueAsString(input)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.titulo").value("hola"));
    }

    @Test
    void get_listaTareas() throws Exception {
        Tarea t1 = new Tarea(); t1.setId(1L); t1.setTitulo("A");
        Tarea t2 = new Tarea(); t2.setId(2L); t2.setTitulo("B");
        Mockito.when(service.listarTareas()).thenReturn(List.of(t1, t2));

        mvc.perform(get("/api/tareas"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[1].id").value(2));
    }
}

