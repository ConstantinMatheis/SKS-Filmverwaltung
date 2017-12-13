package rest;

import org.jboss.ejb3.annotation.SecurityDomain;
import service.ActorService;
import model.Actor;
import service.ActorServiceInterface;

import javax.annotation.Resource;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.DenyAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.net.URI;
import java.security.Principal;
import java.util.List;
import java.util.logging.Logger;

@Stateless
@SecurityDomain("FilmManagementSD")
@DeclareRoles({"MSRead", "MSWrite"})
@XmlRootElement
@Path("/actors")
@Transactional
public class ActorResource {
    private final static Logger LOGGER = Logger.getLogger("ActorResource");

    @PersistenceContext
    private EntityManager em;

    @Context
    private UriInfo uriInfo;

    @Inject
    private ActorServiceInterface actorService;

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @RolesAllowed("MSWrite")
    public Response create(Actor actor) {
        em.persist(actor);
        URI uri = uriInfo.getAbsolutePathBuilder()
                .path(Long.toString(actor.getPk_actor_id()))
                .build();
        return Response.created(uri).build();
    }

    @GET
    @Path("/{setPk_actor_id}")
    @Produces({MediaType.APPLICATION_JSON})
    @RolesAllowed({"MSRead", "MSWrite"})
    public Actor retrieveAsJSONXML(@PathParam("setPk_actor_id") Long setPk_actor_id) {
        return em.find(Actor.class, setPk_actor_id);
    }

    @GET
    @Path("/{setPk_actor_id}")
    @Produces(MediaType.TEXT_PLAIN)
    @RolesAllowed({"MSRead", "MSWrite"})
    public String retrieveAsString(@PathParam("setPk_actor_id") Long setPk_actor_id) {
        Actor actor = em.find(Actor.class, setPk_actor_id);
        return (actor != null ? actor.toString() : null);
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @RolesAllowed({"MSRead", "MSWrite"})
    public List<Actor> getAll() {
        return actorService.getAllActors();
    }

    @PUT
    @Path("/{setPk_actor_id}")
    @Consumes({MediaType.APPLICATION_JSON})
    @RolesAllowed("MSWrite")
    public void update(@PathParam("setPk_actor_id") Long setPk_actor_id, Actor actor) {
        Actor actorOld = em.find(Actor.class, setPk_actor_id);
        if(actorOld != null) {
            actorOld.setBirthday(actor.getBirthday());
            actorOld.setFilms(actor.getFilms());
            actorOld.setFirst_name(actor.getFirst_name());
            actorOld.setGender(actor.getGender());
            actorOld.setLast_name(actor.getLast_name());
        } else {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
    }

    // if the actor is referenced in film_actor table - it will not be deleted (probably feature and not a bug)
    @DELETE
    @Path("/{setPk_actor_id}")
    @RolesAllowed("MSWrite")
    public void delete(@PathParam("setPk_actor_id") Long setPk_actor_id) {
        Actor actor = em.find(Actor.class, setPk_actor_id);
        if(actor != null) {
            em.remove(actor);
        } else {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
    }
}
