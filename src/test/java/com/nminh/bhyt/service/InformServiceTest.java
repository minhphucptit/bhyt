package com.nminh.bhyt.service;

import com.nminh.bhyt.controller.InformController;
import com.nminh.bhyt.model.Inform;
import com.nminh.bhyt.repository.InformRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.junit.Assert.assertEquals;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class InformServiceTest {

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

    @InjectMocks
    private InformService informService;

    @Mock
    private InformRepository informRepository;

    @Test
    public void getAllInformList_WhenHave5Inform_ThenReturn5Inform() {

        List<Inform> informList = new ArrayList<>();
        for(int i=0;i<5;i++){
            Inform inform = new Inform(VALID_DEFAULT_INFORM);
            inform.setId(i);
            informList.add(inform);
        }
        when(informRepository.findAll()).thenReturn(informList);

        assertEquals(informService.getAllListInform().size(), 5);
    }
}
