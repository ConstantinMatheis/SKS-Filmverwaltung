package rest;

import model.Studio;
import org.jboss.ejb3.annotation.SecurityDomain;
import service.StudioService;
import service.StudioServiceInterface;

import javax.annotation.security.DeclareRoles;
import javax.annotation.security.DenyAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.net.URI;
import java.util.List;

@Stateless
@SecurityDomain("FilmManagementSD")
@DeclareRoles({"MSRead", "MSWrite"})
@XmlRootElement
@Path("/studios")
@Transactional
public class StudioResource {
    @PersistenceContext
    private EntityManager em;

    @Context
    private UriInfo uriInfo;

    @Inject
    private StudioServiceInterface studioService;

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @RolesAllowed("MSWrite")
    public Response create(Studio studio) {
        em.persist(studio);
        URI uri = uriInfo.getAbsolutePathBuilder()
                .path(Long.toString(studio.getPk_studio_id()))
                .build();
        return Response.created(uri).build();
    }

    @GET
    @Path("/{pk_studio_id}")
    @Produces({MediaType.APPLICATION_JSON})
    @RolesAllowed({"MSRead", "MSWrite"})
    public Studio retrieveAsJSONXML(@PathParam("pk_studio_id") Long pk_studio_id) {
        return em.find(Studio.class, pk_studio_id);
    }

    @GET
    @Path("/{pk_studio_id}")
    @Produces(MediaType.TEXT_PLAIN)
    @RolesAllowed({"MSRead", "MSWrite"})
    public String retrieveAsString(@PathParam("pk_studio_id") Long pk_studio_id) {
        Studio studio = em.find(Studio.class, pk_studio_id);
        return (studio != null ? studio.toString() : null);
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @RolesAllowed({"MSRead", "MSWrite"})
    public List<Studio> getAll() {
        return studioService.getAllStudios();
    }

    @PUT
    @Path("/{pk_studio_id}")
    @Consumes({MediaType.APPLICATION_JSON})
    @RolesAllowed("MSWrite")
    public void update(@PathParam("pk_studio_id") Long pk_studio_id, Studio studio) {
        Studio studioOld = em.find(Studio.class, pk_studio_id);
        if(studioOld != null) {
            studioOld.setCountrycode(studio.getCountrycode());
            studioOld.setFilms(studio.getFilms());
            studioOld.setFounded_year(studio.getFounded_year());
            studioOld.setHeadquarters(studioOld.getHeadquarters());
            studioOld.setName(studio.getName());
            studioOld.setPostcode(studio.getPostcode());
        } else {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
    }

    @DELETE
    @Path("/{pk_studio_id}")
    @RolesAllowed("MSWrite")
    public void delete(@PathParam("pk_studio_id") Long pk_studio_id) {
        Studio studio = em.find(Studio.class, pk_studio_id);
        if(studio != null) {
            em.remove(studio);
        } else {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
    }
}
