package com.nminh.bhyt.service;

import com.nminh.bhyt.dto.CalculatorDto;
import com.nminh.bhyt.dto.CalculatorFamilyDto;
import com.nminh.bhyt.exception.BadRequestException;
import com.nminh.bhyt.exception.NotFoundException;
import com.nminh.bhyt.model.Inform;
import com.nminh.bhyt.repository.InformRepository;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class InformService {

    @Autowired
    MapperFacade mapperFacade;

    @Autowired
    InformRepository informRepository;

    private final String BASE_SALARY = "1490000";

    public List<Inform> getAllListInform() {

        List<Inform> result = informRepository.findAll();
        return result;
    }

    public Inform getInformById(Integer id) {

        Optional<Inform> result = informRepository.findById(id);
        if(!result.isPresent()){
            throw new NotFoundException("Hồ sơ không tồn tại");
        }
        return result.get();
    }

    public void createNewInform(Inform request) {

        validateInform(request);
        Inform inform = new Inform();
        mapperFacade.map(request, inform);
        informRepository.save(inform);
    }

    public void updateInform(Integer id, Inform request) {

        Optional<Inform> optional = informRepository.findById(id);
        if(!optional.isPresent()){
            throw new NotFoundException("Inform not exits!");
        }
        Inform inform = optional.get();
        mapperFacade.map(request,inform);
        informRepository.save(inform);
    }

    public void deleteInform(Integer id) {

        Optional<Inform> optional = informRepository.findById(id);
        if(!optional.isPresent()){
            throw new NotFoundException("Inform not exits!");
        }
        informRepository.delete(optional.get());
    }

    public List<CalculatorDto> calculatorSingle(List<String> ids) {

        List<CalculatorDto> result = new ArrayList<>();
        for (String id: ids) {
            Optional<Inform> optional = informRepository.findById(Integer.parseInt(id));
            if(!optional.isPresent()){
                throw new NotFoundException("Inform not exits!");
            }
            CalculatorDto dto = new CalculatorDto();
            dto.setId(optional.get().getId());
            dto.setFullname(optional.get().getFullname());
            dto.setCode(optional.get().getCode());
            dto.setCmnd(optional.get().getCmnd());
            dto.setSalary(String.valueOf(optional.get().getSalary()));
            dto.setCost(String.valueOf(optional.get().getSalary().floatValue() * 4.5 / 100));
            dto.setOption("");
            result.add(dto);
        }
        return result;
    }

    public CalculatorDto paymentDetail(String id) {

        CalculatorDto result = new CalculatorDto();
        Optional<Inform> optional = informRepository.findById(Integer.parseInt(id));
        if(!optional.isPresent()){
            throw new NotFoundException("Inform not exits!");
        };
        result.setId(optional.get().getId());
        result.setFullname(optional.get().getFullname());
        result.setCode(optional.get().getCode());
        result.setCmnd(optional.get().getCmnd());
        result.setSalary(String.valueOf(optional.get().getSalary()));
        result.setCost(String.valueOf(optional.get().getSalary().floatValue() * 4.5 / 100));
        result.setLastPayment(optional.get().getLastPayment());
        return result;
    }

    public List<CalculatorFamilyDto> calculatorFamily(List<String> ids) {
        List<CalculatorFamilyDto> result = new ArrayList<>();
        for (String id: ids) {
            List<String> idsMemember = new ArrayList<>();
            List<Inform> familyMemember = informRepository.findAllByFamilyCode(String.valueOf(id));
            if (familyMemember.size() <= 0) {
                 throw new NotFoundException("Family not exits!");
            }
            for (Inform person: familyMemember) {
                idsMemember.add(String.valueOf(person.getId()));
            }
            CalculatorFamilyDto dto = new CalculatorFamilyDto();
            dto.setHousehold(familyMemember.get(0).getHousehold());
            dto.setFamilyCode(familyMemember.get(0).getFamilyCode());
            dto.setBaseSalary(this.BASE_SALARY);
            dto.setCount(String.valueOf(familyMemember.size()));
            dto.setCost(getCostFamily(familyMemember.size()));
            dto.setOption("");
            dto.setIds(idsMemember);
            result.add(dto);
        }
        return result;
    }

    public CalculatorFamilyDto paymentFamilyDetail(String id) {

        CalculatorFamilyDto result = new CalculatorFamilyDto();
        List<Inform> familyMemember = informRepository.findAllByFamilyCode(String.valueOf(id));
        if (familyMemember.size() <= 0) {
            throw new NotFoundException("Family not exits!");
        }
        result.setHousehold(familyMemember.get(0).getHousehold());
        result.setFamilyCode(familyMemember.get(0).getFamilyCode());
        result.setBaseSalary(this.BASE_SALARY);
        result.setCount(String.valueOf(familyMemember.size()));
        result.setCost(getCostFamily(familyMemember.size()));
        result.setLastPayment(familyMemember.get(0).getLastPayment());
        return result;
    }

    private String getCostFamily(Integer numberMember) {

        double cost = 0;
        if (numberMember >= 1) {
          cost += Double.parseDouble(this.BASE_SALARY) * 4.5 / 100;
        }
        if (numberMember >= 2) {
          cost += Double.parseDouble(this.BASE_SALARY) * (4.5 / 100) * (70 / 100);
        }
        if (numberMember >= 3) {
          cost += Double.parseDouble(this.BASE_SALARY) * (4.5 / 100) * (60 / 100);
        }
        if (numberMember >= 4) {
          cost += Double.parseDouble(this.BASE_SALARY) * (4.5 / 100) * (50 / 100);
        }
        if (numberMember >= 5) {
          for (int i = 0; i <= (numberMember - 5); i++) {
            cost += Double.parseDouble(this.BASE_SALARY) * (4.5 / 100) * (40 / 100);
          }
        }
        return String.valueOf(cost);
    }

    private void validateInform(Inform inform) {

        if (inform.getType() == null) {
            throw new BadRequestException("Loại bảo hiểm y tế không được để trống");
        } else if (inform.getType() > 1 || inform.getType() < 0) {
            throw new BadRequestException("Loại bảo hiểm y tế không hợp lệ");
        }
        if (inform.getTarget() == null) {
            throw new BadRequestException("Đối tượng tham gia không được để trống");
        } else if (inform.getTarget() > 1 || inform.getTarget() < 0) {
            throw new BadRequestException("Đối tượng tham gia không hợp lệ");
        }
        if (inform.getFullname() == null) {
            throw new BadRequestException("Họ và tên người tham gia BHYT không được để trống");
        } else if (inform.getFullname().compareTo(inform.getFullname().toUpperCase()) != 0) {
            throw new BadRequestException("Họ và tên người tham gia BHYT phải viết bằng chữ in hoa");
        }
        if (inform.getCode() == null) {
            throw new BadRequestException("Số thẻ BHYT không được để trống");
        }
        if (inform.getCmnd() == null) {
            throw new BadRequestException("Số cmnd/cccd/hộ chiếu không được để trống");
        }
        if (inform.getBirthday() == null) {
            throw new BadRequestException("Ngày sinh không được để trống");
        }
        if (inform.getGender() == null) {
            throw new BadRequestException("Giới tính không được để trống");
        } else if (inform.getGender() > 1 || inform.getGender() < 0) {
            throw new BadRequestException("Giới tính không hợp lệ");
        }
        if (inform.getNation() == null) {
            throw new BadRequestException("Quốc tịch không được để trống");
        }
        if (inform.getGeocode() == null) {
            throw new BadRequestException("Mã vùng không được để trống");
        }
        if (inform.getPhoneNumber() == null) {
            throw new BadRequestException("Số điện thoại không được để trống");
        }
        if (inform.getDkcb() == null) {
            throw new BadRequestException("Nơi đăng ký khám chữa bệnh ban đầu không được để trống");
        }
        if (inform.getSalary() == null) {
            throw new BadRequestException("Thu nhập cá nhân không được để trống");
        }
        if (inform.getType() == 1 && inform.getTarget() != 1) {
            throw new BadRequestException("Bảo hiểm tự nguyện bắt buộc phải đăng ký theo hộ gia đình");
        }
        if (inform.getTarget() == 1) {

            if (inform.getHousehold() == null || inform.getFamilyCode() == null || inform.getPhoneContact() == null || inform.getAddressHousehold() == null) {
                throw new BadRequestException("Đối tượng tham gia là hộ gia đình bắt buộc phải điền phụ lục hộ gia đình");
            }
        }
    }
}
