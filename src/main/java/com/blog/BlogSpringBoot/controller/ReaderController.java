package com.blog.BlogSpringBoot.controller;

import com.blog.BlogSpringBoot.dto.ReaderDTO;
import com.blog.BlogSpringBoot.dto.UserDTO;
import com.blog.BlogSpringBoot.entity.Reader;
import com.blog.BlogSpringBoot.service.BlogReaderService;
import com.blog.BlogSpringBoot.service.ReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequestMapping("/reader")
@RestController
public class ReaderController {
    private final ReaderService readerService;
    @Autowired
    public ReaderController(ReaderService readerService) {
        this.readerService = readerService;
    }
    @GetMapping("/Get")
    public ResponseEntity<?> getReader() {
        return new ResponseEntity<>(readerService.getAllReaders(), HttpStatus.OK);
    }

    @GetMapping("/Get/{nameId}")
    public ResponseEntity<?> getReaderById(@PathVariable int nameId) {

        Optional<Reader> reader = readerService.getReaderById(nameId);
        if (reader.isPresent()) {
            return new ResponseEntity<>(reader, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/Get/name/{name}")
    public ResponseEntity<?> getReaderByName(@PathVariable String name) {
        return new ResponseEntity<>(readerService.getReaderByName(name), HttpStatus.OK);
    }

    @PostMapping("/Post")
    public ResponseEntity<?> postReader(@RequestBody UserDTO userDTO , BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity<>(result.getAllErrors().toString(), HttpStatus.BAD_REQUEST);
        }
        readerService.saveUser(userDTO);
        return new ResponseEntity<>("Se creo exitosamente", HttpStatus.CREATED);
    }

    @PutMapping("/Update/{readerId}")
    public ResponseEntity<?> updateReader(@RequestBody UserDTO userDTO,@PathVariable int readerId , BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity<>(result.getAllErrors().toString(), HttpStatus.BAD_REQUEST);
        }
        readerService.updateReaderUser(readerId,userDTO);
        return new ResponseEntity<>("Actualizacion del reader", HttpStatus.OK);
    }
    @PostMapping("/Delete/{readerId}")
    public ResponseEntity<?> deleteReader(@PathVariable int readerId) {
        readerService.deleteReaderById(readerId);
        return new ResponseEntity<>(readerId,HttpStatus.OK);
    }
}
