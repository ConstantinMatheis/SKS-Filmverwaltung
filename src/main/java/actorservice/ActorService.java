package actorservice;

import model.Actor;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class ActorService implements ActorServiceInterface {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Actor> getAllActors() {
        return em.createNamedQuery("model.Actor.selectAll", Actor.class)
                .getResultList();
    }
}
