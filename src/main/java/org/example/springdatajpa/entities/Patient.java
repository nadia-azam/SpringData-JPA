package org.example.springdatajpa.entities;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class Patient {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;

    @Column(name="NOM" , length = 50) //par defaur varchar(255)
    private  String nom ;

    @Temporal(TemporalType.DATE) // jour moi et annees , sinon time heur minute et sec , timestamp inclut les 2 case
    private Date dateNaissance;
    private boolean malade ;
    private int score;
}
