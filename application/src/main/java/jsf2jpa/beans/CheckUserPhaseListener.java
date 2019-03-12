/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jsf2jpa.beans;

import java.util.HashSet;
import java.util.Set;
import javax.enterprise.inject.spi.CDI;
import javax.faces.component.UIViewRoot;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

/**
 * Simple demo strategy to check if a view can be accessed or not in public.
 * 
 * @author Leonardo Uribe
 */
public class CheckUserPhaseListener implements PhaseListener {

    public static final Set<String> PUBLIC_VIEWS = new HashSet<String>();
    
    static
    {
        PUBLIC_VIEWS.add("/home.jsf");
        PUBLIC_VIEWS.add("/home.xhtml");
        PUBLIC_VIEWS.add("/register.xhtml");
        PUBLIC_VIEWS.add("/register.jsf");
    }
    
    @Override
    public void beforePhase(PhaseEvent pe)
    {
    }

    @Override
    public void afterPhase(PhaseEvent pe)
    {
        UIViewRoot root = pe.getFacesContext().getViewRoot();
        if (root != null && !PUBLIC_VIEWS.contains(root.getViewId()))
        {
            BookingSession session = CDI.current().select(BookingSession.class).get();

            if (session == null || (session != null && session.getUser() == null))
            {
                pe.getFacesContext().getApplication().getNavigationHandler().
                        handleNavigation(pe.getFacesContext(), null, "/home.xhtml");
                pe.getFacesContext().renderResponse();
            }
        }
    }

    @Override
    public PhaseId getPhaseId() {
        return PhaseId.RESTORE_VIEW;
    }
    
}
