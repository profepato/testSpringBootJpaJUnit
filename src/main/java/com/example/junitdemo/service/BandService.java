package com.example.junitdemo.service;

import com.example.junitdemo.model.Band;
import javassist.NotFoundException;

import javax.management.InstanceAlreadyExistsException;
import java.util.List;

public interface BandService {
    void create(Band band) throws InstanceAlreadyExistsException;

    List<Band> readAll();

    Band update(Band band) throws NotFoundException;

    boolean delete(Integer id);

    Integer getCreationYearAverage();

}
