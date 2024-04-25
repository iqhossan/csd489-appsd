package com.dentist.Service.service.impl;

import com.dentist.Service.model.Dentist;
import com.dentist.Service.repo.DentistRepository;
import com.dentist.Service.service.DentistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class DentistServiceImpl implements DentistService {

    private static final Logger logger = Logger.getLogger(DentistServiceImpl.class.getName());

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
    public List<Dentist> getAllDentist(Integer pageNumber, Integer pageSize) {

        try {
            // Define sorting direction and field
            Sort.Order sortOrder = Sort.Order.desc("dentistId"); // Sorting by 'dentist' field in descending order
            Sort sort = Sort.by(sortOrder);
            PageRequest pageRequest = PageRequest.of(pageNumber , pageSize,sort); // Adjust pageNumber to 0-based index
            Page<Dentist> pageData = this.dentistRepository.findAll(pageRequest);
            List<Dentist> allDentist = pageData.getContent();
            allDentist.forEach(d -> logger.info("Fetched Dentist: " + d.toString()));
            return allDentist;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error fetching branches: " + e.getMessage(), e);
            throw new RuntimeException("Error fetching branches");
        }
    }

    @Override
    public List<Dentist> getDentistList() {
        try {
            // Define sorting direction and field
            List<Dentist> allDentist = this.dentistRepository.findAll(Sort.by("firstName"));
            allDentist.forEach(d -> logger.info("Fetched Dentist: " + d.toString()));
            return allDentist;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error fetching branches: " + e.getMessage(), e);
            throw new RuntimeException("Error fetching branches");
        }
    }

    @Override
    public List<Dentist> getAllDentistWithAppointment(Integer pageNumber, Integer pageSize) {
        return null;
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
