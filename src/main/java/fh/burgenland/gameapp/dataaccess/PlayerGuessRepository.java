package fh.burgenland.gameapp.dataaccess;

import fh.burgenland.gameapp.model.PlayerGuess;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PlayerGuessRepository extends JpaRepository<PlayerGuess, Long> {
    List<PlayerGuess> findByFinishedTrueAndUserId(Sort sort, String userId);
    Optional<PlayerGuess> findByIdAndUserId(Long id, String userId);
}