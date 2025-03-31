package cz.upce.fei.nnpiacv.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import cz.upce.fei.nnpiacv.domain.User;
import cz.upce.fei.nnpiacv.dto.UserDTO;
import cz.upce.fei.nnpiacv.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        // Před každým testem smažeme všechny záznamy
        userRepository.deleteAll();
    }

    @AfterEach
    public void tearDown() {
        // Po každém testu smažeme data
        userRepository.deleteAll();
    }

    // 2.1 Test GET endpointu pro získání uživatele s existujícím ID (200 OK)
    @Test
    public void testGetUserById_Success() throws Exception {
        // Vložení testovacího uživatele do H2 databáze
        User user = new User("test@upce.cz", "heslo");
        user = userRepository.save(user);

        mockMvc.perform(get("/api/v1/users/{id}", user.getId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(user.getId().intValue())))
                .andExpect(jsonPath("$.email", is("test@upce.cz")));
    }

    // 2.2 Test GET endpointu, kdy uživatel s daným ID neexistuje (404 Not Found)
    @Test
    public void testGetUserById_NotFound() throws Exception {
        // Ujistíme se, že žádný uživatel s daným ID v DB není
        mockMvc.perform(get("/api/v1/users/{id}", 9999))
                .andExpect(status().isNotFound());
    }

    // 2.3 Test POST endpointu pro vytvoření nového uživatele (201 Created)
    @Test
    public void testPostUser_Success() throws Exception {
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail("novy@priklad.cz");
        userDTO.setPassword("heslo123");

        String json = objectMapper.writeValueAsString(userDTO);

        mockMvc.perform(post("/api/v1/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.email", is("novy@priklad.cz")));
    }

    // 2.4 Test POST endpointu, kdy se pokusíme vytvořit uživatele s již existujícím emailem (409 Conflict)
    @Test
    public void testPostUser_Conflict() throws Exception {
        // Nejprve vložíme uživatele s daným emailem do databáze
        User user = new User("existujici@upce.cz", "heslo123");
        userRepository.save(user);

        // Poté se pokusíme vytvořit nového uživatele se stejným emailem
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail("existujici@upce.cz");
        userDTO.setPassword("heslo123");
        String json = objectMapper.writeValueAsString(userDTO);

        mockMvc.perform(post("/api/v1/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isConflict());
    }
}