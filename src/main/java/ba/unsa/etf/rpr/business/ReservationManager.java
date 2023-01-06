package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Movie;
import ba.unsa.etf.rpr.domain.Reservation;
import ba.unsa.etf.rpr.exception.MovieException;

import java.util.ArrayList;
import java.util.List;

public class ReservationManager {


    public void delete(int categoryId) throws MovieException {
        try{
            DaoFactory.reservationDao().delete(categoryId);
        }catch (MovieException e){
            if (e.getMessage().contains("FOREIGN KEY")){
                throw new MovieException("NO");
            }
            throw e;
        }

    }

    public Reservation update(Reservation cat) throws MovieException{
        return DaoFactory.reservationDao().update(cat);
    }

    public List<Reservation> getAll() throws MovieException{
        return DaoFactory.reservationDao().getAll();
    }
    public List<String> getBookedSeats(Integer id) throws MovieException {
        List<String> booked=new ArrayList<>();
        List<Reservation> all=getAll();
        for(int i=0; i< all.size();i++) {
            if(all.get(i).getMovie().getId()==id)
            booked.add(all.get(i).getSector());
        }
        return booked;
    }
    public Reservation add(Reservation r) throws MovieException{
        return DaoFactory.reservationDao().add(r);
    }
    public void validateSeatField(String seat) throws MovieException {
        if(seat==null)
            throw  new MovieException("To successfully book, seat field must be filled in!");
    }
}
