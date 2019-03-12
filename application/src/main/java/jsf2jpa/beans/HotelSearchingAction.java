package jsf2jpa.beans;


import java.io.Serializable;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.Query;


@Named("hotelSearch")
@javax.enterprise.context.RequestScoped
public class HotelSearchingAction extends SimpleAction implements Serializable
{
    private transient BookingApplication bookingApplication;

    @Inject
    private HotelBean hotelBean;
    
    /**
     * @return the bookingApplication
     */
    public BookingApplication getBookingApplication() 
    {
        if (bookingApplication == null)
        {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            bookingApplication = facesContext.getApplication().
                    evaluateExpressionGet(facesContext, "#{bookingApplication}",
                    BookingApplication.class);
        }
        return bookingApplication;
    }

    public void find(AjaxBehaviorEvent event)
    {
        getHotelBean().setPage(0);
        queryHotels();
    }
    
    public void find(ActionEvent event)
    {
        getHotelBean().setPage(0);
        queryHotels();
    }

    public void find()
    {
        getHotelBean().setPage(0);
        queryHotels();
    }
    
    public void nextPage() {
        getHotelBean().setPage(getHotelBean().getPage()+1);
        queryHotels();
    }
    
    public boolean isNextPageAvailable()
    {
        return getHotelBean().isNextPageAvailable();
    }

    private void queryHotels()
    {
        String pattern = getSearchString() == null ? "%" : '%' + getSearchString().toLowerCase().replace('*', '%') + '%';
        Query query = getEntityManager().createQuery("select h from Hotel h"
                + " where lower(h.name) like :pattern"
                + " or lower(h.city) like :pattern"
                + " or lower(h.zip) like :pattern"
                + " or lower(h.address) like :pattern");
        query.setParameter("pattern", pattern);
        query.setMaxResults(getHotelBean().getPageSize());
        query.setFirstResult(getHotelBean().getPage() * getHotelBean().getPageSize());
        getHotelBean().setHotels(query.getResultList());
    }

    public String getSearchString() {
        return getHotelBean().getSearchString();
    }

    public void setSearchString(String searchString) {
        this.getHotelBean().getSearchString();
    }

    /**
     * @return the hotelBean
     */
    public HotelBean getHotelBean() {
        return hotelBean;
    }

    /**
     * @param hotelBean the hotelBean to set
     */
    public void setHotelBean(HotelBean hotelBean) {
        this.hotelBean = hotelBean;
    }
}
