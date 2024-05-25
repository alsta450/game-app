package fh.burgenland.gameapp.service;

import fh.burgenland.gameapp.dataaccess.GameRepository;
import fh.burgenland.gameapp.model.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameHandler {

    private final GameRepository gameRepository;

    @Autowired
    public GameHandler(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }


    public Game getRandomNumber() {
        return gameRepository.save(new Game((long) (Math.random() * 100) + 1));
    }

    public String compareNumber(long gameId, long numberToCheck) {
        Game game = gameRepository.findById(gameId).orElseThrow();
        if (isNumberLarger(game.getCurrentNumber(), numberToCheck)) {
            return "Your number is larger";
        } else if (isNumberSmaller(game.getCurrentNumber(), numberToCheck)) {
            return "Your number is smaller";
        } else {
            return "You guessed the number!";
        }
    }

    private boolean isNumberLarger(long currentNumber, long numberToCheck) {
        return numberToCheck > currentNumber;
    }

    private boolean isNumberSmaller(long currentNumber, long numberToCheck) {
        return numberToCheck < currentNumber;
    }
}

