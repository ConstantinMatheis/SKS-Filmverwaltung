package service;

import model.Studio;
import org.jboss.ejb3.annotation.SecurityDomain;

import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@SecurityDomain("FilmManagementSD")
@RolesAllowed({"MSRead", "MSWrite"})
public class StudioService implements StudioServiceInterface {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Studio> getAllStudios() {
        return em.createNamedQuery("model.Studio.selectAll", Studio.class)
                .getResultList();
    }
}
