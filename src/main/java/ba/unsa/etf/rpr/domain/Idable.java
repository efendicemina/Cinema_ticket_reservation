package ba.unsa.etf.rpr.domain;

/**
 * Interface that forces all POJO beans to have ID field.
 * @author Emina Efendic
 */
public interface Idable {
    /**
     * Sets id
     * @param id int
     */
    void setId(int id);

    /**
     * Gets id
     * @return id integer
     */
    int getId();
}
