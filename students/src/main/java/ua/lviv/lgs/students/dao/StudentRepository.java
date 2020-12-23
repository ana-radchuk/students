package ua.lviv.lgs.students.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import ua.lviv.lgs.students.domain.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {
	public Optional<Student> findByEmail(String email);
}
