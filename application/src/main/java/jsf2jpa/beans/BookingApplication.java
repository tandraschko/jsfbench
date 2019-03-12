package jsf2jpa.beans;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.model.SelectItem;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@Named("bookingApplication")
@javax.enterprise.context.ApplicationScoped
public class BookingApplication {

    public static final boolean LOG_ENABLED = false;

    private EntityManagerFactory emf;

    private SelectItem[] months;

    private SelectItem[] years;

    @PostConstruct
    public void init() {
        emf = Persistence.createEntityManagerFactory("bookingDatabase");
        getMonths();
        getYears();

        EntityManager em = emf.createEntityManager();

 
        insert(em, "insert into Customer (username, password, name) values ('gavin', 'foobar', 'Gavin King')");
        insert(em, "insert into Customer (username, password, name) values ('demo', 'demo', 'Demo User')");
        for (int i = 1; i <= 200; i++) {
            insert(em, "insert into Customer (username, password, name) values ('demo" + i + "', 'demo', 'Demo User')");
        }

        insert(em, "insert into Hotel (id, price, name, address, city, state, zip, country) values (1, 120, 'Marriott Courtyard', 'Tower Place, Buckhead', 'Atlanta', 'GA', '30305', 'USA')");
        insert(em, "insert into Hotel (id, price, name, address, city, state, zip, country) values (2, 180, 'Doubletree', 'Tower Place, Buckhead', 'Atlanta', 'GA', '30305', 'USA')");
        insert(em, "insert into Hotel (id, price, name, address, city, state, zip, country) values (3, 450, 'W Hotel', 'Union Square, Manhattan', 'NY', 'NY', '10011', 'USA')");
        insert(em, "insert into Hotel (id, price, name, address, city, state, zip, country) values (4, 450, 'W Hotel', 'Lexington Ave, Manhattan', 'NY', 'NY', '10011', 'USA')");
        insert(em, "insert into Hotel (id, price, name, address, city, state, zip, country) values (5, 250, 'Hotel Rouge', '1315 16th Street NW', 'Washington', 'DC', '20036', 'USA')");
        insert(em, "insert into Hotel (id, price, name, address, city, state, zip, country) values (6, 300, '70 Park Avenue Hotel', '70 Park Avenue', 'NY', 'NY', '10011', 'USA')");
        insert(em, "insert into Hotel (id, price, name, address, city, state, zip, country) values (8, 300, 'Conrad Miami', '1395 Brickell Ave', 'Miami', 'FL', '33131', 'USA')");
        insert(em, "insert into Hotel (id, price, name, address, city, state, zip, country) values (9, 80, 'Sea Horse Inn', '2106 N Clairemont Ave', 'Eau Claire', 'WI', '54703', 'USA')");
        insert(em, "insert into Hotel (id, price, name, address, city, state, zip, country) values (10, 90, 'Super 8 Eau Claire Campus Area', '1151 W Macarthur Ave', 'Eau Claire', 'WI', '54701', 'USA')");
        insert(em, "insert into Hotel (id, price, name, address, city, state, zip, country) values (11, 160, 'Marriot Downtown', '55 Fourth Street', 'San Francisco', 'CA', '94103', 'USA')");
        insert(em, "insert into Hotel (id, price, name, address, city, state, zip, country) values (12, 200, 'Hilton Diagonal Mar', 'Passeig del Taulat 262-264', 'Barcelona', 'Catalunya', '08019', 'Spain')");
        insert(em, "insert into Hotel (id, price, name, address, city, state, zip, country) values (13, 210, 'Hilton Tel Aviv', 'Independence Park', 'Tel Aviv', '', '63405', 'Israel')");
        insert(em, "insert into Hotel (id, price, name, address, city, state, zip, country) values (14, 240, 'InterContinental Tokyo Bay', 'Takeshiba Pier', 'Tokyo', '', '105', 'Japan')");
        insert(em, "insert into Hotel (id, price, name, address, city, state, zip, country) values (15, 130, 'Hotel Beaulac', ' Esplanade Léopold-Robert 2', 'Neuchatel', '', '2000', 'Switzerland')");
        insert(em, "insert into Hotel (id, price, name, address, city, state, zip, country) values (16, 140, 'Conrad Treasury Place', 'William & George Streets', 'Brisbane', 'QLD', '4001', 'Australia')");
        insert(em, "insert into Hotel (id, price, name, address, city, state, zip, country) values (17, 230, 'Ritz Carlton', '1228 Sherbrooke St', 'West Montreal', 'Quebec', 'H3G1H6', 'Canada')");
        insert(em, "insert into Hotel (id, price, name, address, city, state, zip, country) values (18, 460, 'Ritz Carlton', 'Peachtree Rd, Buckhead', 'Atlanta', 'GA', '30326', 'USA')");
        insert(em, "insert into Hotel (id, price, name, address, city, state, zip, country) values (19, 220, 'Swissotel', '68 Market Street', 'Sydney', 'NSW', '2000', 'Australia')");
        insert(em, "insert into Hotel (id, price, name, address, city, state, zip, country) values (20, 250, 'Meliá White House', 'Albany Street', 'Regents Park London', '', 'NW13UP', 'Great Britain')");
        insert(em, "insert into Hotel (id, price, name, address, city, state, zip, country) values (21, 210, 'Hotel Allegro', '171 West Randolph Street', 'Chicago', 'IL', '60601', 'USA')");
    }

    void insert(EntityManager em, String i)
    {
        em.getTransaction().begin();
        em.createNativeQuery(i).executeUpdate();
        em.getTransaction().commit();
    }
    
    public SelectItem[] getMonths() {
        if (months == null) {
            months = new SelectItem[12];
            months[0] = new SelectItem(1, "Jan");
            months[1] = new SelectItem(2, "Feb");
            months[2] = new SelectItem(3, "Mar");
            months[3] = new SelectItem(4, "Apr");
            months[4] = new SelectItem(5, "May");
            months[5] = new SelectItem(6, "Jun");
            months[6] = new SelectItem(7, "Jul");
            months[7] = new SelectItem(8, "Aug");
            months[8] = new SelectItem(9, "Sep");
            months[9] = new SelectItem(10, "Oct");
            months[10] = new SelectItem(11, "Nov");
            months[11] = new SelectItem(12, "Dec");
        }
        return months;
    }

    public SelectItem[] getYears() {
        if (years == null) {
            years = new SelectItem[5];
            years[0] = new SelectItem(2006, "2006");
            years[1] = new SelectItem(2007, "2007");
            years[2] = new SelectItem(2008, "2008");
            years[3] = new SelectItem(2009, "2009");
            years[4] = new SelectItem(2010, "2010");
        }
        return years;
    }

    @PreDestroy
    public void destroy() {
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }

    public EntityManagerFactory getEntityManagerFactory() {
        return emf;
    }
}
