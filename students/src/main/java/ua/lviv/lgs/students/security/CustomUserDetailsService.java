package ua.lviv.lgs.students.security;

import java.util.Collections;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ua.lviv.lgs.students.dao.StudentRepository;
import ua.lviv.lgs.students.domain.Student;

@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private StudentRepository studentRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		Optional<Student> studentOptional = studentRepository.findByEmail(email);

		if (studentOptional.isPresent()) {
			Student student = studentOptional.get();
			return new CustomUserDetails(student, Collections.singletonList(student.getRole().toString()));
		}

		throw new UsernameNotFoundException("No user present with useremail:" + email);
	}

}