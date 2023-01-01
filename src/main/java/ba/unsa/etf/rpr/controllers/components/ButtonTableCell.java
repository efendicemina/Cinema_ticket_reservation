package ba.unsa.etf.rpr.controllers.components;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.layout.HBox;

/**
 * Custom component for rendering table cell with one button (BOOK)
 *
 * @param <T> - Bean class represented in the table cells
 */
public class ButtonTableCell<T> extends TableCell<T, T> {

    private Button book;

    /**
     * Default constructor
     * @param buttonOne - event handler for action on first button (BOOK)
     */
    public ButtonTableCell(EventHandler<ActionEvent> buttonOne){
        book = new Button("BOOK");
        book.setOnAction(buttonOne);
    }

    @Override
    protected void updateItem(T o, boolean b) {
        super.updateItem(o, b);
        if (o != null) {
            HBox box = new HBox();
            box.setAlignment(Pos.CENTER);
            book.setUserData(o);
            box.getChildren().add(book);
            setGraphic(box);
        }
    }
}
