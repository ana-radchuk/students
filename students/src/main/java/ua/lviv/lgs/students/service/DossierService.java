package ua.lviv.lgs.students.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.lviv.lgs.students.dao.DossierRepository;
import ua.lviv.lgs.students.domain.Dossier;

@Service
public class DossierService {
	
	@Autowired
	private DossierRepository dossierRepository;
	
	public Dossier save(Dossier dossier) {
		return dossierRepository.save(dossier);
	}
	
	public List<Dossier> getAllDossier(){
		return dossierRepository.findAll();
	}
	
	public Dossier findById(Integer id) {
		return dossierRepository.findById(id).get();
	}

}
