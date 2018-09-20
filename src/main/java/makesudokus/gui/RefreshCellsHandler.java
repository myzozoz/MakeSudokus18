package makesudokus.gui;

import javafx.event.EventHandler;

public abstract class RefreshCellsHandler implements EventHandler<RefreshCellsEvent> {
    public abstract void onEvent();

    @Override
    public void handle(RefreshCellsEvent event) {
        event.invokeHandler(this);
    }
}
