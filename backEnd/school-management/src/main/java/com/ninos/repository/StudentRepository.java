package com.ninos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ninos.model.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {

    // ##### spring data JPA #######
//    List<Student> findStudentsByFirstNameOrLastName(String firstName, String lastName);

    // ##### JPQL #######
    @Query("select s from Student as s where s.firstName like %:name% or s.lastName like %:name%")
    List<Student> findStudentsByName(@Param("name") String name);
//    @Query("select s from Student as s where s.firstName like %:name% or s.lastName like %:name%")
//    Page<Student> findStudentsByName(@Param("name") String name, PageRequest pageRequest);


    @Query("select s from Student as s where s.user.email=:email")
    Student findStudentByEmail(@Param("email") String email);

}
