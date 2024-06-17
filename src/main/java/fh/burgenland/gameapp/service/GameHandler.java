package fh.burgenland.gameapp.service;

import fh.burgenland.gameapp.dataaccess.PlayerGuessRepository;
import fh.burgenland.gameapp.model.PlayerGuess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
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
        OAuth2User user = ((OAuth2User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        PlayerGuess playerGuess = new PlayerGuess();
        playerGuess.setTargetNumber(random.nextInt(100) + 1);
        playerGuess.setTries(0);
        playerGuess.setFinished(false);
        playerGuess.setUserId((String) user.getAttributes().get("sub"));
        playerGuess.setName((String) user.getAttributes().get("name"));
        playerGuessRepository.save(playerGuess);
        return playerGuess;
    }

    public String checkGuess(Long id, int guess) {
        OAuth2User user = ((OAuth2User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        Optional<PlayerGuess> playerGuessOptional = playerGuessRepository.findByIdAndUserId(id, (String) user.getAttributes().get("sub"));
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
        OAuth2User user = ((OAuth2User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        return playerGuessRepository.findByFinishedTrueAndUserId(Sort.by(Sort.Direction.ASC, "tries"), (String) user.getAttributes().get("sub")); // Sort by tries in ascending order
    }
}

