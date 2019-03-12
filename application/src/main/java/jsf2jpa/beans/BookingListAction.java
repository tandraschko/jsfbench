package jsf2jpa.beans;


import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import jsf2jpa.entity.Booking;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Named("bookingList")
@SessionScoped
public class BookingListAction implements Serializable {

    protected static final Logger log = LoggerFactory.getLogger(HotelBookingAction.class);        
    
    @Inject
    private BookingSession bookingSession;

    private List<Booking> bookings;

    public void loadBookings()
    {
        loadBookings(getEntityManager());
    }
    
    public void loadBookings(EntityManager em)
    {
        Query query = em.createQuery("select b from Booking b"
                + " where b.user.username = :username order by b.checkinDate");
        query.setParameter("username", getBookingSession().getUser().getUsername());
        bookings = query.getResultList();
    }    

    public void cancel(Booking booking) {
        if (booking == null)
        {
            return;   
        }
        if (BookingApplication.LOG_ENABLED)
        {
            log.info("Cancel booking: "+booking.getId()+" for "+booking.getUser().getUsername());
        }
        EntityManager em = getEntityManager();
        Booking cancelled = em.find(Booking.class, booking.getId());
        if (cancelled != null) {
            em.getTransaction().begin();
            em.remove(cancelled);
            em.getTransaction().commit();
        }
        loadBookings(em);
        FacesContext facesContext = FacesContext.getCurrentInstance();
        facesContext.addMessage(null, new FacesMessage("Booking cancelled for confirmation number "+cancelled.getId()));
    }

    /**
     * @return the bookings
     */
    public List<Booking> getBookings() {
        if (bookings == null)
        {
            loadBookings();
        }
        return bookings;
    }

    /**
     * @return the hotelSearchAction
     */
    EntityManager getEntityManager()
    {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        return facesContext.getApplication().evaluateExpressionGet(
                facesContext, "#{jpaRequestCycle}", JpaRequestCycle.class).getEntityManager();
    }

    /**
     * @return the bookingSession
     */
    public BookingSession getBookingSession() {
        return bookingSession;
    }

    /**
     * @param bookingSession the bookingSession to set
     */
    public void setBookingSession(BookingSession bookingSession) {
        this.bookingSession = bookingSession;
    }
    
    public boolean isPageEmpty()
    {
        return !(bookings != null && bookings.size() > 0);
    }

}
