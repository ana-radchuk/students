package ua.lviv.lgs.students.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import ua.lviv.lgs.students.domain.Bucket;

public interface BucketRepository extends JpaRepository<Bucket, Integer>{
}
