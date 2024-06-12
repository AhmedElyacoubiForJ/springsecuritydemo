package edu.yacoubi.springsecuritydemo.entites;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Patient {
    @Id @GeneratedValue(strategy = IDENTITY)
    private Long id;
    @NotEmpty @Size(min = 4, max =20)
    private String name;
    private LocalDate birthday;
    private boolean isSick;
    @Min(10)
    private int score;
}

// Bean command runner
   /* @Bean
    public CommandLineRunner commandLineRunner() {
        return args -> {
            System.out.println("Hello World");
        };
    }*/
