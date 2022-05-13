package com.nminh.bhyt.controller;

import com.nminh.bhyt.model.Inform;
import com.nminh.bhyt.model.User;
import com.nminh.bhyt.repository.InformRepository;
import com.nminh.bhyt.repository.UserRepository;
import com.nminh.bhyt.service.InformService;
import com.nminh.bhyt.service.UserService;
import ma.glasnost.orika.MapperFacade;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.nio.charset.Charset;
import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class UserControllerTest {

    public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

    @Autowired
    private MockMvc mockMvc;

    @TestConfiguration
    public static class ServiceTestConfiguration {
        @Bean
        UserService userService() {
            return new UserService();
        }
    }

    @MockBean
    private static UserRepository userRepository;

    private String jsonCreateRequest = "{\n" +
            "    \"username\":\"phuc\",\n" +
            "    \"password\":\"booooooooo\"\n" +
            "}";


    private User user = new User().setUsername("phuc").setPassword("booooooooo").setId(1);


    @Test
    public void testSignInSuccess() throws Exception{
        given(userRepository.findByUsername("phuc")).willReturn(Optional.of(user));
        given(userRepository.existsByUsernameAndPassword("phuc","booooooooo")).willReturn(true);
        mockMvc.perform(post("/api/user/sign-in")
                        .contentType(APPLICATION_JSON_UTF8)
                        .content(jsonCreateRequest))
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value("200"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Success!"));
    }

    @Test
    public void testSignUpSuccess() throws Exception{
        given(userRepository.findByUsername("p")).willReturn(Optional.of(user));

        mockMvc.perform(post("/api/user/sign-up")
                        .contentType(APPLICATION_JSON_UTF8)
                        .content(jsonCreateRequest))
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value("R_200"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Create Successfully!"));
    }


}
