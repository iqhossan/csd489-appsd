package com.dentist.Service.service.impl;


import com.dentist.Service.model.Dentist;
import com.dentist.Service.repo.DentistRepository;
import com.dentist.Service.service.DentistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DentistServiceImpl implements DentistService {

    @Autowired
    private DentistRepository dentistRepository;

    @Override
    public Dentist registerDentist(Dentist dentist) {
        return this.dentistRepository.save(dentist);
    }

    @Override
    public Dentist updateDentist(Dentist dentist) {
        return this.dentistRepository.save(dentist);
    }

    @Override
    public List<Dentist> getAllDentist() {
        return this.dentistRepository.findAll();
    }

    @Override
    public Dentist getDentistById(Long dentistId) {
        return this.dentistRepository.findById(dentistId).get();
    }

    @Override
    public boolean deleteDentist(Long dentistId) {
        this.dentistRepository.deleteById(dentistId);
        return true;
    }
}
