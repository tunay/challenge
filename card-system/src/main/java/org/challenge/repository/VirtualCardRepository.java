package org.challenge.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.challenge.domain.VirtualCard;

@ApplicationScoped
public class VirtualCardRepository implements PanacheRepository<VirtualCard> {
}
