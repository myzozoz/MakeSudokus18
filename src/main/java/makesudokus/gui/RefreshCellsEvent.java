package makesudokus.gui;

import javafx.event.Event;
import javafx.event.EventType;

public class RefreshCellsEvent extends Event {
    public static final EventType<RefreshCellsEvent> REFRESH_CELLS_EVENT_TYPE = new EventType<>(ANY);

    public RefreshCellsEvent(){
        super(REFRESH_CELLS_EVENT_TYPE);
    }

    public void invokeHandler(RefreshCellsHandler handler) {
        handler.onEvent();
    }
}