package ba.unsa.etf.rpr;
import ba.unsa.etf.rpr.business.MovieManager;
import ba.unsa.etf.rpr.business.ReservationManager;
import ba.unsa.etf.rpr.business.UserManager;
import ba.unsa.etf.rpr.domain.Movie;
import org.apache.commons.cli.*;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * @author Emina Efendic
 * CLI (Command Line Interface) implementation in following class
 * Even though this type of presentation layer (called CLI) is becoming past tense for GUI apps
 * it's good to see how you can manipulate data through command line and database also
 *
 */
public class App {
    /**
     * Defining final variables to describe all code having options
     */
    private static final Option addMovie = new Option("add","add-movie",false, "Adding new movie to database");
    private static final Option deleteMovie = new Option("delete","delete-movie",false, "Deleting movie from database");
    private static final Option updateMovie = new Option("update", "update-movie",false, "Updating movie from database");

    private static final Option getMovies = new Option("getM", "get-movies",false, "Printing all movies from database");
    private static final Option getUsers = new Option("getU", "get-users",false, "Printing all users from database");
    private static final Option getReservations = new Option("getR", "get-reservations",false, "Printing all reservations from database");



    /**
     * Prints the usage and options of the program in a formatted way.
     *
     * @param options the options that are available for use in the program
     */
    public static void printFormattedOptions(Options options) {
        HelpFormatter helpFormatter = new HelpFormatter();
        PrintWriter printWriter = new PrintWriter(System.out);
        helpFormatter.printUsage(printWriter, 150, "java -jar projekat1.jar [option] 'something else if needed' ");
        helpFormatter.printOptions(printWriter, 150, options, 2, 7);
        printWriter.close();
    }
    /**
     * Adds all available options for the program.
     *
     * @return the options that are available for use in the program
     */
    public static Options addOptions() {
        Options options = new Options();
        options.addOption(addMovie);
        options.addOption(deleteMovie);
        options.addOption(updateMovie);
        options.addOption(getMovies);
        options.addOption(getReservations);
        options.addOption(getUsers);
        return options;
    }
    /**
     * Main method of CLI
     * @param args
     * @throws Exception
     */

    public static void main(String[] args) throws Exception {
        Options options = addOptions();
        printFormattedOptions(options);
        CommandLineParser commandLineParser = new DefaultParser();

        CommandLine cl = commandLineParser.parse(options, args);

        if((cl.hasOption(addMovie.getOpt()))) {
               try {
                   MovieManager movieManager = new MovieManager();
                   System.out.println("Input in");
                   Movie movie = new Movie();
                   movie.setName(cl.getArgList().get(0));
                   movie.setGenre(cl.getArgList().get(1));
                   DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                   movie.setDate_time(LocalDateTime.parse(cl.getArgList().get(2), formatter));
                   movie.setDuration(Integer.valueOf(cl.getArgList().get(3)));
                   movieManager.add(movie);
                   System.out.println("You successfully added movie to database!");
               }
               catch(Exception e){
                   System.out.println("Incorrect");
               }

        }
        else if(cl.hasOption(updateMovie.getOpt())){
            try {

                MovieManager movieManager = new MovieManager();
                System.out.println("Input in");
                List<Movie> list=movieManager.getAll();
                List<Integer> ids= new ArrayList<>();
                for(int i=0; i< list.size(); i++){
                    Movie movie= list.get(i);
                    ids.add(movie.getId());
                }
                if(ids.contains(Integer.valueOf(cl.getArgList().get(0)))) {
                    Movie movie = new Movie();
                    movie.setId(Integer.parseInt(cl.getArgList().get(0)));
                    movie.setName(cl.getArgList().get(1));
                    movie.setGenre(cl.getArgList().get(2));
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                    movie.setDate_time(LocalDateTime.parse(cl.getArgList().get(3), formatter));
                    movie.setDuration(Integer.valueOf(cl.getArgList().get(4)));
                    movieManager.update(movie);
                    System.out.println("You successfully updated movie in database!");
                }
                else{
                    System.out.println("The given id doesn't exist in the database!");
                }
            }
            catch(Exception e){
                System.out.println("Incorrect");
            }
        }
        else if(cl.hasOption(deleteMovie.getOpt())) {
            try {
                MovieManager movieManager = new MovieManager();
                System.out.println("Input in");
                List<Movie> list = movieManager.getAll();
                List<Integer> ids = new ArrayList<>();
                for (int i = 0; i < list.size(); i++) {
                    Movie movie = list.get(i);
                    ids.add(movie.getId());
                }
                if(ids.contains(Integer.valueOf(cl.getArgList().get(0)))) {
                    movieManager.delete(Integer.parseInt(cl.getArgList().get(0)));
                    System.out.println("You successfully deleted movie from database!");
                } else {
                    System.out.println("The given id doesn't exist in the database!");
                }
            } catch (Exception e) {
                System.out.println("Incorrect");
            }
        }
        else if(cl.hasOption(getMovies.getOpt())){
            MovieManager movieManager = new MovieManager();
            movieManager.getAll().forEach(c -> System.out.println(c.getName()+ " ; " +c.getGenre()
            +" ; " +c.getDate_time()+ " ; " +c.getDuration()));

        }
        else if(cl.hasOption(getUsers.getLongOpt())){
            UserManager userManager = new UserManager();
            userManager.getAll().forEach(c -> System.out.println(c.getId()
                    +" ; "+c.getName()+ " ; " +c.getUsername()
                    +" ; " +c.getPassword()));

        }
        else if(cl.hasOption(getReservations.getOpt())){
            ReservationManager reservationManager = new ReservationManager();
            reservationManager.getAll().forEach(c -> System.out.println(c.getId()+ " ; " +c.getMovie().getName()
                    +" ; " +c.getUser().getUsername()+ " ; " +c.getSector()));

        }
        else {
            printFormattedOptions(options);
            System.exit(-1);
        }
    }
}
