package org.challenge.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.challenge.domain.Account;
import org.challenge.repository.AccountRepository;

@ApplicationScoped
public class AccountService {

    @Inject
    AccountRepository accountRepository;

    public Account getAccountById(Long id) {
        return accountRepository.findById(id);
    }
    @Transactional
    public void createAccount(Account account) {
        accountRepository.persist(account);
    }
}
