package com.example.junitdemo.service.impl;

import com.example.junitdemo.model.Band;
import com.example.junitdemo.repository.BandRepository;
import com.example.junitdemo.service.BandService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.InstanceAlreadyExistsException;
import java.util.List;

@Service
public class BandServiceImpl implements BandService {

    @Autowired
    private BandRepository bandRepository;

    @Override
    public void create(Band band) throws InstanceAlreadyExistsException {
        Band bandByName = bandRepository.findByName(band.getName());

        if(bandByName != null){
            throw new InstanceAlreadyExistsException();
        }

        bandRepository.save(band);
    }

    @Override
    public List<Band> readAll() {
        return bandRepository.findAll();
    }

    @Override
    public Band update(Band band) throws NotFoundException {
        Integer bandId = band.getId();
        Band dbBand = bandRepository.findById(bandId).orElse(null);

        if(dbBand != null){
            dbBand.setName(band.getName());
            dbBand.setCreationYear(band.getCreationYear());

            return bandRepository.save(band);
        }

        throw new NotFoundException("Band is not found");
    }

    @Override
    public boolean delete(Integer id) {
        boolean deleted;
        try{
            Band band = bandRepository.findById(id).orElse(null);

            if(band != null){
                bandRepository.delete(band);
                deleted = true;
            }else{
                deleted = false;
            }
        }catch (Exception ex){
            deleted = false;
        }

        return deleted;
    }

    @Override
    public Integer getCreationYearAverage() {
        return bandRepository.getCreationYearAverage();
    }
}
