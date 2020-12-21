package ua.lviv.lgs.students.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import ua.lviv.lgs.students.dao.StudentRepository;
import ua.lviv.lgs.students.domain.Student;
import ua.lviv.lgs.students.domain.UserRole;

@Service
public class StudentService{
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private PasswordEncoder bCryptPasswordEncoder;


    public void save(Student student) {
        student.setPassword(bCryptPasswordEncoder.encode(student.getPassword()));
        student.setPassword(bCryptPasswordEncoder.encode(student.getPasswordConfirm()));
        student.setRole(UserRole.ROLE_STUDENT);
        studentRepository.save(student);
    }
    
    public Student findByEmail(String email) {
    	return studentRepository.findByEmail(email).get();
    }

}
