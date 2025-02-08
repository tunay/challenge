package org.challenge.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.challenge.domain.VirtualCard;
import org.challenge.repository.VirtualCardRepository;

@ApplicationScoped
public class VirtualCardService {

    @Inject
    VirtualCardRepository virtualCardRepository;
    @Transactional
    public void saveVirtualCard(VirtualCard card) {
        virtualCardRepository.persist(card);
    }
}
