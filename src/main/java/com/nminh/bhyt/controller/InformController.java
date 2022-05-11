package com.nminh.bhyt.controller;

import com.nminh.bhyt.dto.CalculatorDto;
import com.nminh.bhyt.dto.CalculatorFamilyDto;
import com.nminh.bhyt.exception.NotFoundException;
import com.nminh.bhyt.ResponseDto;
import com.nminh.bhyt.model.Inform;
import com.nminh.bhyt.service.InformService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/informs")
@Transactional
public class InformController {

    @Autowired
    InformService informService;

    @GetMapping(value = "")
    ResponseEntity<ResponseDto<Inform>> getListInform(){
        List<Inform> result = informService.getAllListInform();
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto<Inform>("R_200","Success!",result, result.size()));
    }

    @GetMapping(value = "/{id}")
    ResponseEntity<ResponseDto<Inform>> getInform(@PathVariable Integer id){
        Inform result = informService.getInformById(id);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto<>("R_200","Success!", result));
    }

    @PostMapping(value = "")
    ResponseEntity<ResponseDto<Inform>> createInform(@RequestBody Inform request){
        informService.createNewInform(request);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto<>("R_201","Success!"));
    }

    @PatchMapping(value = "/{id}")
    ResponseEntity<ResponseDto<Inform>> updateInform(@PathVariable Integer id,@RequestBody Inform request){
        informService.updateInform(id, request);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto<>("R_200","Success"));
    }

    @DeleteMapping(value = "/{id}")
    ResponseEntity<ResponseDto<Object>> deleteInform(@PathVariable Integer id){
        informService.deleteInform(id);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto<>("R_200","Success!"));
    }

    @RequestMapping(path = "/calculatorSingle", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity calculatorSingle(@RequestBody List<String> ids) {
        List<CalculatorDto> result = informService.calculatorSingle(ids);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(path = "/calculatorFamily", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity calculatorFamily(@RequestBody List<String> ids) {
        List<CalculatorFamilyDto> result = informService.calculatorFamily(ids);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(path = "/paymentDetail", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity paymentDetail(@RequestBody String id) {
        CalculatorDto result = informService.paymentDetail(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(path = "/paymentFamilyDetail", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity paymentDetailFamily(@RequestBody String id) {
        CalculatorFamilyDto result = informService.paymentFamilyDetail(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
