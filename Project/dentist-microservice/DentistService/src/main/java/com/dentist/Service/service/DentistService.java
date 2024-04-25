package com.dentist.Service.service;

import com.dentist.Service.model.Dentist;
import java.util.List;

public interface DentistService {

    Dentist registerDentist(Dentist dentist);
    Dentist updateDentist(Dentist dentist);
    List<Dentist> getAllDentist(Integer pageNumber, Integer pageSize);
    List<Dentist> getDentistList();
    List<Dentist> getAllDentistWithAppointment(Integer pageNumber, Integer pageSize);
    Dentist getDentistById(Long dentistId);
    boolean deleteDentist(Long dentistId);
}
