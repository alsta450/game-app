package fh.burgenland.gameapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
public class Game {

    public Game(Long currentNumber) {
        this.currentNumber = currentNumber;
    }

    public Game() {
        // No args constructor for JPA
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long currentNumber;


    public Long getId() {
        return id;
    }

    public Long getCurrentNumber() {
        return currentNumber;
    }

    @Override
    public String toString() {
        return "Game{" +
                "id=" + id +
                ", currentNumber=" + currentNumber +
                '}';
    }
}
