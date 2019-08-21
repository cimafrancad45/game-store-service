package com.trilogyed.DarylCimafrancaU1Capstone.controllers;

import com.trilogyed.DarylCimafrancaU1Capstone.exception.NotFoundException;
import com.trilogyed.DarylCimafrancaU1Capstone.service.GameStoreServiceLayer;
import com.trilogyed.DarylCimafrancaU1Capstone.viewmodel.ConsoleViewModel;
import com.trilogyed.DarylCimafrancaU1Capstone.viewmodel.GameViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/game")
public class GameController {
    @Autowired
    GameStoreServiceLayer gameStoreServiceLayer;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public GameViewModel addGame(@RequestBody @Valid GameViewModel game) {
        return gameStoreServiceLayer.addGame(game);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public GameViewModel getGame(@PathVariable("id") int id) {
        GameViewModel game = gameStoreServiceLayer.getGameById(id);
        if (game == null)
            throw new NotFoundException("Console could not be found under id" + id);
        return game;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<GameViewModel> getAllGames(){
        return gameStoreServiceLayer.getAllGames();
    }

    @GetMapping("/title/{title}")
    @ResponseStatus(HttpStatus.OK)
    public List<GameViewModel> getGamesByTitle(@PathVariable("title") String title){
        return gameStoreServiceLayer.getGamesByTitle(title);
    }

    @GetMapping("/studio/{studio}")
    @ResponseStatus(HttpStatus.OK)
    public List<GameViewModel> getGamesByStudio(@PathVariable("studio") String studio) {
        return gameStoreServiceLayer.getGamesByStudio(studio);
    }

    @GetMapping("/ersb/{ersb_rating}")
    @ResponseStatus(HttpStatus.OK)
    public List<GameViewModel> getGamesByErsb(@PathVariable("ersb_rating") String ersbRating){
        return gameStoreServiceLayer.getGamesByErsbRating(ersbRating);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String deleteConsole(@PathVariable("id") int id) {
        gameStoreServiceLayer.deleteGame(id);
        return "Game successfully deleted.";
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String updateGame(@PathVariable("id") int id, @RequestBody @Valid GameViewModel gameViewModel) {
        gameStoreServiceLayer.updateGame(gameViewModel);
        return "Game successfully updated.";
    }
}