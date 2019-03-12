package jsf2jpa.beans;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import jsf2jpa.entity.User;

@Named("changePassword")
@RequestScoped
public class ChangePasswordAction extends SimpleAction {

    private User user;

    @Inject
    private BookingSession bookingSession;
    
    @PostConstruct
    public void init()
    {
        user = getBookingSession().getUser();
    }
    
    public User getUser()
    {
        if (user == null)
        {
            user = new User();
        }
        return user;
    }    
    
    private String verify;

    public String changePassword() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        if (user.getPassword().equals(verify))
        {
            try
            {
                getEntityManager().getTransaction().begin();
                user = getEntityManager().merge(user);
                getEntityManager().getTransaction().commit();
            }
            catch(Exception e)
            {
                facesContext.addMessage(null, new FacesMessage("Error when register user on database"));
                if (getEntityManager().getTransaction().isActive())
                {
                    getEntityManager().getTransaction().rollback();
                }
            }
            facesContext.addMessage(null, new FacesMessage("Password updated"));
            return "main";
        } else {
            facesContext.addMessage("register:verify", new FacesMessage("Re-enter new password"));
            revertUser();
            verify = null;
            return null;
        }
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

    private void revertUser() {
        user = getEntityManager().find(User.class, user.getUsername());
    }

    public String getVerify() {
        return verify;
    }

    public void setVerify(String verify) {
        this.verify = verify;
    }
}
