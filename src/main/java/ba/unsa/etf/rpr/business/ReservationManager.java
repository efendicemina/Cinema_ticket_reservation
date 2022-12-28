package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Movie;
import ba.unsa.etf.rpr.domain.Reservation;
import ba.unsa.etf.rpr.exception.MovieException;

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
}
