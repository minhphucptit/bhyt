//package com.nminh.bhyt.controller;
//
//import com.nminh.bhyt.model.HouseHold;
//import com.nminh.bhyt.model.Inform;
//import com.nminh.bhyt.repository.HoldHouseRepository;
//import com.nminh.bhyt.repository.InformRepository;
//import ma.glasnost.orika.MapperFacade;
//import org.junit.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//
//import java.nio.charset.Charset;
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.mockito.BDDMockito.given;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//public class HoldHouseControllerTest {
//    public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(),
//            MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private static HoldHouseRepository holdHouseRepository;
//
//    @MockBean
//    private static MapperFacade mapperFacade;
//    private HouseHold houseHold = new HouseHold().setName("duc");
//
//}
//
