package org.challenge.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.challenge.domain.Account;

@ApplicationScoped
public class AccountRepository implements PanacheRepository<Account> {
}
