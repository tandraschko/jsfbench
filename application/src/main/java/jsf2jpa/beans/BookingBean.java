/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jsf2jpa.beans;

import java.io.Serializable;
import javax.inject.Named;
import jsf2jpa.entity.Booking;

/**
 *
 * @author lu4242
 */
@Named("bookingBean")
@javax.enterprise.context.SessionScoped
public class BookingBean implements Serializable {
    
    private Booking booking;

    public BookingBean()
    {
    }
    
    /**
     * @return the booking
     */
    public Booking getBooking() {
        return booking;
    }

    /**
     * @param booking the booking to set
     */
    public void setBooking(Booking booking) {
        this.booking = booking;
    }
    
}
