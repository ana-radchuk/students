package ua.lviv.lgs.students;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import ua.lviv.lgs.students.dao.BucketRepository;
import ua.lviv.lgs.students.dao.DossierRepository;
import ua.lviv.lgs.students.dao.StudentRepository;
import ua.lviv.lgs.students.domain.Bucket;
import ua.lviv.lgs.students.domain.Dossier;
import ua.lviv.lgs.students.domain.Student;
import ua.lviv.lgs.students.domain.UserRole;
import ua.lviv.lgs.students.service.BucketService;
import ua.lviv.lgs.students.service.DossierService;
import ua.lviv.lgs.students.service.StudentService;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ApplicationTests {

	@Autowired
	private StudentService studentService;

	@Autowired
	private DossierService dossierService;

	@Autowired
	private BucketService bucketService;

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private DossierRepository dossierRepository;

	@Autowired
	private BucketRepository bucketRepository;

	@Test
	public void testSaveStudent() {
		List<Student> students = studentRepository.findAll();
		assertThat(students, hasSize(0));

		Student student = new Student();
		student.setEmail("student@email.com");
		student.setFirstName("Ivan");
		student.setLastName("Ivanenko");
		student.setPassword("1234");
		student.setPasswordConfirm("1234");
		student.setRole(UserRole.ROLE_STUDENT);

		studentService.save(student);

		students = studentRepository.findAll();
		assertThat(students, hasSize(1));

		Student studentFromDb = students.get(0);
		assertTrue(studentFromDb.getEmail().equals(student.getEmail()));
		assertTrue(studentFromDb.getFirstName().equals(student.getFirstName()));
		assertTrue(studentFromDb.getLastName().equals(student.getLastName()));
		assertTrue(studentFromDb.getRole().equals(student.getRole()));
	}

	@Test
	public void testFindByEmail() {
		List<Student> students = studentRepository.findAll();
		assertThat(students, hasSize(0));

		Student student = new Student();
		student.setEmail("custom@email.com");
		student.setFirstName("Petro");
		student.setLastName("Petrenko");
		student.setPassword("4321");
		student.setPasswordConfirm("4321");
		student.setRole(UserRole.ROLE_STUDENT);

		studentRepository.save(student);

		students = studentRepository.findAll();
		assertThat(students, hasSize(1));

		Student findByEmail = studentService.findByEmail(student.getEmail());

		assertTrue(findByEmail.getEmail().equals(student.getEmail()));
		assertTrue(findByEmail.getFirstName().equals(student.getFirstName()));
		assertTrue(findByEmail.getLastName().equals(student.getLastName()));
		assertTrue(findByEmail.getRole().equals(student.getRole()));
	}

	@Test
	public void testSavePeriodical() {
		List<Dossier> dossiers = dossierRepository.findAll();
		assertThat(dossiers, hasSize(0));

		Dossier dossier = new Dossier();
		dossier.setName("Ivan Ivanenko");
		dossier.setDescription("1st year, Faculty of Arts");
		dossier.setEncodedPhoto("upload");
		dossier.setPoints(99.0);

		dossierService.save(dossier);

		dossiers = dossierRepository.findAll();
		assertThat(dossiers, hasSize(1));

		Dossier dossierFromDb = dossiers.get(0);
		assertTrue(dossierFromDb.getName().equals(dossier.getName()));
		assertTrue(dossierFromDb.getDescription().equals(dossier.getDescription()));
		assertTrue(dossierFromDb.getEncodedPhoto().equals(dossier.getEncodedPhoto()));
		assertTrue(dossierFromDb.getPoints().equals(dossier.getPoints()));
	}

	@Test
	public void testFindById() {
		List<Dossier> dossiers = dossierRepository.findAll();
		assertThat(dossiers, hasSize(0));

		Dossier dossier = new Dossier();
		dossier.setName("Petro Petrenko");
		dossier.setDescription("2nd year, Faculty of Sciences");
		dossier.setEncodedPhoto("upload");
		dossier.setPoints(99.5);

		dossierRepository.save(dossier);

		dossiers = dossierRepository.findAll();
		assertThat(dossiers, hasSize(1));

		Dossier dossierFromDb = dossierService.findById(dossiers.get(0).getId());

		assertTrue(dossierFromDb.getName().equals(dossier.getName()));
		assertTrue(dossierFromDb.getDescription().equals(dossier.getDescription()));
		assertTrue(dossierFromDb.getEncodedPhoto().equals(dossier.getEncodedPhoto()));
		assertTrue(dossierFromDb.getPoints().equals(dossier.getPoints()));
	}

	@Test
	public void testGetAllDossier() {
		List<Dossier> dossiers = dossierRepository.findAll();
		assertThat(dossiers, hasSize(0));

		Dossier dossier = new Dossier();
		dossier.setName("Petro Petrenko");
		dossier.setDescription("2nd year, Faculty of Sciences");
		dossier.setEncodedPhoto("upload");
		dossier.setPoints(99.5);

		Dossier dossier2 = new Dossier();
		dossier2.setName("Taras Tarasenko");
		dossier2.setDescription("3rd year, Faculty of Law");
		dossier2.setEncodedPhoto("upload");
		dossier2.setPoints(87.5);

		dossierRepository.saveAll(Arrays.asList(dossier, dossier2));

		dossiers = dossierRepository.findAll();
		assertThat(dossiers, hasSize(2));

		List<Dossier> dossierFromDb = dossierService.getAllDossier();

		assertTrue(dossierFromDb.get(0).getName().equals(dossier.getName()));
		assertTrue(dossierFromDb.get(0).getDescription().equals(dossier.getDescription()));
		assertTrue(dossierFromDb.get(0).getEncodedPhoto().equals(dossier.getEncodedPhoto()));
		assertTrue(dossierFromDb.get(0).getPoints().equals(dossier.getPoints()));

		assertTrue(dossierFromDb.get(1).getName().equals(dossier2.getName()));
		assertTrue(dossierFromDb.get(1).getDescription().equals(dossier2.getDescription()));
		assertTrue(dossierFromDb.get(1).getEncodedPhoto().equals(dossier2.getEncodedPhoto()));
		assertTrue(dossierFromDb.get(1).getPoints().equals(dossier2.getPoints()));
	}

	@Test
	public void testAddToBucket() {
		Student student = new Student();
		student.setEmail("student@email.com");
		student.setFirstName("Ivan");
		student.setLastName("Ivanenko");
		student.setPassword("1234");
		student.setPasswordConfirm("1234");
		student.setRole(UserRole.ROLE_STUDENT);

		studentService.save(student);

		Student studentFromDb = studentRepository.findAll().stream().findFirst().get();

		List<Dossier> dossiers = dossierRepository.findAll();
		assertThat(dossiers, hasSize(0));

		Dossier dossier = new Dossier();
		dossier.setName("Petro Petrenko");
		dossier.setDescription("2nd year, Faculty of Sciences");
		dossier.setEncodedPhoto("upload");
		dossier.setPoints(99.5);

		dossierService.save(dossier);

		Dossier dossierFromDb = dossierRepository.findAll().stream().findFirst().get();

		Date now = new Date();
		Bucket bucket = new Bucket();
		bucket.setDossier(dossierFromDb);
		bucket.setStudent(studentFromDb);
		bucket.setRegistrationDate(now);

		List<Bucket> buckets = bucketRepository.findAll();
		assertThat(buckets, hasSize(0));

		bucketService.add(bucket);

		buckets = bucketRepository.findAll();
		assertThat(buckets, hasSize(1));

		Bucket bucketFromDb = buckets.get(0);

		assertTrue(bucketFromDb.getDossier().equals(dossierFromDb));
		assertTrue(bucketFromDb.getStudent().equals(studentFromDb));
		assertTrue(bucketFromDb.getRegistrationDate().equals(now));
	}

	@Test
	public void testDeleteFromBucket() {
		Student student = new Student();
		student.setEmail("student@email.com");
		student.setFirstName("Ivan");
		student.setLastName("Ivanenko");
		student.setPassword("1234");
		student.setPasswordConfirm("1234");
		student.setRole(UserRole.ROLE_STUDENT);
		
		studentService.save(student);

		Student studentFromDb = studentRepository.findAll().stream().findFirst().get();

		List<Dossier> dossiers = dossierRepository.findAll();
		assertThat(dossiers, hasSize(0));

		Dossier dossier = new Dossier();
		dossier.setName("Petro Petrenko");
		dossier.setDescription("2nd year, Faculty of Sciences");
		dossier.setEncodedPhoto("upload");
		dossier.setPoints(99.5);

		Dossier dossier2 = new Dossier();
		dossier2.setName("Taras Ivanenko");
		dossier2.setDescription("2nd year, Faculty of Sciences");
		dossier2.setEncodedPhoto("upload");
		dossier2.setPoints(92.5);
		
		dossierRepository.saveAll(Arrays.asList(dossier, dossier2));

		List<Dossier> dossierFromDb = dossierRepository.findAll();

		Date now = new Date();
		Bucket bucket = new Bucket();
		bucket.setDossier(dossierFromDb.get(0));
		bucket.setStudent(studentFromDb);
		bucket.setRegistrationDate(now);

		Bucket bucket2 = new Bucket();
		bucket2.setDossier(dossierFromDb.get(1));
		bucket2.setStudent(studentFromDb);
		bucket2.setRegistrationDate(now);

		List<Bucket> buckets = bucketRepository.findAll();
		assertThat(buckets, hasSize(0));

		bucketRepository.saveAll(Arrays.asList(bucket, bucket2));

		buckets = bucketRepository.findAll();
		assertThat(buckets, hasSize(2));

		bucketService.delete(buckets.get(0));

		buckets = bucketRepository.findAll();
		assertThat(buckets, hasSize(1));
	}

	@Test
	public void testGetAll() {
		Student student = new Student();
		student.setEmail("student@email.com");
		student.setFirstName("Ivan");
		student.setLastName("Ivanenko");
		student.setPassword("1234");
		student.setPasswordConfirm("1234");
		student.setRole(UserRole.ROLE_STUDENT);

		studentService.save(student);

		Student studentFromDb = studentRepository.findAll().stream().findFirst().get();

		List<Dossier> dossiers = dossierRepository.findAll();
		assertThat(dossiers, hasSize(0));

		Dossier dossier = new Dossier();
		dossier.setName("Petro Petrenko");
		dossier.setDescription("2nd year, Faculty of Sciences");
		dossier.setEncodedPhoto("upload");
		dossier.setPoints(99.5);
		
		Dossier dossier2 = new Dossier();
		dossier2.setName("Ivan Petrenko");
		dossier2.setDescription("2nd year, Faculty of Arts");
		dossier2.setEncodedPhoto("upload");
		dossier2.setPoints(96.5);

		dossierRepository.saveAll(Arrays.asList(dossier, dossier2));

		List<Dossier> dossierFromDb = dossierRepository.findAll();

		Date now = new Date();
		Bucket bucket = new Bucket();
		bucket.setDossier(dossierFromDb.get(0));
		bucket.setStudent(studentFromDb);
		bucket.setRegistrationDate(now);

		Bucket bucket2 = new Bucket();
		bucket2.setDossier(dossierFromDb.get(1));
		bucket2.setStudent(studentFromDb);
		bucket2.setRegistrationDate(now);

		List<Bucket> buckets = bucketRepository.findAll();
		assertThat(buckets, hasSize(0));

		bucketRepository.saveAll(Arrays.asList(bucket, bucket2));

		List<Bucket> bucketsFromDb = bucketService.getAll();
		assertThat(bucketsFromDb, hasSize(2));
	}

}