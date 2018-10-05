package makesudokus.logic.algorithms.crook;


import makesudokus.structures.OrderedIntegerList;
import makesudokus.structures.UnorderedList;

/**
 * A preemptive set is a tuple of small lists shaped like {[1,2,6], [c(1,7), c(1,9), c(2,9)]} (example from
 * Crook's paper. The first list contains markups (the markups make up the span of the set) and the second
 * one the cells where said markups are found within a related area (3x3 box, row, column) and it's length is
 * called size.
 */
public class PreemptiveSet {
    private OrderedIntegerList markup;
    private UnorderedList cells;
    //Span is the length of the markup list.
    private int span;
    //Size is the amount of cells in the set
    private int size;

    /**
     * Constructor for the set.
     */
    public PreemptiveSet() {
        this.markup = new OrderedIntegerList();
        this.cells = new UnorderedList();
        this.span = 0;
        this.size = 0;
    }

    /**
     * Add a cell to the set.
     * @param cell Cell to be added.
     */
    public void addCell(Cell cell) {
        //Add cell to the list of cells
        this.cells.add(cell);
        //Add the markups to the list of markups
        for(int i = 0; i < cell.countMarkups(); i++) {
            this.markup.add(cell.getMarkupDigits()[i]);
        }
        this.size++;
        this.span = this.markup.size();
    }

    /**
     * @return Returns the span of the set, or the amount of entries in the markup list.
     */
    public int getSpan() {
        return this.span;
    }

    /**
     * @return Returns the size of the set, or the amount of cells listed.
     */
    public int getSize() {
        return this.size;
    }

    @Override
    public String toString() {
        String s = "{(";
        for(int i = 0; i < markup.size() - 1; i++) {
            s += markup.get(i) + ",";
        }
        s += markup.get(markup.size()-1) + ") | (";
        for(int i = 0; i < cells.size() - 1; i++) {
            s += i + ",";
        }
        s += cells.size() - 1 + ")}";
        return s;
    }

}
