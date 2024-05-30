package fh.burgenland.gameapp.dataaccess;

import fh.burgenland.gameapp.model.PlayerGuess;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlayerGuessRepository extends JpaRepository<PlayerGuess, Long> {
    List<PlayerGuess> findByFinishedTrue(Sort sort); // Method to find only finished games with sorting
}