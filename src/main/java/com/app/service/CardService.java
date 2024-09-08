package com.app.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.model.Card;
import com.app.repository.CardRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CardService {

    @Autowired
    CardRepository cardRepository;

    public List<Card> getAllCards(){
        List<Card> cardList = cardRepository.findAll();
        return cardList;
    }

    public Card getCardById(int cardId){
        Optional<Card> cardOptional = cardRepository.findById(cardId);
        Card card = cardOptional.get();
        return card;
    }
}
