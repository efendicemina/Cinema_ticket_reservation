package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Reservation;
import ba.unsa.etf.rpr.exception.MovieException;

import java.util.ArrayList;
import java.util.List;
/**
 *The ReservationManager class is a business layer class that handles operations related to reservations.
 *It communicates with the data access layer (DaoFactory) to perform CRUD operations on the reservation entities.
 *@author Emina Efendic
 */
public class ReservationManager {
    /**
     * Deletes reservation, from db table reservations, with a given id.
     * @param rId int
     * @throws MovieException thrown in case of problem with db
     */
    public void delete(int rId) throws MovieException {
        try{
            DaoFactory.reservationDao().delete(rId);
        }catch (MovieException e){
            if (e.getMessage().contains("FOREIGN KEY")){
                throw new MovieException("NO");
            }
            throw e;
        }

    }
    /**
     * Updates reservation in db table reservations.
     * @param cat Reservation
     * @return Reservation that is updated
     * @throws MovieException thrown in case of problem with db
     */
    public Reservation update(Reservation cat) throws MovieException{
        return DaoFactory.reservationDao().update(cat);
    }
    /**
     *Fetches all Reservation objects from table reservations and stores it in a list.
     * @return List of all reservations
     * @throws MovieException thrown in case of problem with db
     */
    public List<Reservation> getAll() throws MovieException{
        return DaoFactory.reservationDao().getAll();
    }

    /**
     * Gets all booked seats for the given movie id.
     * @param id Integer
     * @return List of all booked seats
     * @throws MovieException in case of problem with db
     */
    public List<String> getBookedSeats(Integer id) throws MovieException {
        List<String> booked=new ArrayList<>();
        List<Reservation> all=getAll();
        for (Reservation reservation : all) {
            if (reservation.getMovie().getId() == id)
                booked.add(reservation.getSector());
        }
        return booked;
    }
    /**
     * Adds Reservation object to table reservations.
     * @param r Reservation
     * @return added Reservation
     * @throws MovieException in case of problems with db
     */
    public Reservation add(Reservation r) throws MovieException{
        return DaoFactory.reservationDao().add(r);
    }

    /**
     * Validates if seat field is filled in properly.
     * @param seat String
     * @throws MovieException in case filed isn't filled in
     */
    public void validateSeatField(String seat) throws MovieException {
        if(seat==null)
            throw  new MovieException("To successfully book, seat field must be filled in!");
    }
}
