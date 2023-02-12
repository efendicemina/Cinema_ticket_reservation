package ba.unsa.etf.rpr.domain;

import java.util.Objects;

/**
 * Class holding all essential information about our users.
 * @author Emina Efendic
 */
public class User implements Idable{
    private int id;
    private String name;
    private String phone;
    private String email;
    private boolean admin;
    private String username;
    private String password;

    /**
     * Gets id
     * @return id int
     */
    public int getId() {
        return id;
    }

    /**
     * Sets id
     * @param id int
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets name
     * @return name String
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name
     * @param name String
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets phone
     * @return phone String
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Sets phone
     * @param phone String
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Gets email
     * @return email String
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets email
     * @param email String
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Checks if user is admin
     * @return admin boolean
     */
    public boolean isAdmin() {
        return admin;
    }

    /**
     * Sets admin
     * @param admin boolean
     */
    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    /**
     * Gets username
     * @return username String
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets username
     * @param username String
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets password
     * @return password String
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets password
     * @param password String
     */
    public void setPassword(String password) {
        this.password = password;
    }
    /**
     * Checks if objects are equal
     * @param o Object
     * @return boolean, true if they are equal, false if not
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id;
    }
    /**
     * Converts object to a String
     * @return name String
     */
    @Override
    public String toString() {
        return username;
    }
    /**
     * Gives hash code of the object
     * @return int hashCode
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, password,phone,admin, username);
    }
}
