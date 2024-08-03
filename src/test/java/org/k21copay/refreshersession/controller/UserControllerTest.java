package org.k21copay.refreshersession.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.k21copay.refreshersession.entity.User;
import org.k21copay.refreshersession.service.UserService;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Arrays;
import java.util.Optional;
import static org.hamcrest.Matchers.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    public void testGetAllUsers() throws Exception {
        User user = User.builder().name("Joba").email("jobaade@gmail.com").build();

        given(userService.findAll()).willReturn(Arrays.asList(user));

        mockMvc.perform(get("/api/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name", is("Joba")));
    }

    @Test
    public void testGetUserById() throws Exception {
        User user = User.builder().name("Joba").email("jobaade@gmail.com").build();

        given(userService.findById(1L)).willReturn(Optional.of(user));

        mockMvc.perform(get("/api/users/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Joba")));
    }

    @Test
    public void testCreateUser() throws Exception {
        User user = User.builder().name("Joba").email("jobaade@gmail.com").build();


        given(userService.save(Mockito.any(User.class))).willReturn(user);

        mockMvc.perform(post("/api/users")
                        .contentType("application/json")
                        .content("{\"name\": \"Joba\", \"email\": \"jobaade@gmail.com\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Joba")));
    }

    @Test
    public void testUpdateUser() throws Exception {
        User user = User.builder().name("Joba").email("jobaade@gmail.com").build();


        given(userService.findById(1L)).willReturn(Optional.of(user));
        given(userService.save(Mockito.any(User.class))).willReturn(user);

        mockMvc.perform(put("/api/users/1")
                        .contentType("application/json")
                        .content("{\"name\": \"Joba\", \"email\": \"jobaade@gmail.com\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Joba")));
    }

    @Test
    public void testDeleteUser() throws Exception {
        User user = User.builder().id(1L).build();

        given(userService.findById(1L)).willReturn(Optional.of(user));

        mockMvc.perform(delete("/api/users/1"))
                .andExpect(status().isOk());
    }
}
