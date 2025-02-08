package org.challenge.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.challenge.domain.Account;
import org.challenge.domain.Card;
import org.challenge.domain.User;
import org.challenge.domain.VirtualCard;
import org.challenge.repository.UserRepository;

@ApplicationScoped
public class UserService {

    @Inject
    UserRepository userRepository;

    @Inject
    AccountService accountService;

    @Inject
    CardService cardService;

    @Inject
    VirtualCardService virtualCardService;

    public User findById(Long userId) {
        return userRepository.findById(userId);
    }
    @Transactional
    public void createUser(User user) {
        userRepository.persist(user);
    }

    @Transactional
    public Account createAccount(User user, Account account) {
        account.setUser(user);
        accountService.createAccount(account);
        return account;
    }

    @Transactional
    public Card createCard(Account account, Card card) {
        card.setAccount(account);
        cardService.saveCard(card);
        return card;
    }

    @Transactional
    public VirtualCard createVirtualCard(Account account, VirtualCard virtualCard) {
        virtualCard.setAccount(account);
        virtualCardService.saveVirtualCard(virtualCard);
        return virtualCard;
    }
}