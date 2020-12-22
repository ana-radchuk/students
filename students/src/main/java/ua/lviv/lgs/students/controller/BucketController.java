package ua.lviv.lgs.students.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ua.lviv.lgs.students.domain.Bucket;
import ua.lviv.lgs.students.domain.Dossier;
import ua.lviv.lgs.students.domain.Student;
import ua.lviv.lgs.students.service.BucketService;
import ua.lviv.lgs.students.service.DossierService;
import ua.lviv.lgs.students.service.StudentService;

@Controller
public class BucketController {

	@Autowired
	private BucketService bucketService;

	@Autowired
	private StudentService studentService;

	@Autowired
	private DossierService dossierService;

	@RequestMapping(value = "/buckets", method = RequestMethod.GET)
	public ModelAndView getAllItems() {
		return getBucketItems();
	}

	@RequestMapping(value = "/bucket", method = RequestMethod.POST)
	public ModelAndView create(@RequestParam String dossierId) {
		Dossier dossier = dossierService.findById(Integer.parseInt(dossierId));

		Bucket bucket = new Bucket();
		bucket.setDossier(dossier);
		bucket.setRegistrationDate(new Date());

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String studentEmail = auth.getName();
		Student student = studentService.findByEmail(studentEmail);
		bucket.setStudent(student);

		bucketService.add(bucket);
		return getBucketItems();
	}

	@RequestMapping(value = "/bucket", method = RequestMethod.GET)
	public ModelAndView delete(@RequestParam String id) {
		bucketService.delete(new Bucket(Integer.parseInt(id.replaceAll("\\s",""))));
		return getBucketItems();
	}

	private ModelAndView getBucketItems() {
		ModelAndView map = new ModelAndView("bucket");
		map.addObject("bucketItems", bucketService.getAll());
		return map;
	}

}
