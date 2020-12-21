package ua.lviv.lgs.students.service;

import java.io.IOException;
import java.util.Base64;

import org.springframework.web.multipart.MultipartFile;

import ua.lviv.lgs.students.domain.Dossier;

public class DossierDTOHelper {

	
	public static Dossier createEntity(MultipartFile file, String name, String description, Double points) throws IOException {
		Dossier dossier = new Dossier();
		dossier.setName(name);
		dossier.setDescription(description);
		dossier.setPrice(points);
		dossier.setEncodedImage(Base64.getEncoder().encodeToString(file.getBytes()));
		
		return dossier;
	}
}
