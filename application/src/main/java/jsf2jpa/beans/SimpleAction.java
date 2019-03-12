/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jsf2jpa.beans;

import javax.inject.Inject;
import javax.persistence.EntityManager;

/**
 *
 * @author lu4242
 */
public class SimpleAction
{
    @Inject
    private JpaRequestCycle jpaRequestCycle;
    
    EntityManager getEntityManager()
    {
        return jpaRequestCycle.getEntityManager();
    }

    /**
     * @return the bookingApplication
     */
    public BookingApplication getBookingApplication() {
        return jpaRequestCycle.getBookingApplication();
    }

    /**
     * @param bookingApplication the bookingApplication to set
     */
    public void setBookingApplication(BookingApplication bookingApplication) {
        jpaRequestCycle.setBookingApplication(bookingApplication);
    }

    /**
     * @return the jpaRequestCycle
     */
    public JpaRequestCycle getJpaRequestCycle() {
        return jpaRequestCycle;
    }

    /**
     * @param jpaRequestCycle the jpaRequestCycle to set
     */
    public void setJpaRequestCycle(JpaRequestCycle jpaRequestCycle) {
        this.jpaRequestCycle = jpaRequestCycle;
    }
}
