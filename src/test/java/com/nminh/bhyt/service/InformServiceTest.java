package com.nminh.bhyt.service;

import com.nminh.bhyt.dto.CalculatorDto;
import com.nminh.bhyt.dto.CalculatorFamilyDto;
import com.nminh.bhyt.exception.BadRequestException;
import com.nminh.bhyt.exception.NotFoundException;
import com.nminh.bhyt.model.Inform;
import com.nminh.bhyt.repository.InformRepository;
import ma.glasnost.orika.MapperFacade;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import static org.junit.Assert.assertEquals;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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

    @InjectMocks
    private InformService informService;

    @Mock
    MapperFacade mapperFacade;

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

    @Test
    public void getInformById_WhenHaveValidId_ThenReturnInform() {

        Inform informExpected = new Inform(VALID_DEFAULT_INFORM);
        informExpected.setId(1);
        when(informRepository.findById(any())).thenReturn(Optional.of(informExpected));

        assertEquals(informService.getInformById(1), informExpected);
    }

    @Test(expected = NotFoundException.class)
    public void getInformById_WhenHaveInvalidId_ThenThrowException() {

        Inform informExpected = new Inform(VALID_DEFAULT_INFORM);
        informExpected.setId(1);

        assertEquals(informService.getInformById(2), informExpected);
    }

    @Test
    public void createNewInform_WhenHaveValidInform_ThenReturnSuccessfull() {

        Inform inform = new Inform(VALID_DEFAULT_INFORM);
        inform.setId(1);
        informService.createNewInform(inform);

        verify(informRepository, times(1)).save(any(Inform.class));
    }

    @Test(expected = BadRequestException.class)
    public void createNewInform_WhenHaveTypeNull_ThenThrowBadRequestException() {

        Inform inform = new Inform(VALID_DEFAULT_INFORM);
        inform.setId(1);
        inform.setType(null);
        informService.createNewInform(inform);

        verify(informRepository, times(1)).save(any(Inform.class));
    }

    @Test(expected = BadRequestException.class)
    public void createNewInform_WhenHaveTypeInvalid_ThenThrowBadRequestException() {

        Inform inform = new Inform(VALID_DEFAULT_INFORM);
        inform.setId(1);
        inform.setType(3);
        informService.createNewInform(inform);

        verify(informRepository, times(1)).save(any(Inform.class));
    }

    @Test(expected = BadRequestException.class)
    public void createNewInform_WhenHaveTargetNull_ThenThrowBadRequestException() {

        Inform inform = new Inform(VALID_DEFAULT_INFORM);
        inform.setId(1);
        inform.setTarget(null);
        informService.createNewInform(inform);

        verify(informRepository, times(1)).save(any(Inform.class));
    }

    @Test(expected = BadRequestException.class)
    public void createNewInform_WhenHaveTargetInvalid_ThenThrowBadRequestException() {

        Inform inform = new Inform(VALID_DEFAULT_INFORM);
        inform.setId(1);
        inform.setTarget(3);
        informService.createNewInform(inform);

        verify(informRepository, times(1)).save(any(Inform.class));
    }

    @Test(expected = BadRequestException.class)
    public void createNewInform_WhenHaveFullnameNull_ThenThrowBadRequestException() {

        Inform inform = new Inform(VALID_DEFAULT_INFORM);
        inform.setId(1);
        inform.setFullname(null);
        informService.createNewInform(inform);

        verify(informRepository, times(1)).save(any(Inform.class));
    }

    @Test(expected = BadRequestException.class)
    public void createNewInform_WhenHaveFullnameNotUppercase_ThenThrowBadRequestException() {

        Inform inform = new Inform(VALID_DEFAULT_INFORM);
        inform.setId(1);
        inform.setFullname("abc");
        informService.createNewInform(inform);

        verify(informRepository, times(1)).save(any(Inform.class));
    }

    @Test(expected = BadRequestException.class)
    public void createNewInform_WhenHaveCodeNull_ThenThrowBadRequestException() {

        Inform inform = new Inform(VALID_DEFAULT_INFORM);
        inform.setId(1);
        inform.setCode(null);
        informService.createNewInform(inform);

        verify(informRepository, times(1)).save(any(Inform.class));
    }

    @Test(expected = BadRequestException.class)
    public void createNewInform_WhenHaveCmndNull_ThenThrowBadRequestException() {

        Inform inform = new Inform(VALID_DEFAULT_INFORM);
        inform.setId(1);
        inform.setCmnd(null);
        informService.createNewInform(inform);

        verify(informRepository, times(1)).save(any(Inform.class));
    }

    @Test(expected = BadRequestException.class)
    public void createNewInform_WhenHaveBirthdayNull_ThenThrowBadRequestException() {

        Inform inform = new Inform(VALID_DEFAULT_INFORM);
        inform.setId(1);
        inform.setBirthday(null);
        informService.createNewInform(inform);

        verify(informRepository, times(1)).save(any(Inform.class));
    }

    @Test(expected = BadRequestException.class)
    public void createNewInform_WhenHaveGenderNull_ThenThrowBadRequestException() {

        Inform inform = new Inform(VALID_DEFAULT_INFORM);
        inform.setId(1);
        inform.setGender(null);
        informService.createNewInform(inform);

        verify(informRepository, times(1)).save(any(Inform.class));
    }

    @Test(expected = BadRequestException.class)
    public void createNewInform_WhenHaveGenderInvalid_ThenThrowBadRequestException() {

        Inform inform = new Inform(VALID_DEFAULT_INFORM);
        inform.setId(1);
        inform.setGender(3);
        informService.createNewInform(inform);

        verify(informRepository, times(1)).save(any(Inform.class));
    }

    @Test(expected = BadRequestException.class)
    public void createNewInform_WhenHaveNationNull_ThenThrowBadRequestException() {

        Inform inform = new Inform(VALID_DEFAULT_INFORM);
        inform.setId(1);
        inform.setNation(null);
        informService.createNewInform(inform);

        verify(informRepository, times(1)).save(any(Inform.class));
    }

    @Test(expected = BadRequestException.class)
    public void createNewInform_WhenHaveGeocodeNull_ThenThrowBadRequestException() {

        Inform inform = new Inform(VALID_DEFAULT_INFORM);
        inform.setId(1);
        inform.setGeocode(null);
        informService.createNewInform(inform);

        verify(informRepository, times(1)).save(any(Inform.class));
    }

    @Test(expected = BadRequestException.class)
    public void createNewInform_WhenHavePhoneNumberNull_ThenThrowBadRequestException() {

        Inform inform = new Inform(VALID_DEFAULT_INFORM);
        inform.setId(1);
        inform.setPhoneNumber(null);
        informService.createNewInform(inform);

        verify(informRepository, times(1)).save(any(Inform.class));
    }

    @Test(expected = BadRequestException.class)
    public void createNewInform_WhenHaveDkcbNull_ThenThrowBadRequestException() {

        Inform inform = new Inform(VALID_DEFAULT_INFORM);
        inform.setId(1);
        inform.setDkcb(null);
        informService.createNewInform(inform);

        verify(informRepository, times(1)).save(any(Inform.class));
    }

    @Test(expected = BadRequestException.class)
    public void createNewInform_WhenHaveSalaryNull_ThenThrowBadRequestException() {

        Inform inform = new Inform(VALID_DEFAULT_INFORM);
        inform.setId(1);
        inform.setSalary(null);
        informService.createNewInform(inform);

        verify(informRepository, times(1)).save(any(Inform.class));
    }

    @Test(expected = BadRequestException.class)
    public void createNewInform_WhenHaveType1AndTargetInvalid_ThenThrowBadRequestException() {

        Inform inform = new Inform(VALID_DEFAULT_INFORM);
        inform.setId(1);
        inform.setType(1);
        inform.setTarget(0);
        informService.createNewInform(inform);

        verify(informRepository, times(1)).save(any(Inform.class));
    }

    @Test(expected = BadRequestException.class)
    public void createNewInform_WhenHaveTarget1AndHouseholdNull_ThenThrowBadRequestException() {

        Inform inform = new Inform(VALID_DEFAULT_INFORM);
        inform.setId(1);
        inform.setTarget(1);
        inform.setHousehold(null);
        informService.createNewInform(inform);

        verify(informRepository, times(1)).save(any(Inform.class));
    }

    @Test(expected = BadRequestException.class)
    public void createNewInform_WhenHaveTarget1AndFamilyCodeNull_ThenThrowBadRequestException() {

        Inform inform = new Inform(VALID_DEFAULT_INFORM);
        inform.setId(1);
        inform.setTarget(1);
        inform.setFamilyCode(null);
        informService.createNewInform(inform);

        verify(informRepository, times(1)).save(any(Inform.class));
    }

    @Test(expected = BadRequestException.class)
    public void createNewInform_WhenHaveTarget1AndPhoneContactNull_ThenThrowBadRequestException() {

        Inform inform = new Inform(VALID_DEFAULT_INFORM);
        inform.setId(1);
        inform.setTarget(1);
        inform.setPhoneContact(null);
        informService.createNewInform(inform);

        verify(informRepository, times(1)).save(any(Inform.class));
    }

    @Test(expected = BadRequestException.class)
    public void createNewInform_WhenHaveTarget1AndAddressHouseholdNull_ThenThrowBadRequestException() {

        Inform inform = new Inform(VALID_DEFAULT_INFORM);
        inform.setId(1);
        inform.setTarget(1);
        inform.setAddressHousehold(null);
        informService.createNewInform(inform);

        verify(informRepository, times(1)).save(any(Inform.class));
    }

    @Test
    public void updateInform_WhenHaveValidIdAndValidInform_ThenReturnSuccessfull() {

        Inform inform = new Inform(VALID_DEFAULT_INFORM);
        Inform informToUpdate = new Inform(VALID_DEFAULT_INFORM);
        inform.setId(1);
        informToUpdate.setId(1);
        informToUpdate.setFullname("TEST ABC");

        when(informRepository.findById(any())).thenReturn(Optional.of(inform));
        informService.updateInform(informToUpdate.getId(), informToUpdate);

        verify(informRepository, times(1)).save(any(Inform.class));
        assertEquals(informService.getInformById(inform.getId()).getFullname(), inform.getFullname());
    }

    @Test(expected = NotFoundException.class)
    public void updateInform_WhenHaveInvalidIdAndValidInform_ThenThrowException() {

        Inform inform = new Inform(VALID_DEFAULT_INFORM);
        Inform informToUpdate = new Inform(VALID_DEFAULT_INFORM);
        inform.setId(1);
        informToUpdate.setId(1);
        informToUpdate.setFullname("TEST ABC");
        informService.updateInform(2, informToUpdate);

        verify(informRepository, times(1)).save(any(Inform.class));
        assertEquals(informService.getInformById(1).getFullname(), inform.getFullname());
    }

    @Test
    public void deleteInform_WhenHaveValidId_ThenReturnSuccessfull() {

        Inform inform = new Inform(VALID_DEFAULT_INFORM);
        inform.setId(1);

        when(informRepository.findById(any())).thenReturn(Optional.of(inform));
        informService.deleteInform(inform.getId());

        verify(informRepository, times(1)).delete(any(Inform.class));
    }

    @Test(expected = NotFoundException.class)
    public void deleteInform_WhenHaveInvalidId_ThenReturnSuccessfull() {

        Inform inform = new Inform(VALID_DEFAULT_INFORM);
        inform.setId(1);
        informService.deleteInform(2);

        verify(informRepository, times(1)).delete(any(Inform.class));
    }

    @Test
    public void calculatorSingle_WhenHaveValidIds_ThenReturnListCalculatorDto() {

        Inform inform = new Inform(VALID_DEFAULT_INFORM);
        inform.setId(1);
        List<String> ids = new ArrayList<>();
        ids.add(String.valueOf(inform.getId()));

        when(informRepository.findById(any())).thenReturn(Optional.of(inform));
        List<CalculatorDto> result = informService.calculatorSingle(ids);
        assertEquals(result.get(0).getCost(), "5400.0");
    }

    @Test(expected = NotFoundException.class)
    public void calculatorSingle_WhenHaveInvalidId_ThenThrowException() {

        Inform inform = new Inform(VALID_DEFAULT_INFORM);
        inform.setId(1);
        List<String> ids = new ArrayList<>();
        ids.add(String.valueOf("2"));
        List<CalculatorDto> result = informService.calculatorSingle(ids);

        assertEquals(result.get(0).getCost(), "5400.0");
    }

    @Test
    public void paymentDetail_WhenHaveValidId_ThenReturnCalculatorDto() {

        Inform inform = new Inform(VALID_DEFAULT_INFORM);
        inform.setId(1);

        when(informRepository.findById(any())).thenReturn(Optional.of(inform));
        CalculatorDto result = informService.paymentDetail(String.valueOf(inform.getId()));
        assertEquals(result.getCost(), "5400.0");
    }

    @Test(expected = NotFoundException.class)
    public void paymentDetail_WhenHaveInvalidId_ThenThrowException() {

        Inform inform = new Inform(VALID_DEFAULT_INFORM);
        inform.setId(1);

        CalculatorDto result = informService.paymentDetail(String.valueOf("2"));
        assertEquals(result.getCost(), "5400.0");
    }

    @Test
    public void calculatorFamily_WhenHaveValidIds_ThenReturnCalculatorFamilyDto() {

        List<String> ids = new ArrayList<>();
        ids.add("8888");
        List<Inform> informListExpected = new ArrayList<>();
        for (int i = 0; i <= 6; i ++) {
            Inform informMember = new Inform(VALID_DEFAULT_INFORM);
            informMember.setId(i + 1);
            informMember.setFamilyCode("9999");
            informListExpected.add(informMember);
        }

        when(informRepository.findAllByFamilyCode(any())).thenReturn(informListExpected);

        List<CalculatorFamilyDto> result = informService.calculatorFamily(ids);
        assertEquals(result.get(0).getCost(), "268200.0");
    }

    @Test(expected = NotFoundException.class)
    public void calculatorFamily_WhenHaveInvalidIds_ThenThrowException() {

        List<String> ids = new ArrayList<>();
        ids.add("8888");
        Inform informMember1 = new Inform(VALID_DEFAULT_INFORM);
        informMember1.setId(1);
        informMember1.setFamilyCode("9999");
        Inform informMember2 = new Inform(VALID_DEFAULT_INFORM);
        informMember2.setId(2);
        informMember2.setFamilyCode("9999");
        List<Inform> informListExpected = new ArrayList<>();
        informListExpected.add(informMember1);
        informListExpected.add(informMember2);

        List<CalculatorFamilyDto> result = informService.calculatorFamily(ids);
        assertEquals(result.get(0).getCost(), "113985.0");
    }

    @Test
    public void paymentFamilyDetail_WhenHaveValidId_ThenReturnCalculatorFamilyDto() {

        String id = "9999";
        Inform informMember1 = new Inform(VALID_DEFAULT_INFORM);
        informMember1.setId(1);
        informMember1.setFamilyCode("9999");
        Inform informMember2 = new Inform(VALID_DEFAULT_INFORM);
        informMember2.setId(2);
        informMember2.setFamilyCode("9999");
        List<Inform> informListExpected = new ArrayList<>();
        informListExpected.add(informMember1);
        informListExpected.add(informMember2);

        when(informRepository.findAllByFamilyCode(any())).thenReturn(informListExpected);

        CalculatorFamilyDto result = informService.paymentFamilyDetail(id);
        assertEquals(result.getCost(), "113985.0");
    }

    @Test(expected = NotFoundException.class)
    public void paymentFamilyDetail_WhenHaveInvalidId_ThenThrowException() {

        String id = "8888";
        Inform informMember1 = new Inform(VALID_DEFAULT_INFORM);
        informMember1.setId(1);
        informMember1.setFamilyCode("9999");
        Inform informMember2 = new Inform(VALID_DEFAULT_INFORM);
        informMember2.setId(2);
        informMember2.setFamilyCode("9999");
        List<Inform> informListExpected = new ArrayList<>();
        informListExpected.add(informMember1);
        informListExpected.add(informMember2);

        CalculatorFamilyDto result = informService.paymentFamilyDetail(id);
        assertEquals(result.getCost(), "67050.0");
    }
}
