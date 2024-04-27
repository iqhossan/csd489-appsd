package com.patient.service;

import com.patient.service.model.Patient;
import com.patient.service.repo.PatientRepository;
import com.patient.service.service.impl.PatientServiceImpl;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
class PatientServiceApplicationTests {

	@Mock
	private PatientRepository patientRepository;

	@InjectMocks
	private PatientServiceImpl patientService;

	@Test
	void contextLoads() {
	}

	@Test
	@Order(1)
	public void test_getPatients(){

		List<Patient> patients = new ArrayList<>();
		patients.add(new Patient(null,"iqbal","hossan",
				"641 233 9544","a@gmail.com",100,1L)); //mocking
		patients.add(new Patient(null,"john","doe",
				"987 233 9544","john@gmail.com",200,1L)); //mocking
		patients.add(new Patient(null,"Rey","Brea",
				"123 233 9544","a@gmail.com",100,1L)); //mocking
		when(patientRepository.findAll()).thenReturn(patients);
		assertEquals(3,patientService.getPatientsExcludePagination().size());
	}

	@Test
	@Order(2)
	public void test_getPatient() throws Exception {
		List<Patient> patients = new ArrayList<>();
		patients.add(new Patient(101L,"iqbal","hossan",
				"641 233 9544","a@gmail.com",100,1L));
		patients.add(new Patient(102L,"john","doe",
				"987 233 9544","john@gmail.com",200,1L));
		patients.add(new Patient(103L,"Rey","Brea",
				"123 233 9544","a@gmail.com",100,1L));
		Long pid = 102L;

		when(patientRepository.findById(pid)).thenReturn(Optional.ofNullable(patients.stream()
				.filter(p -> p.getPatientId().equals(pid))
				.findFirst()
				.orElse(null)));
		assertEquals(102L,patientService.getPatient(pid).getPatientId());
	}

	@Test
	@Order(3)
	public void test_addPatient(){
		Patient patient =new Patient(101L,"iqbal","hossan",
				"641 233 9544","a@gmail.com",100,1L);
		when(patientRepository.save(patient)).thenReturn(patient);
		assertEquals(patient,patientService.addPatient(patient));
	}

	@Test
	@Order(4)
	public void test_updatePatient(){
		Patient patient =new Patient(101L,"iqbal","hossan",
				"641 233 9544","a@gmail.com",100,1L);
		when(patientRepository.save(patient)).thenReturn(patient);
		assertEquals(patient,patientService.updatePatient(patient));
	}

	@Test
	@Order(5)
	public void test_deletePatient(){
		Patient patient =new Patient(101L,"iqbal","hossan",
				"641 233 9544","a@gmail.com",100,1L);
		Long pid = 101L;
		patientRepository.deleteById(pid);
		verify(patientRepository, times(1)).deleteById(pid);
	}


}
