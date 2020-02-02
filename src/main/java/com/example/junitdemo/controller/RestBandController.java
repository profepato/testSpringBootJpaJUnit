package com.example.junitdemo.controller;

import com.example.junitdemo.model.Band;
import com.example.junitdemo.service.BandService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.management.InstanceAlreadyExistsException;
import javax.xml.ws.Response;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/bands")
public class RestBandController {

    @Autowired
    private BandService bandService;

    @GetMapping
    public ResponseEntity getBands(){
        return ResponseEntity.ok(bandService.readAll());
    }

    @PostMapping
    public ResponseEntity postBand(@RequestBody Band band){
        try {
            bandService.create(band);
            return ResponseEntity.ok(band);
        } catch (InstanceAlreadyExistsException e) {
            HashMap<String, String> response = new HashMap<>();
            response.put("message", "Band is already created");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
        }
    }

    @DeleteMapping("/{bandId}")
    public ResponseEntity deleteBand(@PathVariable Integer bandId){
        boolean deleted = bandService.delete(bandId);
        HashMap<String, Boolean> response = new HashMap<>();

        response.put("deleted", deleted);

        return ResponseEntity.ok(response);
    }

    @PutMapping
    public ResponseEntity putBand(@RequestBody Band band){
        try {
            band = bandService.update(band);
            return ResponseEntity.ok(band);
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }

    }

    @GetMapping("/avg")
    public ResponseEntity getCreationYearAverage(){
        HashMap<String, Integer> response = new HashMap<>();
        response.put("avg", bandService.getCreationYearAverage());
        return ResponseEntity.ok(response);
    }
}
