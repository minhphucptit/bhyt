package com.nminh.bhyt.controller;

import com.nminh.bhyt.exception.NotFoundException;
import com.nminh.bhyt.ResponseDto;
import com.nminh.bhyt.model.Inform;
import com.nminh.bhyt.repository.InformRepository;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/informs")
@Transactional
public class InformApi {
    @Autowired
    InformRepository informRepository;

    @Autowired
    MapperFacade mapperFacade;

    @GetMapping(value = "")
    ResponseEntity<ResponseDto<Inform>> getListInform(){
        List<Inform> list = informRepository.findAll();

        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto<>("R_200","Success!",list, list.size()));
    }

    @GetMapping(value = "/{id}")
    ResponseEntity<ResponseDto<Inform>> getInform(@PathVariable Integer id){
        Optional<Inform> optional = informRepository.findById(id);

        if(!optional.isPresent()){
            throw new NotFoundException("Inform not exits!");
        }

        Inform inform = optional.get();

        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto<>("R_200","Success!",inform));
    }

    @PostMapping(value = "")
    ResponseEntity<ResponseDto<Inform>> createInform(@RequestBody Inform request){
        Inform inform = new Inform();

        mapperFacade.map(request,inform);

        informRepository.save(inform);

        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto<>("R_201","Success!",inform));
    }

    @PatchMapping(value = "/{id}")
    ResponseEntity<ResponseDto<Inform>> updateInform(@PathVariable Integer id,@RequestBody Inform request){
        Optional<Inform> optional = informRepository.findById(id);

        if(!optional.isPresent()){
            throw new NotFoundException("Inform not exits!");
        }

        Inform inform = optional.get();

        mapperFacade.map(request,inform);

        informRepository.save(inform);

        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto<>("R_200","Success",inform));
    }

    @DeleteMapping(value = "/{id}")
    ResponseEntity<ResponseDto<Object>> deleteInform(@PathVariable Integer id){
        Optional<Inform> optional = informRepository.findById(id);

        if(!optional.isPresent()){
            throw new NotFoundException("Inform not exits!");
        }

        informRepository.delete(optional.get());

        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto<>("R_200","Success!"));
    }

}
