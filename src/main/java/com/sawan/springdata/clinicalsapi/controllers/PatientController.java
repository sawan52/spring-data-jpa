package com.sawan.springdata.clinicalsapi.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sawan.springdata.clinicalsapi.model.ClinicalData;
import com.sawan.springdata.clinicalsapi.model.Patient;
import com.sawan.springdata.clinicalsapi.repos.PatientRepository;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class PatientController {

	private PatientRepository patientRepository;
	private Map<String, String> filters = new HashMap<>();

	@Autowired
	public PatientController(PatientRepository patientRepository) {
		this.patientRepository = patientRepository;
	}

	@RequestMapping(value = "/patients", method = RequestMethod.GET)
	public List<Patient> getAllPatients() {
		return patientRepository.findAll();
	}

	@RequestMapping(value = "/patients/{id}", method = RequestMethod.GET)
	public Patient getPatient(@PathVariable("id") int id) {
		return patientRepository.findById(id).get();
	}

	@RequestMapping(value = "/patients", method = RequestMethod.POST)
	public Patient savePatient(@RequestBody Patient patient) {
		return patientRepository.save(patient);
	}

	@RequestMapping(value = "/patients/analyze/{id}", method = RequestMethod.GET)
	public Patient analyze(@PathVariable("id") int id) {
		Patient patient = patientRepository.findById(id).get();
		List<ClinicalData> data = patient.getClinicalData();

		ArrayList<ClinicalData> duplicateData = new ArrayList<>(data);
		for (ClinicalData eachEntry : duplicateData) {

			if (filters.containsKey(eachEntry.getComponentName())) {
				data.remove(eachEntry);
				continue;
			} else {
				filters.put(eachEntry.getComponentName(), null);
			}

			if (eachEntry.getComponentName().equals("hw")) {
				String[] heightAndWeight = eachEntry.getComponentValue().split("/");
				if (heightAndWeight != null && heightAndWeight.length > 1) {

					float heightInMetres = Float.parseFloat(heightAndWeight[0]) * 0.4536F;
					float bmi = Float.parseFloat(heightAndWeight[1]) / (heightInMetres * heightInMetres);

					ClinicalData bmiData = new ClinicalData();
					bmiData.setComponentName("bmi");
					bmiData.setComponentValue(Float.toString(bmi));
					data.add(bmiData);
				}

			}
		}
		filters.clear();
		return patient;
	}

}
