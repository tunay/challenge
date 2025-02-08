package org.challenge.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.challenge.domain.CardActivityLog;
import org.challenge.repository.CardActivityLogRepository;

@ApplicationScoped
public class CardActivityLogService {

    @Inject
    CardActivityLogRepository cardActivityLogRepository;

    @Transactional
    public void saveCardActivityLog(CardActivityLog cardActivityLog) {
        cardActivityLogRepository.persist(cardActivityLog);
    }
}
