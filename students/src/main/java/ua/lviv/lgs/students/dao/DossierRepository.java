package ua.lviv.lgs.students.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import ua.lviv.lgs.students.domain.Dossier;

public interface DossierRepository extends JpaRepository<Dossier, Integer>{
}
