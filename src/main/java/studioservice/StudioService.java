package studioservice;

import model.Actor;
import model.Studio;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class StudioService implements StudioServiceInterface {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Studio> getAllStudios() {
        return em.createNamedQuery("model.Studio.selectAll", Studio.class)
                .getResultList();
    }
}
