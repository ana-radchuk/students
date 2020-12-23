package ua.lviv.lgs.students.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import ua.lviv.lgs.students.service.DossierDTOHelper;
import ua.lviv.lgs.students.service.DossierService;

@Controller
public class DossierController {

	@Autowired
	DossierService dossierService;

	@RequestMapping(value = "/addDossier", method = RequestMethod.POST)
	public ModelAndView createDossier(
			@RequestParam MultipartFile photo, 
			@RequestParam String name, 
			@RequestParam String description, 
			@RequestParam Double points) throws IOException {		
		
		dossierService.save(DossierDTOHelper.createEntity(photo, name, description, points));
		return new ModelAndView("redirect:/home");
	}
}
