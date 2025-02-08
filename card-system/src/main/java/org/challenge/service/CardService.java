package org.challenge.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.challenge.domain.Account;
import org.challenge.domain.Card;
import org.challenge.domain.User;
import org.challenge.repository.AccountRepository;
import org.challenge.repository.CardActivityLogRepository;
import org.challenge.repository.CardRepository;
import org.challenge.repository.UserRepository;

@ApplicationScoped
public class CardService {

    @Inject
    CardRepository cardRepository;

    @Inject
    AccountRepository accountRepository;

    @Inject
    CardActivityLogRepository cardActivityLogRepository;

    @Transactional
    public Card saveCard(Card card) {
        cardRepository.persist(card);
        return card;
    }

    @Transactional
    public boolean cancelCardByCard(Card card) {
        try {
            cardRepository.delete(card);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public Card getCard(Long cardId) {
        return cardRepository.findById(cardId);
    }

//    public Card findByCardId(Integer cardId) {
//        return cardRepository.getByCardNumber(cardId);
//    }
}
