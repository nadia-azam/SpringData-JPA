package org.example.springdatajpa;

import org.example.springdatajpa.entities.Patient;
import org.example.springdatajpa.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Date;
import java.util.List;

@SpringBootApplication
public class SpringDataJpaApplication implements CommandLineRunner {

	@Autowired
	private PatientRepository patientRepository ;
	@Override
	public void run(String... args) throws Exception {

		for (int i= 0 ; i<100 ; i++){
			patientRepository.save(new Patient(null , "nadia" , new Date() , Math.random()>0.5 ? true : false , (int)(Math.random()*10)));
		}



		// ca c est de la pagination
		Page<Patient> patients = patientRepository.findAll(PageRequest.of(0 , 3)); // radi t9ssem la liste suivant des tranche de 5patient , ila bghit 5 lewlin (id mn 1 l 5) je met 0 5  , l 5 li morahom  1 5 , endhom l id mn 6 heta l 11

		System.out.println("Total page :"+patients.getTotalPages());
		System.out.println("Total elements :"+patients.getTotalElements());
		System.out.println("Num page :"+patients.getNumber());

		List<Patient> content = patients.getContent();   // si je veut la premiere page c est a dire les 5 premier patient je remplace byMalade par content

		// exploitation des methodes que j ai ajouté dans l interface
		Page<Patient> byMalade = patientRepository.findByMalade(true , PageRequest.of(0,4));


		List<Patient> patientList = patientRepository.chercherPatients("%n%" , 30);

		patientList.forEach( p ->{
			System.out.println("========================");
			System.out.println(p.getId());
			System.out.println(p.getNom());
			System.out.println(p.getScore());
			System.out.println(p.getDateNaissance());
			System.out.println(p.isMalade());
		});


		System.out.println("***********************");
		Patient patient = patientRepository.findById(1L).orElse(null);

		if(patient != null){
			System.out.println(patient.getNom());
			System.out.println(patient.isMalade());
		}

		patient.setScore(870);
		patientRepository.save(patient); // là save fait le update , il fait le insert lorsque le id est null sinon il fait le update

		//patientRepository.deleteById(1L);
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringDataJpaApplication.class, args);
	}

}
