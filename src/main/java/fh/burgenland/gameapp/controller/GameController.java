package fh.burgenland.gameapp.controller;

import fh.burgenland.gameapp.model.Game;
import fh.burgenland.gameapp.service.GameHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GameController {
    private final GameHandler gameHandler;

    @Autowired
    public GameController(GameHandler gameHandler) {
        this.gameHandler = gameHandler;
    }

    @GetMapping("/randomNumber")
    public Game getRandomNumber() {
        return gameHandler.getRandomNumber();
    }

    @GetMapping("/randomNumber/{gameId}/{numberToCheck}")
    public String compareNumber(@PathVariable long gameId, @PathVariable long numberToCheck) {
        return gameHandler.compareNumber(gameId, numberToCheck);
    }
}
