package ba.unsa.etf.rpr.controllers.components;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

/**
 * Button cell factory for creation of buttons for each cell in the table
 * @param <T>
 * @author Emina Efendic
 */
public class ButtonCellFactory<T> implements Callback<TableColumn<T, T>, TableCell<T, T>> {

    private final EventHandler<ActionEvent> buttonOne;


    /**
     *
     * @param buttonOne - event handler button (BOOK)
     */
    public ButtonCellFactory(EventHandler<ActionEvent> buttonOne){
        this.buttonOne = buttonOne;
    }

    @Override
    public TableCell<T, T> call(TableColumn<T, T> quoteObjectTableColumn) {
        return new ButtonTableCell<>(buttonOne);
    }
}