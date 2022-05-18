package com.nminh.bhyt.controller;

import com.nminh.bhyt.ResponseDto;
import com.nminh.bhyt.exception.NotFoundException;
import com.nminh.bhyt.model.HouseHold;
import com.nminh.bhyt.repository.HoldHouseRepository;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/hold-house")
@Transactional
public class HoldHouseApi {
    @Autowired
    HoldHouseRepository holdHouseRepository;
    @Autowired
    MapperFacade mapperFacade;
    @GetMapping(value = "")
    ResponseEntity<ResponseDto<HouseHold>> getListHouseHold() {
        List<HouseHold> list = holdHouseRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto<>("R_200", "Success!", list, list.size()));
    }
    @GetMapping(value = "/{id}")
    ResponseEntity<ResponseDto<HouseHold>>getHouseHold(@PathVariable Integer id){
        Optional<HouseHold> optional= holdHouseRepository.findById(id);
        if(!optional.isPresent()){
            throw new NotFoundException("HouseHold not exits!");
        }

        HouseHold houseHold = optional.get();

        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto<>("R_200","Success!",houseHold));
    }
    @PostMapping(value = "")
    ResponseEntity<ResponseDto<HouseHold>>createHouseHold(@RequestBody HouseHold reqquest){
        HouseHold houseHold= new HouseHold();
        mapperFacade.map(reqquest,houseHold);
        holdHouseRepository.save(houseHold);
        return ResponseEntity.status(HttpStatus.OK).body((new ResponseDto<>("R_200","Success",houseHold)));
    }
    @PatchMapping(value = "/{id}")
    ResponseEntity<ResponseDto<HouseHold>> updateHousehold(@PathVariable Integer id,@RequestBody HouseHold request){
        Optional<HouseHold> optional = holdHouseRepository.findById(id);

        if(!optional.isPresent()){
            throw new NotFoundException("HouseHold not exits!");
        }

        HouseHold houseHold = optional.get();

        mapperFacade.map(request,houseHold);

        holdHouseRepository.save(houseHold);

        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto<>("R_200","Success",houseHold));
    }

    @DeleteMapping(value = "/{id}")
    ResponseEntity<ResponseDto<Object>> deleteHouseHold(@PathVariable Integer id){
        Optional<HouseHold> optional = holdHouseRepository.findById(id);

        if(!optional.isPresent()){
            throw new NotFoundException("HouseHold not exits!");
        }

        holdHouseRepository.delete(optional.get());

        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto<>("R_200","Success!"));
    }

}

