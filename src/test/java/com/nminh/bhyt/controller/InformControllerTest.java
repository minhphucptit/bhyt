package com.nminh.bhyt.controller;

import com.nminh.bhyt.model.Inform;
import com.nminh.bhyt.repository.InformRepository;
import com.nminh.bhyt.service.InformService;
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

import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Date;
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
    private InformService informService;

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

    private final Inform VALID_DEFAULT_INFORM = new Inform(
            1,
            1,
            "Nguyen Van A",
            "NGUYEN VAN C",
            "123456789",
            "123456789",
            new Date(),
            1,
            "Vietnam",
            "Kinh",
            "84",
            "0374306505",
            "18",
            "Xuân Thủy",
            "Hà Nội",
            "Bệnh viên nhi",
            "Nguyen Van X",
            new BigDecimal(120000),
            "Nguyen Van A",
            "9999",
            "0374306505",
            "Nha 18 Ngo 32 Anh Dung, Hai Phong",
            new Date().toLocaleString()
    );

    @Test
    public void getAllInformList_WhenHave5Inform_ThenReturn5Inform() throws Exception{
        List<Inform> informList = new ArrayList<>();
        for(int i=0;i<5;i++){
            Inform inform = new Inform(VALID_DEFAULT_INFORM);
            inform.setId(i);
            informList.add(inform);
        }

        given(informService.getAllListInform()).willReturn(informList);
        mockMvc.perform(get("/api/informs"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value("R_200"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.totalItems").value("5"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.items[1].id").value(1));
    }

    @Test
    public void createNewInform_WhenInformInvalid_ThenReturnSuccessfull() throws Exception{
        informService.createNewInform(isA(Inform.class));
        mockMvc.perform(post("/api/informs")
                        .contentType(APPLICATION_JSON_UTF8)
                        .content(VALID_DEFAULT_INFORM.formatToStringReqeust()))
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
