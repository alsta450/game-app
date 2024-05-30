package fh.burgenland.gameapp.controller;

import fh.burgenland.gameapp.service.GameHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HighscoreController {

    private final GameHandler service;

    public HighscoreController(@Autowired GameHandler service) {
        this.service = service;
    }

    @GetMapping("/randomNumber/highscore")
    public String getHighscores(Model model) {
        model.addAttribute("highscores", service.getFinishedGames());
        return "highscore";
    }
}