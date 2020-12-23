package ua.lviv.lgs.students.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.lviv.lgs.students.dao.DossierRepository;
import ua.lviv.lgs.students.domain.Dossier;

@Service
public class DossierService {

	private Logger logger = LoggerFactory.getLogger(DossierService.class);

	@Autowired
	private DossierRepository dossierRepository;

	public Dossier save(Dossier dossier) {
		logger.info("Create dossier {} : " + dossier);
		return dossierRepository.save(dossier);
	}

	public List<Dossier> getAllDossier() {
		logger.info("Get all dossier items");
		return dossierRepository.findAll();
	}

	public Dossier findById(Integer id) {
		logger.info("Get dossier by id: " + id);
		return dossierRepository.findById(id).get();
	}

}
