package fh.burgenland.gameapp.controller;

import fh.burgenland.gameapp.model.PlayerGuess;
import fh.burgenland.gameapp.service.GameHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GameController {
    private final GameHandler gameHandler;

    @Autowired
    public GameController(GameHandler gameHandler) {
        this.gameHandler = gameHandler;
    }

    @GetMapping("/randomNumber")
    public String startGame(Model model) {
        PlayerGuess playerGuess = gameHandler.createNewGame();
        model.addAttribute("playerGuess", playerGuess);
        return "index";
    }

    @PostMapping("/randomNumber/game")
    public String makeGuess(@RequestParam Long id, @RequestParam int guess, Model model) {
        String message = gameHandler.checkGuess(id, guess);
        PlayerGuess playerGuess = gameHandler.getPlayerGuess(id);
        model.addAttribute("playerGuess", playerGuess);
        model.addAttribute("message", message);
        return "index";
    }
}
