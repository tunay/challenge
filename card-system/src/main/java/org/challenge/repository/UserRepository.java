package org.challenge.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.challenge.domain.User;

@ApplicationScoped
public class UserRepository implements PanacheRepository<User> {}
