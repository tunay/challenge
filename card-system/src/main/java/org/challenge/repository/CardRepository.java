package org.challenge.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.challenge.domain.Card;

import java.util.List;

@ApplicationScoped
public class CardRepository implements PanacheRepository<Card> {

    @Inject
    EntityManager em;

    public List<Card> list() {
        return listAll();
    }

    public Card listById(Long id) {
        return findById(id);
    }

    public Card getByCardNumber(Integer cardNumber) {
        String sql = "SELECT * FROM Cards c WHERE c.card_number ";
        Query query = em.createNativeQuery(sql);
        return (Card) query.getSingleResult();
    }

//    @Transactional
//    public Card save(Card card) {
//        persist(card);
//        return card;
//    }
}
