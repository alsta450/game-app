package fh.burgenland.gameapp.service;

import fh.burgenland.gameapp.dataaccess.PlayerGuessRepository;
import fh.burgenland.gameapp.model.PlayerGuess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class GameHandler {

    private final PlayerGuessRepository playerGuessRepository;

    @Autowired
    public GameHandler(PlayerGuessRepository playerGuessRepository) {
        this.playerGuessRepository = playerGuessRepository;
    }


    private final Random random = new Random();

    public PlayerGuess createNewGame() {
        PlayerGuess playerGuess = new PlayerGuess();
        playerGuess.setTargetNumber(random.nextInt(100) + 1);
        playerGuess.setTries(0);
        playerGuess.setFinished(false);
        playerGuessRepository.save(playerGuess);
        return playerGuess;
    }

    public String checkGuess(Long id, int guess) {
        Optional<PlayerGuess> playerGuessOptional = playerGuessRepository.findById(id);
        if (playerGuessOptional.isPresent()) {
            PlayerGuess playerGuess = playerGuessOptional.get();
            playerGuess.setTries(playerGuess.getTries() + 1);
            playerGuess.setPlayerGuess(guess);
            if (guess > playerGuess.getTargetNumber()) {
                playerGuess.setMessage("The number is smaller");
            } else if (guess < playerGuess.getTargetNumber()) {
                playerGuess.setMessage("The number is larger");
            } else {
                playerGuess.setMessage("You guessed the number in " + playerGuess.getTries() + " tries!");
                playerGuess.setFinished(true); // Mark the game as finished
            }
            playerGuessRepository.save(playerGuess);
            return playerGuess.getMessage();
        }
        return "Game not found";
    }

    public PlayerGuess getPlayerGuess(Long id) {
        return playerGuessRepository.findById(id).orElse(null);
    }

    public List<PlayerGuess> getFinishedGames() {
        return playerGuessRepository.findByFinishedTrue(Sort.by(Sort.Direction.ASC, "tries")); // Sort by tries in ascending order
    }
}

