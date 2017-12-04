package rest;

import actorservice.ActorService;
import model.Studio;
import model.Studio;
import studioservice.StudioService;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.net.URI;
import java.util.List;

@XmlRootElement
@Path("/studios")
@Transactional
public class StudioResource {
    @PersistenceContext
    private EntityManager em;

    @Context
    private UriInfo uriInfo;

    @Inject
    private StudioService studioService;

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
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
    public Studio retrieveAsJSONXML(@PathParam("pk_studio_id") Long pk_studio_id) {
        return em.find(Studio.class, pk_studio_id);
    }

    @GET
    @Path("/{pk_studio_id}")
    @Produces(MediaType.TEXT_PLAIN)
    public String retrieveAsString(@PathParam("pk_studio_id") Long pk_studio_id) {
        Studio studio = em.find(Studio.class, pk_studio_id);
        return (studio != null ? studio.toString() : null);
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<Studio> getAll() {
        return studioService.getAllStudios();
    }

    @PUT
    @Path("/{pk_studio_id}")
    @Consumes({MediaType.APPLICATION_JSON})
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
    public void delete(@PathParam("pk_studio_id") Long pk_studio_id) {
        Studio studio = em.find(Studio.class, pk_studio_id);
        if(studio != null) {
            em.remove(studio);
        } else {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
    }
}
