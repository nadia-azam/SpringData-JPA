package org.example.springdatajpa.repositories;

import org.example.springdatajpa.entities.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface PatientRepository extends JpaRepository<Patient,Long> {

    // ajout des methode que je veux
    List<Patient> findByMalade(boolean m);

    Page<Patient> findByMalade(boolean m, Pageable pageable); // m la maladie et peageable c est l interface Page qu on va implementer avec PageRequest

    List<Patient> findByMaladeIsTrueAndScoreLessThan(int score);

    List<Patient> findByDateNaissanceBetweenAndMaladeIsTrueOrNomLike(Date d1 , Date d2 , String mc);


    @Query("select p from Patient p where p.nom like :x and p.score<:y")
    List<Patient> chercherPatients(@Param("x") String nom , @Param("y") int scoreMin);


}
