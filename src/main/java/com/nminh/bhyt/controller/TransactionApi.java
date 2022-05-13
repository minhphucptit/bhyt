//package com.nminh.bhyt.controller;
//
//import com.nminh.bhyt.ResponseDto;
//import com.nminh.bhyt.exception.NotFoundException;
//import com.nminh.bhyt.model.Transaction;
//import com.nminh.bhyt.repository.TransactionRepository;
//import ma.glasnost.orika.MapperFacade;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//import java.util.Optional;
//
//@RestController
//@Transactional
//@RequestMapping(value = "/api/transaction")
//public class TransactionApi {
//    @Autowired
//    TransactionRepository transactionRepository;
//    @Autowired
//    MapperFacade mapperFacade;
//    @GetMapping(value = "")
//    ResponseEntity<ResponseDto<Transaction>> getListTransaction(){
//        List<Transaction> list= transactionRepository.findAll();
//        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto<>("R_200", "Success!", list, list.size()));
//    }
//    @GetMapping(value = "/{id}")
//    ResponseEntity<ResponseDto<Transaction>>getTransaction(@PathVariable Integer id){
//        Optional<Transaction> optional= transactionRepository.findById(id);
//        if(!optional.isPresent()){
//            throw new NotFoundException("Transaction not exits!");
//        }
//
//        Transaction transaction = optional.get();
//
//        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto<>("R_200","Success!",transaction));
//    }
//    @PostMapping(value = "")
//    ResponseEntity<ResponseDto<Transaction>>createTransaction(@RequestBody Transaction reqquest){
//        Transaction transaction= new Transaction();
//        mapperFacade.map(reqquest,transaction);
//        transactionRepository.save(transaction);
//        return ResponseEntity.status(HttpStatus.OK).body((new ResponseDto<>("R_200","Success",transaction)));
//    }
//    @PatchMapping(value = "/{id}")
//    ResponseEntity<ResponseDto<Transaction>> updateTransaction(@PathVariable Integer id,@RequestBody Transaction request){
//        Optional<Transaction> optional = transactionRepository.findById(id);
//
//        if(!optional.isPresent()){
//            throw new NotFoundException("Transaction not exits!");
//        }
//
//        Transaction transaction = optional.get();
//
//        mapperFacade.map(request,transaction);
//
//        transactionRepository.save(transaction);
//
//        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto<>("R_200","Success",transaction));
//    }
//
//    @DeleteMapping(value = "/{id}")
//    ResponseEntity<ResponseDto<Object>> deleteTransaction(@PathVariable Integer id){
//        Optional<Transaction> optional = transactionRepository.findById(id);
//
//        if(!optional.isPresent()){
//            throw new NotFoundException("Tracsaction not exits!");
//        }
//
//        transactionRepository.delete(optional.get());
//
//        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto<>("R_200","Success!"));
//    }
//}
