package com.nminh.bhyt.controller;

import com.nminh.bhyt.model.Inform;
import com.nminh.bhyt.repository.InformRepository;
import ma.glasnost.orika.MapperFacade;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(InformController.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class InformControllerTest {

    public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private static InformRepository informRepository;

    @MockBean
    private static MapperFacade mapperFacade;

    private Inform inform = new Inform().setFullname("duc");

    private String jsonCreateRequest = "{\n" +
            "    \"type\":1,\n" +
            "    \"target\":1,\n" +
            "    \"hostname\":\"abc\",\n" +
            "    \"fullname\":\"phuc\",\n" +
            "    \"birthday\":\"30/07/2000\",\n" +
            "    \"gender\":1,\n" +
            "    \"code\":\"C001\",\n" +
            "    \"salary\":1000.99\n" +
            "\n" +
            "}";

    private String jsonUpdateRequest = "{\n" +
            "    \"type\":2,\n" +
            "    \"target\":2,\n" +
            "    \"hostname\":\"cde\",\n" +
            "    \"fullname\":\"duc\",\n" +
            "    \"birthday\":\"19/07/1999\",\n" +
            "    \"gender\":1,\n" +
            "    \"code\":\"C002\",\n" +
            "    \"salary\":9999.99\n" +
            "\n" +
            "}";

    @Test
    public void testGetInformsSuccess() throws Exception{
        List<Inform> informList = new ArrayList<>();
        for(int i=0;i<5;i++){
            Inform brand = new Inform().setId(i).setCity("Ha Noi"+i).setCode("C00"+i);
            informList.add(brand);
        }

        given(informRepository.findAll()).willReturn(informList);
        mockMvc.perform(get("/api/informs"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value("R_200"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.totalItems").value("5"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.items[1].id").value(1));
    }

    @Test
    public void testCreateInformSuccess() throws Exception{
        given(informRepository.save(isA(Inform.class))).willAnswer(i -> i.getArgument(0));

        mockMvc.perform(post("/api/informs")
                        .contentType(APPLICATION_JSON_UTF8)
                        .content(jsonCreateRequest))
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value("R_201"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.item.fullname").value("phuc"));
    }

    @Test
    public void testUpdateInformSuccess() throws Exception{
        given(informRepository.findById(1)).willReturn(Optional.of(inform));
        given(informRepository.save(isA(Inform.class))).willAnswer(i -> i.getArgument(0));
//        given(mapperFacade.map())

        mockMvc.perform(patch("/api/informs/{id}",1)
                        .contentType(APPLICATION_JSON_UTF8)
                        .content(jsonUpdateRequest))
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value("R_200"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.item.fullname").value("duc"));
    }

    @Test
    public void testDeleteInformSuccess() throws Exception{
        given(informRepository.findById(1)).willReturn(Optional.of(inform));
        informRepository.delete(inform);
//        given(mapperFacade.map())

        mockMvc.perform(delete("/api/informs/{id}",1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value("R_200"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Success!"));
    }

}
