package com.formation.biblio.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.formation.biblio.domain.dto.BookCreateRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("dev")
class BookControllerIT {
    @Autowired MockMvc mvc;
    @Autowired ObjectMapper json;

    @Test
    void post_creeUnLivre_etRetourne201() throws Exception {
        BookCreateRequest req = new BookCreateRequest("978-9999", "Test Driven", "Kent Beck", 2002);
        mvc.perform(post("/api/books")
                        .contentType("application/json")
                        .content(json.writeValueAsString(req)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.isbn").value("978-9999"));
    }

    @Test
    void post_donneesInvalides_retourne400() throws Exception {
        mvc.perform(post("/api/books")
                        .contentType("application/json")
                        .content("{\"isbn\":\"\",\"title\":\"\",\"authorName\":\"\",\"year\":-1}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void get_idInconnu_retourne404() throws Exception {
        mvc.perform(get("/api/books/99999")).andExpect(status().isNotFound());
    }
}