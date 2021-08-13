package com.sawan.springdata.clinicalsapi.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sawan.springdata.clinicalsapi.dto.ClinicalDataRequest;
import com.sawan.springdata.clinicalsapi.model.ClinicalData;
import com.sawan.springdata.clinicalsapi.model.Patient;
import com.sawan.springdata.clinicalsapi.repos.ClinicalDataRepository;
import com.sawan.springdata.clinicalsapi.repos.PatientRepository;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class ClinicalDataController {

	private PatientRepository patientRepository;
	private ClinicalDataRepository clinicalDataRepository;

	ClinicalDataController(PatientRepository patientRepository, ClinicalDataRepository clinicalDataRepository) {
		this.patientRepository = patientRepository;
		this.clinicalDataRepository = clinicalDataRepository;
	}

	@RequestMapping(value = "/clinicals", method = RequestMethod.POST)
	public ClinicalData saveClinicalData(@RequestBody ClinicalDataRequest request) {
		Patient patient = patientRepository.findById(request.getPatientId()).get();
		ClinicalData data = new ClinicalData();
		data.setComponentName(request.getComponentName());
		data.setComponentValue(request.getComponentValue());
		data.setPatient(patient);
		
		return clinicalDataRepository.save(data);
	}

}
