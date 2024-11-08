package com.blog.BlogSpringBoot.controller;

import com.blog.BlogSpringBoot.dto.ReaderDTO;
import com.blog.BlogSpringBoot.entity.Reader;
import com.blog.BlogSpringBoot.service.BlogReaderService;
import com.blog.BlogSpringBoot.service.ReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<?> getReaderById(@PathVariable String nameId) {
        return new ResponseEntity<>(readerService.getReaderByName(nameId), HttpStatus.OK);
    }

    @GetMapping("/Get/name/{name}")
    public ResponseEntity<?> getReaderByName(@PathVariable String name) {
        return new ResponseEntity<>(readerService.getReaderByName(name), HttpStatus.OK);
    }

    @PostMapping("/Post")
    public ResponseEntity<?> postReader(@RequestBody ReaderDTO readerDTO) {
        return new ResponseEntity<>(readerService.saveReader(readerDTO), HttpStatus.OK);
    }

    @PutMapping("/Update/{readerId}")
    public ResponseEntity<?> updateReader(@RequestBody ReaderDTO readerDTO,@PathVariable int readerId) {
        return new ResponseEntity<>(readerService.updateReader(readerId,readerDTO),HttpStatus.OK);
    }
    @PostMapping("/Delete/{readerId}")
    public ResponseEntity<?> deleteReader(@PathVariable int readerId) {
        readerService.deleteReaderById(readerId);
        return new ResponseEntity<>(readerId,HttpStatus.OK);
    }
}
