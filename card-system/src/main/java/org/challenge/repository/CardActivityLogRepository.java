package org.challenge.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.challenge.domain.CardActivityLog;

@ApplicationScoped
public class CardActivityLogRepository implements PanacheRepository<CardActivityLog> {
}
