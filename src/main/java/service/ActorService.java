package service;

import model.Actor;
import org.jboss.ejb3.annotation.SecurityDomain;
import service.ActorServiceInterface;

import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
@SecurityDomain("FilmManagementSD")
@RolesAllowed({"MSRead", "MSWrite"})
public class ActorService implements ActorServiceInterface {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Actor> getAllActors() {
        return em.createNamedQuery("model.Actor.selectAll", Actor.class)
                .getResultList();
    }
}
