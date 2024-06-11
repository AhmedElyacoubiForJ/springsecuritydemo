package edu.yacoubi.springsecuritydemo.entites;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Patient {
    @Id @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String name;
    private Date birthDate;
    private boolean isSick;
    private int score;
}

// Bean command runner
   /* @Bean
    public CommandLineRunner commandLineRunner() {
        return args -> {
            System.out.println("Hello World");
        };
    }*/
