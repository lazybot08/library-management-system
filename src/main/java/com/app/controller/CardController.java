package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.model.Card;
import com.app.service.CardService;

@RestController
@RequestMapping("/card")
public class CardController {

    @Autowired
    private CardService cardService;

    @GetMapping("/findAll")
    public List<Card> getAllCards(){
        return cardService.getAllCards();
    }

    @GetMapping("/find/{cardid}")
    public Card getCardById(@PathVariable("cardid") int cardId){
        return cardService.getCardById(cardId);
    }
}
